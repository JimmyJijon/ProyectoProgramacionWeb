package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.Materia;
import com.gestion.estudiantes.model.enums.EstadoMateria;
import com.gestion.estudiantes.repository.EstudianteMateriaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteMateriaService {
    private final EstudianteMateriaRepository estudianteMateriaRepository;
    private final EstudianteService estudianteService;
    private final MateriaService materiaService;

    // Constructor único con inyección de dependencias
    public EstudianteMateriaService(
            EstudianteMateriaRepository estudianteMateriaRepository,
            EstudianteService estudianteService,
            MateriaService materiaService) {
        this.estudianteMateriaRepository = estudianteMateriaRepository;
        this.estudianteService = estudianteService;
        this.materiaService = materiaService;
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

    // Método para inscribir a un estudiante en una materia
    public EstudianteMateria inscribirEstudianteEnMateria(Long estudianteId, Long materiaId, String periodoAcademico) {
        // Verificar si el estudiante ya está inscrito en la materia
        if (estudianteMateriaRepository.findByEstudianteIdAndMateriaId(estudianteId, materiaId).isPresent()) {
            throw new IllegalArgumentException("El estudiante ya está inscrito en esta materia.");
        }

        // Buscar estudiante y materia utilizando los servicios existentes
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        Materia materia = materiaService.obtenerPorId(materiaId);

        // Crear la nueva inscripción
        EstudianteMateria nuevaInscripcion = new EstudianteMateria();
        nuevaInscripcion.setEstudiante(estudiante);
        nuevaInscripcion.setMateria(materia);
        nuevaInscripcion.setFechaInscripcion(LocalDate.now());
        nuevaInscripcion.setEstado(EstadoMateria.EN_CURSO);
        nuevaInscripcion.setPeriodoAcademico(periodoAcademico);

        // Guardar en la base de datos
        return estudianteMateriaRepository.save(nuevaInscripcion);
    }
}

