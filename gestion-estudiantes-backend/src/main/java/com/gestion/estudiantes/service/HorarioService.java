package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.Horario;
import com.gestion.estudiantes.repository.HorarioRepository;

import java.util.List;

@Service
public class HorarioService {
    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    //  Obtener horarios por materia
    public List<Horario> obtenerPorMateria(Long materiaId) {
        return horarioRepository.findByMateriaId(materiaId);
    }

    //  Obtener horarios por aula
    public List<Horario> obtenerPorAula(Long aulaId) {
        return horarioRepository.findByAulaId(aulaId);
    }

    //  Guardar un nuevo horario
    public Horario guardar(Horario horario) {
        return horarioRepository.save(horario);
    }

    //Eliminar un horario por ID
    public void eliminar(Long id) {
        horarioRepository.deleteById(id);
    }

    //Eliminar todos los horarios de una materia
    public void eliminarPorMateria(Long materiaId) {
        horarioRepository.deleteByMateriaId(materiaId);
    }

    //Eliminar todos los horarios de un aula
    public void eliminarPorAula(Long aulaId) {
        horarioRepository.deleteByAulaId(aulaId);
    }
}
