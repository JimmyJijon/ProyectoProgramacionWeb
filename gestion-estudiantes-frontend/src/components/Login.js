import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import '../App.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/v2/login', {
        username,
        password
      });
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('estudianteId', response.data.estudiante_id); // Almacenar el estudianteId

      navigate('/home');
    } catch (err) {
      setError('Credenciales incorrectas');
    }
  };

  return (
    <div className="containerlog" style={{
      backgroundImage: "url('/fondoLogin.png')", 
    backgroundSize: "cover",  
    backgroundRepeat: "no-repeat", 
    backgroundPosition: "center center",
    width: "100vw", 
    height: "100vh", 
    position: "fixed",
    top: 0,
    left: 0,
    }}>
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