package com.gestion.estudiantes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.enums.EstadoMateria;
import com.gestion.estudiantes.service.EstudianteMateriaService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EstudianteMateriaController {
    private final EstudianteMateriaService estudianteMateriaService;

    public EstudianteMateriaController(EstudianteMateriaService estudianteMateriaService) {
        this.estudianteMateriaService = estudianteMateriaService;
    }

    @GetMapping("/materia-estudiante/{estudianteId}")
    public ResponseEntity<List<EstudianteMateria>> obtenerMateriasPorEstudiante(@PathVariable Long estudianteId) {
    List<EstudianteMateria> materias = estudianteMateriaService.obtenerMateriasPorEstudiante(estudianteId);
    
    if (materias.isEmpty()) {
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(materias);
}


    @GetMapping("/estudiante-materia/{materiaId}")
    public List<EstudianteMateria> obtenerEstudiantesPorMateria(@PathVariable Long materiaId) {
        return estudianteMateriaService.obtenerEstudiantesPorMateria(materiaId);
    }

    @GetMapping("estudiante-materia/nota")
    public Optional<Double> obtenerNota(@RequestParam Long estudianteId, @RequestParam Long materiaId) {
        return estudianteMateriaService.obtenerNota(estudianteId, materiaId);
    }

    @PutMapping("estudiante-materia/actualizar-estado")
    public String actualizarEstadoMateria(@RequestParam Long estudianteId, @RequestParam Long materiaId, @RequestParam EstadoMateria nuevoEstado) {
        estudianteMateriaService.actualizarEstadoMateria(estudianteId, materiaId, nuevoEstado);
        return "Estado actualizado correctamente";
    }
}
