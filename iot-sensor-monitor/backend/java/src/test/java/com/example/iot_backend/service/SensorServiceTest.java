// src/test/java/com/example/iot_backend/service/SensorServiceTest.java
package com.example.iot_backend.service;

import com.example.iot_backend.model.Sensor;
import com.example.iot_backend.repository.SensorRepository;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorServiceTest {

    private SensorRepository repo;
    private PrometheusMeterRegistry registry;
    private SensorService service;

    @BeforeEach
    void setup() {
        repo = mock(SensorRepository.class);
        registry = mock(PrometheusMeterRegistry.class);
        service = new SensorService(repo, registry);
    }

    @Test
    void getLatestReading_whenPresent_returnsSensor() {
        Sensor s = new Sensor();
        s.setId(1L);
        s.setSensorType("temperature");
        s.setValue(25.0f);
        s.setUnit("C");
        s.setTimestamp(LocalDateTime.now());

        when(repo.findTopByOrderByTimestampDesc()).thenReturn(Optional.of(s));

        Optional<Sensor> result = service.getLatestReading();
        assertTrue(result.isPresent());
        assertEquals("temperature", result.get().getSensorType());
        verify(repo).findTopByOrderByTimestampDesc();
    }

    @Test
    void getLatestReading_whenEmpty_returnsEmptyOptional() {
        when(repo.findTopByOrderByTimestampDesc()).thenReturn(Optional.empty());

        Optional<Sensor> result = service.getLatestReading();
        assertFalse(result.isPresent());
        verify(repo).findTopByOrderByTimestampDesc();
    }

    @Test
    void getLatest_whenTemperatureExists_returnsValue() {
        Sensor sensor = new Sensor();
        sensor.setValue(22.5f);

        when(repo.findTopBySensorTypeOrderByTimestampDesc("temperature"))
                .thenReturn(Optional.of(sensor));

        double latest = service.getLatest("temperature");
        assertEquals(22.5, latest, 0.001);
        verify(repo).findTopBySensorTypeOrderByTimestampDesc("temperature");
    }

    @Test
    void getLatest_whenHumidityExists_returnsValue() {
        Sensor sensor = new Sensor();
        sensor.setValue(48.9f);

        when(repo.findTopBySensorTypeOrderByTimestampDesc("humidity"))
                .thenReturn(Optional.of(sensor));

        double latest = service.getLatest("humidity");
        assertEquals(48.9, latest, 0.001);
        verify(repo).findTopBySensorTypeOrderByTimestampDesc("humidity");
    }

    @Test
    void getLatest_whenMissing_returnsNaN() {
        when(repo.findTopBySensorTypeOrderByTimestampDesc("unknown"))
                .thenReturn(Optional.empty());

        double latest = service.getLatest("unknown");
        assertTrue(Double.isNaN(latest));
        verify(repo).findTopBySensorTypeOrderByTimestampDesc("unknown");
    }
}




