import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Attempting login with username:", username, "and password:", password);

    try {
      // Attempt login
      const response = await axios.post('http://localhost:8080/api/v2/login', {
        username,
        password,
      });
      console.log("Login response:", response.data);

      // Extract and store the token
      const token = response.data.token;
      console.log("Token extracted from login response:", token);
      localStorage.setItem('token', token);

      // Extract estudiante_id from login response
      let studentId = response.data.estudiante_id;
      console.log("StudentId from login response:", studentId);

      // If estudiante_id is null or undefined, try fetching the full user profile
      if (studentId === null || studentId === undefined) {
        console.log("StudentId not found in login response, fetching profile...");
        try {
          const profileResponse = await axios.get('http://localhost:8080/api/v2/perfil', {
            headers: {
              'Authorization': token  // Using the token as-is (expected format: "token_...")
            }
          });
          console.log("Perfil response:", profileResponse.data);
          // Use destructuring and optional chaining to extract estudiante_id
          const { estudiante, estudiante_id } = profileResponse.data;
          const extractedId = estudiante?.id ?? estudiante_id;
          if (extractedId) {
            studentId = extractedId;
            console.log("StudentId extracted using destructuring and optional chaining:", studentId);
          } else {
            console.log("No estudiante or estudiante_id field found in profile response. StudentId remains undefined.");
          }
        } catch (profileError) {
          console.error("Error fetching user profile:", profileError);
        }
      }
      
      if (studentId !== null && studentId !== undefined) {
        console.log("Final studentId to be stored:", studentId);
        localStorage.setItem('estudianteId', String(studentId));
        navigate('/home');
      } else {
        console.error("El usuario no tiene un perfil de estudiante asociado.");
        setError("El usuario no tiene un perfil de estudiante asociado.");
      }
    } catch (err) {
      console.error("Login error:", err);
      setError('Credenciales incorrectas');
    }
  };

  return (
    <div
      className="containerlog"
      style={{
        backgroundImage: "url('/fondoLogin.png')",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center center",
        width: "100vw",
        height: "100vh",
        position: "fixed",
        top: 0,
        left: 0,
      }}
    >
      <div className="login-container">
        <h2>Ingresa al SAT</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="username">Usuario:</label>
            <input
              id="username"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Contraseña:</label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit">Acceder</button>
        </form>
      </div>
    </div>
  );
};

export default Login;
