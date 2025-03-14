import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; 
import axios from "axios";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { useNavigate } from "react-router-dom";

function CronogramaComponent() {
    const { estudianteId } = useParams(); 
    const [materias, setMaterias] = useState([]);
    const [estudianteNombre, setEstudianteNombre] = useState(""); //nuevo estado para guardar el nombre
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    console.log("Estudiante ID recibido en Cronograma:", estudianteId);

    useEffect(() => {
        if (!estudianteId) {
            console.error("Error: estudianteId es undefined o null.");
            setLoading(false);
            return;
        }

        const idNumerico = parseInt(estudianteId, 10); //convertir a número
        if (isNaN(idNumerico)) {
            console.error("Error: estudianteId no es un número válido.");
            setLoading(false);
            return;
        }

        //peticion para obtener el nombre del estudiante
        axios
            .get(`http://localhost:8080/api/v1/estudiantes/${idNumerico}`)
            .then((response) => {
                if (response.data) {
                    setEstudianteNombre(`${response.data.nombre} ${response.data.apellido}`);
                } else {
                    console.warn("No se encontró información del estudiante.");
                    setEstudianteNombre("Estudiante Desconocido");
                }
            })
            .catch((error) => {
                console.error("Error obteniendo el nombre del estudiante:", error);
                setEstudianteNombre("Error al obtener datos");
            });

        // p eticion para obtener el cronograma de materias
        axios
            .get(`http://localhost:8080/api/v1/materia-estudiante/${idNumerico}`)
            .then((response) => {
                if (response.status === 204 || !response.data.length) {
                    console.warn("No hay materias asignadas para este estudiante.");
                    setMaterias([]);
                } else {
                    const data = response.data.map((materia) => ({
                        codigomat: materia.materia.codigo,
                        nombremat: materia.materia.nombre,
                        inicio: materia.fechaInicio,
                        termina: materia.fechaFin,
                        estadomat: materia.estado,
                        credito: materia.materia.creditos,
                        profesor: `${materia.materia.profesor.nombre} ${materia.materia.profesor.apellido}`,
                        horarios: materia.materia.horarios.length > 0
                            ? materia.materia.horarios.map(h => `${h.diaSemana} (${h.horaInicio} - ${h.horaFin})`).join(", ")
                            : "Sin horario",
                        aula: materia.materia.horarios.length > 0 ? materia.materia.horarios[0].aula.nombre : "Sin aula",
                    }));

                    setMaterias(data);
                }
            })
            .catch((error) => {
                console.error("Error obteniendo las materias:", error);
            })
            .finally(() => {
                setLoading(false);
            });

    }, [estudianteId]);

    return (
        <div className="cronogramaprincipal">
            <div className="container mt-4 p-4 bg-white shadow-lg rounded border border-danger">
                
                {/* Titulo con el nombre del estudiante */}
                <div className="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2 border-danger">
                    <Button 
                        label="Atrás" 
                        icon="pi pi-arrow-left" 
                        className="p-button-text text-danger fw-bold"
                        onClick={() => navigate(-1)} 
                    />
                    <h2 className="text-dark fw-bold">
                         Cronograma de <span className="text-danger">{estudianteNombre}</span>
                    </h2>
                    <Button 
                        label="Ir al horario" 
                        className="p-button-raised p-button-danger"
                    />
                </div>

                {/* tb de materias */}
                <div className="table-responsive">
                    {loading ? (
                        <p className="text-center text-muted">Cargando cronograma...</p>
                    ) : materias.length === 0 ? (
                        <p className="text-center text-danger fw-bold">No hay materias asignadas.</p>
                    ) : (
                        <DataTable 
                            value={materias} 
                            className="p-datatable-striped p-datatable-hover custom-table"
                            paginator 
                            rows={5}
                        >
                            <Column field="nombremat" header="Asignatura" sortable />
                            <Column field="credito" header="Horas Créditos" sortable />
                            <Column field="inicio" header="Inicia" sortable />
                            <Column field="termina" header="Termina" sortable />
                            <Column field="profesor" header="Profesor(es)" sortable />
                            <Column field="horarios" header="Horarios" sortable />
                            <Column field="aula" header="Aula" sortable />
                        </DataTable>
                    )}
                </div>
            </div>

            {/* estilos color rojo*/}
            <style jsx>{`
                .custom-table .p-datatable-thead > tr {
                    background-color: #dc3545 !important;
                    color: white !important;
                }
                .custom-table .p-datatable-even {
                    background-color: rgb(245, 15, 15) !important;
                }
                .custom-table .p-datatable-odd {
                    background-color: #fff !important;
                }
            `}</style>
        </div>
    );
}

export default CronogramaComponent;

