package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.HabitoPersonal;

import java.util.List;

@Repository
public interface HabitoPersonalRepository extends JpaRepository<HabitoPersonal, Long> {

    //  Obtener todos los hábitos personales de una ficha médica específica
    List<HabitoPersonal> findByFichaMedicaId(Long fichaMedicaId);

    //  Eliminar todos los hábitos personales de una ficha médica específica
    void deleteByFichaMedicaId(Long fichaMedicaId);
}