package com.gestion.estudiantes.repository;
import com.gestion.estudiantes.model.EventoCalendario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventoCalendarioRepository extends JpaRepository<EventoCalendario, Long> {
}
