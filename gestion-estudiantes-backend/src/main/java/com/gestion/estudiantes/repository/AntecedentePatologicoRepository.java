package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.AntecedentePatologico;

import java.util.List;

@Repository
public interface AntecedentePatologicoRepository extends JpaRepository<AntecedentePatologico, Long> {

    // Obtener todos los antecedentes patológicos de una ficha médica específica
    List<AntecedentePatologico> findByFichaMedicaId(Long fichaMedicaId);

    // Eliminar todos los antecedentes patológicos de una ficha médica específica
    void deleteByFichaMedicaId(Long fichaMedicaId);
}
