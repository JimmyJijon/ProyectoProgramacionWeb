package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ficha_medica")
@Data
public class FichaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String antecedentesPatologicos;
    private String habitosPersonales;

    @OneToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;
}

