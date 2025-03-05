import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; 
import axios from "axios";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";

function CronogramaComponent() {
    const { estudianteId } = useParams(); 
    const [materias, setMaterias] = useState([]);

    console.log("Estudiante ID recibido en Cronograma:", estudianteId);

    useEffect(() => {
        if (!estudianteId) {
            console.error("Error: estudianteId es undefined o null.");
            return;
        }

        axios
            .get(`http://localhost:8080/api/v1/materia-estudiante/${estudianteId}`)
            .then((response) => {
                if (response.status === 204) {
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
            });
    }, [estudianteId]);

    return (
        <div className="CronogramaComponent">
            <Button label="Ir al horario" className="buttonHorario" />
            <h2>Cronograma del estudiante {estudianteId}</h2> {}
            <DataTable value={materias} tableStyle={{ minWidth: "50rem" }}>
                <Column field="nombremat" header="Asignatura"></Column>
                <Column field="credito" header="Horas Creditos"></Column>
                <Column field="inicio" header="Inicia"></Column>
                <Column field="termina" header="Termina"></Column>
                <Column field="profesor" header="Profesor(es)"></Column>
                <Column field="horarios" header="Horarios"></Column>
                <Column field="aula" header="Aula"></Column>
            </DataTable>
        </div>
    );
}

export default CronogramaComponent;
