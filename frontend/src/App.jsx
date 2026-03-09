import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { NotificationProvider } from "./context/NotificationContext";
import { ProtectedRoute } from "./routes/ProtectedRoute";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Register from "./pages/Register";
import SearchProducts from "./pages/products/SearchProducts";
import RegisterPurchase from "./pages/invoices/RegisterPurchase";
import InvoiceList from "./pages/invoices/InvoiceList";
import InvoiceItemsList from "./pages/invoices/items/InvoiceItemList";
import ShoppingLists from "./pages/shoppingList/ShoppingLists";
import ShoppingListItemList from "./pages/shoppingList/item/ShoppingListItemList";

function App() {
  return (
    <Router>
      <AuthProvider>
        <NotificationProvider>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            <Route element={<ProtectedRoute />}>
              <Route path="/" element={<Navigate to="/home" replace />} />
              <Route path="/home" element={<Home />} />

              <Route path="/product/search" element={<SearchProducts />} />

              <Route path="/invoice/register" element={<RegisterPurchase />} />
              <Route path="/invoice/list" element={<InvoiceList />} />

              <Route path="/invoice/item/list" element={<InvoiceItemsList />} />

              <Route path="/shopping-list/list" element={<ShoppingLists />} />

              <Route
                path="/shopping-list/:shoppingListId/item"
                element={<ShoppingListItemList />}
              />
              <Route path="/admin" element={<div>Admin Panel</div>} />
            </Route>

            <Route path="*" element={<div>404 Not Found</div>} />
          </Routes>
        </NotificationProvider>
      </AuthProvider>
    </Router>
  );
}

export default App;
