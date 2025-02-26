package com.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.estudiantes.model.EventoCalendario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoCalendarioRepository extends JpaRepository<EventoCalendario, Long> {

    //Buscar eventos por título
    Optional<EventoCalendario> findByTitulo(String titulo);

    //Buscar eventos por fecha
    List<EventoCalendario> findByFecha(LocalDate fecha);

    //Buscar eventos en un rango de fechas
    List<EventoCalendario> findByFechaBetween(LocalDate inicio, LocalDate fin);

    //Eliminar evento por título
    void deleteByTitulo(String titulo);

    //Eliminar eventos por fecha
    void deleteByFecha(LocalDate fecha);
}