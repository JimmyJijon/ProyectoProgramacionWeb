// src/components/PaginaMateriasComponent.jsx

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import TablaMateriasComponent from './TablaMateriasComponent';

/**
 * Vista que obtiene y muestra la lista de materias en las que el estudiante está inscrito.
 * Se comunica con el backend en http://localhost:8080/api/v1/estudiante-materias.
 */
export default function PaginaMateriasComponent() {
  const [materias, setMaterias] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Se asume que el backend corre en el puerto 8080 y expone este endpoint
        const response = await fetch('http://localhost:8080/api/v1/estudiante-materias');
        if (!response.ok) {
          throw new Error(`Error del servidor: ${response.status}`);
        }
        const data = await response.json();
        setMaterias(data);
      } catch (error) {
        console.error('Error al obtener las materias:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h2>Materias de Estudiante</h2>
      <TablaMateriasComponent materias={materias} />
      <Link to="/home" className="p-button p-component p-button-text p-button-primary">
        Volver a Inicio
      </Link>
    </div>
  );
}
