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
import lombok.Getter;
import lombok.Setter;
//ESTA ES LA TABLA Materia QUE APARECE EN POSTGRES
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="materia")
public class Materia {

    //Atributos de la tabla Materia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "estado", nullable = false)
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @OneToMany(mappedBy = "materia")
    @JsonManagedReference("materia-horario")
    private List<Horario> horarios;

    @Column(name = "creditos", nullable = false)
    private int creditos;

    @ManyToMany(mappedBy = "materias")
    @JsonBackReference
    private List<MallaCurricular> mallasCurriculares;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EstudianteMateria> estudianteMaterias;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<MallaCurricular> getMallasCurriculares() {
        return mallasCurriculares;
    }

    public void setMallasCurriculares(List<MallaCurricular> mallasCurriculares) {
        this.mallasCurriculares = mallasCurriculares;
    }

    public List<EstudianteMateria> getEstudianteMaterias() {
        return estudianteMaterias;
    }

    public void setEstudianteMaterias(List<EstudianteMateria> estudianteMaterias) {
        this.estudianteMaterias = estudianteMaterias;
    }
}