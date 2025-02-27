package com.gestion.estudiantes.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//ESTA ES LA TABLA estudiante_materia QUE PERMITE OBTENER LAS NOTAS E INSCRIPCIONES DE UN ESTUDIANTE QUE CURSA UNA MATERIA


@Entity
@Table(name = "estudiante_materia")
@Data
public class EstudianteMateria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name="estudiante_id", nullable = false)
  @JsonBackReference("estudiante-materia")
  private Estudiante estudiante;

  @ManyToOne
  @JoinColumn(name="materia_id", nullable = false)
  @JsonBackReference("materia-estudiante")
  private Materia materia;

  @Column(name="fecha_inscripcion")
  private LocalDate fechaInscripcion;

  @Column(name = "nota")
  private String nota;

  @Enumerated(EnumType.STRING)
  private EstadoMateria estado; // Cada estudiante tiene su propio estado /* EN_CURSO - APROBADA - REPROBADA - CANCELADA */
  
  @Column
  private String periodoAcademico; // ej "2024-1"
  
  
}

