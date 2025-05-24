package com.example.iot_backend.controller;

import com.example.iot_backend.model.Sensor;
import com.example.iot_backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService service;

    @Autowired
    public SensorController(SensorService service) {
        this.service = service;
    }

    @GetMapping("/latest")
    public ResponseEntity<Sensor> latestSensor() {
        return service.getLatestReading()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping
    public List<Sensor> allSensors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String sensorType
    ) {
        // delegate to your new service.findAll(page, size, sensorType)
        return service.findAll(page, size,
                sensorType == null || sensorType.isEmpty() ? "all" : sensorType
        );
    }
}





