import {
  Navigate,
  Route,
  BrowserRouter as Router,
  Routes,
} from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { NotificationProvider } from "./context/NotificationContext";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import InvoiceList from "./pages/invoices/InvoiceList";
import RegisterPurchase from "./pages/invoices/RegisterPurchase";
import InvoiceItemsList from "./pages/invoices/items/InvoiceItemList";
import ProductAnalyse from "./pages/products/ProductAnalyse";
import SearchProducts from "./pages/products/SearchProducts";
import Reports from "./pages/report/Reports";
import ShoppingLists from "./pages/shoppingList/ShoppingLists";
import ShoppingListItemList from "./pages/shoppingList/item/ShoppingListItemList";
import { ProtectedRoute } from "./routes/ProtectedRoute";

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
              <Route
                path="/product/analyse/:productId"
                element={<ProductAnalyse />}
              />

              <Route path="/invoice/register" element={<RegisterPurchase />} />
              <Route path="/invoice/list" element={<InvoiceList />} />

              <Route path="/invoice/item/list" element={<InvoiceItemsList />} />

              <Route path="/shopping-list/list" element={<ShoppingLists />} />

              <Route path="/reports" element={<Reports />} />

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
