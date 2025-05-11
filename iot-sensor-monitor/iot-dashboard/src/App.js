// src/App.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SensorTable from './components/SensorTable';
import SensorChart from './components/SensorChart';

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get('/sensors?page=0&size=100')
      .then(res => setData(res.data.content || []))
      .catch(err => console.error('Error fetching sensor data:', err));
  }, []);

  return (
    <div className="p-4">
      <h1 className="text-2xl mb-4">IoT Sensor Dashboard</h1>
      <SensorChart data={data} />
      <SensorTable data={data} />
    </div>
  );
}

export default App;

