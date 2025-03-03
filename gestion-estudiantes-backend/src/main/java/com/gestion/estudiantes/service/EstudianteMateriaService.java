package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.enums.EstadoMateria;
import com.gestion.estudiantes.repository.EstudianteMateriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteMateriaService {
    private final EstudianteMateriaRepository estudianteMateriaRepository;

    public EstudianteMateriaService(EstudianteMateriaRepository estudianteMateriaRepository) {
        this.estudianteMateriaRepository = estudianteMateriaRepository;
    }

    // Obtener las materias en las que está inscrito un estudiante
    
    public List<EstudianteMateria> obtenerMateriasPorEstudiante(Long estudianteId) {
    return estudianteMateriaRepository.findByEstudianteId(estudianteId);
    }


    // Obtener los estudiantes inscritos en una materia
    public List<EstudianteMateria> obtenerEstudiantesPorMateria(Long materiaId) {
        return estudianteMateriaRepository.findByMateriaId(materiaId);
    }

    // Obtener la nota de un estudiante en una materia específica
    public Optional<Double> obtenerNota(Long estudianteId, Long materiaId) {
        return estudianteMateriaRepository.obtenerNota(estudianteId, materiaId);
    }

    // Actualizar el estado de la materia para un estudiante
    public void actualizarEstadoMateria(Long estudianteId, Long materiaId, EstadoMateria nuevoEstado) {
        estudianteMateriaRepository.actualizarEstado(nuevoEstado, estudianteId, materiaId);
    }
}
