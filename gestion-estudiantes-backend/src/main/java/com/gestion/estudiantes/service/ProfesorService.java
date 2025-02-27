package com.gestion.estudiantes.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.gestion.estudiantes.model.Profesor;
import com.gestion.estudiantes.repository.ProfesorRepository;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    //Obtener todos los profesores
    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    // Obtener un profesor por ID
    public Profesor obtenerPorId(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor con ID " + id + " no encontrado"));
    }

    // Buscar profesor por email
    public Optional<Profesor> obtenerPorEmail(String email) {
        return profesorRepository.findByEmail(email);
    }

    // Buscar profesores por nombre
    public List<Profesor> buscarPorNombre(String nombre) {
        return profesorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar profesores por apellido
    public List<Profesor> buscarPorApellido(String apellido) {
        return profesorRepository.findByApellidoContainingIgnoreCase(apellido);
    }

    // Guardar un nuevo profesor
    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    //Actualizar profesor
    public Profesor actualizarProfesor(Long id, Profesor profesorRequest) {
        Profesor profesor = obtenerPorId(id);

        profesor.setNombre(profesorRequest.getNombre());
        profesor.setApellido(profesorRequest.getApellido());
        profesor.setEmail(profesorRequest.getEmail());
        profesor.setTelefono(profesorRequest.getTelefono());

        return profesorRepository.save(profesor);
    }

    //Eliminar profesor
    public void eliminarProfesor(Long id) {
        Profesor profesor = obtenerPorId(id);
        profesorRepository.delete(profesor);
    }
}
