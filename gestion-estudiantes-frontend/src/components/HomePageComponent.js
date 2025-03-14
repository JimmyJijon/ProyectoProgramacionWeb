import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import EstudianteCard from "./EstudianteCard";

function HomePageComponent() {
  const [estudianteId, setEstudianteId] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedEstudianteId = localStorage.getItem("estudianteId");
    console.log("Stored estudianteId:", storedEstudianteId);
    if (storedEstudianteId) {
      const numericId = parseInt(storedEstudianteId, 10);
      if (!isNaN(numericId)) {
        setEstudianteId(numericId);
      } else {
        console.error("El estudianteId en localStorage no es un número válido.");
        navigate('/login');
      }
    } else {
      console.error("No se encontró el estudianteId en el localStorage.");
      navigate('/login');
    }
  }, [navigate]);
  
  const botones = [
    { icono: "/actividad-extracurricular.png", texto: "Actividad extracurricular" },
    { icono: "/aulavirtual.png", texto: "Aula virtual", link: "https://tesonline.academicok.com/login/index.php" },
    { icono: "/bolsalaboral.png", texto: "Bolsa laboral" },
    { icono: "/botondepagos.png", texto: "Botón de pagos" },
    { icono: "/calendario.png", texto: "Calendario de actividades de la institución", link: "/calendario" },
    { icono: "/evaluaciondemisprofesores.png", texto: "Evaluación del alumno a sus docentes" },
    { icono: "/facturas.png", texto: "Facturas emitidas" },
    { icono: "/fichasocioeconomica.png", texto: "Ficha socioeconómica" },
    { icono: "hojadevida.png", texto: "Hoja de vida del estudiante" },
    { icono: "matriculaciononline.png", texto: "Matriculación online", link: estudianteId ? `/matriculacion/${estudianteId}` : "#" },
    { icono: "micronograma.png", texto: "Cronograma de mis materias y profesores", link: estudianteId ? `/cronograma/${estudianteId}` : "#" },
    { icono: "mismaterias.png", texto: "Notas y asistencia en el periodo lectivo", link: "/pagina-materias" },
    { icono: "videosdeinduccion.png", texto: "Videos de inducción institucional", link: "/vidinduccion" },
    { icono: "añadirestudiante.png", texto: "Añadir estudiante", link: "/estudiantes" },
  ];

  return (
    <div className="homePageComponent">
      <div className="perfilComponent"></div>

      <div className="main-content">
        <div className="EstContainer">
          <div className="FotoDelEst">
            <img className="imgEst" src="/userUnknown.webp" alt="Perfil Estudiante" />
          </div>
          {estudianteId ? <EstudianteCard estudianteId={estudianteId} /> : <p>Cargando información...</p>}
        </div>

        <div className="botones-container">
          {botones.map((item, index) =>
            item.link ? (
              item.link.startsWith("http") ? (
                <a key={index} href={item.link} target="_blank" rel="noopener noreferrer" className="boton-item">
                  <img src={item.icono} alt={item.texto} className="icono-boton" />
                  <span>{item.texto}</span>
                </a>
              ) : (
                <Link key={index} to={item.link} className="boton-item">
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
          )}
        </div>
      </div>
    </div>
  );
}

export default HomePageComponent;
