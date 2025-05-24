package com.example.iot_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;   // <-- Add this import!

@SpringBootApplication
@EnableScheduling  // <-- Add this annotation!
public class IotBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotBackendApplication.class, args);
	}

}

