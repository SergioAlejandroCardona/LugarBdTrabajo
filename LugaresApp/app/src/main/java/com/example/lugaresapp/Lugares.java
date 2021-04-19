package com.example.lugaresapp;

public class Lugares {

    private int Id;
    private String Nombre;
    private int Poblacion;
    private int Latitud;
    private int Longitud;

    public Lugares() {
    }

    public Lugares(int id, String nombre, int poblacion, int latitud, int longitud) {
        Id = id;
        Nombre = nombre;
        Poblacion = poblacion;
        Latitud = latitud;
        Longitud = longitud;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPoblacion() {
        return Poblacion;
    }

    public void setPoblacion(int poblacion) {
        Poblacion = poblacion;
    }

    public int getLatitud() {
        return Latitud;
    }

    public void setLatitud(int latitud) {
        Latitud = latitud;
    }

    public int getLongitud() {
        return Longitud;
    }

    public void setLongitud(int longitud) {
        Longitud = longitud;
    }
}
