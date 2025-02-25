import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from 'primereact/button';


function CronogramaComponent() {
  // Definir datos de ejemplo
  const products = [
    { code: "P001", name: "Producto A", category: "Categoría 1", quantity: 10 },
    { code: "P002", name: "Producto B", category: "Categoría 2", quantity: 20 },
    { code: "P003", name: "Producto C", category: "Categoría 3", quantity: 15 },
  ];

  return (
    <div className="CronogramaComponent">
      
      <Button label="Ir al horario" className="buttonHorario"/>
      
      <DataTable value={products} tableStyle={{ minWidth: "50rem" }}>
        <Column field="code" header="Asignatura"></Column>
        <Column field="name" header="Inicia"></Column>
        <Column field="category" header="Termina"></Column>
        <Column field="quantity" header="Profesor(es)"></Column>
        <Column field="quantitys" header="Horarios"></Column>
        <Column field="quantityss" header="Aula"></Column>
      </DataTable>
    </div>
  );
}

export default CronogramaComponent;