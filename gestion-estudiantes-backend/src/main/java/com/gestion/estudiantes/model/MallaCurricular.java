package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "malla_curricular")
@Data
public class MallaCurricular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "mallaCurricular")
    private List<Materia> materias;

    @OneToOne
    @JoinColumn(name = "carrera_id", nullable = false)
    private Carrera carrera;
}

