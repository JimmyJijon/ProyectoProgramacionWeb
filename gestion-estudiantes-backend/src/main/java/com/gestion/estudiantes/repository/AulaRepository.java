package com.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    //buscar un aula por id
    Optional<Aula> findById(Long id);

    //buscar aula por nombre
    Optional<Aula> findByNombre(String nombre);

    //buscar aulas por edificio
    List<Aula> findByEdificio(String edificio);

    //eliminar un aula por nombre
    void deleteByNombre(String nombre);

    //eliminar aulas por edificio
    void deleteByEdificio(String edificio);
}
