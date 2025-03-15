package com.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull; // Import the NonNull annotation from Spring
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    
    /**
     * Overrides the default findById method to enforce non-null constraints.
     *
     * @param id the identifier of the Aula, must not be null.
     * @return a non-null Optional containing the Aula if found, otherwise an empty Optional.
     */
    @Override
    @NonNull // Ensures the return type is non-null, fulfilling the inherited contract.
    Optional<Aula> findById(@NonNull Long id);  // The parameter is explicitly marked as non-null.

    // Buscar aula por nombre.
    Optional<Aula> findByNombre(String nombre);

    // Buscar aulas por edificio.
    List<Aula> findByEdificio(String edificio);

    // Eliminar un aula por nombre.
    void deleteByNombre(String nombre);

    // Eliminar aulas por edificio.
    void deleteByEdificio(String edificio);
}
