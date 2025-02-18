package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.estudiantes.model.Materia;
/*esta clase permite utilizar los metodos CRUD que interactuan con la tabla materia en POSTGRES 
(Spring permite usarlos mediante extends JpaRepository, sin necesidad de escribirlos manualmente)*/
@Repository
public interface MateriaRepository extends JpaRepository<Materia,Long>{

}
