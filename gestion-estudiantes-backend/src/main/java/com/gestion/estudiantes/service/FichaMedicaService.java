package com.gestion.estudiantes.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.FichaMedica;
import com.gestion.estudiantes.repository.FichaMedicaRepository;

@Service
public class FichaMedicaService {
    private final FichaMedicaRepository fichaMedicaRepository;

    public FichaMedicaService(FichaMedicaRepository fichaMedicaRepository) {
        this.fichaMedicaRepository = fichaMedicaRepository;
    }

    // Obtener ficha médica por estudiante
    public Optional<FichaMedica> obtenerPorEstudiante(Long estudianteId) {
        return fichaMedicaRepository.findByEstudianteId(estudianteId);
    }

    // Guardar una nueva ficha médica (verifica que no exista)
    public FichaMedica guardarFichaMedica(FichaMedica fichaMedica) {
        if (fichaMedicaRepository.existsByEstudianteId(fichaMedica.getEstudiante().getId())) {
            throw new RuntimeException("El estudiante ya tiene una ficha médica registrada.");
        }
        return fichaMedicaRepository.save(fichaMedica);
    }

    // Actualizar una ficha médica
    public FichaMedica actualizarFichaMedica(Long estudianteId, FichaMedica fichaMedicaRequest) {
        FichaMedica fichaMedica = fichaMedicaRepository.findByEstudianteId(estudianteId)
                .orElseThrow(() -> new RuntimeException("Ficha médica no encontrada para el estudiante con ID " + estudianteId));

        fichaMedica.setAntecedentesPatologicos(fichaMedicaRequest.getAntecedentesPatologicos());
        fichaMedica.setHabitosPersonales(fichaMedicaRequest.getHabitosPersonales());

        return fichaMedicaRepository.save(fichaMedica);
    }

    // Eliminar una ficha médica por estudiante
    public void eliminarFichaMedica(Long estudianteId) {
        fichaMedicaRepository.deleteByEstudianteId(estudianteId);
    }
}
