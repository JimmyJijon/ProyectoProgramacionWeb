// src/components/TablaMateriasComponent.jsx

import React from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';

/**
 * Componente que renderiza la tabla de materias utilizando PrimeReact DataTable.
 * Recibe como prop "materias", que es un arreglo de objetos con la información de cada materia.
 */
export default function TablaMateriasComponent({ materias }) {

  // Plantilla personalizada para la columna "Acciones"
  const plantillaAcciones = (rowData) => {
    return (
      <Button
        label="Detalles"
        icon="pi pi-info-circle"
        className="p-button-text p-button-primary"
        onClick={() => alert(`Detalles de la materia:
          Nombre: ${rowData.nombre}
          Código: ${rowData.codigo}
          Profesor: ${rowData.profesorNombre}
          Nota: ${rowData.nota}
          Estado: ${rowData.estado}
          Periodo Académico: ${rowData.periodoAcademico}
          Fecha de Inicio: ${rowData.fechaInicio}
          Fecha de Fin: ${rowData.fechaFin}
          Fecha de Inscripción: ${rowData.fechaInscripcion}`)}
      />
    );
  };

  // Opciones de paginación
  const rowsPerPageOptions = [5, 10, 20];

  const columns = [
    { field: 'nombre', header: 'Materia' },
    { field: 'codigo', header: 'Código', sortable: true, filter: true, filterPlaceholder: 'Buscar por código' },
    { field: 'profesorNombre', header: 'Docente', sortable: true, filter: true, filterPlaceholder: 'Buscar por docente' },
    { field: 'nota', header: 'Nota', sortable: true, filter: true, filterPlaceholder: 'Buscar por nota' },
    { field: 'estado', header: 'Estado', sortable: true, filter: true, filterPlaceholder: 'Buscar por estado' },
    { field: 'periodoAcademico', header: 'Periodo Académico', sortable: true, filter: true, filterPlaceholder: 'Buscar por periodo' },
    { body: plantillaAcciones, header: 'Acciones' }
  ];

  return (
    <div className="card" style={{ width: '100%', overflowX: 'auto' }}>
      <DataTable
        value={materias}
        paginator
        rows={5}
        rowsPerPageOptions={rowsPerPageOptions}
        responsiveLayout="scroll"
        sortMode="multiple"
        emptyMessage="No se encontraron materias."
        filterDisplay="row"
      >
        {columns.map((col, i) => (
          <Column key={i} field={col.field} header={col.header} sortable={col.sortable} filter={col.filter} filterPlaceholder={col.filterPlaceholder} body={col.body} />
        ))}
      </DataTable>
    </div>
  );
}
