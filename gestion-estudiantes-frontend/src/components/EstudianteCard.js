import React, { useEffect, useState } from "react";
import EstudianteService from "../services/EstudianteService";

const EstudianteCard = ({ estudianteId }) => {
  const [estudiante, setEstudiante] = useState(null);

  useEffect(() => {
    EstudianteService.getEstudianteById(estudianteId)
      .then((response) => {
        setEstudiante(response.data);
      })
      .catch((error) => {
        console.error("Error al obtener los datos del estudiante:", error);
      });
  }, [estudianteId]);

  if (!estudiante) {
    return <p className="text-center text-secondary">Cargando...</p>;
  }

  return (
    
    <div className="container d-flex flex-column align-items-center p-2">
  <div className="card shadow p-2 text-center text-dark" style={{ maxWidth: "300px" }}>
    <h3 className="card-title fw-bold text-dark" style={{ fontSize: "1.2rem" }}>
      {estudiante.nombre} {estudiante.apellido}
    </h3>
    <p className="text-dark" style={{ fontSize: "0.9rem" }}>
      <strong>No. Identificación:</strong><br /> <span className="text-dark">{estudiante.cedula}</span>
    </p>
    <p className="text-dark" style={{ fontSize: "0.9rem" }}>
    <strong>Correo Institucional:</strong><br /> <span className="fw-normal">{estudiante.email_institucional}</span>
    </p>
    <p className="text-dark" style={{ fontSize: "0.9rem" }}>
      <strong>Correo Personal:</strong><br /> <span className="fw-normal">{estudiante.email_personal}</span>
    </p>
    <div className="my-1">
      <span className="badge bg-success p-1" style={{ fontSize: "0.8rem" }}>
        Carrera: {estudiante.carrera?.nombre || "No asignada"}
      </span>
    </div>
    <button className="btn btn-primary btn-sm mt-2">
      🎓 Carnet
    </button>
  </div>
</div>
    
  );
};

export default EstudianteCard;