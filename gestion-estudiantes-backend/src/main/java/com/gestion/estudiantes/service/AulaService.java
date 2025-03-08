package com.gestion.estudiantes.service;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.Aula;
import com.gestion.estudiantes.repository.AulaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {
    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    //buscar un aula por ID
    public Optional<Aula> obtenerAulaPorId(Long id) {
        return aulaRepository.findById(id);
    }

    //obtener aula por nombre
    public Optional<Aula> obtenerPorNombre(String nombre) {
        return aulaRepository.findByNombre(nombre);
    }

    //obtener aulas por edificio
    public List<Aula> obtenerPorEdificio(String edificio) {
        return aulaRepository.findByEdificio(edificio);
    }

    //guardar una nueva aula
    public Aula guardar(Aula aula) {
        return aulaRepository.save(aula);
    }

    //eliminar un aula por ID
    public void eliminar(Long id) {
        aulaRepository.deleteById(id);
    }

    //rliminar un aula por nombre
    public void eliminarPorNombre(String nombre) {
        aulaRepository.deleteByNombre(nombre);
    }

    //eliminar todas las aulas de un edificio
    public void eliminarPorEdificio(String edificio) {
        aulaRepository.deleteByEdificio(edificio);
    }
}
