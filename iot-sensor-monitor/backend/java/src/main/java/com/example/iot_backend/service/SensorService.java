package com.example.iot_backend.service;

import com.example.iot_backend.model.Sensor;
import com.example.iot_backend.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository repo;

    @Autowired
    public SensorService(SensorRepository repo) {
        this.repo = repo;
    }

    public Optional<Sensor> getLatestReading() {
        return repo.findTopByOrderByTimestampDesc();
    }

    public Page<Sensor> getAllReadings(Pageable pageable) {
        return repo.findAllByOrderByTimestampDesc(pageable);
    }

    // New:
    public Page<Sensor> getReadingsByType(String sensorType, Pageable pageable) {
        return repo.findBySensorTypeOrderByTimestampDesc(sensorType, pageable);
    }
}




