package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "matriculacion")
@Data
public class Matriculacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    private String fechaMatricula;
}

