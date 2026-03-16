import React, { useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import api from "@/api/axios";
import Input from "@/components/Input";
import Button from "@/components/Button";
import { useNotification } from "@/context/NotificationContext";

export default function ResetPassword() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const { showToast } = useNotification();

  const token = searchParams.get("token");
  const email = searchParams.get("email");

  const [passwordData, setPasswordData] = useState({
    newPassword: "",
    confirmPassword: "",
  });
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  const handleReset = async (e) => {
    e.preventDefault();
    if (passwordData.newPassword !== passwordData.confirmPassword) {
      showToast("Passwords do not match", "error");
      return;
    }

    setLoading(true);
    try {
      await api.post("/user/reset-password", {
        token,
        email,
        newPassword: passwordData.newPassword,
        confirmPassword: passwordData.confirmPassword,
      });

      showToast("Password reset successfully! Please log in.");
      navigate("/login");
    } catch (err) {
      setErrors(err.response?.data?.fieldErrors || {});
      showToast(
        err.response?.data?.message || "Failed to reset password",
        "error",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
      <div className="max-w-md w-full bg-white p-8 rounded-xl shadow-lg border border-gray-100">
        <h2 className="text-2xl font-bold text-center text-gray-900 mb-6">
          Reset your password
        </h2>

        <form onSubmit={handleReset} className="space-y-4">
          <Input
            type="password"
            label="New Password"
            placeholder="Enter new password"
            value={passwordData.newPassword}
            onChange={(e) =>
              setPasswordData({ ...passwordData, newPassword: e.target.value })
            }
            error={errors.newPassword}
            required
          />

          <Input
            type="password"
            label="Confirm New Password"
            placeholder="Confirm new password"
            value={passwordData.confirmPassword}
            onChange={(e) =>
              setPasswordData({
                ...passwordData,
                confirmPassword: e.target.value,
              })
            }
            error={errors.confirmPassword}
            required
          />

          <Button type="submit" loading={loading}>
            Reset password
          </Button>
        </form>
      </div>
    </div>
  );
}
