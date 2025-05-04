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

    // New: get all readings, newest first, with pagination
    Page<Sensor> findAllByOrderByTimestampDesc(Pageable pageable);
}



