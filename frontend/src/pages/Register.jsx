import React, { useState } from "react";
import { useAuth } from "@/context/UseAuth";
import { useNavigate } from "react-router-dom";
import "@/index.css";
import Input from "@/components/Input";
import Button from "@/components/Button";

export default function Register() {
  const { register } = useAuth();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [fieldErrors, setFieldErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });

    // Clear the red outline as soon as they start fixing it
    if (fieldErrors[name]) {
      setFieldErrors({ ...fieldErrors, [name]: null });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setFieldErrors({});

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match");
      setLoading(false);
      setFieldErrors({ password: true, confirmPassword: true });
      return;
    }

    try {
      await register(
        formData.username,
        formData.email,
        formData.password,
        formData.confirmPassword,
      );
      navigate("/home");
    } catch (err) {
      setError(err.message || "Register failed");
      if (err.fieldErrors) setFieldErrors(err.fieldErrors);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-md rounded-xl bg-white p-8 shadow-lg">
        <h2 className="text-3xl font-bold text-gray-900 text-center">
          Create your account
        </h2>
        <p className="mt-2 text-center text-gray-600">
          Please fill in the details below to get started.
        </p>

        {error && (
          <div className="mt-4 p-2 bg-red-100 text-red-700 rounded">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="mt-6 flex flex-col gap-4">
          <Input
            label="Username"
            name="username"
            placeholder="Name"
            value={formData.username}
            onChange={handleChange}
            error={fieldErrors.username}
            required
          />
          <Input
            label="Email"
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            error={fieldErrors.email}
            required
          />
          <Input
            label="Password"
            type="password"
            name="password"
            placeholder="••••••••"
            value={formData.password}
            onChange={handleChange}
            error={fieldErrors.password}
            required
          />
          <Input
            label="Confirm Password"
            type="password"
            name="confirmPassword"
            placeholder="••••••••"
            value={formData.confirmPassword}
            onChange={handleChange}
            error={fieldErrors.confirmPassword}
            required
          />
          <Button type="submit" loading={loading}>
            Register
          </Button>{" "}
        </form>

        <p className="mt-4 text-center text-gray-600">
          Already have an account?{" "}
          <a href="/login" className="text-blue-600 hover:underline">
            Sign in here
          </a>
        </p>
      </div>
    </div>
  );
}
