package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.EventoCalendario;
import com.gestion.estudiantes.repository.EventoCalendarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoCalendarioService {
    private final EventoCalendarioRepository eventoCalendarioRepository;

    public EventoCalendarioService(EventoCalendarioRepository eventoCalendarioRepository) {
        this.eventoCalendarioRepository = eventoCalendarioRepository;
    }

    //Obtener evento por título
    public Optional<EventoCalendario> obtenerPorTitulo(String titulo) {
        return eventoCalendarioRepository.findByTitulo(titulo);
    }

    //Obtener eventos por fecha
    public List<EventoCalendario> obtenerPorFecha(LocalDate fecha) {
        return eventoCalendarioRepository.findByFecha(fecha);
    }

    //Obtener eventos en un rango de fechas
    public List<EventoCalendario> obtenerPorRangoDeFechas(LocalDate inicio, LocalDate fin) {
        return eventoCalendarioRepository.findByFechaBetween(inicio, fin);
    }

    //Guardar un nuevo evento
    public EventoCalendario guardar(EventoCalendario evento) {
        return eventoCalendarioRepository.save(evento);
    }

    //Eliminar un evento por ID
    public void eliminar(Long id) {
        eventoCalendarioRepository.deleteById(id);
    }

    //Eliminar un evento por título
    public void eliminarPorTitulo(String titulo) {
        eventoCalendarioRepository.deleteByTitulo(titulo);
    }

    //Eliminar eventos por fecha
    public void eliminarPorFecha(LocalDate fecha) {
        eventoCalendarioRepository.deleteByFecha(fecha);
    }
}
