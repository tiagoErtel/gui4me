import React, { createContext, useContext, useState, useCallback } from "react";

const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [toast, setToast] = useState(null);

  const showToast = useCallback((message, type = "success") => {
    setToast({ message, type });
    setTimeout(() => setToast(null), 4000);
  }, []);

  return (
    <NotificationContext.Provider value={{ showToast }}>
      {children}
      {toast && (
        <div className="fixed bottom-5 right-5 z-[100] animate-bounce-in">
          <div
            className={`px-6 py-3 rounded-lg shadow-lg text-white font-medium ${
              toast.type === "success" ? "bg-green-600" : "bg-red-600"
            }`}
          >
            {toast.message}
          </div>
        </div>
      )}
    </NotificationContext.Provider>
  );
};

export const useNotification = () => useContext(NotificationContext);
