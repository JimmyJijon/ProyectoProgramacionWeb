package com.gestion.estudiantes.model;

import jakarta.persistence.*;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestion.estudiantes.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role rol; // Enum: ESTUDIANTE, PROFESOR, ADMIN

    @OneToOne
    @JoinColumn(name = "estudiante_id") // Relación con Estudiante
    @JsonIgnore
    private Estudiante estudiante;

    @Column(name = "estudiante_id_column")
    private Long estudianteIdColumn;

    // Constructor vacío (obligatorio para JPA)
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Long getEstudianteIdColumn() {
        return estudianteIdColumn;
    }

    public void setEstudianteIdColumn(Long estudianteIdColumn) {
        this.estudianteIdColumn = estudianteIdColumn;
    }

    // Agregar el método getEstudianteId
    public Long getEstudianteId() {
        return estudianteIdColumn;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteIdColumn = estudianteId;
    }

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ToString
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
