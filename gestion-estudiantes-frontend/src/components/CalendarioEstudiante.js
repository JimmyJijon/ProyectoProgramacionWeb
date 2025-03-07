import React, { useState, useEffect } from "react";
import { Calendar } from "primereact/calendar";
import { Button } from "primereact/button";
import { useNavigate } from "react-router-dom";
import { addLocale } from "primereact/api";
import axios from "axios"; // Para obtener los eventos desde la API
import "../App.css";

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

const CalendarioEstudiante = () => {
  const navigate = useNavigate();
  const [fechaSeleccionada, setFechaSeleccionada] = useState(new Date());
  const [eventos, setEventos] = useState([]); // Todos los eventos del calendario
  const [eventosFiltrados, setEventosFiltrados] = useState([]); // Solo los eventos de la fecha seleccionada

  // Cargar eventos desde la base de datos
  useEffect(() => {
    axios.get("http://localhost:8080/api/eventos")
      .then(response => {
        setEventos(response.data); // Guardamos todos los eventos
      })
      .catch(error => console.error("Error cargando eventos:", error));
  }, []);

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
    if (!dateMeta.day) return null;
    const dateString = `${dateMeta.year}-${String(dateMeta.month + 1).padStart(2, "0")}-${String(dateMeta.day).padStart(2, "0")}`;
    const tieneEvento = eventos.some((evento) => evento.fecha === dateString);

    return (
      <div className={`p-datepicker-date ${tieneEvento ? "evento-activo" : ""}`}>
        {dateMeta.day}
      </div>
    );
  };

  // Función para manejar la selección de fecha y filtrar eventos de ese día
  const seleccionarFecha = (e) => {
    const nuevaFecha = e.value;
    setFechaSeleccionada(nuevaFecha);

    const dateString = nuevaFecha.toISOString().split("T")[0];
    const eventosDelDia = eventos.filter(evento => evento.fecha === dateString);
    
    setEventosFiltrados(eventosDelDia);
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
        <Calendar 
          value={fechaSeleccionada} 
          onChange={seleccionarFecha} 
          inline 
          locale="es"
          dateTemplate={customDateTemplate}
        />

        {/* Sección donde se muestran los eventos filtrados */}
        <div className="p-3 border-round shadow-2 w-30">
          <h3>Actividades del Día</h3>
          {eventosFiltrados.length > 0 ? (
           <ul className="lista-eventos">
              {eventosFiltrados.map((evento) => (
               <li key={evento.id} className="evento-item">
                  <strong>{evento.titulo}</strong>
                  <p>{evento.descripcion}</p>
                </li>
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
