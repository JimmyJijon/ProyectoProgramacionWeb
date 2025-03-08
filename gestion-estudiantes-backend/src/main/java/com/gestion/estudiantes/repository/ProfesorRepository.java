package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.estudiantes.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    // Buscar profesor por email (único)
    Optional<Profesor> findByEmail(String email);

    //Buscar profesores por nombre (busca coincidencias parciales)
    List<Profesor> findByNombreContainingIgnoreCase(String nombre);

    // Buscar profesores por apellido
    List<Profesor> findByApellidoContainingIgnoreCase(String apellido);

    // Buscar profesor por nombre y apellido exacto
    Optional<Profesor> findByNombreAndApellido(String nombre, String apellido);
}
