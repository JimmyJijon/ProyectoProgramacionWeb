/*
 * Creado por: Alex Cordova
 * Email: alex_cordova@expalsa.com
 */
package com.gestion.estudiantes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="malla_materia")
public class MallaContenido {

    private long IDMalla;

    private long IDMateria;

    private int numNivel;
}
