package com.gestion.estudiantes.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestion.estudiantes.model.enums.DiaSemana;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "horario")
@Data
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaSemana diaSemana; //enum para los días

    @Column(nullable = false)
    private LocalTime horaInicio; 

    @Column(nullable = false)
    private LocalTime horaFin; 

    @ManyToOne
    @JoinColumn(name = "materia_id")
    @JsonBackReference("materia-horario")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
}
