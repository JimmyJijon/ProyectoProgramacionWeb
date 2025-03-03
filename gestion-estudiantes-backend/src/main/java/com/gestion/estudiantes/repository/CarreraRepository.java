package com.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.estudiantes.model.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    // Buscar carrera por nombre (debe ser único)
    Optional<Carrera> findByNombre(String nombre);

    // Listar todas las carreras ordenadas por nombre
    List<Carrera> findAllByOrderByNombreAsc();

    // Contar cuántos estudiantes están inscritos en una carrera específica
    @Query("SELECT COUNT(e) FROM Estudiante e WHERE e.carrera.id = :carreraId")
    long contarEstudiantesPorCarrera(@Param("carreraId") Long carreraId);
}
