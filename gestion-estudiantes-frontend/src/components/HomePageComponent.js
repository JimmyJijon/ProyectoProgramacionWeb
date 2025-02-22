import React, { useEffect, useState } from "react";
import EstudianteCard from "./EstudianteCard"; // Importa el componente
import { useNavigate } from "react-router-dom";


//ESTE ES EL COMPONENTE QUE MOSTRARA LA PANTALLA PRINCIPAL "HOME"
function HomePageComponent() {
  
  const navigate = useNavigate(); // Hook para navegar
  // Si link empieza con "http", lo abrirá en otra pestaña,  con "/", usará navigate() para cambiar la página
  // Agregar componentes despues de "/"
  const botones = [
    { icono: "/actividad-extracurricular.png", texto: "Actividad extracurricular", link: "/"},
    { icono: "/aulavirtual.png", texto: "Aula virtual", link: "https://tesonline.academicok.com/login/index.php"},
    { icono: "/bolsalaboral.png", texto: "Bolsa laboral", link: "/"},
    { icono: "/botondepagos.png", texto: "Botón de pagos", link: "/" },
    { icono: "/calendario.png", texto: "Calendario de actividades de la institución", link: "/calendario"},
    { icono: "/evaluaciondemisprofesores.png", texto: "Evaluación del alumno a sus docentes", link: "/"},
    { icono: "/facturas.png", texto: "Facturas emitidas", link: "/"},
    { icono: "/fichasocioeconomica.png", texto: "Ficha socioeconómica", link: "/"},
    { icono: "hojadevida.png", texto: "Hoja de vida del estudiante", link: "/"},
    { icono: "matriculaciononline.png", texto: "Matriculación online", link: "/"},
    { icono: "mensajeriainterna.png", texto: "Mensajeria interna", link: "/"},
    { icono: "micronograma.png", texto: "Cronograma de mis materias y profesores", link: "/"},
    { icono: "mifichamedica.png", texto: "Datos personales y familiares medicos", link: "/"},
    { icono: "mimalla.png", texto: "Ver mi cumplimiento de malla", link: "/"},
    { icono: "misfinanzas.png", texto: "Mis rubros y deuda", link: "/"},
    { icono: "mishorarios.png", texto: "Ver mis horarios de clases programados para el periodo", link: "/"},
    { icono: "mismaterias.png", texto: "Notas y asistencia en el periodo lectivo", link: "/"},
    { icono: "misplanes.png", texto: "Resumen de mis planes contratados", link: "/"},
    { icono: "recordacademico.png", texto: "Notas obtenidas en la carrera", link: "/"},
    { icono: "solicituddecertificados.png", texto: "Solicitudes de certificados online", link: "/"},
    { icono: "videosdeinduccion.png", texto: "Videos de inducción institucional", link: "/"},
    { icono: "vinculacioncomunidad.png", texto: "Vinculación con la comunidad", link: "/"},

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
        <EstudianteCard estudianteId={9}/>
      </div>

      {/* Contenedor para los “botones” */}
      <div className="botones-container">
        {botones.map((item, index) => (
          item.link.startsWith("http") ? (
            <a 
              key={index} 
              href={item.link} 
              target="_blank" 
              rel="noopener noreferrer" 
              className="boton-item"
            >
              <img src={item.icono} alt={item.texto} className="icono-boton" />
              <span>{item.texto}</span>
            </a>
          ) : (
            <div 
              key={index} 
              className="boton-item" 
              onClick={() => navigate(item.link)} 
              style={{ cursor: "pointer" }}
            >
              <img src={item.icono} alt={item.texto} className="icono-boton" />
              <span>{item.texto}</span>
            </div>
          )
        ))}
      </div>
    </div>
  );
}

export default HomePageComponent;
