import { useAuth } from "../context/UseAuth"
import { useNavigate } from "react-router-dom"

export default function Home() {
    const { user, logout } = useAuth()
    const navigate = useNavigate()

    const handleLogout = async () => {
        await logout()
        navigate("/") // redireciona para landing page
    }

    const cards = [
        { title: "🔍 Search Products", desc: "Quickly find any product you need.", href: "/product/search" },
        { title: "🧾 Register Purchase", desc: "Record your recent shopping.", href: "/invoice/register" },
        { title: "📄 List Invoices", desc: "View and manage your invoice history.", href: "/invoice/list" },
        { title: "🛒 Shopping Lists", desc: "Create and track shopping plans.", href: "/shopping-list" },
        { title: "📊 Reports", desc: "Analyze your purchases and patterns.", href: "/report/dashboard" },
    ]

    return (
        <div className="min-h-screen bg-gray-100 p-8">
            <header className="flex justify-between items-center mb-8">
                <h1 className="text-2xl font-bold">Welcome, {user?.username || "User"}!</h1>
                <button
                    onClick={handleLogout}
                    className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition"
                >
                    Logout
                </button>
            </header>

            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                {cards.map((card) => (
                    <a
                        key={card.href}
                        href={card.href}
                        className="bg-white p-6 rounded-xl shadow hover:shadow-lg transition flex flex-col gap-2"
                    >
                        <h2 className="text-xl font-bold">{card.title}</h2>
                        <p className="text-gray-600">{card.desc}</p>
                    </a>
                ))}
            </div>
        </div>
    )
}
