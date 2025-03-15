package com.gestion.estudiantes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.estudiantes.dto.MateriaEstudianteDTO;
import com.gestion.estudiantes.exception.ResourceNotFoundException;
import com.gestion.estudiantes.model.Estudiante;
import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.model.Materia;
import com.gestion.estudiantes.repository.EstudianteMateriaRepository;
import com.gestion.estudiantes.repository.EstudianteRepository;
import com.gestion.estudiantes.repository.MateriaRepository;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private EstudianteMateriaRepository estudianteMateriaRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // Listar todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Guardar un nuevo estudiante
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Obtener estudiante por ID con manejo de error
    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ID " + id + " no existe"));
    }

    // Actualizar un estudiante
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteRequest) {
        Estudiante estudiante = obtenerEstudiantePorId(id); // Busca el estudiante o lanza error

        estudiante.setNombre(estudianteRequest.getNombre());
        estudiante.setApellido(estudianteRequest.getApellido());
        estudiante.setEmailInstitucional(estudianteRequest.getEmailInstitucional());
        estudiante.setEmailPersonal(estudianteRequest.getEmailPersonal());
        estudiante.setCedula(estudianteRequest.getCedula());
        estudiante.setFechaNacimiento(estudianteRequest.getFechaNacimiento());
        estudiante.setEdad(estudianteRequest.getEdad());
        estudiante.setEstadoCivil(estudianteRequest.getEstadoCivil());
        estudiante.setPaisNacimiento(estudianteRequest.getPaisNacimiento());
        estudiante.setCelular(estudianteRequest.getCelular());

        return estudianteRepository.save(estudiante);
    }

    // Desactivar estudiante (Soft Delete)
    public Map<String, Boolean> desactivarEstudiante(Long id) {
        Estudiante estudiante = obtenerEstudiantePorId(id);
        estudiante.setActivo(false);
        estudianteRepository.save(estudiante);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public List<MateriaEstudianteDTO> findMateriasByEstudianteId(Long estudianteId) {
        List<EstudianteMateria> estudianteMaterias = estudianteMateriaRepository.findByEstudianteId(estudianteId);
        return estudianteMaterias.stream().map(em -> {
            MateriaEstudianteDTO dto = new MateriaEstudianteDTO();
            dto.setId(em.getMateria().getId());
            dto.setNombre(em.getMateria().getNombre());
            dto.setCodigo(em.getMateria().getCodigo());
            dto.setActivo(em.getMateria().isActivo());
            dto.setProfesorNombre(em.getMateria().getProfesor().getNombre());
            dto.setCreditos(em.getMateria().getCreditos());
            dto.setNota(em.getNota());
            dto.setEstado(em.getEstado().name()); // Convertir enum a String
            dto.setPeriodoAcademico(em.getPeriodoAcademico());
            dto.setFechaInicio(em.getFechaInicio());
            dto.setFechaFin(em.getFechaFin());
            dto.setFechaInscripcion(em.getFechaInscripcion());
            return dto;
        }).collect(Collectors.toList());
    }
}
