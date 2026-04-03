import { Link } from "react-router-dom"

export default function Landing() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-md rounded-xl bg-white p-8 shadow-lg">
        <h1 className="text-3xl font-bold text-gray-900 text-center">
          Welcome to Gui4Me
        </h1>

        <p className="mt-4 text-center text-gray-600">
          Discover amazing features to track your purchases, manage shopping
          lists, and explore product insights.
        </p>

        <div className="mt-8 flex gap-4">
          <Link
            to="/login"
            className="flex-1 rounded-lg bg-blue-600 px-4 py-2 text-center text-white hover:bg-blue-700 transition"
          >
            Login
          </Link>

          <Link
            to="/register"
            className="flex-1 rounded-lg border border-gray-300 px-4 py-2 text-center font-medium text-gray-700 hover:bg-gray-100 transition"
          >
            Register
          </Link>
        </div>
      </div>
    </div>
  )
}

