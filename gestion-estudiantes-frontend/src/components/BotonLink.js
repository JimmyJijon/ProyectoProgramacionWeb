// BotonLink.js
import React from 'react';
import { Link } from 'react-router-dom';

function BotonLink({ icono, texto, link }) {
  return (
    <Link to={link} className="boton-item">
      <img src={icono} alt={texto} className="icono-boton" />
      <span>{texto}</span>
    </Link>
  );
}

export default BotonLink;
