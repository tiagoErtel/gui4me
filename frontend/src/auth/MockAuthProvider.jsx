import { createContext, useContext, useState } from "react"

const AuthContext = createContext(null)

export function MockAuthProvider({ children }) {
  const [user, setUser] = useState({ username: "Tiago" })

  const login = async () => setUser({ username: "Tiago" })
  const register = async () => setUser({ username: "Tiago" })
  const logout = async () => setUser(null)

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (!context) throw new Error("useAuth must be used inside MockAuthProvider")
  return context
}
