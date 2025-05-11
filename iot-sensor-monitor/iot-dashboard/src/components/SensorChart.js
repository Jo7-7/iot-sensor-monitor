// src/components/SensorChart.js
import React from 'react';
import {
  ResponsiveContainer,
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
} from 'recharts';

export default function SensorChart({ data }) {
  // pull CSS variables at runtime
  const styles   = getComputedStyle(document.body);
  const accent   = styles.getPropertyValue('--accent').trim();
  const grid     = styles.getPropertyValue('--grid').trim();
  const fg       = styles.getPropertyValue('--fg').trim();
  const muted    = styles.getPropertyValue('--text-muted').trim();
  const cardBg   = styles.getPropertyValue('--card-bg').trim();

  // format your data for the chart
  const chartData = data.map(s => ({
    time: new Date(s.timestamp).toLocaleTimeString([], { hour12: false }),
    value: s.value,
  }));

  return (
    <ResponsiveContainer width="100%" height={300}>
      <LineChart
        data={chartData}
        margin={{ top: 10, right: 20, left: 0, bottom: 0 }}
      >
        <defs>
          <linearGradient id="grad" x1="0" y1="0" x2="0" y2="1">
            <stop offset="5%" stopColor={accent} stopOpacity={0.3} />
            <stop offset="95%" stopColor={accent} stopOpacity={0} />
          </linearGradient>
        </defs>

        <CartesianGrid stroke={grid} strokeDasharray="3 3" />

        <XAxis
          dataKey="time"
          tick={{ fill: muted, fontSize: 12 }}
          axisLine={{ stroke: grid }}
          tickLine={{ stroke: grid }}
        />

        <YAxis
          tick={{ fill: muted, fontSize: 12 }}
          axisLine={{ stroke: grid }}
          tickLine={{ stroke: grid }}
        />

        <Tooltip
          // use the pulled-in cardBg var here
          contentStyle={{ backgroundColor: cardBg }}
          labelStyle={{ color: fg }}
          itemStyle={{ color: fg }}
        />

        <Line
          type="monotone"
          dataKey="value"
          stroke={accent}
          strokeWidth={2}
          dot={{ fill: accent, r: 3 }}
          fill="url(#grad)"
          isAnimationActive
          animationDuration={800}
        />
      </LineChart>
    </ResponsiveContainer>
  );
}



