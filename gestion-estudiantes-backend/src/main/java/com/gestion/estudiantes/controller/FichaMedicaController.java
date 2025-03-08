package com.gestion.estudiantes.controller;

import java.util.Optional;

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

import com.gestion.estudiantes.model.FichaMedica;
import com.gestion.estudiantes.service.FichaMedicaService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/ficha-medica")
public class FichaMedicaController {
    private final FichaMedicaService fichaMedicaService;

    public FichaMedicaController(FichaMedicaService fichaMedicaService) {
        this.fichaMedicaService = fichaMedicaService;
    }

    @GetMapping("/{estudianteId}")
    public ResponseEntity<Optional<FichaMedica>> obtenerPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(fichaMedicaService.obtenerPorEstudiante(estudianteId));
    }

    @PostMapping
    public ResponseEntity<FichaMedica> guardarFichaMedica(@RequestBody FichaMedica fichaMedica) {
        return ResponseEntity.ok(fichaMedicaService.guardarFichaMedica(fichaMedica));
    }

    @PutMapping("/{estudianteId}")
    public ResponseEntity<FichaMedica> actualizarFichaMedica(@PathVariable Long estudianteId, @RequestBody FichaMedica fichaMedica) {
        return ResponseEntity.ok(fichaMedicaService.actualizarFichaMedica(estudianteId, fichaMedica));
    }

    @DeleteMapping("/{estudianteId}")
    public ResponseEntity<Void> eliminarFichaMedica(@PathVariable Long estudianteId) {
        fichaMedicaService.eliminarFichaMedica(estudianteId);
        return ResponseEntity.noContent().build();
    }
}
