package com.gestion.estudiantes.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "email_institucional")
  private String emailInstitucional;

  @Column(name = "email_personal")
  private String emailPersonal;

  @Column(name = "cedula")
  private String cedula;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  @Column(name = "edad")
  private Integer edad;

  @Column(name = "estado_civil")
  private String estadoCivil;

  @Column(name = "pais_nacimiento")
  private String paisNacimiento;

  @Column(name = "contacto")
  private String celular;

  @Column(name = "activo")
  private boolean activo = true;

  @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("estudiante-materia")
  private List<EstudianteMateria> estudianteMaterias;

  @ManyToOne
  @JoinColumn(name = "carrera_id", nullable = false)
  @JsonManagedReference("estudiante-carrera")
  private Carrera carrera;

  // Getters y Setters
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getEmailInstitucional() {
    return emailInstitucional;
  }

  public void setEmailInstitucional(String emailInstitucional) {
    this.emailInstitucional = emailInstitucional;
  }

  public String getEmailPersonal() {
    return emailPersonal;
  }

  public void setEmailPersonal(String emailPersonal) {
    this.emailPersonal = emailPersonal;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public String getPaisNacimiento() {
    return paisNacimiento;
  }

  public void setPaisNacimiento(String paisNacimiento) {
    this.paisNacimiento = paisNacimiento;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public List<EstudianteMateria> getEstudianteMaterias() {
    return estudianteMaterias;
  }

  public void setEstudianteMaterias(List<EstudianteMateria> estudianteMaterias) {
    this.estudianteMaterias = estudianteMaterias;
  }

  public Carrera getCarrera() {
    return carrera;
  }

  public void setCarrera(Carrera carrera) {
    this.carrera = carrera;
  }
}
