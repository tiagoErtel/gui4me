import React, { createContext, useState, useEffect, useCallback } from "react";
import api from "../api/axios";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const checkAuth = useCallback(async () => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const response = await api.get("/user/me");
        setUser({ ...response.data, token });
      } catch (error) {
        console.error("Auth initialization failed", error);
        localStorage.removeItem("token");
        setUser(null);
      }
    } else {
      setUser(null);
    }
    setLoading(false);
  }, []);

  useEffect(() => {
    checkAuth();
  }, [checkAuth]);

  const login = async (email, password) => {
    try {
      const response = await api.post("/auth/login", { email, password });
      const { token } = response.data;
      localStorage.setItem("token", token);

      api.defaults.headers.common["Authorization"] = `Bearer ${token}`;

      await checkAuth();
      return { success: true };
    } catch (error) {
      throw error.response?.data || { message: "Internal Server Error" };
    }
  };

  const register = async (username, email, password, confirmPassword) => {
    try {
      await api.post("/auth/register", {
        username,
        email,
        password,
        confirmPassword,
      });
      return { success: true };
    } catch (error) {
      throw error.response?.data || { message: "Registration failed" };
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setUser(null);
    window.location.href = "/login";
  };

  return (
    <AuthContext.Provider
      value={{ user, login, register, logout, loading, checkAuth }}
    >
      {!loading && children}
    </AuthContext.Provider>
  );
};
