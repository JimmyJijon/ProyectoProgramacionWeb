import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
  const token = localStorage.getItem('token'); // Verifica si hay un token almacenado

  return token ? children : <Navigate to="/" />; // Si hay token, muestra la página; si no, redirige al login
};

export default ProtectedRoute; 
