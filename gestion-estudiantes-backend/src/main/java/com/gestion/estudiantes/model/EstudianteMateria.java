package com.gestion.estudiantes.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestion.estudiantes.model.enums.EstadoMateria;

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
//ESTA ES LA TABLA estudiante_materia QUE PERMITE OBTENER LAS NOTAS E INSCRIPCIONES DE UN ESTUDIANTE QUE CURSA UNA MATERIA
@Entity
@Table(name = "estudiante_materia")
public class EstudianteMateria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name="estudiante_id", nullable = false)
  @JsonBackReference(value = "estudiante-materias") // 🔹 Evita la recursión infinita al serializar
  private Estudiante estudiante;

  @ManyToOne
  @JoinColumn(name="materia_id", nullable = false)
  @JsonBackReference(value = "materia-estudiantes") // 🔹 Evita recursión infinita en Materia
  private Materia materia;

  @Column(name="fecha_inscripcion")
  private LocalDate fechaInscripcion;

  @Column(name = "nota")
  private Double nota;

  @Enumerated(EnumType.STRING)
  private EstadoMateria estado; // Cada estudiante tendrá su propio estado
  
  @Column
  private String periodoAcademico; // Ejemplo: "2024-1"
  
}
