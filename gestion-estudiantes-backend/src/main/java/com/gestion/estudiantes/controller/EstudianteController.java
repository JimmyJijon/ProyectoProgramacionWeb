package com.gestion.estudiantes.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.estudiantes.dto.MateriaEstudianteDTO;
import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.service.EstudianteService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EstudianteController {
    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes() {
        return estudianteService.listarEstudiantes();
    }

    @PostMapping("/estudiantes")
    public Estudiante guardarEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.guardarEstudiante(estudiante);
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiante> listarEstudiantePorId(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerEstudiantePorId(id));
    }

    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, estudiante));
    }

    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.desactivarEstudiante(id));
    }

    @GetMapping("/estudiante-materias")
    public ResponseEntity<List<MateriaEstudianteDTO>> getMateriasPorEstudiante(@RequestParam Long estudiante_id) {
        List<MateriaEstudianteDTO> materias = estudianteService.findMateriasByEstudianteId(estudiante_id);
        return ResponseEntity.ok(materias);
    }
}
