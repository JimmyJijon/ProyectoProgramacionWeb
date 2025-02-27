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
        onClick={() => alert(`Detalles de la materia: ${rowData.materia}`)}
      />
    );
  };

  return (
    <div className="card">
      <DataTable
        value={materias}
        paginator
        rows={5}
        responsiveLayout="scroll"
        sortMode="multiple"
        emptyMessage="No se encontraron materias."
      >
        <Column field="materia" header="Materia" sortable />
        <Column field="nrc" header="NRC" sortable />
        <Column field="docente" header="Docente" sortable />
        <Column field="nota1" header="Nota 1" sortable />
        <Column field="nota2" header="Nota 2" sortable />
        <Column field="nota3" header="Nota 3" sortable />
        <Column field="notaFinal" header="Nota Final" sortable />
        <Column field="porcentaje" header="Porcentaje" sortable />
        <Column field="estado" header="Estado" sortable />
        <Column body={plantillaAcciones} header="Acciones" />
      </DataTable>
    </div>
  );
}
