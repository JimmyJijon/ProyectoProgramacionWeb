package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.Horario;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    //Obtener todos los horarios de una materia específica
    List<Horario> findByMateriaId(Long materiaId);

    //Obtener todos los horarios de un aula específica
    List<Horario> findByAulaId(Long aulaId);

    //Eliminar todos los horarios de una materia específica
    void deleteByMateriaId(Long materiaId);

    //Eliminar todos los horarios de un aula específica
    void deleteByAulaId(Long aulaId);
}
