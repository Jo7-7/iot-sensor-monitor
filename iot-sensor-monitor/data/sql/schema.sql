CREATE DATABASE IF NOT EXISTS iot;

USE iot;

CREATE TABLE IF NOT EXISTS sensors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_type VARCHAR(50),
    value FLOAT,
    unit VARCHAR(10),
    timestamp DATETIME
);
