package com.gestion.estudiantes.repository;

import com.gestion.estudiantes.model.EstudianteMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad EstudianteMateria.
 * Proporciona métodos CRUD para gestionar las inscripciones de estudiantes en materias.
 */
@Repository
public interface EstudianteMateriaRepository extends JpaRepository<EstudianteMateria, Long> {
}
