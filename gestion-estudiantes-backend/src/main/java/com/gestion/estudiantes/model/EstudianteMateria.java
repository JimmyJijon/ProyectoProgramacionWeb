package com.gestion.estudiantes.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// ESTA ES LA TABLA estudiante_materia QUE PERMITE OBTENER LAS NOTAS E INSCRIPCIONES DE UN ESTUDIANTE QUE CURSA UNA MATERIA
@Entity
@Table(name = "estudiante_materia")
public class EstudianteMateria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name="estudiante_id", nullable = false)
  @JsonBackReference(value = "estudiante-materias")
  private Estudiante estudiante;

  @ManyToOne
  @JoinColumn(name="materia_id", nullable = false)
  @JsonBackReference(value = "materia-estudiantes")
  private Materia materia;

  @Column(name="fecha_inscripcion")
  private LocalDate fechaInscripcion;

  // Campo existente de nota, que se podría usar como notaFinal o eliminarlo si se crean notas individuales
  @Column(name = "nota")
  private Double nota;

  // Nuevos campos para almacenar notas parciales y otros datos requeridos en la vista
  @Column(name = "nota1")
  private Double nota1;

  @Column(name = "nota2")
  private Double nota2;

  @Column(name = "nota3")
  private Double nota3;

  @Column(name = "nota_final")
  private Double notaFinal;

  @Column(name = "porcentaje")
  private String porcentaje;

  @Column(name = "estado")
  private String estado;

  // Constructores, getters y setters

  public EstudianteMateria() {}

  // Puedes incluir un constructor completo o los necesarios
  // Getters y Setters para cada campo

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Estudiante getEstudiante() { return estudiante; }
  public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

  public Materia getMateria() { return materia; }
  public void setMateria(Materia materia) { this.materia = materia; }

  public LocalDate getFechaInscripcion() { return fechaInscripcion; }
  public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

  public Double getNota() { return nota; }
  public void setNota(Double nota) { this.nota = nota; }

  public Double getNota1() { return nota1; }
  public void setNota1(Double nota1) { this.nota1 = nota1; }

  public Double getNota2() { return nota2; }
  public void setNota2(Double nota2) { this.nota2 = nota2; }

  public Double getNota3() { return nota3; }
  public void setNota3(Double nota3) { this.nota3 = nota3; }

  public Double getNotaFinal() { return notaFinal; }
  public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal; }

  public String getPorcentaje() { return porcentaje; }
  public void setPorcentaje(String porcentaje) { this.porcentaje = porcentaje; }

  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }
}
