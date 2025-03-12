import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";

const MateriasDisponibles = ({ estudianteId: propEstudianteId }) => {
    const [materias, setMaterias] = useState([]);
    const [estudianteId, setEstudianteId] = useState(null);
    const [materiasInscritas, setMateriasInscritas] = useState(new Set());
    const [loading, setLoading] = useState(null);
    const [totalMateriasInscritas, setTotalMateriasInscritas] = useState(0);
    const toast = useRef(null);
    const navigate = useNavigate();

    const LIMITE_MATERIAS = 5; //Maximo de materias permitidas

    useEffect(() => {
        const storedEstudianteId = localStorage.getItem("estudianteId");
        if (propEstudianteId) {
            setEstudianteId(propEstudianteId);
        } else if (storedEstudianteId) {
            setEstudianteId(parseInt(storedEstudianteId, 10));
        }
    }, [propEstudianteId]);

    useEffect(() => {
        if (!estudianteId) return;

        axios.get(`http://localhost:8080/api/v1/materia-estudiante/${estudianteId}`)
            .then((response) => {
                const inscritasIds = new Set(response.data.map((item) => item.materia.id));
                setMateriasInscritas(inscritasIds);
                setTotalMateriasInscritas(inscritasIds.size);
            })
            .catch((error) => console.error("Error obteniendo materias inscritas:", error));
    }, [estudianteId]);

    useEffect(() => {
        if (!estudianteId) return;

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

                            const aulasFormateadas = horarios
                                .map((h) => `${h.aula.nombre} (${h.aula.edificio})`)
                                .join(", ");

                            return {
                                ...materia,
                                profesor: `${materia.profesor.nombre} ${materia.profesor.apellido}`,
                                horario: horariosFormateados || "Sin horario",
                                aula: aulasFormateadas || "Sin aula",
                            };
                        } catch (error) {
                            console.error(`Error al obtener horarios para materia ${materia.id}`, error);
                            return {
                                ...materia,
                                profesor: `${materia.profesor.nombre} ${materia.profesor.apellido}`,
                                horario: "Sin horario",
                                aula: "Sin aula",
                            };
                        }
                    })
                );

                const materiasFiltradas = materiasConHorarios.filter((materia) => !materiasInscritas.has(materia.id));

                setMaterias(materiasFiltradas);
            } catch (error) {
                console.error("Error al obtener materias:", error);
            }
        };

        fetchMateriasConHorarios();
    }, [estudianteId, materiasInscritas]);

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

        if (totalMateriasInscritas >= LIMITE_MATERIAS) {
            toast.current.show({
                severity: "error",
                summary: "Límite alcanzado",
                detail: "Ya estás inscrito en el máximo de 5 materias por módulo",
                life: 4000,
            });
            return;
        }

        setLoading(materiaId);

        try {
            const formData = new URLSearchParams();
            formData.append("estudianteId", estudianteId);
            formData.append("materiaId", materiaId);

            await axios.post(`http://localhost:8080/api/v1/estudiante-materia/inscribir`,
                formData.toString(),
                { headers: { "Content-Type": "application/x-www-form-urlencoded" } }
            );

            setMateriasInscritas((prev) => new Set([...prev, materiaId]));
            setTotalMateriasInscritas((prev) => prev + 1);
            setMaterias((prevMaterias) => prevMaterias.filter((materia) => materia.id !== materiaId));

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
        } finally {
            setLoading(null);
        }
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
                <Column field="nombre" header="Materia"></Column>
                <Column field="codigo" header="Código"></Column>
                <Column field="creditos" header="Créditos"></Column>
                <Column field="profesor" header="Profesor"></Column>
                <Column field="horario" header="Horario"></Column>
                <Column field="aula" header="Aula"></Column> 
                <Column body={(rowData) => {
                    const isDisabled = loading === rowData.id || totalMateriasInscritas >= LIMITE_MATERIAS;
                    return (
                        <Button
                            label={isDisabled ? "Max. Materias" : "Inscribir"}
                            icon={isDisabled ? "pi pi-ban" : "pi pi-plus"}
                            className={isDisabled ? "p-button-outlined p-button-secondary" : "p-button-danger"}
                            onClick={() => inscribirEnMateria(rowData.id)}
                            disabled={isDisabled}
                        />
                    );
                }} header="Acción" />
            </DataTable>
        </div>
    );
};

export default MateriasDisponibles;



