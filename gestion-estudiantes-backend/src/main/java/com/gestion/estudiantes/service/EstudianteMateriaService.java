package com.gestion.estudiantes.service;

import com.gestion.estudiantes.model.EstudianteMateria;
import com.gestion.estudiantes.repository.EstudianteMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar la lógica de negocio relacionada con las inscripciones
 * de estudiantes en materias.
 */
@Service
public class EstudianteMateriaService {

    @Autowired
    private EstudianteMateriaRepository estudianteMateriaRepository;

    /**
     * Obtiene todas las inscripciones de estudiantes en materias.
     * @return Lista de EstudianteMateria.
     */
    public List<EstudianteMateria> obtenerTodasLasMaterias() {
        return estudianteMateriaRepository.findAll();
    }
}
