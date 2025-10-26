import { useEffect, useState } from "react";

function App() {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/users/2")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Error en la respuesta del backend");
        }
        return res.json();
      })
      .then((data) => {
        setUser(data); // { email: "...", username: "..." }
      })
      .catch((err) => {
        setError(err.message);
      });
  }, []);

  return (
    <div style={{ fontFamily: "sans-serif", padding: "2rem" }}>
      <h1>Demo Frontend + Backend</h1>

      {error && <p style={{ color: "red" }}>Error: {error}</p>}

      {!user && !error && <p>Cargando...</p>}

      {user && (
        <div style={{
          border: "1px solid #ccc",
          borderRadius: "8px",
          padding: "1rem",
          maxWidth: "300px"
        }}>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Username:</strong> {user.username}</p>
        </div>
      )}
    </div>
  );
}

export default App;
