package com.gestion.estudiantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.Materia;
import com.gestion.estudiantes.model.enums.EstadoMateria;
import com.gestion.estudiantes.repository.EstudianteMateriaRepository;
import com.gestion.estudiantes.repository.EstudianteRepository;
import com.gestion.estudiantes.repository.MateriaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteMateriaService {

    @Autowired
    private EstudianteMateriaRepository estudianteMateriaRepository;
    private final EstudianteService estudianteService;
    private final MateriaService materiaService;

    
    public EstudianteMateriaService(
            EstudianteMateriaRepository estudianteMateriaRepository,
            EstudianteService estudianteService,
            MateriaService materiaService) {
        this.estudianteMateriaRepository = estudianteMateriaRepository;
        this.estudianteService = estudianteService;
        this.materiaService = materiaService;
    }

  
    public List<EstudianteMateria> obtenerMateriasPorEstudiante(Long estudianteId) {
        return estudianteMateriaRepository.findByEstudianteId(estudianteId);
    }

   
    public List<EstudianteMateria> obtenerEstudiantesPorMateria(Long materiaId) {
        return estudianteMateriaRepository.findByMateriaId(materiaId);
    }

  
    public Optional<Double> obtenerNota(Long estudianteId, Long materiaId) {
        return estudianteMateriaRepository.obtenerNota(estudianteId, materiaId);
    }

  
    public void actualizarEstadoMateria(Long estudianteId, Long materiaId, EstadoMateria nuevoEstado) {
        estudianteMateriaRepository.actualizarEstado(nuevoEstado, estudianteId, materiaId);
    }

    @Autowired
    private EstudianteRepository estudianteRepository;  

    @Autowired
    private MateriaRepository materiaRepository;
  
    public EstudianteMateria inscribirEstudianteEnMateria(Long estudianteId, Long materiaId, String periodoAcademico, 
                                                      String nota, EstadoMateria estadoMateria, 
                                                      LocalDate fechaInicio, LocalDate fechaFin) {
    // Verificar si el estudiante ya está inscrito
    Optional<EstudianteMateria> existente = estudianteMateriaRepository.findByEstudianteIdAndMateriaId(estudianteId, materiaId);
    
    if (existente.isPresent()) {
        return existente.get(); // Si ya está inscrito, devuelve la inscripción existente
    }

    // Buscar estudiante y materia
    Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
    Materia materia = materiaService.obtenerPorId(materiaId);

    // **Valores Automáticos**
    LocalDate fechaActual = LocalDate.now();
    periodoAcademico = (periodoAcademico != null) ? periodoAcademico : "2025-1"; // Valor por defecto
    estadoMateria = (estadoMateria != null) ? estadoMateria : EstadoMateria.EN_CURSO; // Estado inicial
    fechaInicio = (fechaInicio != null) ? fechaInicio : fechaActual;
    fechaFin = (fechaFin != null) ? fechaFin : fechaInicio.plusMonths(4); // Por defecto dura 4 meses

    // Crear nueva inscripción
    EstudianteMateria nuevaInscripcion = new EstudianteMateria();
    nuevaInscripcion.setEstudiante(estudiante);
    nuevaInscripcion.setMateria(materia);
    nuevaInscripcion.setFechaInscripcion(fechaActual);
    nuevaInscripcion.setEstado(estadoMateria);
    nuevaInscripcion.setPeriodoAcademico(periodoAcademico);
    nuevaInscripcion.setFechaInicio(fechaInicio);
    nuevaInscripcion.setFechaFin(fechaFin);
    nuevaInscripcion.setNota(Double.parseDouble(nota));

        // Guardar en la base de datos
        return estudianteMateriaRepository.save(nuevaInscripcion);
    }

    public List<EstudianteMateria> getAllEstudianteMaterias() {
        return estudianteMateriaRepository.findAll();
    }

    public EstudianteMateria saveEstudianteMateria(EstudianteMateria estudianteMateria) {
        return estudianteMateriaRepository.save(estudianteMateria);
    }

    public EstudianteMateria getEstudianteMateria(Long id) {
        return estudianteMateriaRepository.findById(id).orElse(null);
    }

    public void deleteEstudianteMateria(Long id) {
        estudianteMateriaRepository.deleteById(id);
    }
}
