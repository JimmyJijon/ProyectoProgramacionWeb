package com.gestion.estudiantes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gestion.estudiantes.model.EventoCalendario;
import com.gestion.estudiantes.service.EventoCalendarioService;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:3000")
public class EventoCalendarioController {
    @Autowired
    private EventoCalendarioService eventoService;

    @GetMapping
    public List<EventoCalendario> obtenerEventos() {
        return eventoService.obtenerEventos();
    }
}


