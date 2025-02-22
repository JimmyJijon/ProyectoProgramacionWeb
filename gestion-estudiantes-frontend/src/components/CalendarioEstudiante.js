import React, { useState } from "react";
import { Calendar } from "primereact/calendar";
import { Button } from "primereact/button";
import { useNavigate } from "react-router-dom";
import { addLocale } from "primereact/api";


// Configuración del idioma español
addLocale("es", {
  firstDayOfWeek: 1,
  dayNames: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
  dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
  dayNamesMin: ["D", "L", "M", "M", "J", "V", "S"],
  monthNames: [
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
  ],
  monthNamesShort: ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"],
  today: "Hoy",
  clear: "Limpiar"
});

const CalendarioEstudiante = () => {
  const navigate = useNavigate();
  const [fechaSeleccionada, setFechaSeleccionada] = useState(new Date());

  const cambiarMes = (cambio) => {
    setFechaSeleccionada((prevFecha) => {
      const nuevaFecha = new Date(prevFecha);
      nuevaFecha.setMonth(prevFecha.getMonth() + cambio);
      return nuevaFecha;
    });
  };

  return (
    <div className="p-4">
      <div className="mb-4">
        <Button 
          label="Atrás" 
          icon="pi pi-arrow-left" 
          className="p-button-text mt-3" 
          onClick={() => navigate(-1)} 
        />
      </div>

      <h2 className="text-center mt-4">Calendario de actividades de la Institución</h2>

      <div className="flex flex-column align-items-center mt-4">
        <div className="p-3 border-round bg-primary text-white text-center">
          {fechaSeleccionada.toLocaleString("es-ES", { month: "long", year: "numeric" })}
        </div>
        <div className="mt-3 flex gap-2">
          <Button icon="pi pi-chevron-left" label="Mes Anterior" onClick={() => cambiarMes(-1)} />
          <Button icon="pi pi-calendar" label="Mes Actual" onClick={() => setFechaSeleccionada(new Date())} />
          <Button icon="pi pi-chevron-right" label="Próximo Mes" onClick={() => cambiarMes(1)} />
        </div>
      </div>

      <div className="flex mt-5 justify-content-center gap-5">
        {/* Se establece el locale a "es" */}
        <Calendar 
          value={fechaSeleccionada} 
          onChange={(e) => setFechaSeleccionada(e.value)} 
          inline 
          showButtonBar 
          locale="es"
        />
        <div className="p-3 border-round shadow-2 w-30">
          <h3>Actividades del Día</h3>
          <ul>
            <li>Ejemplo de actividad 1</li>
            <li>Ejemplo de actividad 2</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default CalendarioEstudiante;