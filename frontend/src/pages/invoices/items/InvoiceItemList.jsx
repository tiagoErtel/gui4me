import React, { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import api from "@/api/axios";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";

export default function InvoiceItemsList() {
  const [searchParams] = useSearchParams();
  const invoiceId = searchParams.get("invoiceId");

  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const { showToast } = useNotification();

  useEffect(() => {
    if (invoiceId) {
      fetchItems();
    } else {
      setLoading(false);
      showToast("No invoice ID provided", "error");
    }
  }, [invoiceId]);

  const fetchItems = async () => {
    setLoading(true);
    try {
      const response = await api.get(
        `/invoice/item/list?invoiceId=${invoiceId}`,
      );
      setItems(response.data);
    } catch (err) {
      showToast("Failed to load invoice items", "error");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <section className="space-y-6">
        <div>
          <BackButton />
          <div className="text-center">
            <h2 className="text-3xl font-bold text-gray-900">Invoice Items</h2>
            <p className="text-gray-600">Details of the selected invoice</p>
          </div>
        </div>

        {loading ? (
          <div className="text-center py-10">Loading items...</div>
        ) : items.length === 0 ? (
          <div className="text-center py-10 bg-white rounded-xl shadow border border-dashed border-gray-300">
            <p className="text-gray-500">No items found.</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {items.map((item, index) => (
              <div
                key={index}
                className="bg-white p-5 rounded-xl shadow-sm border border-gray-200"
              >
                <div className="card-content">
                  <h3 className="text-lg font-bold text-blue-600 mb-2">
                    {item.productName}
                  </h3>
                  <div className="space-y-1 text-sm text-gray-700">
                    <p className="font-medium text-gray-500">
                      {item.quantity} {item.unit}
                    </p>
                    <div className="pt-2 border-t border-gray-100 flex justify-between">
                      <span>Unit:</span>
                      <span className="font-semibold text-gray-900">
                        R$ {item.unitPrice?.toFixed(2)}
                      </span>
                    </div>
                    <div className="flex justify-between text-base">
                      <span className="font-bold">Total:</span>
                      <span className="font-bold text-gray-900">
                        R$ {item.totalPrice?.toFixed(2)}
                      </span>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
