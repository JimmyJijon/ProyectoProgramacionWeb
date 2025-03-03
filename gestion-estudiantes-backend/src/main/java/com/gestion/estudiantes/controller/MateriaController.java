package com.gestion.estudiantes.controller;


import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestion.estudiantes.model.Materia;
import com.gestion.estudiantes.service.MateriaService;

//AQUI ESTA EL API REST QUE PERMITIRA USAR LOS CRUD DE MATERIA HACIENDO PETICIONES DESDE EL FRONT REACT
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/materias")
public class MateriaController {
    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public List<Materia> obtenerTodas() {
        return materiaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.obtenerPorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public Optional<Materia> obtenerPorCodigo(@PathVariable String codigo) {
        return materiaService.obtenerPorCodigo(codigo);
    }

    @GetMapping("/buscar")
    public List<Materia> buscarPorNombre(@RequestParam String nombre) {
        return materiaService.buscarPorNombre(nombre);
    }

    @GetMapping("/activas")
    public List<Materia> obtenerMateriasActivas() {
        return materiaService.obtenerMateriasActivas();
    }

    @GetMapping("/profesor/{profesorId}")
    public List<Materia> obtenerMateriasPorProfesor(@PathVariable Long profesorId) {
        return materiaService.obtenerMateriasPorProfesor(profesorId);
    }

    @GetMapping("/malla/{mallaId}")
    public List<Materia> obtenerMateriasPorMalla(@PathVariable Long mallaId) {
        return materiaService.obtenerMateriasPorMalla(mallaId);
    }

    @GetMapping("/creditos/{creditos}")
    public List<Materia> obtenerMateriasPorCreditos(@PathVariable int creditos) {
        return materiaService.obtenerMateriasPorCreditos(creditos);
    }

    @PostMapping
    public Materia guardarMateria(@RequestBody Materia materia) {
        return materiaService.guardarMateria(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> actualizarMateria(@PathVariable Long id, @RequestBody Materia materia) {
        return ResponseEntity.ok(materiaService.actualizarMateria(id, materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarMateria(@PathVariable Long id) {
        materiaService.desactivarMateria(id);
        return ResponseEntity.noContent().build();
    }
}
