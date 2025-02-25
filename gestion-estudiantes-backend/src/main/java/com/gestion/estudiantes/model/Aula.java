package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aula")
@Data
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String edificio;
}
