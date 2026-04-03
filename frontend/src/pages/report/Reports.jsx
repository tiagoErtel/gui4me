import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import api from "@/api/axios";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
);

export default function Reports() {
  const [reportData, setReportData] = useState([]);
  const [loading, setLoading] = useState(true);
  const { showToast } = useNotification();

  useEffect(() => {
    const fetchReport = async () => {
      try {
        const response = await api.get("/reports/invoices-by-store");
        setReportData(response.data);
      } catch (err) {
        showToast("Failed to load report data", "error");
      } finally {
        setLoading(false);
      }
    };
    fetchReport();
  }, []);

  const commonOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      x: {
        ticks: {
          callback: (val, index) => {
            const label = reportData[index]?.storeName || "";
            return label.length > 15 ? label.slice(0, 15) + "…" : label;
          },
        },
      },
    },
  };

  const invoicesChartData = {
    labels: reportData.map((row) => row.storeName),
    datasets: [
      {
        label: "Invoices per Store",
        data: reportData.map((row) => row.invoiceCount),
        backgroundColor: "rgba(54, 162, 235, 0.6)",
      },
    ],
  };

  const priceChartData = {
    labels: reportData.map((row) => row.storeName),
    datasets: [
      {
        label: "Total Spent (R$)",
        data: reportData.map((row) => row.totalPrice),
        backgroundColor: "rgba(75, 192, 192, 0.6)",
      },
    ],
  };

  if (loading) return <div className="p-10 text-center">Loading Charts...</div>;

  return (
    <div className="max-w-6xl mx-auto p-6">
      <BackButton />
      <h1 className="text-3xl font-bold mb-8 text-gray-900">Reports</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div className="bg-white p-4 rounded-xl shadow-sm border h-[400px]">
          <Bar
            data={invoicesChartData}
            options={{
              ...commonOptions,
              plugins: { title: { display: true, text: "Invoices per Store" } },
            }}
          />
        </div>

        <div className="bg-white p-4 rounded-xl shadow-sm border h-[400px]">
          <Bar
            data={priceChartData}
            options={{
              ...commonOptions,
              plugins: {
                title: { display: true, text: "Total Spent per Store" },
              },
              scales: {
                ...commonOptions.scales,
                y: { ticks: { callback: (value) => `R$${value}` } },
              },
            }}
          />
        </div>
      </div>
    </div>
  );
}
