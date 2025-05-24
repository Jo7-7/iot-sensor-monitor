# 🌡️ IoT Sensor Data Monitor

A full-stack IoT data pipeline: simulates live sensor data, stores it in MySQL, exposes it via a Spring Boot REST API, and visualizes it in real-time using a React dashboard, Prometheus, and Grafana—all running in Docker.

---

## 🚀 Features

Simulated sensor data: Temperature & humidity values, inserted every 5 seconds

MySQL storage: Robust relational DB for time-series sensor data

REST API: Spring Boot (Java) backend exposing paginated sensor data

React dashboard: Live-updating charts and tables

Prometheus monitoring: Collects metrics from backend

Grafana dashboards: Real-time visualization, alerting, and analytics

100% Dockerized: Fast local setup, clean environment, easy deployment

---

## 🧰 Tech Stack

| Component       | Technology          |
|-----------------|----------------------|
| Data Simulation | Python + mysql-connector  |
| Database        | MySQL 8        |
| Backend API	    | Java 17, Spring Boot, Micrometer |
| Frontend	 | React, Axios, Recharts   |
| Monitoring	          | Prometheus, Grafana |
| Version Control | Git + GitHub        |
| DevOps		 | 	Docker, Docker Compose|

---

## 📁 Project Structure

```bash
iot-sensor-monitor/
├── backend/                # Spring Boot Java backend
│   └── java/
│       └── ...             # Java source and resources
├── data/
│   └── sql/schema.sql
├── simulator/              # Python simulator
│   ├── sensor_simulator.py
│   ├── requirements.txt
│   └── Dockerfile
├── iot-dashboard/          # React frontend
│   ├── src/
│   │   ├── App.js
│   │   └── components/
│   │       └── SensorChart.js
│   ├── package.json
│   ├── Dockerfile        # Dockerfile for frontend build/serve
│   └── nginx.conf        # Nginx config for static serving
├── monitoring/
│   └── prometheus.yml
├── docker-compose.yml
├── README.md
└── LICENSE

```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Jo7-7/iot-sensor-monitor.git
cd iot-sensor-monitor
```

### 2. Start the project (all services)
(Docker Desktop recommended, no need to start each part manually!)

```bash
docker-compose up --build
```
This command will build and launch:

MySQL database (port 3307)

Spring Boot backend API (port 8080)

React dashboard (port 3000)

Python simulator (auto-starts)

Prometheus (port 9090)

Grafana (port 3001, login: admin / admin)

### 3 Access the apps

React dashboard: http://localhost:3000

Backend API: http://localhost:8080/sensors

Grafana: http://localhost:3001 (admin/admin)

Prometheus: http://localhost:9090

## 📊 Example Screenshots

![Capture d'écran 2025-05-24 201931](https://github.com/user-attachments/assets/5214f373-aedb-465a-b1cf-209c5e11b8ba)

![Capture d'écran 2025-05-24 202000](https://github.com/user-attachments/assets/623b72b0-d0d6-4422-af0b-6e94ea4dc37a)

![Capture d'écran 2025-05-24 202034](https://github.com/user-attachments/assets/2ac767ad-96da-49a9-9ff2-f30896e6c33b)

![Capture d'écran 2025-05-24 202149](https://github.com/user-attachments/assets/1150db70-195e-45cc-961c-daa63e49c649)


## 📄 License

This project is licensed under the MIT License. See LICENSE for details.


## ✍️ Author
Josué KOFFI
GitHub

🙋‍♂️ Why this project?
This project demonstrates a real-world, production-like IoT data pipeline. It’s perfect for learning or showcasing:

Full-stack application development

Real-time data ingestion and visualization

Cloud-native DevOps with Docker

Modern monitoring with Prometheus & Grafana

Ready to use. Fork, star, or deploy!
If you have questions or want to contribute, feel free to open an issue or pull request.

