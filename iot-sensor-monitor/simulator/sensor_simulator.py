# Sensor simulator script

import random
import time
import pymysql
import os
from datetime import datetime
from dotenv import load_dotenv

load_dotenv()

# Load DB credentials from .env
DB_HOST = os.getenv("DB_HOST")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")
DB_NAME = os.getenv("DB_NAME")

# Function to simulate a sensor reading
def simulate_sensor(sensor_type):
    if sensor_type == "temperature":
        return round(random.uniform(20.0, 30.0), 2), "C"
    elif sensor_type == "humidity":
        return round(random.uniform(30.0, 60.0), 2), "%"
    else:
        raise ValueError("Unsupported sensor type")

# Connect to MySQL
connection = pymysql.connect(
    host=DB_HOST,
    user=DB_USER,
    password=DB_PASSWORD,
    db=DB_NAME,
    cursorclass=pymysql.cursors.DictCursor
)

try:
    while True:
        for sensor in ["temperature", "humidity"]:
            value, unit = simulate_sensor(sensor)
            timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

            with connection.cursor() as cursor:
                sql = "INSERT INTO sensors (sensor_type, value, unit, timestamp) VALUES (%s, %s, %s, %s)"
                cursor.execute(sql, (sensor, value, unit, timestamp))
            connection.commit()

            print(f"[{timestamp}] Inserted {sensor}: {value}{unit}")

        time.sleep(5)  # simulate a reading every 5 seconds

except KeyboardInterrupt:
    print("Simulation stopped.")
finally:
    connection.close()
