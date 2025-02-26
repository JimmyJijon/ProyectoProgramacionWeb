package com.gestion.estudiantes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestion.estudiantes.model.Materia;
import com.gestion.estudiantes.repository.MateriaRepository;

@Service
public class MateriaService {
    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    // Obtener todas las materias
    public List<Materia> obtenerTodas() {
        return materiaRepository.findAll();
    }

    // Obtener materia por ID
    public Materia obtenerPorId(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia con ID " + id + " no encontrada"));
    }

    // Buscar materia por código
    public Optional<Materia> obtenerPorCodigo(String codigo) {
        return materiaRepository.findByCodigo(codigo);
    }

    // Buscar materias por nombre
    public List<Materia> buscarPorNombre(String nombre) {
        return materiaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Obtener materias activas
    public List<Materia> obtenerMateriasActivas() {
        return materiaRepository.findByActivoTrue();
    }

    // Obtener materias de un profesor
    public List<Materia> obtenerMateriasPorProfesor(Long profesorId) {
        return materiaRepository.findByProfesorId(profesorId);
    }

    // Obtener materias por malla curricular
    public List<Materia> obtenerMateriasPorMalla(Long mallaId) {
        return materiaRepository.findByMallaCurricularId(mallaId);
    }

    // Obtener materias con créditos mayores a X
    public List<Materia> obtenerMateriasPorCreditos(int creditos) {
        return materiaRepository.findByCreditosGreaterThanEqual(creditos);
    }

    // Guardar una nueva materia
    public Materia guardarMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    // Actualizar materia
    public Materia actualizarMateria(Long id, Materia materiaRequest) {
        Materia materia = obtenerPorId(id);

        materia.setNombre(materiaRequest.getNombre());
        materia.setCodigo(materiaRequest.getCodigo());
        materia.setActivo(materiaRequest.isActivo());
        materia.setCreditos(materiaRequest.getCreditos());
        materia.setProfesor(materiaRequest.getProfesor());
        materia.setMallaCurricular(materiaRequest.getMallaCurricular());

        return materiaRepository.save(materia);
    }

    // Eliminar materia (Soft Delete)
    public void desactivarMateria(Long id) {
        Materia materia = obtenerPorId(id);
        materia.setActivo(false);
        materiaRepository.save(materia);
    }
}
