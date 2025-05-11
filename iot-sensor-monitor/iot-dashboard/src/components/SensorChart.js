// src/components/SensorChart.js

import React from 'react';
import { LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

export default function SensorChart({ data }) {
  const chartData = data.map(s => ({
    time: new Date(s.timestamp).toLocaleTimeString(),
    value: s.value
  }));

  return (
    <LineChart width={700} height={300} data={chartData}>
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="time" />
      <YAxis />
      <Tooltip />
      <Line type="monotone" dataKey="value" />
    </LineChart>
  );
}

