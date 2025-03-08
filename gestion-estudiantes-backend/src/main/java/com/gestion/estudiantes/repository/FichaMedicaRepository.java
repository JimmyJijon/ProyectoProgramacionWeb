package com.gestion.estudiantes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.estudiantes.model.FichaMedica;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {

    // Buscar ficha médica por ID del estudiante (Debe ser única)
    Optional<FichaMedica> findByEstudianteId(Long estudianteId);

    //  Verificar si un estudiante ya tiene ficha médica
    boolean existsByEstudianteId(Long estudianteId);

    //  Eliminar ficha médica por ID del estudiante
    void deleteByEstudianteId(Long estudianteId);
}
