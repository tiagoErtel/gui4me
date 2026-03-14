import React, { useState } from "react";
import { useAuth } from "@/context/UseAuth";
import { useNavigate, Link } from "react-router-dom";
import Input from "@/components/Input";
import Button from "@/components/Button";

export default function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const [fieldErrors, setFieldErrors] = useState({}); // Track specific field highlights
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setFieldErrors({});

    try {
      await login(email, password);
      navigate("/home");
    } catch (err) {
      // Access the message and fieldErrors thrown by your AuthContext
      setError(err.message || "Something went wrong");

      if (err.fieldErrors) {
        setFieldErrors(err.fieldErrors);
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div className="w-full max-w-md rounded-xl bg-white p-8 shadow-lg">
        <h2 className="text-3xl font-bold text-gray-900 text-center">
          Sign in to your account
        </h2>
        <p className="mt-2 text-center text-gray-600">
          Enter your credentials to access your account.
        </p>

        {error && (
          <div className="mt-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded text-sm text-center">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="mt-6 flex flex-col gap-4">
          <Input
            label="Email"
            type="email"
            placeholder="name@example.com"
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
              if (fieldErrors.email)
                setFieldErrors({ ...fieldErrors, email: null });
            }}
            error={fieldErrors.email}
            required
          />

          <Input
            label="Password"
            type="password"
            placeholder="••••••••"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
              if (fieldErrors.password)
                setFieldErrors({ ...fieldErrors, password: null });
            }}
            error={fieldErrors.password}
            required
          />

          <Button type="submit" loading={loading}>
            Sign in
          </Button>
        </form>

        <div className="mt-6 space-y-2 text-center text-sm">
          <p className="text-gray-600">
            Don't have an account?{" "}
            <Link
              to="/register"
              className="text-blue-600 hover:underline font-medium"
            >
              Create one here
            </Link>
          </p>
          <p className="text-gray-500">
            Forgot your account?{" "}
            <Link
              to="/recover-account"
              className="text-blue-600 hover:underline font-medium"
            >
              Recover your account
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}
