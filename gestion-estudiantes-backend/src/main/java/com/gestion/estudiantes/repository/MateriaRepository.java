package com.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    // Buscar materia por código (debe ser único)
    Optional<Materia> findByCodigo(String codigo);

    // Buscar materias por nombre (coincidencias parciales, ignorando mayúsculas/minúsculas)
    List<Materia> findByNombreContainingIgnoreCase(String nombre);

    // Obtener todas las materias activas
    List<Materia> findByActivoTrue();

    // Obtener materias asignadas a un profesor específico
    List<Materia> findByProfesorId(Long profesorId);

    // Obtener materias asociadas a una malla curricular
    @Query("SELECT m FROM Materia m JOIN m.mallasCurriculares mc WHERE mc.id = :mallaId")
    List<Materia> findByMallaCurricular(@Param("mallaId") Long mallaId);

    // Obtener materias con más de X créditos
    List<Materia> findByCreditosGreaterThanEqual(int creditos);

    // Obtener materias asignadas a un estudiante específico
    @Query("SELECT em.materia FROM EstudianteMateria em WHERE em.estudiante.id = :estudianteId")
    List<Materia> findByEstudianteId(@Param("estudianteId") Long estudianteId);
}
