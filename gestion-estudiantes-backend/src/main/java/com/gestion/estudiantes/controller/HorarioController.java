package com.gestion.estudiantes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.estudiantes.model.Horario;
import com.gestion.estudiantes.service.HorarioService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/horarios")
public class HorarioController {
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping("/materia/{materiaId}")
    public List<Horario> obtenerPorMateria(@PathVariable Long materiaId) {
        return horarioService.obtenerPorMateria(materiaId);
    }

    @GetMapping("/aula/{aulaId}")
    public List<Horario> obtenerPorAula(@PathVariable Long aulaId) {
        return horarioService.obtenerPorAula(aulaId);
    }

    @PostMapping
    public Horario guardar(@RequestBody Horario horario) {
        return horarioService.guardar(horario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/materia/{materiaId}")
    public ResponseEntity<Void> eliminarPorMateria(@PathVariable Long materiaId) {
        horarioService.eliminarPorMateria(materiaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/aula/{aulaId}")
    public ResponseEntity<Void> eliminarPorAula(@PathVariable Long aulaId) {
        horarioService.eliminarPorAula(aulaId);
        return ResponseEntity.noContent().build();
    }
}
