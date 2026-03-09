import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "@/api/axios";
import Input from "@/components/Input";
import Button from "@/components/Button";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";
import { FaPlus, FaTimes, FaEllipsisV } from "react-icons/fa";

export default function ShoppingLists() {
  const [lists, setLists] = useState([]);
  const [newListName, setNewListName] = useState("");
  const [showForm, setShowForm] = useState(false);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();
  const { showToast } = useNotification();

  useEffect(() => {
    fetchLists();
  }, []);

  const fetchLists = async () => {
    try {
      const response = await api.get("/shopping-list/list");
      setLists(response.data);
    } catch (err) {
      showToast("Error loading shopping lists", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await api.post("/shopping-list/create", { name: newListName });
      showToast("List created successfully!");
      setNewListName("");
      setShowForm(false);
      fetchLists(); // Refresh list
    } catch (err) {
      showToast("Failed to create list", "error");
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this list?")) return;
    try {
      await api.post("/shopping-list/delete", { shoppingList: id });
      showToast("List deleted");
      fetchLists();
    } catch (err) {
      showToast("Failed to delete list", "error");
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <BackButton />

      <div className="flex justify-between items-center mb-8">
        <div>
          <h2 className="text-3xl font-bold text-gray-900">Shopping Lists</h2>
          <p className="text-gray-600">Your saved shopping lists</p>
        </div>

        {!showForm && (
          <button
            onClick={() => setShowForm(true)}
            className="bg-blue-600 text-white p-4 rounded-full shadow-lg hover:bg-blue-700 transition"
          >
            <FaPlus />
          </button>
        )}
      </div>

      {showForm && (
        <div className="bg-white p-6 rounded-xl shadow-md border border-blue-100 mb-8 relative animate-fade-in-down">
          <button
            onClick={() => setShowForm(false)}
            className="absolute top-4 right-4 text-gray-400 hover:text-gray-600"
          >
            <FaTimes />
          </button>
          <h4 className="text-lg font-bold mb-4">Create a New Shopping List</h4>
          <form onSubmit={handleCreate} className="flex gap-2">
            <Input
              value={newListName}
              onChange={(e) => setNewListName(e.target.value)}
              placeholder="Give your shopping list a name"
              required
            />
            <Button type="submit" className="w-auto px-6">
              Create
            </Button>
          </form>
        </div>
      )}

      {loading ? (
        <p className="text-center">Loading...</p>
      ) : lists.length === 0 ? (
        <div className="text-center py-10 bg-gray-50 rounded-xl border-2 border-dashed">
          <p className="text-gray-500">No shopping lists created yet.</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 gap-4">
          {lists.map((list) => (
            <div
              key={list.id}
              className="bg-white p-4 rounded-xl shadow-sm border border-gray-200 flex justify-between items-center hover:border-blue-300 transition"
            >
              <div
                className="flex-grow cursor-pointer"
                onClick={() => navigate(`/shopping-list/${list.id}/item`)}
              >
                <h3 className="text-lg font-semibold text-gray-800">
                  {list.name}
                </h3>
              </div>

              <details className="relative group">
                <summary className="list-none cursor-pointer p-2 text-gray-400 hover:text-gray-600">
                  <FaEllipsisV />
                </summary>
                <div className="absolute right-0 mt-2 w-32 bg-white border rounded-lg shadow-xl z-10 py-1">
                  <button className="w-full text-left px-4 py-2 text-sm hover:bg-gray-100">
                    Rename
                  </button>
                  <button
                    onClick={() => handleDelete(list.id)}
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
