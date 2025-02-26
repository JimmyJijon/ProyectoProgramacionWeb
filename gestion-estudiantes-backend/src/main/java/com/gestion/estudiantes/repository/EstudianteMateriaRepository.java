package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.enums.EstadoMateria;

import java.util.List;
import java.util.Optional;

public interface EstudianteMateriaRepository extends JpaRepository<EstudianteMateria, Long> {

    //  Buscar todas las materias en las que está inscrito un estudiante
    List<EstudianteMateria> findByEstudianteId(Long estudianteId);

    //  Buscar todos los estudiantes inscritos en una materia específica
    List<EstudianteMateria> findByMateriaId(Long materiaId);

    //  Buscar un estudiante en una materia específica (Para verificar si está inscrito)
    Optional<EstudianteMateria> findByEstudianteIdAndMateriaId(Long estudianteId, Long materiaId);

    //  Obtener la nota de un estudiante en una materia específica
    @Query("SELECT em.nota FROM EstudianteMateria em WHERE em.estudiante.id = :estudianteId AND em.materia.id = :materiaId")
    Optional<Double> obtenerNota(@Param("estudianteId") Long estudianteId, @Param("materiaId") Long materiaId);

    //  Actualizar el estado de una materia para un estudiante
    @Query("UPDATE EstudianteMateria em SET em.estado = :estado WHERE em.estudiante.id = :estudianteId AND em.materia.id = :materiaId")
    void actualizarEstado(@Param("estado") EstadoMateria estado, @Param("estudianteId") Long estudianteId, @Param("materiaId") Long materiaId);
}
