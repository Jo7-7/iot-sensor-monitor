# 🌡️ IoT Sensor Data Monitor

A modular big data project that simulates IoT sensor data (e.g., temperature, humidity), stores it in a MySQL database, and exposes the data via a Java REST API. Python is used for simulating and inserting data. Everything is organized for clarity, scalability, and multi-language development.

---

## 🚀 Features

- Simulate real-time IoT sensor data (temperature, humidity, etc.)
- Store sensor readings in a MySQL database
- Expose latest sensor readings using a Java-based REST API
- Clean, modular project structure for collaborative development

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

iot-sensor-monitor/
├── backend/
│   └── java/
│       ├── src/
│       │   └── main/
│       │       ├── java/
│       │       │   └── com/example/iot/
│       │       │       └── SensorController.java
│       │       └── resources/
│       │           └── application.properties
│       └── pom.xml
├── data/
│   └── sql/
│       └── schema.sql                # MySQL table schema
├── simulator/
│   ├── sensor_simulator.py          # Sensor simulation script
│   └── requirements.txt
├── .gitignore
├── README.md
└── LICENSE


---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/iot-sensor-monitor.git
cd iot-sensor-monitor

2. Set Up MySQL
Create a database named iot

Run the SQL schema to create the sensors table:

CREATE DATABASE iot;

USE iot;

CREATE TABLE sensors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sensor_type VARCHAR(50),
    value FLOAT,
    unit VARCHAR(10),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);

3. Python Setup (simulator)

cd simulator
python -m venv venv
source venv/bin/activate  # Windows: venv\Scripts\activate
pip install -r requirements.txt
python sensor_simulator.py

⚙️ Edit sensor_simulator.py with your actual MySQL credentials

4. Java Setup (backend API)
Open the backend/java/ folder in IntelliJ

Add MySQL JDBC driver to pom.xml

Configure DB credentials in application.properties

Run the application and access your API (e.g., /sensors/latest)

📡 Sample Output

Inserted value: 26.34
Inserted value: 24.98

📈 Future Improvements
Real-time ingestion using MQTT or Kafka

Interactive dashboards using Python or React

Multi-sensor and multi-device support

Alerts and thresholds on sensor conditions

📄 License
This project is licensed under the MIT License.

✍️ Author
Josué KOFFI
GitHub
