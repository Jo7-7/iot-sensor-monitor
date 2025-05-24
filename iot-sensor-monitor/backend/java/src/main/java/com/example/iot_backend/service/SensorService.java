package com.example.iot_backend.service;

import com.example.iot_backend.model.Sensor;
import com.example.iot_backend.repository.SensorRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SensorService {

    private final SensorRepository repo;
    private final MeterRegistry registry;
    // Latest sensor per type, updated by DB and metrics
    private final Map<String, Sensor> latestByType = new ConcurrentHashMap<>();

    @Autowired
    public SensorService(SensorRepository repo, MeterRegistry registry) {
        this.repo = repo;
        this.registry = registry;
    }

    /**
     * Page through Sensor readings, update the “latestByType” map,
     * and (re-)register a gauge for each type, defaulting to 0.0 if missing.
     */
    public List<Sensor> findAll(int page, int size, String sensorType) {
        PageRequest pr = PageRequest.of(page, size, Sort.by("timestamp").descending());
        List<Sensor> readings;

        if ("all".equalsIgnoreCase(sensorType)) {
            readings = repo.findAllByOrderByTimestampDesc(pr).getContent();
        } else {
            readings = repo.findBySensorTypeOrderByTimestampDesc(sensorType, pr).getContent();
        }

        readings.forEach(r -> {
            latestByType.put(r.getSensorType(), r);
            // (Re-)register a gauge for this type if not already present.
            Gauge.builder("iot_sensor_readings_value",
                            () -> Optional.ofNullable(latestByType.get(r.getSensorType()))
                                    .map(Sensor::getValue)
                                    .map(Double::valueOf)
                                    .orElse(0.0)
                    )
                    .description("Latest sensor reading")
                    .tag("sensorType", r.getSensorType())
                    .register(registry);
        });

        return readings;
    }

    /**
     * Periodically update metrics for all sensor types, even if REST API not called.
     */
    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void updateLatestMetrics() {
        // Adjust size as needed, or use a large number for all sensors
        findAll(0, 10, "all");
    }

    /** Get the most recent reading overall */
    public Optional<Sensor> getLatestReading() {
        return repo.findTopByOrderByTimestampDesc();
    }

    /** Get the most recent reading by sensor type, fallback to NaN if not present */
    public double getLatest(String sensorType) {
        return repo.findTopBySensorTypeOrderByTimestampDesc(sensorType)
                .map(Sensor::getValue)
                .map(Double::valueOf)
                .orElse(Double.NaN);
    }
}















