##### 🌡️ IoT Sensor Data Monitor

A full-stack project that simulates IoT sensor data, stores it in MySQL, exposes it through a Spring Boot REST API, and visualizes it with a React dashboard.


---

## 🚀 Features

- Simulated temperature & humidity data insertion every 5 seconds
- MySQL storage with schema in data/sql/schema.sql
- Spring Boot REST API:
  - GET /sensors/latest
  - GET /sensors?page=&size= (paginated)
- React dashboard with real-time chart and table



---

## 🧰 Tech Stack

| Component       | Technology          |
|-----------------|----------------------|
| Data Simulation | Python (VSCode)     |
| Database        | MySQL               |
| API             | Java (IntelliJ / Spring Boot) |
| Version Control | Git + GitHub        |

---

## 📁 Project Structure

```bash
iot-sensor-monitor/
├── backend/
│   └── java/                   # Spring Boot application
│       ├── pom.xml             # Maven configuration
│       └── src/
│           ├── main/
│           │   ├── java/com/example/iot_backend/  # Java source code
│           │   │   ├── IotBackendApplication.java
│           │   │   ├── controller/
│           │   │   │   └── SensorController.java
│           │   │   ├── model/
│           │   │   │   └── Sensor.java
│           │   │   ├── repository/
│           │   │   │   └── SensorRepository.java
│           │   │   └── service/
│           │   │       └── SensorService.java
│           │   └── resources/
│           │       └── application.properties
│           └── test/java/com/example/iot_backend/  # Unit tests
│               └── service/
│                   └── SensorServiceTest.java
├── data/
│   └── sql/
│       └── schema.sql         # MySQL schema for sensors table
├── simulator/
│   ├── sensor_simulator.py    # Python script to generate and insert sensor data
│   ├── requirements.txt       # Python dependencies
│   └── .env                   # Database credentials
├── iot-dashboard/             # React frontend dashboard
│   ├── package.json           # npm config & proxy setup
│   └── src/
│       ├── App.js             # Main React component
│       ├── index.js
│       └── components/
│           ├── SensorTable.js
│           └── SensorChart.js
├── .gitignore
├── README.md                  # Project overview & setup instructions
└── LICENSE                    # MIT License
```

---

## 🚀 Getting Started

### 1. Run the Simulator

```bash
cd simulator
python -m venv venv
source venv/bin/activate      # Windows: venv\Scripts\activate
pip install -r requirements.txt
python sensor_simulator.py
```

### 2. Start the Backend API

```bash
cd backend/java
# Using Maven wrapper:
./mvnw spring-boot:run
# Or if you have Maven installed:
mvn spring-boot:run
```

### 3. Launch the React Dashboard

```bash
cd iot-dashboard
npm install
npm start
```

# 📄 License

This project is licensed under the MIT License. See LICENSE for details.


# ✍️ Author
Josué KOFFI
GitHub
