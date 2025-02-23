import React, { useState } from "react";
import { Calendar } from "primereact/calendar";
import { Button } from "primereact/button";
import { useNavigate } from "react-router-dom";
import { addLocale } from "primereact/api";
import "./CalendarioEstudiante.css";

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
});

// Lista de actividades con sus fechas
const actividades = [
  { fecha: "2025-02-14", evento: "Día de San Valentín - Celebración en la institución" },
  { fecha: "2025-03-08", evento: "Día Internacional de la Mujer - Charla especial" },
  { fecha: "2025-04-22", evento: "Día de la Tierra - Campaña de reciclaje" },
];

const CalendarioEstudiante = () => {
  const navigate = useNavigate();
  const [fechaSeleccionada, setFechaSeleccionada] = useState(new Date());
  const [actividadesDelDia, setActividadesDelDia] = useState([]);

  // Función para cambiar el mes del calendario
  const cambiarMes = (cambio) => {
    setFechaSeleccionada((prevFecha) => {
      const nuevaFecha = new Date(prevFecha);
      nuevaFecha.setMonth(prevFecha.getMonth() + cambio);
      return nuevaFecha;
    });
  };

  // Función para personalizar los días con eventos en el calendario
  const customDateTemplate = (dateMeta) => {
    if (!dateMeta.day) return null; // Previene errores en fechas vacías
  
    // Formateamos la fecha en formato YYYY-MM-DD
    const dateString = `${dateMeta.year}-${String(dateMeta.month + 1).padStart(2, "0")}-${String(dateMeta.day).padStart(2, "0")}`;
  
    // Verificamos si la fecha tiene actividades
    const tieneEvento = actividades.some((act) => act.fecha === dateString);
  
    return (
      <div className={`p-datepicker-date ${tieneEvento ? "evento-activo" : ""}`}>
        {dateMeta.day}
      </div>
    );
  };

  // Función para manejar la selección de fecha y mostrar las actividades del día
  const seleccionarFecha = (e) => {
    const nuevaFecha = e.value;
    setFechaSeleccionada(nuevaFecha);

    // Obtener actividades del día seleccionado
    const dateString = nuevaFecha.toISOString().split("T")[0];
    const actividadesFiltradas = actividades
      .filter((act) => act.fecha === dateString)
      .map((act) => act.evento);

    setActividadesDelDia(actividadesFiltradas);
  };

  return (
    <div className="p-6">
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
        <div className="border-round bg-primary text-white text-center">
          {fechaSeleccionada.toLocaleString("es-ES", { month: "long", year: "numeric" })}
        </div>
        <div className="mt-3 flex gap-2">
          <Button icon="pi pi-chevron-left" label="Mes Anterior" onClick={() => cambiarMes(-1)} />
          <Button icon="pi pi-calendar" label="Mes Actual" onClick={() => setFechaSeleccionada(new Date())} />
          <Button icon="pi pi-chevron-right" label="Próximo Mes" onClick={() => cambiarMes(1)} />
        </div>
      </div>

      <div className="flex mt-5 justify-content-center gap-5">
        {/* Se establece el locale a "es" y se usa dateTemplate */}
        <Calendar 
          value={fechaSeleccionada} 
          onChange={seleccionarFecha} 
          inline 
          
          locale="es"
          dateTemplate={customDateTemplate}
        />
        <div className="p-3 border-round shadow-2 w-30">
          <h3>Actividades del Día</h3>
          {actividadesDelDia.length > 0 ? (
            <ul>
              {actividadesDelDia.map((evento, index) => (
                <li key={index}>{evento}</li>
              ))}
            </ul>
          ) : (
            <p>No hay actividades programadas.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default CalendarioEstudiante;