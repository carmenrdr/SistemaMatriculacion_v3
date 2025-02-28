package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public abstract class Grado {

    protected String nombre;
    protected String iniciales;
    protected int numAnios;

    public Grado(String nombre) {

    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {

    }

    private void setIniciales() {

    }

    protected abstract void setNumAnios(int numAnios) {

    }

    @Override
    public String toString() {
        return iniciales + " - " + nombre;
    }
}
