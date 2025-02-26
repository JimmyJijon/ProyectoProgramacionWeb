package com.gestion.estudiantes.controller;

import org.springframework.web.bind.annotation.*;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.enums.EstadoMateria;
import com.gestion.estudiantes.service.EstudianteMateriaService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiante-materia")
public class EstudianteMateriaController {
    private final EstudianteMateriaService estudianteMateriaService;

    public EstudianteMateriaController(EstudianteMateriaService estudianteMateriaService) {
        this.estudianteMateriaService = estudianteMateriaService;
    }

    @GetMapping("/materias/{estudianteId}")
    public List<EstudianteMateria> obtenerMateriasPorEstudiante(@PathVariable Long estudianteId) {
        return estudianteMateriaService.obtenerMateriasPorEstudiante(estudianteId);
    }

    @GetMapping("/estudiantes/{materiaId}")
    public List<EstudianteMateria> obtenerEstudiantesPorMateria(@PathVariable Long materiaId) {
        return estudianteMateriaService.obtenerEstudiantesPorMateria(materiaId);
    }

    @GetMapping("/nota")
    public Optional<Double> obtenerNota(@RequestParam Long estudianteId, @RequestParam Long materiaId) {
        return estudianteMateriaService.obtenerNota(estudianteId, materiaId);
    }

    @PutMapping("/actualizar-estado")
    public String actualizarEstadoMateria(@RequestParam Long estudianteId, @RequestParam Long materiaId, @RequestParam EstadoMateria nuevoEstado) {
        estudianteMateriaService.actualizarEstadoMateria(estudianteId, materiaId, nuevoEstado);
        return "Estado actualizado correctamente";
    }
}