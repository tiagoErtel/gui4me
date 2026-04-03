import React from "react";

export default function Input({ label, error, ...props }) {
  return (
    <div className="flex flex-col gap-1 w-full">
      {label && (
        <label className="text-sm font-medium text-gray-700">{label}</label>
      )}
      <input
        {...props}
        className={`w-full rounded-lg border px-4 py-2 focus:outline-none transition ${
          error
            ? "border-red-500 ring-2 ring-red-200"
            : "border-gray-300 focus:ring-2 focus:ring-blue-500"
        } ${props.className || ""}`}
      />
    </div>
  );
}
