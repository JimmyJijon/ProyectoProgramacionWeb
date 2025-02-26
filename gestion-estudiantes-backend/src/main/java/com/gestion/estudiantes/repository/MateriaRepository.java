package com.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.estudiantes.model.Materia;


/*esta clase permite utilizar los metodos CRUD que interactuan con la tabla materia en POSTGRES 
(Spring permite usarlos mediante extends JpaRepository, sin necesidad de escribirlos manualmente)*/

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    //  Buscar materia por código (debe ser único)
    Optional<Materia> findByCodigo(String codigo);

    //  Buscar materias por nombre (coincidencias parciales, ignorando mayúsculas/minúsculas)
    List<Materia> findByNombreContainingIgnoreCase(String nombre);

    //  Obtener todas las materias activas
    List<Materia> findByActivoTrue();

    //  Obtener materias asignadas a un profesor específico
    List<Materia> findByProfesorId(Long profesorId);

    //  Obtener materias de una malla curricular
    List<Materia> findByMallaCurricularId(Long mallaCurricularId);

    //  Obtener materias con más de X créditos
    List<Materia> findByCreditosGreaterThanEqual(int creditos);
}