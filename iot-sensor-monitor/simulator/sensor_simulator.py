import os
import mysql.connector
import time
import random
from datetime import datetime

DB_CONFIG = {
    'user': os.getenv('DB_USER', 'root'),
    'password': os.getenv('DB_PASSWORD', 'rootpass'),
    'host': os.getenv('DB_HOST', 'db'),
    'port': int(os.getenv('DB_PORT', 3306)),
    'database': os.getenv('DB_NAME', 'iot')
}

def wait_for_mysql():
    while True:
        try:
            conn = mysql.connector.connect(**DB_CONFIG)
            conn.close()
            print("Connected to MySQL successfully!")
            break
        except mysql.connector.Error as e:
            print("Waiting for MySQL to be ready...", e)
            time.sleep(2)

def insert_sensor_reading(sensor_type, value, unit):
    conn = mysql.connector.connect(**DB_CONFIG)
    cur = conn.cursor()
    cur.execute(
        "INSERT INTO sensors (sensor_type, value, unit, timestamp) VALUES (%s, %s, %s, %s)",
        (sensor_type, value, unit, datetime.now())
    )
    conn.commit()
    cur.close()
    conn.close()

if __name__ == "__main__":
    wait_for_mysql()
    while True:
        temp = round(random.uniform(15, 30), 2)
        humidity = round(random.uniform(30, 70), 2)
        insert_sensor_reading('temperature', temp, 'C')
        insert_sensor_reading('humidity', humidity, '%')
        print(f"Inserted: temperature={temp}C, humidity={humidity}%")
        time.sleep(1)



