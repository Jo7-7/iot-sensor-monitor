# 🌡️ IoT Sensor Data Monitor

A full-stack project that simulates IoT sensor data, stores it in MySQL, exposes it through a Spring Boot REST API, and visualizes it with a React dashboard. Live monitoring and dashboards are powered by Prometheus and Grafana.

---

## 🚀 Features

Simulated temperature & humidity data insertion every 5 seconds

MySQL storage with schema in data/sql/schema.sql

Spring Boot REST API

GET /sensors/latest (most recent)

GET /sensors?page=&size= (paginated)

React dashboard with real-time chart and table

Live metrics with Prometheus & Grafana (Docker)

---

## 🧰 Tech Stack

| Component       | Technology          |
|-----------------|----------------------|
| Data Simulation | Python (Docker)    |
| Database        | MySQL               |
| API             | Java (IntelliJ / Spring Boot) |
| Frontend	 | React    |
| Monitoring	          | Prometheus, Grafana |
| Version Control | Git + GitHub        |
| Orchestration	 | Docker Compose       |

---

## 📁 Project Structure

```bash
iot-sensor-monitor/
├── backend/
│   └── java/
│       ├── pom.xml
│       └── src/
│           ├── main/java/com/example/iot_backend/
│           │   ├── IotBackendApplication.java
│           │   ├── controller/
│           │   │   └── SensorController.java
│           │   ├── model/
│           │   │   └── Sensor.java
│           │   ├── repository/
│           │   │   └── SensorRepository.java
│           │   └── service/
│           │       └── SensorService.java
│           └── resources/
│               └── application.properties
├── data/
│   └── sql/
│       └── schema.sql
├── simulator/
│   ├── sensor_simulator.py
│   ├── requirements.txt
│   └── .env
├── iot-dashboard/
│   ├── package.json
│   └── src/
│       ├── App.js
│       ├── index.js
│       └── components/
│           ├── SensorTable.js
│           └── SensorChart.js
├── .gitignore
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

### 2. Start All Services with Docker Compose
(Docker Desktop recommended, no need to start each part manually!)

```bash
docker-compose up --build
```

### 3. Run the Simulator (if needed manually)

```bash
cd simulator
python -m venv venv
source venv/bin/activate      # Windows: venv\Scripts\activate
pip install -r requirements.txt
python sensor_simulator.py
```

### 4. Access the Applications

```bash
React Dashboard: http://localhost:3000

Backend API (Swagger/REST): http://localhost:8080/sensors

Prometheus: http://localhost:9090

Grafana: http://localhost:3001

Default credentials:

Username: admin

Password: admin
```


## 📝 REST API Endpoints
/sensors/latest — Most recent sensor reading

/sensors?page=&size= — Paginated sensor data


## 📊 Live Dashboards

Grafana: Visualizes live data (line charts, gauges, etc.)

Prometheus: Scrapes backend metrics for visualization


## 📄 License

This project is licensed under the MIT License. See LICENSE for details.


## ✍️ Author
Josué KOFFI
GitHub

## Feel free to contribute, open issues, or star the repo!

