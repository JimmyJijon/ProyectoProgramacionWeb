package com.gestion.estudiantes.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//ESTA ES LA TABLA Materia QUE APARECE EN POSTGRES
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="materia")
public class Materia {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="codigo", unique = true)
    private String codigo;

    @Column(name = "estado")
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @OneToMany(mappedBy = "materia")
    @JsonManagedReference("materia-horario")
    private List<Horario> horarios;

    @Column(name = "creditos")
    private int creditos; // Valor por defecto

   
    @ManyToMany(mappedBy = "materias") // Relacion con MallaCurricular
    @JsonBackReference
    private List<MallaCurricular> mallasCurriculares;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<EstudianteMateria> estudianteMaterias;
}