package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.EventoCalendario;
import com.gestion.estudiantes.repository.EventoCalendarioRepository;

import java.util.List;

@Service
public class EventoCalendarioService {

    private final EventoCalendarioRepository eventoCalendarioRepository;

    public EventoCalendarioService(EventoCalendarioRepository eventoCalendarioRepository) {
        this.eventoCalendarioRepository = eventoCalendarioRepository;
    }

    public List<EventoCalendario> obtenerEventos() {
        return eventoCalendarioRepository.findAll();
    }
}
