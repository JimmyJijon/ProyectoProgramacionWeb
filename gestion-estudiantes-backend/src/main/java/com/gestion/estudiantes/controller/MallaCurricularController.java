package com.gestion.estudiantes.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.estudiantes.model.MallaCurricular;
import com.gestion.estudiantes.service.MallaCurricularService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/mallas")
public class MallaCurricularController {
    private final MallaCurricularService mallaCurricularService;

    public MallaCurricularController(MallaCurricularService mallaCurricularService) {
        this.mallaCurricularService = mallaCurricularService;
    }

    @GetMapping("/carrera/{carreraId}")
    public ResponseEntity<Optional<MallaCurricular>> obtenerPorCarrera(@PathVariable Long carreraId) {
        return ResponseEntity.ok(mallaCurricularService.obtenerPorCarrera(carreraId));
    }

    @PostMapping
    public ResponseEntity<MallaCurricular> guardar(@RequestBody MallaCurricular mallaCurricular) {
        return ResponseEntity.ok(mallaCurricularService.guardar(mallaCurricular));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mallaCurricularService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/carrera/{carreraId}")
    public ResponseEntity<Void> eliminarPorCarrera(@PathVariable Long carreraId) {
        mallaCurricularService.eliminarPorCarrera(carreraId);
        return ResponseEntity.noContent().build();
    }
}
