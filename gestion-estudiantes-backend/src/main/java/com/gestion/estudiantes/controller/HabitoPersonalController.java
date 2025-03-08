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

import com.gestion.estudiantes.model.HabitoPersonal;
import com.gestion.estudiantes.service.HabitoPersonalService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/habitos")
public class HabitoPersonalController {
    private final HabitoPersonalService habitoPersonalService;

    public HabitoPersonalController(HabitoPersonalService habitoPersonalService) {
        this.habitoPersonalService = habitoPersonalService;
    }

    @GetMapping("/{fichaMedicaId}")
    public List<HabitoPersonal> obtenerPorFichaMedica(@PathVariable Long fichaMedicaId) {
        return habitoPersonalService.obtenerPorFichaMedica(fichaMedicaId);
    }

    @PostMapping
    public HabitoPersonal guardar(@RequestBody HabitoPersonal habito) {
        return habitoPersonalService.guardar(habito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        habitoPersonalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ficha/{fichaMedicaId}")
    public ResponseEntity<Void> eliminarPorFichaMedica(@PathVariable Long fichaMedicaId) {
        habitoPersonalService.eliminarPorFichaMedica(fichaMedicaId);
        return ResponseEntity.noContent().build();
    }
}
