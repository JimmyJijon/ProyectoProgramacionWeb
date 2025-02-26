package com.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    // Buscar un aula por nombre
    Optional<Aula> findByNombre(String nombre);

    // Buscar aulas por edificio
    List<Aula> findByEdificio(String edificio);

    // Eliminar un aula por nombre
    void deleteByNombre(String nombre);

    // Eliminar aulas por edificio
    void deleteByEdificio(String edificio);
}
