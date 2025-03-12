import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import EstudianteCard from "./EstudianteCard";
import FichaMedicaDialog from "./FichaMedicaDialog";
import EstudianteService from "../services/EstudianteService";

function HomePageComponent() {
    const [estudianteId, setEstudianteId] = useState(null);
    const [estudiante, setEstudiante] = useState(null);

    // Obtener estudianteId desde localStorage
    useEffect(() => {
        const storedEstudianteId = localStorage.getItem('estudianteId'); 
        if (storedEstudianteId) {
            setEstudianteId(parseInt(storedEstudianteId, 10));
        } else {
            console.error("No se encontró el estudianteId en el localStorage.");
        }
    }, []);

    // Obtener datos del estudiante
    useEffect(() => {
        if (estudianteId) {
            EstudianteService.getEstudianteById(estudianteId)
                .then((response) => {
                    setEstudiante(response.data);
                })
                .catch((error) => {
                    console.error("Error al obtener los datos del estudiante:", error);
                });
        }
    }, [estudianteId]);

    // Lista de botones del menú
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
        { icono: "mensajeriainterna.png", texto: "Mensajeria interna" },
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
        {
            icono: "mimalla.png",
            texto: "Ver mi cumplimiento de malla",
            link: "/malla",
          }
    ];

    return (

        <div>
            {/* Mensaje de Bienvenida con estilos en línea */}
            <div style={{
                textAlign: "center",
                padding: "10px 15px", 
                marginTop: "70px",
                background: "linear-gradient(to right, #ff416c, #ff4b2b)", 
                color: "white",
                borderRadius: "10px",
                boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.2)",
                fontSize: "1.5rem",
                fontWeight: "bold",
                animation: "fadeIn 0.8s ease-in-out",
                width: "2550px", 
                maxWidth: "80%",  
            }}>
                {estudiante ? (
                    <>
                        ¡Bienvenido, <span style={{ fontWeight: "800", textTransform: "capitalize" }}>{estudiante.nombre} {estudiante.apellido}</span>!  
                        <span style={{ fontSize: "1.8rem", marginLeft: "8px" }}></span>
                    </>
                ) : (
                    "Cargando..."
                )}
            </div>
        <div className="homePageComponent">
            <div className="perfilComponent"></div>

            <div className="EstContainer">
                <div style={{ position: "absolute", top: "630px", left: "49px" }}>
                    {estudianteId && <FichaMedicaDialog estudianteId={estudianteId} />}
                </div>
                <div className="FotoDelEst">
                    <img className="imgEst" src="/userUnknown.webp" alt="Perfil Estudiante" />
                </div>
                {estudianteId ? <EstudianteCard estudianteId={estudianteId} /> : <p>Cargando información...</p>}
            </div>

            {/* Menú de botones */}
            <div className="botones-container">
                {botones.map((item, index) => (
                    item.link ? (
                        item.link.startsWith("http") ? (
                            <a key={index} href={item.link} target="_blank" rel="noopener noreferrer" className="boton-item">
                                <img src={item.icono} alt={item.texto} className="icono-boton" />
                                <span>{item.texto}</span>
                            </a>
                        ) : (
                            <Link key={index} to={item.link} className={`boton-item ${!estudianteId ? "disabled" : ""}`}>
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
        </div>
    );
}

export default HomePageComponent;
