package com.gestion.estudiantes.controller;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.service.EstudianteMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para exponer los endpoints relacionados con las inscripciones
 * de estudiantes en materias.
 */
@RestController
@RequestMapping("/api/estudiante-materias")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil en desarrollo)
public class EstudianteMateriaController {

    @Autowired
    private EstudianteMateriaService estudianteMateriaService;

    /**
     * Endpoint para obtener todas las inscripciones de estudiantes en materias.
     * @return Lista de inscripciones (EstudianteMateria).
     */
    @GetMapping
    public List<EstudianteMateria> obtenerEstudianteMaterias() {
        return estudianteMateriaService.obtenerTodasLasMaterias();
    }
}
