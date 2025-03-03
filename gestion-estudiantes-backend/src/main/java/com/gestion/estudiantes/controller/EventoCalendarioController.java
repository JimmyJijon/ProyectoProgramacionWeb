package com.gestion.estudiantes.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.estudiantes.model.EventoCalendario;
import com.gestion.estudiantes.service.EventoCalendarioService;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/eventos")
public class EventoCalendarioController {
    private final EventoCalendarioService eventoCalendarioService;

    public EventoCalendarioController(EventoCalendarioService eventoCalendarioService) {
        this.eventoCalendarioService = eventoCalendarioService;
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Optional<EventoCalendario>> obtenerPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(eventoCalendarioService.obtenerPorTitulo(titulo));
    }

    @GetMapping("/fecha/{fecha}")
    public List<EventoCalendario> obtenerPorFecha(@PathVariable LocalDate fecha) {
        return eventoCalendarioService.obtenerPorFecha(fecha);
    }

    @GetMapping("/rango")
    public List<EventoCalendario> obtenerPorRangoDeFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        return eventoCalendarioService.obtenerPorRangoDeFechas(inicio, fin);
    }

    @PostMapping
    public ResponseEntity<EventoCalendario> guardar(@RequestBody EventoCalendario evento) {
        return ResponseEntity.ok(eventoCalendarioService.guardar(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eventoCalendarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/titulo/{titulo}")
    public ResponseEntity<Void> eliminarPorTitulo(@PathVariable String titulo) {
        eventoCalendarioService.eliminarPorTitulo(titulo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/fecha/{fecha}")
    public ResponseEntity<Void> eliminarPorFecha(@PathVariable LocalDate fecha) {
        eventoCalendarioService.eliminarPorFecha(fecha);
        return ResponseEntity.noContent().build();
    }
}
