package com.example.iot_backend.repository;

import com.example.iot_backend.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    // Single most recent reading across all sensor types
    Optional<Sensor> findTopByOrderByTimestampDesc();

    // Single most recent reading of a specific sensor type
    Optional<Sensor> findTopBySensorTypeOrderByTimestampDesc(String sensorType);

    // Page through all readings (newest first)
    Page<Sensor> findAllByOrderByTimestampDesc(Pageable pageable);

    // Page through readings of a given type (newest first)
    Page<Sensor> findBySensorTypeOrderByTimestampDesc(String sensorType, Pageable pageable);
}






