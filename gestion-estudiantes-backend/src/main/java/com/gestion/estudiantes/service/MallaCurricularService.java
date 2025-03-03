package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.MallaCurricular;
import com.gestion.estudiantes.repository.MallaCurricularRepository;

import java.util.Optional;

@Service
public class MallaCurricularService {
    private final MallaCurricularRepository mallaCurricularRepository;

    public MallaCurricularService(MallaCurricularRepository mallaCurricularRepository) {
        this.mallaCurricularRepository = mallaCurricularRepository;
    }

    //Obtener la malla curricular por carrera
    public Optional<MallaCurricular> obtenerPorCarrera(Long carreraId) {
        return mallaCurricularRepository.findByCarreraId(carreraId);
    }

    //Guardar una nueva malla curricular
    public MallaCurricular guardar(MallaCurricular mallaCurricular) {
        return mallaCurricularRepository.save(mallaCurricular);
    }

    //Eliminar una malla curricular por ID
    public void eliminar(Long id) {
        mallaCurricularRepository.deleteById(id);
    }

    //Eliminar la malla curricular de una carrera específica
    public void eliminarPorCarrera(Long carreraId) {
        mallaCurricularRepository.deleteByCarreraId(carreraId);
    }
}
