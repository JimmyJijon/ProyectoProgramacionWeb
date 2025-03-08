import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";
import { useNavigate } from "react-router-dom";


const MateriasDisponibles = ({ estudianteId: propEstudianteId }) => {
    const [materias, setMaterias] = useState([]);
    const [estudianteId, setEstudianteId] = useState(null);
    const [materiasInscritas, setMateriasInscritas] = useState([]);
    const toast = useRef(null);
    const navigate = useNavigate();

 
    useEffect(() => {
        const storedEstudianteId = localStorage.getItem("estudianteId");

        console.log("Prop estudianteId recibido:", propEstudianteId);
        console.log("EstudianteId almacenado en localStorage:", storedEstudianteId);

        if (propEstudianteId) {
            setEstudianteId(propEstudianteId);
        } else if (storedEstudianteId) {
            setEstudianteId(parseInt(storedEstudianteId, 10));
        }
    }, [propEstudianteId]);

 
    useEffect(() => {
        const fetchMateriasConHorarios = async () => {
            try {
                const responseMaterias = await axios.get("http://localhost:8080/api/v1/materias");
                const materiasData = responseMaterias.data;

                const materiasConHorarios = await Promise.all(
                    materiasData.map(async (materia) => {
                        try {
                            const responseHorarios = await axios.get(
                                `http://localhost:8080/api/v1/horarios/materia/${materia.id}`
                            );
                            const horarios = responseHorarios.data;
                            
                            const horariosFormateados = horarios
                                .map((h) => `${h.diaSemana} ${h.horaInicio} - ${h.horaFin}`)
                                .join(", ");

                            return {
                                ...materia,
                                profesor: `${materia.profesor.nombre} ${materia.profesor.apellido}`,
                                horario: horariosFormateados || "Sin horario",
                            };
                        } catch (error) {
                            console.error(`Error al obtener horarios para materia ${materia.id}`, error);
                            return {
                                ...materia,
                                profesor: `${materia.profesor.nombre} ${materia.profesor.apellido}`,
                                horario: "Sin horario",
                            };
                        }
                    })
                );

                setMaterias(materiasConHorarios);
            } catch (error) {
                console.error("Error al obtener materias:", error);
            }
        };

        fetchMateriasConHorarios();
    }, []);

 
    useEffect(() => {
        if (estudianteId) {
            axios.get(`http://localhost:8080/api/v1/estudiante-materia/${estudianteId}`)
                .then((response) => {
                    const inscritas = response.data.map((materia) => materia.materia.id);
                    setMateriasInscritas(inscritas);
                })
                .catch((error) => console.error("Error obteniendo materias inscritas:", error));
        }
    }, [estudianteId]);

   
    const inscribirEnMateria = async (materiaId) => {
        if (!estudianteId) {
            toast.current.show({
                severity: "warn",
                summary: "Error",
                detail: "No se encontró un estudiante válido",
                life: 3000,
            });
            return;
        }

        if (materiasInscritas.includes(materiaId)) {
            toast.current.show({
                severity: "info",
                summary: "Ya estás inscrito",
                detail: "Ya estás inscrito en esta materia",
                life: 3000,
            });
            return;
        }

        const periodoAcademico = "2024-1"; 

        try {
            await axios.post(`http://localhost:8080/api/v1/estudiante-materia/inscribir`, {
                estudianteId,
                materiaId,
                periodoAcademico
            });

            setMateriasInscritas((prev) => [...prev, materiaId]);

            toast.current.show({
                severity: "success",
                summary: "Inscripción exitosa",
                detail: "Te has inscrito correctamente en la materia",
                life: 3000,
            });

        } catch (error) {
            console.error("Error al inscribirse:", error);
            toast.current.show({
                severity: "error",
                summary: "Error",
                detail: "No se pudo completar la inscripción",
                life: 3000,
            });
        }
    };

   
    const inscribirTemplate = (rowData) => {
        const inscrito = materiasInscritas.includes(rowData.id);
        return (
            <Button
                label={inscrito ? "Inscrito" : "Inscribir"}
                icon={inscrito ? "pi pi-check" : "pi pi-plus"}
                className={`p-button-${inscrito ? "secondary" : "success"}`}
                onClick={() => !inscrito && inscribirEnMateria(rowData.id)}
                disabled={inscrito}
            />
        );
    };

    return (
        <div className="cardMat">
            <div className="mb-4">
                <Button 
                  label="Atrás" 
                  icon="pi pi-arrow-left" 
                  className="p-button-text" 
                  onClick={() => navigate(-1)}
                />
            </div>
            <Toast ref={toast} />
            <h2>Materias Disponibles</h2>
            <DataTable
                value={materias}
                scrollable
                scrollHeight="600px"
                paginator
                rows={10}
                rowsPerPageOptions={[10, 20, 50]}
                style={{ minWidth: "50rem" }}
            >
                <Column field="id" header="ID"></Column>
                <Column field="nombre" header="Materia"></Column>
                <Column field="codigo" header="Código"></Column>
                <Column field="creditos" header="Créditos"></Column>
                <Column field="profesor" header="Profesor"></Column>
                <Column field="horario" header="Horario"></Column>
                <Column body={inscribirTemplate} header="Acción"></Column>
            </DataTable>
        </div>
    );
};

export default MateriasDisponibles;


