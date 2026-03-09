import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Input from "@/components/Input";
import Button from "@/components/Button";
import BackButton from "../../components/BackButton";
import api from "@/api/axios";

export default function SearchProducts() {
  const [query, setQuery] = useState("");
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const response = await api.get(`/product/search?productName=${query}`);
      setProducts(response.data);
    } catch (err) {
      setError(err.message || "Failed to fetch products");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <BackButton />
      <div className="mb-8 text-center">
        <h2 className="text-3xl font-bold text-gray-900">Search Products</h2>
        <p className="text-gray-600">Find items in our database</p>
      </div>

      <form onSubmit={handleSearch} className="flex gap-2 mb-8">
        <Input
          placeholder="Search by product name..."
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          required
        />
        <Button type="submit" loading={loading} className="w-auto px-6">
          Search
        </Button>
      </form>

      {error && (
        <div className="p-4 mb-4 bg-red-100 text-red-700 rounded-lg text-center">
          {error}
        </div>
      )}

      {products.length === 0 && !loading ? (
        <div className="text-center py-10 bg-white rounded-xl shadow border border-dashed border-gray-300">
          <p className="text-gray-500">
            No products found. Try a different search term.
          </p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {products.map((product) => (
            <div
              key={product.id}
              onClick={() => navigate(`/product/analyse/${product.id}`)}
              className="bg-white p-5 rounded-xl shadow-sm border border-gray-200 hover:shadow-md hover:border-blue-300 transition cursor-pointer"
            >
              <h3 className="text-lg font-bold text-blue-600 mb-2">
                {product.normalizedName}
              </h3>
              <div className="grid grid-cols-2 gap-y-1 text-sm text-gray-700">
                <p className="col-span-2 text-xs text-gray-400 mb-2">
                  Name: {product.name}
                </p>
                <p>
                  <span className="font-semibold text-gray-900">Max:</span> R${" "}
                  {product.maxPrice.toFixed(2)}
                </p>
                <p>
                  <span className="font-semibold text-gray-900">Min:</span> R${" "}
                  {product.minPrice.toFixed(2)}
                </p>
                <p>
                  <span className="font-semibold text-gray-900">Avg:</span> R${" "}
                  {product.avgPrice.toFixed(2)}
                </p>
                <p>
                  <span className="font-semibold text-gray-900">Sold:</span>{" "}
                  {product.timesSold}
                </p>
                <p className="col-span-2">
                  <span className="font-semibold text-gray-900">Stores:</span>{" "}
                  {product.storesCount}
                </p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
