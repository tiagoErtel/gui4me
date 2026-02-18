import React, { createContext, useState, useEffect } from "react";
import api from "../api/axios";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // 1. Initial Load: Check for an existing token
  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem("token");
      if (token) {
        try {
          // Note: In the future, you can add a /api/users/me endpoint
          // to fetch actual user profile data here.
          setUser({ token });
        } catch (error) {
          console.error("Auth initialization failed", error);
          localStorage.removeItem("token");
        }
      }
      setLoading(false);
    };
    checkAuth();
  }, []);

  // 2. Login Logic
  const login = async (email, password) => {
    try {
      // Updated keys to match your Java LoginRequest Record
      const response = await api.post("/auth/login", {
        email: email,
        password: password,
      });

      const { token } = response.data;

      localStorage.setItem("token", token);
      setUser({ email, token });

      return { success: true };
    } catch (error) {
      console.error("Login attempt failed:", error);

      // Extract the custom error message from your GlobalExceptionHandler
      const errorData = error.response?.data;

      // We throw the whole data object so the Login UI can see
      // fields like "resendLink" or "message"
      throw errorData || { message: "Internal Server Error" };
    }
  };

  // 3. Registration Logic
  const register = async (username, email, password, confirmPassword) => {
    try {
      // Matches your RegisterRequest Record
      await api.post("/auth/register", {
        username,
        email,
        password,
        confirmPassword,
      });
      return { success: true };
    } catch (error) {
      console.error("Registration failed:", error);
      throw error.response?.data || { message: "Registration failed" };
    }
  };

  // 4. Logout Logic
  const logout = () => {
    localStorage.removeItem("token");
    setUser(null);
    // Using a redirect ensures all app state/interceptors are reset
    window.location.href = "/login";
  };

  return (
    <AuthContext.Provider value={{ user, login, register, logout, loading }}>
      {!loading && children}
    </AuthContext.Provider>
  );
};
