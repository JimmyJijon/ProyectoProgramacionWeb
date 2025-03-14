package com.gestion.estudiantes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.enums.EstadoMateria;
import com.gestion.estudiantes.service.EstudianteMateriaService;

import java.time.LocalDate;
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

    @GetMapping("/estudiante-materia/nota")
    public Optional<Double> obtenerNota(@RequestParam Long estudianteId, @RequestParam Long materiaId) {
        return estudianteMateriaService.obtenerNota(estudianteId, materiaId);
    }

    @PutMapping("/estudiante-materia/actualizar-estado")
    public String actualizarEstadoMateria(@RequestParam Long estudianteId, @RequestParam Long materiaId, @RequestParam EstadoMateria nuevoEstado) {
        estudianteMateriaService.actualizarEstadoMateria(estudianteId, materiaId, nuevoEstado);
        return "Estado actualizado correctamente";
    }


    @PostMapping("estudiante-materia/inscribir")
    public ResponseEntity<?> inscribirEstudiante(
        @RequestParam Long estudianteId, 
        @RequestParam Long materiaId
) {
    try {
        // Llama al servicio sin necesidad de pasar más datos
        EstudianteMateria inscripcion = estudianteMateriaService.inscribirEstudianteEnMateria(estudianteId, materiaId, null, null, null, null, null);

        return ResponseEntity.ok(inscripcion);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("⚠️ Error: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("🚨 Error al inscribir estudiante: " + e.getMessage());
    }
}

    @GetMapping("/api/v1/estudiante-materias-todas")
    public ResponseEntity<List<EstudianteMateria>> getAllEstudianteMaterias() {
        List<EstudianteMateria> materias = estudianteMateriaService.getAllEstudianteMaterias();
        if (materias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materias);
    }
}
