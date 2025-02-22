import React, { useState } from "react";
import { Calendar } from "primereact/calendar";
import { Button } from "primereact/button";
import { useNavigate } from "react-router-dom";

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
      <Button label="Atrás" icon="pi pi-arrow-left" className="p-button-text" onClick={() => navigate(-1)} />
      <h2 className="text-center mt-4">Calendario de actividades de la Institución</h2>
      <div className="flex flex-column align-items-center mt-4">
        <div className="p-3 border-round bg-primary text-white">
          {fechaSeleccionada.toLocaleString("default", { month: "long", year: "numeric" })}
        </div>
        <div className="mt-3 flex gap-2">
          <Button icon="pi pi-chevron-left" label="Mes Anterior" onClick={() => cambiarMes(-1)} />
          <Button icon="pi pi-calendar" label="Mes Actual" onClick={() => setFechaSeleccionada(new Date())} />
          <Button icon="pi pi-chevron-right" label="Próximo Mes" onClick={() => cambiarMes(1)} />
        </div>
      </div>
      <div className="flex mt-4 justify-content-center gap-5">
        <Calendar value={fechaSeleccionada} onChange={(e) => setFechaSeleccionada(e.value)} inline showButtonBar />
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