import React, { useEffect, useState } from "react";
import EstudianteCard from "./EstudianteCard"; // Importa el componente


//ESTE ES EL COMPONENTE QUE MOSTRARA LA PANTALLA PRINCIPAL "HOME"
function HomePageComponent() {

  const botones = [
    { icono: "/actividad-extracurricular.png", texto: "Actividad extracurricular" },
    { icono: "/aulavirtual.png", texto: "Aula virtual" },
    { icono: "/bolsalaboral.png", texto: "Bolsa laboral" },
    { icono: "/botondepagos.png", texto: "Botón de pagos" },
    { icono: "/calendario.png", texto: "Calendario de actividades de la institución"},
    { icono: "/facturasemitidas.png", texto: "Facturas emitidas"},
    { icono: "", texto: "Ficha socioeconómica"},
    { icono: "", texto: "Hoja de vida del estudiante"},
    { icono: "", texto: "Matriculación online"},
    { icono: "", texto: "Mensajeria interna"},
    { icono: "", texto: "Cronograma de mis materias y profesores"},
    { icono: "", texto: "Datos personales y familiares medicos"},
    { icono: "", texto: "Mis rubros y deuda"},
    { icono: "", texto: "Ver mis horarios de clases programados para el periodo"},
    { icono: "", texto: "Notas y asistencia en el periodo lectivo"},
    { icono: "", texto: "Resumen de mis planes contratados"},
    { icono: "", texto: "Notas obtenidas en la carrera"},
    { icono: "", texto: "Solicitudes de certificados online"},
    { icono: "", texto: "Videos de inducción institucional"},
    { icono: "", texto: "Vinculación con la comunidad"},

    // ... y así con los demás
  ];

  return (
    <div className="homePageComponent">
      
      <div className='perfilComponent'>
      </div>
      
      <div className='EstContainer'>
        <div className='FotoDelEst'>
        <img className= "imgEst"src="/userUnknown.webp"></img>
        </div>
        <EstudianteCard estudianteId={2}/>
      </div>

      {/* Contenedor para los “botones” */}
      <div className="botones-container">
        {botones.map((item, index) => (
          <div key={index} className="boton-item">
            <img src={item.icono} alt={item.texto} className="icono-boton" />
            <span>{item.texto}</span>
          </div>
        ))}
      </div>

    </div>
  );
}

export default HomePageComponent;
