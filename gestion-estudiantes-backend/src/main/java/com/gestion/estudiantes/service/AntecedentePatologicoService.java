package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.AntecedentePatologico;
import com.gestion.estudiantes.repository.AntecedentePatologicoRepository;

import java.util.List;

@Service
public class AntecedentePatologicoService {
    private final AntecedentePatologicoRepository antecedentePatologicoRepository;

    public AntecedentePatologicoService(AntecedentePatologicoRepository antecedentePatologicoRepository) {
        this.antecedentePatologicoRepository = antecedentePatologicoRepository;
    }

    //  Obtener antecedentes por ficha médica
    public List<AntecedentePatologico> obtenerPorFichaMedica(Long fichaMedicaId) {
        return antecedentePatologicoRepository.findByFichaMedicaId(fichaMedicaId);
    }

    // Guardar un nuevo antecedente patológico
    public AntecedentePatologico guardar(AntecedentePatologico antecedente) {
        return antecedentePatologicoRepository.save(antecedente);
    }

    //  Eliminar un antecedente patológico por ID
    public void eliminar(Long id) {
        antecedentePatologicoRepository.deleteById(id);
    }

    // Eliminar todos los antecedentes de una ficha médica
    public void eliminarPorFichaMedica(Long fichaMedicaId) {
        antecedentePatologicoRepository.deleteByFichaMedicaId(fichaMedicaId);
    }
}
