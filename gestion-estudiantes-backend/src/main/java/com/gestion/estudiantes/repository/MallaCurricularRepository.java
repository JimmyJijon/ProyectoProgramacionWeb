package com.gestion.estudiantes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.MallaCurricular;

@Repository
public interface MallaCurricularRepository extends JpaRepository<MallaCurricular, Long> {

    //Obtener la malla curricular de una carrera específica
    Optional<MallaCurricular> findByCarreraId(Long carreraId);

    //Eliminar la malla curricular de una carrera específica
    void deleteByCarreraId(Long carreraId);
}
