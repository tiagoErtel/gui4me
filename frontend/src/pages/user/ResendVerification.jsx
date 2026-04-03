import React, { useState } from "react";
import api from "@/api/axios";
import Input from "@/components/Input";
import Button from "@/components/Button";
import { useNotification } from "@/context/NotificationContext";
import { Link } from "react-router-dom";

export default function ResendVerification() {
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState({});
  const { showToast } = useNotification();

  const handleResend = async (e) => {
    e.preventDefault();
    setLoading(true);
    setErrors({});

    try {
      const response = await api.post("/user/resend-verification-email", {
        email,
      });
      showToast(
        response.data.message || "Verification email resent!",
        "success",
      );
      setEmail("");
    } catch (err) {
      setErrors(err.response?.data?.fieldErrors || {});
      showToast(
        err.response?.data?.message || "Could not resend email",
        "error",
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4">
      <div className="max-w-md w-full bg-white p-8 rounded-xl shadow-lg border border-gray-100">
        <h2 className="text-2xl font-bold text-center text-gray-900 mb-2">
          Resend verification email
        </h2>
        <p className="text-center text-gray-600 mb-8 text-sm">
          Didn't get the link? Enter your email and we'll send a new one.
        </p>

        <form onSubmit={handleResend} className="space-y-6">
          <Input
            type="email"
            label="Email Address"
            placeholder="Your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            error={errors.email}
            required
          />

          <Button type="submit" loading={loading}>
            {loading ? "Sending..." : "Resend email"}
          </Button>
        </form>

        <div className="mt-6 text-center">
          <Link to="/login" className="text-sm text-blue-600 hover:underline">
            Back to Login
          </Link>
        </div>
      </div>
    </div>
  );
}
