FROM python:3.11-slim
WORKDIR /app
COPY sensor_simulator.py .
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
CMD ["python", "sensor_simulator.py"]


