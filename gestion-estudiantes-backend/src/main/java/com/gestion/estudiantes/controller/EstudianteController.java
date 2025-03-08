package com.gestion.estudiantes.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.service.EstudianteService;

//AQUI ESTA EL API REST QUE PERMITIRA USAR LOS CRUD DE ESTUDIANTE HACIENDO PETICIONES DESDE EL FRONT REACT
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EstudianteController {
    private final EstudianteService estudianteService;

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
}
