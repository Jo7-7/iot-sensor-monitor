package com.example.iot_backend.controller;

import com.example.iot_backend.model.Sensor;
import com.example.iot_backend.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // New paginated endpoint:
    @GetMapping
    public Page<Sensor> allSensors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return service.getAllReadings(PageRequest.of(page, size));
    }
}




