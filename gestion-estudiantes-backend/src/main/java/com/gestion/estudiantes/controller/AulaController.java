package com.gestion.estudiantes.controller;

import java.util.List;
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

import com.gestion.estudiantes.model.Aula;
import com.gestion.estudiantes.service.AulaService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/aulas")
public class AulaController {
    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }
     
     //obtener aula por Id
     @GetMapping("/{id}")
     public ResponseEntity<Aula> obtenerAulaPorId(@PathVariable Long id) {
         return aulaService.obtenerAulaPorId(id)
                 .map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
     }


    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Optional<Aula>> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(aulaService.obtenerPorNombre(nombre));
    }

    @GetMapping("/edificio/{edificio}")
    public List<Aula> obtenerPorEdificio(@PathVariable String edificio) {
        return aulaService.obtenerPorEdificio(edificio);
    }

    @PostMapping
    public ResponseEntity<Aula> guardar(@RequestBody Aula aula) {
        return ResponseEntity.ok(aulaService.guardar(aula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        aulaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<Void> eliminarPorNombre(@PathVariable String nombre) {
        aulaService.eliminarPorNombre(nombre);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/edificio/{edificio}")
    public ResponseEntity<Void> eliminarPorEdificio(@PathVariable String edificio) {
        aulaService.eliminarPorEdificio(edificio);
        return ResponseEntity.noContent().build();
    }
}
