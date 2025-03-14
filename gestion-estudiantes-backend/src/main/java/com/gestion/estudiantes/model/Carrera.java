package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
    @JsonBackReference("estudiante-carrera") 
    private List<Estudiante> estudiantes;

    
    @OneToOne(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("carrera-malla")
    private MallaCurricular mallaCurricular;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public MallaCurricular getMallaCurricular() {
        return mallaCurricular;
    }

    public void setMallaCurricular(MallaCurricular mallaCurricular) {
        this.mallaCurricular = mallaCurricular;
    }
}
