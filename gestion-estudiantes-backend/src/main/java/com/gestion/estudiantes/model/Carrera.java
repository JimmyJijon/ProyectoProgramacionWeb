package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carrera")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    
    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true) // Relación con Estudiante
    @JsonIgnoreProperties("carrera") // 🔹 Evita la recursión infinita
    @JsonIgnore
    private List<Estudiante> estudiantes;


    @OneToOne(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    private MallaCurricular mallaCurricular;
    
}
