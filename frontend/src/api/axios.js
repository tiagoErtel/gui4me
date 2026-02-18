import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: { "Content-Type": "application/json" },
});

// Request Interceptor (Existing)
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// NEW: Response Interceptor for Global Logout
api.interceptors.response.use(
  (response) => response,
  (error) => {
    // If the server returns 401 (Unauthorized) or 403 (Forbidden)
    // it usually means the token is expired or invalid.
    if (
      error.response &&
      (error.response.status === 401 || error.response.status === 403)
    ) {
      console.warn("Session expired. Logging out...");
      localStorage.removeItem("token");

      // Redirect to login if not already there
      if (window.location.pathname !== "/login") {
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  },
);

export default api;
