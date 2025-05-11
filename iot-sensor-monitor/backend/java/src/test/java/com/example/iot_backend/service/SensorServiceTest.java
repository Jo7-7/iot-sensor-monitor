package com.example.iot_backend.service;

import com.example.iot_backend.model.Sensor;
import com.example.iot_backend.repository.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorServiceTest {

    private SensorRepository repo;
    private SensorService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(SensorRepository.class);
        service = new SensorService(repo);
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
}

