package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public abstract class Grado {

    protected String nombre;
    protected String iniciales;
    protected int numAnios;

    public Grado(String nombre) throws OperationNotSupportedException {
        this.setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) throws OperationNotSupportedException {

        if (nombre == null) {
            throw new OperationNotSupportedException("ERROR: el nombre no puede ser nulo.");
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

    protected abstract void setNumAnios(int numAnios) throws OperationNotSupportedException;

    @Override
    public String toString() {
        return "(" + iniciales + ") - " + nombre;
    }
}
