package com.example.pablo.gestionjugadores;

import java.io.Serializable;

public class Jugador implements Serializable {
    private Integer id;
    private String nombre;
    private String equipo;
    private Integer edad;
    private Integer posicion;
    private String descripcion;

    public Jugador(Integer id, String nombre, String equipo, Integer edad, Integer posicion, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.equipo = equipo;
        this.edad = edad;
        this.posicion = posicion;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", equipo='" + equipo + '\'' +
                ", edad=" + edad +
                ", posicion=" + posicion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
