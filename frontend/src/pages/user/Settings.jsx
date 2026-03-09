import React, { useState } from "react";
import api from "@/api/axios";
import Input from "@/components/Input";
import Button from "@/components/Button";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";
import { useAuth } from "@/context/UseAuth";

export default function Settings() {
  const { user, login } = useAuth();
  const { showToast } = useNotification();

  const [newUsername, setNewUsername] = useState(user?.username || "");

  const [passwordData, setPasswordData] = useState({
    currentPassword: "",
    newPassword: "",
    confirmPassword: "",
  });

  const [errors, setErrors] = useState({});

  const handleUsernameUpdate = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post("/user/settings/username", {
        newUsername,
      });
      showToast("Username updated successfully!");
    } catch (err) {
      setErrors(err.response?.data?.fieldErrors || {});
      showToast("Failed to update username", "error");
    }
  };

  const handlePasswordUpdate = async (e) => {
    e.preventDefault();
    if (passwordData.newPassword !== passwordData.confirmPassword) {
      showToast("New passwords do not match", "error");
      return;
    }

    try {
      await api.post("/user/settings/password", passwordData);
      showToast("Password updated successfully!");
      setPasswordData({
        currentPassword: "",
        newPassword: "",
        confirmPassword: "",
      });
      setErrors({});
    } catch (err) {
      setErrors(err.response?.data?.fieldErrors || {});
      showToast("Failed to update password", "error");
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <BackButton />

      <div className="mb-8">
        <h2 className="text-3xl font-bold text-gray-900">Settings</h2>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-200">
          <h3 className="text-xl font-semibold mb-4 text-gray-700">
            Account Info
          </h3>
          <form onSubmit={handleUsernameUpdate} className="space-y-4">
            <Input
              label="Username"
              value={newUsername}
              onChange={(e) => setNewUsername(e.target.value)}
              error={errors.newUsername}
              placeholder="New username"
            />
            <Button type="submit">Update Info</Button>
          </form>
        </div>

        <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-200">
          <h3 className="text-xl font-semibold mb-4 text-gray-700">
            Change Password
          </h3>
          <form onSubmit={handlePasswordUpdate} className="space-y-4">
            <Input
              type="password"
              label="Current Password"
              value={passwordData.currentPassword}
              onChange={(e) =>
                setPasswordData({
                  ...passwordData,
                  currentPassword: e.target.value,
                })
              }
              error={errors.currentPassword}
              placeholder="Current password"
            />
            <Input
              type="password"
              label="New Password"
              value={passwordData.newPassword}
              onChange={(e) =>
                setPasswordData({
                  ...passwordData,
                  newPassword: e.target.value,
                })
              }
              error={errors.newPassword}
              placeholder="New password"
            />
            <Input
              type="password"
              label="Confirm Password"
              value={passwordData.confirmPassword}
              onChange={(e) =>
                setPasswordData({
                  ...passwordData,
                  confirmPassword: e.target.value,
                })
              }
              error={errors.confirmPassword}
              placeholder="Confirm the password"
            />
            <Button type="submit">Update Password</Button>
          </form>
        </div>
      </div>
    </div>
  );
}
