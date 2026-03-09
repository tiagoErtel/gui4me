import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import api from "@/api/axios";
import BackButton from "@/components/BackButton";
import { useNotification } from "@/context/NotificationContext";

export default function ProductAnalyse() {
  const { productId } = useParams();
  const [analysis, setAnalysis] = useState([]);
  const [loading, setLoading] = useState(true);
  const { showToast } = useNotification();

  useEffect(() => {
    const fetchAnalysis = async () => {
      try {
        const response = await api.get(`/product/analyse/${productId}`);
        setAnalysis(response.data);
      } catch (err) {
        showToast("Failed to load product analysis", "error");
      } finally {
        setLoading(false);
      }
    };
    fetchAnalysis();
  }, [productId]);

  const productName =
    analysis.length > 0 ? analysis[0].productNormalizedName : "";

  return (
    <div className="max-w-4xl mx-auto p-6">
      <BackButton />

      <div className="mb-8 text-center">
        <h2 className="text-3xl font-bold text-gray-900">
          Analysis: {productName || "Product"}
        </h2>
        <p className="text-gray-600">Price comparison by store</p>
      </div>

      {loading ? (
        <div className="text-center py-10">Analyzing prices...</div>
      ) : analysis.length === 0 ? (
        <div className="text-center py-10 bg-white rounded-xl shadow border border-dashed border-gray-300">
          <p className="text-gray-500">
            No analysis available for this product.
          </p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {analysis.map((item, index) => (
            <div
              key={index}
              className="bg-white p-6 rounded-xl shadow-sm border border-gray-200"
            >
              <h3 className="text-xl font-bold text-blue-600 mb-4">
                {item.storeName}
              </h3>
              <div className="grid grid-cols-2 gap-4 text-sm">
                <div className="bg-gray-50 p-3 rounded-lg">
                  <span className="text-gray-500 block">Max Price</span>
                  <span className="font-bold text-gray-900 text-lg">
                    R$ {item.maxPrice.toFixed(2)}
                  </span>
                </div>
                <div className="bg-gray-50 p-3 rounded-lg">
                  <span className="text-gray-500 block">Min Price</span>
                  <span className="font-bold text-green-600 text-lg">
                    R$ {item.minPrice.toFixed(2)}
                  </span>
                </div>
                <div className="bg-gray-50 p-3 rounded-lg">
                  <span className="text-gray-500 block">Avg Price</span>
                  <span className="font-bold text-gray-900 text-lg">
                    R$ {item.avgPrice.toFixed(2)}
                  </span>
                </div>
                <div className="bg-gray-50 p-3 rounded-lg">
                  <span className="text-gray-500 block">Times Sold</span>
                  <span className="font-bold text-gray-900 text-lg">
                    {item.timesSold}
                  </span>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
