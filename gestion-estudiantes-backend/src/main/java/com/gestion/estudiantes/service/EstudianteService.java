package com.gestion.estudiantes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.gestion.estudiantes.exception.ResourceNotFoundException;
import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.repository.EstudianteRepository;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    //Listar todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    //guardar un nuevo estudiante
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    //Obtener estudiante por ID con manejo de error
    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ID " + id + " no existe"));
    }

    // Actualizar un estudiante
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteRequest) {
        Estudiante estudiante = obtenerEstudiantePorId(id); // Busca el estudiante o lanza error

        estudiante.setNombre(estudianteRequest.getNombre());
        estudiante.setApellido(estudianteRequest.getApellido());
        estudiante.setEmailInstitucional(estudianteRequest.getEmailInstitucional());
        estudiante.setEmail_personal(estudianteRequest.getEmail_personal());
        estudiante.setCedula(estudianteRequest.getCedula());
        estudiante.setFechaNacimiento(estudianteRequest.getFechaNacimiento());
        estudiante.setEdad(estudianteRequest.getEdad());
        estudiante.setEstadoCivil(estudianteRequest.getEstadoCivil());
        estudiante.setPaisNacimiento(estudianteRequest.getPaisNacimiento());
        estudiante.setCelular(estudianteRequest.getCelular());

        return estudianteRepository.save(estudiante);
    }

    //Desactivar estudiante (Soft Delete)
    public Map<String, Boolean> desactivarEstudiante(Long id) {
        Estudiante estudiante = obtenerEstudiantePorId(id);
        estudiante.setActivo(false);
        estudianteRepository.save(estudiante);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
