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

import com.gestion.estudiantes.model.AntecedentePatologico;
import com.gestion.estudiantes.service.AntecedentePatologicoService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/antecedentes")
public class AntecedentePatologicoController {
    private final AntecedentePatologicoService antecedentePatologicoService;

    public AntecedentePatologicoController(AntecedentePatologicoService antecedentePatologicoService) {
        this.antecedentePatologicoService = antecedentePatologicoService;
    }

    @GetMapping("/{fichaMedicaId}")
    public List<AntecedentePatologico> obtenerPorFichaMedica(@PathVariable Long fichaMedicaId) {
        return antecedentePatologicoService.obtenerPorFichaMedica(fichaMedicaId);
    }

    @PostMapping
    public AntecedentePatologico guardar(@RequestBody AntecedentePatologico antecedente) {
        return antecedentePatologicoService.guardar(antecedente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        antecedentePatologicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ficha/{fichaMedicaId}")
    public ResponseEntity<Void> eliminarPorFichaMedica(@PathVariable Long fichaMedicaId) {
        antecedentePatologicoService.eliminarPorFichaMedica(fichaMedicaId);
        return ResponseEntity.noContent().build();
    }
}
