package com.gestion.estudiantes.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.gestion.estudiantes.exception.ResourceNotFoundException;
import com.gestion.estudiantes.model.Materia;
import com.gestion.estudiantes.repository.MateriaRepository;

import jakarta.persistence.criteria.Predicate.BooleanOperator;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class MateriaController {

   @Autowired
   private MateriaRepository materiaRepository;

   @GetMapping("/materias") //metodo para listar todas las materias
   public List<Materia> listarMaterias(){
    return materiaRepository.findAll();
   }
   
   @PostMapping("/materias")
   //@RequestBody indica que el cuerpo (body) de la solicitud HTTP POST debe convertirse en un objeto Materia.
   public Materia guardarMateria(@RequestBody Materia materia){
    return materiaRepository.save(materia);
   }

   @GetMapping("/materias/{id}")
   //@PathVariable Long id extrae el valor de {id} en la URL y lo pasa al método como parámetro.
   public ResponseEntity<Materia>listarMateriaPorId(@PathVariable Long id){
    Materia materia = materiaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No existe materia con el ID: " + id));
    return ResponseEntity.ok(materia);
   }

   @PutMapping("/materias/{id}")
   public ResponseEntity<Materia>actualizarMateria(@PathVariable Long id, @RequestBody Materia materiaRequest){
    Materia materia = materiaRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("No existe materia con el ID: " + id));
    materia.setNombre(materiaRequest.getNombre());
    materia.setCodigo(materiaRequest.getCodigo());
    materia.setActivo(materiaRequest.isActivo());
    /*no agregamos el atributo private List <EstudianteMateria> estudiantes; porque aun no se define si desde
        este endpoint actualizaremos materias*/
    Materia materiaActualizada = materiaRepository.save(materia);
    return ResponseEntity.ok(materiaActualizada);
   }

   @DeleteMapping("/materias/{id}")
   public ResponseEntity<Map<String, Boolean>> desactivarMateria(@PathVariable Long id){
     Materia materia = materiaRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("No existe materia con el ID: " + id));
              //o de lo contrario, lanzar
    materia.setActivo(false); //En lugar de eliminar, lo desactivamos
    materiaRepository.save(materia); // Guardamos el cambio en la base de datos
    Map<String, Boolean> response= new HashMap<>();
    response.put("desactivated",Boolean.TRUE);
    return ResponseEntity.ok(response);

   }
   
}
