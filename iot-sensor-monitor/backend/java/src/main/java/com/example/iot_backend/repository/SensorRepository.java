package com.example.iot_backend.repository;

import com.example.iot_backend.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findTopByOrderByTimestampDesc();

    // New: page through all readings
    Page<Sensor> findAllByOrderByTimestampDesc(Pageable pageable);

    // New: page through readings of a given type
    Page<Sensor> findBySensorTypeOrderByTimestampDesc(String sensorType, Pageable pageable);
}




