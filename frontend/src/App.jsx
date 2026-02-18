import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { ProtectedRoute } from "./routes/ProtectedRoute";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Register from "./pages/Register"

function App() {
    return (
        <Router>
            <AuthProvider>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />

                    <Route element={<ProtectedRoute />}>
                        <Route path="/" element={<Navigate to="/home" replace />} />
                        <Route path="/home" element={<Home />} />
                        <Route path="/admin" element={<div>Admin Panel</div>} />
                    </Route>

                    <Route path="*" element={<div>404 Not Found</div>} />
                </Routes>
            </AuthProvider>
        </Router>
    );
}

export default App;
