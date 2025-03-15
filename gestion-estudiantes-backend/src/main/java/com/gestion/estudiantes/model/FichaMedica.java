package com.gestion.estudiantes.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ficha_medica")
@Data
public class FichaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "fichaMedica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AntecedentePatologico> antecedentesPatologicos;

    @OneToMany(mappedBy = "fichaMedica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitoPersonal> habitosPersonales;

    @OneToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AntecedentePatologico> getAntecedentesPatologicos() {
        return antecedentesPatologicos;
    }

    public void setAntecedentesPatologicos(List<AntecedentePatologico> antecedentesPatologicos) {
        this.antecedentesPatologicos = antecedentesPatologicos;
    }

    public List<HabitoPersonal> getHabitosPersonales() {
        return habitosPersonales;
    }

    public void setHabitosPersonales(List<HabitoPersonal> habitosPersonales) {
        this.habitosPersonales = habitosPersonales;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}

