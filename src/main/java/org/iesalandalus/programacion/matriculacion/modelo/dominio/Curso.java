package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Curso {

    PRIMERO("Primero"), SEGUNDO("Segundo");
    private final String cadenaAMostrar;

    private Curso (String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return ordinal() + ".- " + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return this.cadenaAMostrar;
    }
}
