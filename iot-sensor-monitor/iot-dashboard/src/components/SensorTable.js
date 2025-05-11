// src/components/SensorTable.js

import React from 'react';

export default function SensorTable({ data }) {
  return (
    <table className="min-w-full text-left">
      <thead>
        <tr>
          <th>ID</th>
          <th>Type</th>
          <th>Value</th>
          <th>Unit</th>
          <th>Timestamp</th>
        </tr>
      </thead>
      <tbody>
        {data.map(s => (
          <tr key={s.id}>
            <td>{s.id}</td>
            <td>{s.sensorType}</td>
            <td>{s.value}</td>
            <td>{s.unit}</td>
            <td>{new Date(s.timestamp).toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

