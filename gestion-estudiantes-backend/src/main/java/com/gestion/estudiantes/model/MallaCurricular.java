package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "malla_curricular")
@Data
public class MallaCurricular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "malla_materia", //tabla intermedia
        joinColumns = @JoinColumn(name = "malla_curricular_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_id")
    )

    //@JsonIgnore
    private List<Materia> materias;

    @OneToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    @JsonBackReference("carrera-malla")
    private Carrera carrera;
}


