package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.estudiantes.model.Estudiante;

/*esta clase permite utilizar los metodos CRUD que interactuan con la tabla estudiante en POSTGRES 
(Spring permite usarlos mediante extends JpaRepository, sin necesidad de escribirlos manualmente)*/
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long>{

}
