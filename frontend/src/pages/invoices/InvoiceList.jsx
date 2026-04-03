import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "@/api/axios";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";

export default function InvoiceList() {
  const [invoices, setInvoices] = useState([]);
  const [sort, setSort] = useState("issuanceDate,desc");
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const { showToast } = useNotification();

  useEffect(() => {
    fetchInvoices();
  }, [sort]);

  const fetchInvoices = async () => {
    setLoading(true);
    try {
      const response = await api.get(`/invoice/list?sort=${sort}`);
      setInvoices(response.data);
    } catch (err) {
      showToast("Failed to load invoices", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleSortChange = (e) => {
    setSort(e.target.value);
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <BackButton />

      <div className="mb-8 text-center">
        <h2 className="text-3xl font-bold text-gray-900">Invoices List</h2>
        <p className="text-gray-600">Explore the details of your invoices</p>
      </div>

      {loading ? (
        <div className="text-center py-10">Loading invoices...</div>
      ) : invoices.length === 0 ? (
        <div className="text-center py-10 bg-white rounded-xl shadow border border-dashed border-gray-300">
          <p className="text-gray-500">No invoices found.</p>
        </div>
      ) : (
        <>
          <div className="flex items-center gap-2 mb-6">
            <label htmlFor="sort" className="text-sm font-medium">
              Sort by:
            </label>
            <select
              id="sort"
              value={sort}
              onChange={handleSortChange}
              className="p-2 border rounded-lg bg-white shadow-sm"
            >
              <option value="issuanceDate,desc">Date (Newest)</option>
              <option value="issuanceDate,asc">Date (Oldest)</option>
              <option value="totalPrice,desc">Total Price (Highest)</option>
              <option value="totalPrice,asc">Total Price (Lowest)</option>
            </select>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {invoices.map((invoice) => (
              <div
                key={invoice.id}
                onClick={() =>
                  navigate(`/invoice/item/list?invoiceId=${invoice.id}`)
                }
                className="bg-white p-5 rounded-xl shadow-sm border border-gray-200 hover:shadow-md hover:border-blue-300 transition cursor-pointer group"
              >
                <div className="flex justify-between items-start">
                  <div>
                    <h3 className="text-lg font-bold text-gray-900 group-hover:text-blue-600">
                      {invoice.store?.name || "Unknown Store"}
                    </h3>
                    <p className="text-sm text-gray-500">
                      {new Date(invoice.issuanceDate).toLocaleDateString()}
                    </p>
                  </div>
                  <div className="text-right">
                    <p className="text-lg font-bold text-blue-600">
                      R$ {invoice.totalPrice.toFixed(2)}
                    </p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
}
