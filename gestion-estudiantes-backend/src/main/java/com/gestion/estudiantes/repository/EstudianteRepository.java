package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.estudiantes.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    //  Buscar estudiante por cédula
    Optional<Estudiante> findByCedula(String cedula);

    // Buscar estudiante por email institucional
    Optional<Estudiante> findByEmailInstitucional(String emailInstitucional);

    //  Buscar estudiantes por nombre (puede devolver más de uno)
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);

    //  Buscar todos los estudiantes activos
    List<Estudiante> findByActivoTrue();

    // Buscar estudiantes por carrera
    List<Estudiante> findByCarreraId(Long carreraId);

    //  Contar cuántos estudiantes están en una carrera específica
    @Query("SELECT COUNT(e) FROM Estudiante e WHERE e.carrera.id = :carreraId")
    long contarEstudiantesPorCarrera(@Param("carreraId") Long carreraId);
}
