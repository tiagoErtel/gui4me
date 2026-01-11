const API_URL = "http://localhost:8080/api/auth"

async function getCurrentUser() {
  const res = await fetch(`${API_URL}/me`, {
    credentials: "include",
  })

  if (!res.ok) return null
  return res.json()
}

async function login(email, password) {
  const res = await fetch(`${API_URL}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify({ email, password }),
  })

  if (!res.ok) {
    throw new Error("Login failed")
  }

  return getCurrentUser()
}

async function logout() {
  await fetch(`${API_URL}/logout`, {
    method: "POST",
    credentials: "include",
  })
}

export default { login, logout, getCurrentUser }

