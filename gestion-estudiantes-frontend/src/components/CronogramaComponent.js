import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from 'primereact/button';

import React, { useEffect, useState } from "react";
import axios from "axios";


function CronogramaComponent() {
  const [materias, setMaterias] = useState([]);

  useEffect(() => {
   
    axios
      .get("http://localhost:8080/api/v1/materia-estudiante/210")
      .then((response) => {
        if (response.status === 204) {
          setMaterias([]); 
        } else {
          // Mapear los datos para que coincidan con las columnas de la tabla
          const data = response.data.map((materia) => ({
            codigomat: materia.materia.codigo,
            nombremat: materia.materia.nombre,
            inicio: materia.fechaInicio,
            termina: materia.fechaFin,
            estadomat: materia.estado,
            credito: materia.materia.creditos,
            profesor: `${materia.materia.profesor.nombre} ${materia.materia.profesor.apellido}`,
            horarios: materia.materia.horarios.length > 0
              ? materia.materia.horarios.map(h => 
                  `${h.diaSemana} (${h.horaInicio} - ${h.horaFin})`
                ).join(", ") 
              : "Sin horario",
            aula: materia.materia.horarios.length > 0
              ? materia.materia.horarios[0].aula.nombre
              : "Sin aula",
          }));

          setMaterias(data);
        }
      })
      .catch((error) => {
        console.error("Error obteniendo las materias:", error);
      });
  }, []);


  return (
    <div className="CronogramaComponent">
      <Button label="Ir al horario" className="buttonHorario" />

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