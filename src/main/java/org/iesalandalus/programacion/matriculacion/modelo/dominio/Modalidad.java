package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public enum Modalidad {

    PRESENCIAL("Presencial"), SEMIPRESENCIAL("Semipresencial.");
    private final String cadenaAMostrar;

    private Modalidad (String cadenaAMostrar) {
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
