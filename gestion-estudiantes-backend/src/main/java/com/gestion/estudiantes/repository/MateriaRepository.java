package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia,Long>{

}
