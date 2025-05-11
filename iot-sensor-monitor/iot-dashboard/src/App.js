// src/App.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SensorChart from './components/SensorChart';

function App() {
  const [data, setData]     = useState([]);
  const [filter, setFilter] = useState('all');
  const [page, setPage]     = useState(0);
  const [size, setSize]     = useState(20);
  const [stats, setStats]   = useState({ latest: null, avgTemp: 0, avgHum: 0 });
  const [dark, setDark]     = useState(false);

  // Toggle dark mode
  useEffect(() => {
    document.body.classList.toggle('dark', dark);
  }, [dark]);

  // Fetch & stats
  useEffect(() => {
    const fetchData = async () => {
      try {
        let url = `/sensors?page=${page}&size=${size}`;
        if (filter !== 'all') url += `&sensorType=${filter}`;
        const res = await axios.get(url);
        const content = res.data.content || [];
        setData(content);

        if (content.length) {
          const latest = content[0];
          const temps  = content.filter(s => s.sensorType === 'temperature').map(s => s.value);
          const hums   = content.filter(s => s.sensorType === 'humidity').map(s => s.value);

          const avgTemp = temps.length
            ? (temps.reduce((sum, v) => sum + v, 0) / temps.length).toFixed(1)
            : 0;
          const avgHum = hums.length
            ? (hums.reduce((sum, v) => sum + v, 0) / hums.length).toFixed(1)
            : 0;

          setStats({ latest, avgTemp, avgHum });
        }
      } catch (err) {
        console.error('Error fetching sensor data:', err);
      }
    };

    fetchData();
    const id = setInterval(fetchData, 5000);
    return () => clearInterval(id);
  }, [filter, page, size]);

  return (
    <div
      className="dashboard-container fade-in"
      style={{ "--i": 0 }}
    >
      {/* Controls Bar */}
      <div
        className="dashboard-controls fade-in"
        style={{ "--i": 1 }}
      >
        <div>
          <label>Sensor:&nbsp;</label>
          <select
            value={filter}
            onChange={e => { setFilter(e.target.value); setPage(0); }}
          >
            <option value="all">All</option>
            <option value="temperature">Temperature</option>
            <option value="humidity">Humidity</option>
          </select>
        </div>

        <div>
          <button
            onClick={() => setPage(p => Math.max(p - 1, 0))}
            disabled={page === 0}
          >
            Prev
          </button>
          <span> Page {page + 1} </span>
          <button onClick={() => setPage(p => p + 1)}>
            Next
          </button>
        </div>

        <div>
          <label>Page Size:&nbsp;</label>
          <input
            type="number"
            value={size}
            min={1} max={100}
            onChange={e => { setSize(Number(e.target.value)); setPage(0); }}
          />
        </div>

        <button
          onClick={() => setDark(d => !d)}
          className="fade-in"
          style={{ "--i": 2 }}
        >
          {dark ? '☀️ Light' : '🌙 Dark'}
        </button>
      </div>

      {/* Stats Cards */}
      <div
        className="dashboard-stats fade-in"
        style={{ "--i": 3 }}
      >
        {stats.latest && (
          <div className="stat-card fade-in" style={{ "--i": 4 }}>
            <h4>Latest ({stats.latest.sensorType})</h4>
            <p className="stat-value">
              {stats.latest.value} {stats.latest.unit}
            </p>
            <small>{new Date(stats.latest.timestamp).toLocaleString()}</small>
          </div>
        )}
        <div className="stat-card fade-in" style={{ "--i": 5 }}>
          <h4>Avg Temperature</h4>
          <p className="stat-value">{stats.avgTemp} °C</p>
        </div>
        <div className="stat-card fade-in" style={{ "--i": 6 }}>
          <h4>Avg Humidity</h4>
          <p className="stat-value">{stats.avgHum} %</p>
        </div>
      </div>

      {/* Main Grid: Chart + Table */}
      <div
        className="dashboard-main fade-in"
        style={{ "--i": 7 }}
      >
        <div
          className="chart-card fade-in"
          style={{ "--i": 8 }}
        >
          <SensorChart data={data} />
        </div>

        <div
          className="sensor-table-wrapper fade-in"
          style={{ "--i": 9 }}
        >
          <table className="sensor-table">
            <thead>
              <tr>
                {['ID','Type','Value','Unit','Timestamp'].map(h => (
                  <th key={h}>{h}</th>
                ))}
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
        </div>
      </div>
    </div>
  );
}

export default App;





