package com.gestion.estudiantes.service;
import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.HabitoPersonal;
import com.gestion.estudiantes.repository.HabitoPersonalRepository;
import java.util.List;

@Service
public class HabitoPersonalService {
    private final HabitoPersonalRepository habitoPersonalRepository;

    public HabitoPersonalService(HabitoPersonalRepository habitoPersonalRepository) {
        this.habitoPersonalRepository = habitoPersonalRepository;
    }

    //  Obtener hábitos por ficha médica
    public List<HabitoPersonal> obtenerPorFichaMedica(Long fichaMedicaId) {
        return habitoPersonalRepository.findByFichaMedicaId(fichaMedicaId);
    }

    //  Guardar un nuevo hábito personal
    public HabitoPersonal guardar(HabitoPersonal habito) {
        return habitoPersonalRepository.save(habito);
    }

    //  Eliminar un hábito personal por ID
    public void eliminar(Long id) {
        habitoPersonalRepository.deleteById(id);
    }

    //  Eliminar todos los hábitos de una ficha médica
    public void eliminarPorFichaMedica(Long fichaMedicaId) {
        habitoPersonalRepository.deleteByFichaMedicaId(fichaMedicaId);
    }
}