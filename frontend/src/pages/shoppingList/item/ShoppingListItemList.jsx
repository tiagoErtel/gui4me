import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import api from "@/api/axios";
import Input from "@/components/Input";
import Button from "@/components/Button";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";
import { FaPlus, FaTimes, FaEllipsisV } from "react-icons/fa";

export default function ShoppingListItemList() {
  const { shoppingListId } = useParams();
  const { showToast } = useNotification();

  const [shoppingList, setShoppingList] = useState(null);
  const [items, setItems] = useState([]);
  const [products, setProducts] = useState([]);

  const [showForm, setShowForm] = useState(false);
  const [loading, setLoading] = useState(true);

  const [selectedProductId, setSelectedProductId] = useState("");
  const [quantity, setQuantity] = useState("");

  useEffect(() => {
    fetchData();
  }, [shoppingListId]);

  const fetchData = async () => {
    try {
      const response = await api.get(`/shopping-list/${shoppingListId}/item`);
      setShoppingList(response.data.shoppingList);
      setItems(response.data.shoppingListItems);
      setProducts(response.data.productList);
    } catch (err) {
      showToast("Error loading items", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      product: { id: selectedProductId },
      quantity: parseFloat(quantity),
    };

    try {
      await api.post(`/shopping-list/${shoppingListId}/item/save`, payload);
      showToast("Item added!");
      setSelectedProductId("");
      setQuantity("");
      setShowForm(false);
      fetchData(); // Refresh list
    } catch (err) {
      showToast("Failed to add item", "error");
    }
  };

  const handleDelete = async (itemId) => {
    if (!window.confirm("Remove this item?")) return;
    try {
      await api.post(`/shopping-list/${shoppingListId}/item/delete`, {
        id: itemId,
      });
      showToast("Item removed");
      fetchData();
    } catch (err) {
      showToast("Failed to delete", "error");
    }
  };

  if (loading)
    return <div className="p-10 text-center">Loading list details...</div>;

  return (
    <div className="max-w-4xl mx-auto p-6">
      <BackButton to="/shopping-list" />

      <div className="flex justify-between items-center mb-8">
        <div>
          <h2 className="text-3xl font-bold text-gray-900">
            List: {shoppingList?.name}
          </h2>
          <p className="text-gray-600">Manage your items</p>
        </div>
        {!showForm && (
          <button
            onClick={() => setShowForm(true)}
            className="bg-blue-600 text-white p-4 rounded-full shadow-lg hover:bg-blue-700"
          >
            <FaPlus />
          </button>
        )}
      </div>

      {showForm && (
        <div className="bg-white p-6 rounded-xl shadow-md border border-blue-100 mb-8 relative">
          <button
            onClick={() => setShowForm(false)}
            className="absolute top-4 right-4 text-gray-400"
          >
            <FaTimes />
          </button>
          <h4 className="text-lg font-bold mb-4">Add Item to the List</h4>
          <form onSubmit={handleSave} className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-1">Product</label>
              <select
                className="w-full p-2 border rounded-lg bg-white"
                value={selectedProductId}
                onChange={(e) => setSelectedProductId(e.target.value)}
                required
              >
                <option value="">Select a product</option>
                {products.map((p) => (
                  <option key={p.id} value={p.id}>
                    {p.name}
                  </option>
                ))}
              </select>
            </div>
            <Input
              type="number"
              label="Quantity"
              placeholder="e.g. 2"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
              required
            />
            <Button type="submit">Add Item</Button>
          </form>
        </div>
      )}

      {items.length === 0 ? (
        <div className="text-center py-10 bg-gray-50 rounded-xl border-2 border-dashed">
          <p className="text-gray-500">No items added yet.</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 gap-4">
          {items.map((item) => (
            <div
              key={item.id}
              className="bg-white p-5 rounded-xl shadow-sm border border-gray-200 flex justify-between items-center"
            >
              <div>
                <h3 className="text-lg font-bold text-gray-900">
                  {item.product.name}
                </h3>
                <p className="text-sm text-gray-500 font-medium">
                  Quantity: {item.quantity}
                </p>
              </div>
              <details className="relative">
                <summary className="list-none cursor-pointer p-2 text-gray-400">
                  <FaEllipsisV />
                </summary>
                <div className="absolute right-0 mt-2 w-32 bg-white border rounded shadow-xl z-10">
                  <button
                    onClick={() => handleDelete(item.id)}
                    className="w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-red-50"
                  >
                    Delete
                  </button>
                </div>
              </details>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
