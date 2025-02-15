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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Estudiante")
public class Estudiante {
  //ESTA ES LA TABLA ESTUDIANTE QUE APARECE EN POSTGRES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name="nombre")
  private String nombre;

  @Column(name="apellido")
  private String apellido;

  @Column(name = "email")
  private String email;

  @Column(name = "Cedula")
  private String cedula;

  @Column(name= "fecha_nacimiento")
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


  @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
  @JsonManagedReference // 🔹 Indica que esta es la parte "principal" de la relación
  private List<EstudianteMateria> materias;

}
