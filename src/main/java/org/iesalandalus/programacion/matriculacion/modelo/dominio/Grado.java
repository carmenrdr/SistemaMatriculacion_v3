package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public abstract class Grado {

    protected String nombre;
    protected String iniciales;
    protected int numAnios;

    public Grado(String nombre) throws IllegalArgumentException {
        this.setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) throws IllegalArgumentException {

        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        } else {
            this.nombre = nombre;
        }

        setIniciales();

    }

    private void setIniciales() {

        StringBuilder inicialesArray = new StringBuilder();

        String[] palabras = this.nombre.split("\\s+");

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                inicialesArray.append(palabra.charAt(0));
            }
        }

        this.iniciales = inicialesArray.toString().toUpperCase();

    }

    protected abstract void setNumAnios(int numAnios) throws IllegalArgumentException;

    @Override
    public String toString() {
        return "(" + iniciales + ") - " + getNombre();
    }
}
