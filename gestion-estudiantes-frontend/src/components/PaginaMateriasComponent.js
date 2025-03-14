// src/components/PaginaMateriasComponent.jsx

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { Tooltip } from 'primereact/tooltip';
import TablaMateriasComponent from './TablaMateriasComponent';

/**
 * Vista que obtiene y muestra la lista de materias en las que el estudiante está inscrito.
 * Se comunica con el backend en http://localhost:8080/api/v1/estudiante-materias.
 */
export default function PaginaMateriasComponent() {
  const [materias, setMaterias] = useState([]);
  const [estudiante, setEstudiante] = useState(null);
  const estudianteId = 1000; // Reemplaza con el ID del estudiante almacenado en la sesión

  useEffect(() => {
    const fetchEstudiante = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/v1/estudiantes/${estudianteId}`);
        if (!response.ok) {
          throw new Error(`Error del servidor: ${response.status}`);
        }
        const data = await response.json();
        setEstudiante(data);
      } catch (error) {
        console.error('Error al obtener los datos del estudiante:', error);
      }
    };

    fetchEstudiante();
  }, [estudianteId]);

  useEffect(() => {
    if (estudiante) {
      const fetchData = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/v1/estudiante-materias?estudiante_id=${estudiante.id}`);
          if (!response.ok) {
            throw new Error(`Error del servidor: ${response.status}`);
          }
          const data = await response.json();
          console.log(data); // Verificar los datos aquí
          setMaterias(data);
        } catch (error) {
          console.error('Error al obtener las materias:', error);
        }
      };

      fetchData();
    }
  }, [estudiante]);

  if (!estudiante) {
    return <div>Cargando...</div>;
  }

  const estudianteNombre = estudiante.nombre || 'Nombre no disponible';

  return (
    <div>
      <Tooltip target=".estudiante-nombre" content={`ID del Estudiante: ${estudiante.id}`} />
      <h2 className="estudiante-nombre" style={{ marginBottom: '1em' }}>
        Materias de Estudiante: {estudianteNombre}
      </h2>
      <Card title="Materias de Estudiante" style={{ marginBottom: '2em' }}>
        <TablaMateriasComponent materias={materias} />
      </Card>
      <Link to="/home">
        <Button label="Volver a Inicio" icon="pi pi-home" className="p-button-text p-button-primary" />
      </Link>
    </div>
  );
}
