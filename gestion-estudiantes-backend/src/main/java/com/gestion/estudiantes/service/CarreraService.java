package com.gestion.estudiantes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.Carrera;
import com.gestion.estudiantes.repository.CarreraRepository;

@Service
public class CarreraService {
    private final CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    //Obtener todas las carreras ordenadas
    public List<Carrera> obtenerTodas() {
        return carreraRepository.findAllByOrderByNombreAsc();
    }

    //Obtener carrera por ID
    public Carrera obtenerPorId(Long id) {
        return carreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera con ID " + id + " no encontrada"));
    }

    //Buscar carrera por nombre
    public Optional<Carrera> obtenerPorNombre(String nombre) {
        return carreraRepository.findByNombre(nombre);
    }

    //Guardar una nueva carrera (verifica que no exista)
    public Carrera guardarCarrera(Carrera carrera) {
        if (carreraRepository.findByNombre(carrera.getNombre()).isPresent()) {
            throw new RuntimeException("La carrera ya existe");
        }
        return carreraRepository.save(carrera);
    }

    //Actualizar carrera
    public Carrera actualizarCarrera(Long id, Carrera carreraRequest) {
        Carrera carrera = obtenerPorId(id);
        carrera.setNombre(carreraRequest.getNombre());
        return carreraRepository.save(carrera);
    }

    //Eliminar carrera (Solo si no tiene estudiantes inscritos)
    public void eliminarCarrera(Long id) {
        long estudiantes = carreraRepository.contarEstudiantesPorCarrera(id);
        if (estudiantes > 0) {
            throw new RuntimeException("No se puede eliminar la carrera porque tiene estudiantes inscritos");
        }
        carreraRepository.deleteById(id);
    }
}
