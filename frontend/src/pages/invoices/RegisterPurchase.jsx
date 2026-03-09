import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Html5Qrcode, Html5QrcodeScannerState } from "html5-qrcode";
import Input from "@/components/Input";
import Button from "@/components/Button";
import api from "@/api/axios";
import { useNotification } from "@/context/NotificationContext";

export default function RegisterPurchase() {
  const { showToast } = useNotification();
  const navigate = useNavigate();
  const scannerRef = useRef(null); // To store the scanner instance
  const [cameras, setCameras] = useState([]);
  const [selectedCameraId, setSelectedCameraId] = useState("");
  const [invoiceUrl, setInvoiceUrl] = useState("");
  const [showManual, setShowManual] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({});

  useEffect(() => {
    Html5Qrcode.getCameras()
      .then((devices) => {
        if (devices && devices.length > 0) {
          setCameras(devices);
          const backCamera = devices[devices.length - 1].id;
          setSelectedCameraId(backCamera);
        }
      })
      .catch((err) => console.error("Camera access error:", err));

    return () => stopScanner();
  }, []);

  useEffect(() => {
    if (selectedCameraId) {
      startScanner(selectedCameraId);
    }
  }, [selectedCameraId]);

  const stopScanner = async () => {
    if (
      scannerRef.current &&
      scannerRef.current.getState() === Html5QrcodeScannerState.SCANNING
    ) {
      try {
        await scannerRef.current.stop();
        scannerRef.current.clear();
      } catch (err) {
        console.error("Stop failed:", err);
      }
    }
  };

  const startScanner = async (cameraId) => {
    await stopScanner();

    const html5QrCode = new Html5Qrcode("qrcode-reader");
    scannerRef.current = html5QrCode;

    try {
      await html5QrCode.start(
        cameraId,
        {
          fps: 10,
          qrbox: document.getElementById("qrcode-reader").clientWidth * 0.6,
        },
        onScanSuccess,
        (warn) => {},
      );
    } catch (err) {
      console.error("Start failed:", err);
    }
  };

  const onScanSuccess = async (decodedText) => {
    setLoading(true);
    await stopScanner();
    handleRegister(decodedText);
  };

  const handleRegister = async (url) => {
    setLoading(true);
    setError(null);
    setFieldErrors({});
    try {
      await api.post("/invoice/register", { invoiceUrl: url });
      showToast("Invoice registered successfully!", "success");
      navigate("/invoice/register");
    } catch (err) {
      showToast(err.message || "Failed to register", "error");
      setShowManual(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-xl mx-auto p-6">
      <div className="mb-6 text-center">
        <h2 className="text-3xl font-bold">Register Purchase</h2>
        <p className="text-gray-600">Scan the invoice or input the link</p>
      </div>

      <div className="mb-4">
        <label className="block text-sm font-medium mb-1">Choose camera:</label>
        <select
          className="w-full p-2 border rounded-lg"
          value={selectedCameraId}
          onChange={(e) => setSelectedCameraId(e.target.value)}
        >
          {cameras.map((camera, i) => (
            <option key={camera.id} value={camera.id}>
              {camera.label || `Camera ${i + 1}`}
            </option>
          ))}
        </select>
      </div>

      <div
        id="qrcode-reader"
        className="overflow-hidden rounded-xl bg-black aspect-square"
      ></div>

      {!showManual && (
        <p className="mt-4 text-center">
          Having trouble scanning?{" "}
          <button
            onClick={() => setShowManual(true)}
            className="text-blue-600 underline"
          >
            Paste link manually
          </button>
        </p>
      )}

      {(showManual || error) && (
        <form
          onSubmit={(e) => {
            e.preventDefault();
            handleRegister(invoiceUrl);
          }}
          className="mt-6 space-y-4 border-t pt-6"
        >
          <Input
            label="Invoice Link"
            placeholder="Paste the invoice link here"
            value={invoiceUrl}
            onChange={(e) => setInvoiceUrl(e.target.value)}
            error={fieldErrors.invoiceUrl}
          />
          {error && !fieldErrors.invoiceUrl && (
            <p className="text-red-500 text-sm text-center">{error}</p>
          )}
          <Button type="submit" loading={loading}>
            Submit Link
          </Button>
        </form>
      )}

      {loading && !showManual && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-lg flex items-center gap-4">
            <div className="animate-spin h-5 w-5 border-2 border-blue-600 border-t-transparent rounded-full"></div>
            <span>Loading invoice, please wait...</span>
          </div>
        </div>
      )}
    </div>
  );
}
