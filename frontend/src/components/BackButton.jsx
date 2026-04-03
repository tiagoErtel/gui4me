import React from "react";
import { useNavigate } from "react-router-dom";

export default function BackButton() {
  const navigate = useNavigate();
  return (
    <button
      onClick={() => navigate(-1)}
      className="mb-6 text-blue-600 hover:text-blue-800 flex items-center gap-2"
    >
      ← Back
    </button>
  );
}
