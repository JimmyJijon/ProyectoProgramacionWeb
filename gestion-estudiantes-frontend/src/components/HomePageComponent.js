import React from "react";
import EstudianteCard from "./EstudianteCard"; 
import { Link } from 'react-router-dom'; // Asegúrate de importar "Link" en tus botones como "/vidinduccion"

//ESTE ES EL COMPONENTE QUE MOSTRARA LA PANTALLA PRINCIPAL "HOME"
function HomePageComponent() {

  const botones = [
    { icono: "/actividad-extracurricular.png", texto: "Actividad extracurricular" },
    { icono: "/aulavirtual.png", texto: "Aula virtual", link: "https://tesonline.academicok.com/login/index.php" },
    { icono: "/bolsalaboral.png", texto: "Bolsa laboral" },
    { icono: "/botondepagos.png", texto: "Botón de pagos" },
    { icono: "/calendario.png", texto: "Calendario de actividades de la institución" },
    { icono: "/evaluaciondemisprofesores.png", texto: "Evaluación del alumno a sus docentes" },
    { icono: "/facturas.png", texto: "Facturas emitidas" },
    { icono: "/fichasocioeconomica.png", texto: "Ficha socioeconómica" },
    { icono: "hojadevida.png", texto: "Hoja de vida del estudiante" },
    { icono: "matriculaciononline.png", texto: "Matriculación online" },
    { icono: "mensajeriainterna.png", texto: "Mensajeria interna" },
    { icono: "micronograma.png", texto: "Cronograma de mis materias y profesores" },
    { icono: "mifichamedica.png", texto: "Datos personales y familiares médicos" },
    { icono: "mimalla.png", texto: "Ver mi cumplimiento de malla" },
    { icono: "misfinanzas.png", texto: "Mis rubros y deuda" },
    { icono: "mishorarios.png", texto: "Ver mis horarios de clases programados para el periodo" },
    { icono: "mismaterias.png", texto: "Notas y asistencia en el periodo lectivo" },
    { icono: "misplanes.png", texto: "Resumen de mis planes contratados" },
    { icono: "recordacademico.png", texto: "Notas obtenidas en la carrera" },
    { icono: "solicituddecertificados.png", texto: "Solicitudes de certificados online" },
    { icono: "videosdeinduccion.png", texto: "Videos de inducción institucional", link: "/vidinduccion" },
    { icono: "vinculacioncomunidad.png", texto: "Vinculación con la comunidad" },
  ];

  return (
    <div className="homePageComponent">
      <div className="perfilComponent"></div>

      <div className="EstContainer">
        <div className="FotoDelEst">
          <img className="imgEst" src="/userUnknown.webp" alt="Perfil Estudiante" />
        </div>
        <EstudianteCard estudianteId={9} />
      </div>

      {/* Contenedor para los botones */}
      <div className="botones-container">
        {botones.map((item, index) => (
          item.link ? (
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
              <Link 
                key={index} 
                to={item.link} 
                className="boton-item"
              >
                <img src={item.icono} alt={item.texto} className="icono-boton" />
                <span>{item.texto}</span>
              </Link>
            )
          ) : (
            <div key={index} className="boton-item">
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
