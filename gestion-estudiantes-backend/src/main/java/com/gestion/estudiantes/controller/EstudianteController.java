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
import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.repository.EstudianteRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EstudianteController {
  
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes(){
        return estudianteRepository.findAll();
    }
    
    @PostMapping("/estudiantes")
    //@RequestBody indica que el cuerpo (body) de la solicitud HTTP POST debe convertirse en un objeto Estudiante.
    public Estudiante guardarEstudiante(@RequestBody Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }
    
    @GetMapping("/estudiantes/{id}")
    //@PathVariable Long id extrae el valor de {id} en la URL y lo pasa al método como parámetro.
    public ResponseEntity<Estudiante> listarEstudiantePorId(@PathVariable Long id){
        Estudiante estudiante = estudianteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ese ID no existe: " + id));
        return ResponseEntity.ok(estudiante);
    }
    
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteRequest){
        Estudiante estudiante = estudianteRepository.findById(id)
                      .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ese ID no existe : " + id));

        estudiante.setNombre(estudianteRequest.getNombre());
        estudiante.setApellido(estudianteRequest.getApellido());
        estudiante.setEmail(estudianteRequest.getEmail());
        estudiante.setCedula(estudianteRequest.getCedula());
        estudiante.setFechaNacimiento(estudianteRequest.getFechaNacimiento());
        estudiante.setEdad(estudianteRequest.getEdad());
        estudiante.setEstadoCivil(estudianteRequest.getEstadoCivil());
        estudiante.setPaisNacimiento(estudianteRequest.getPaisNacimiento());
        estudiante.setPaisNacimiento(estudianteRequest.getPaisNacimiento());
        estudiante.setCelular(estudianteRequest.getCelular());
        /*no agregamos el atributo private List <EstudianteMateria> estudiantes; porque aun no se define si desde
        este endpoint actualizaremos materias*/
        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
        return ResponseEntity.ok(estudianteActualizado);
    }
    
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEstudiante(@PathVariable Long id){
      Estudiante estudiante = estudianteRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ese ID no existe : " + id));

      estudiante.setActivo(false); // 🔹 En lugar de eliminar, lo desactivamos
      estudianteRepository.save(estudiante); // 🔹 Guardamos el cambio en la base de datos
      Map<String,Boolean> response = new HashMap();
      response.put("deleted",Boolean.TRUE);
      return ResponseEntity.ok(response);
    }
}
