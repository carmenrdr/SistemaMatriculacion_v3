package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public enum Modalidad {

    PRESENCIAL("Presencial"), SEMIPRESENCIAL("Semipresencial.");
    private final String cadenaAMostrar;

    private Modalidad (String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() throws OperationNotSupportedException {
        if (this == PRESENCIAL) {
            return "0.-"+this.cadenaAMostrar;
        } else if (this == SEMIPRESENCIAL) {
            return "1.-"+this.cadenaAMostrar;
        } else {
            throw new OperationNotSupportedException("ERROR: El nombre del curso no es válido.");
        }
    }

    @Override
    public String toString() {
        return this.cadenaAMostrar;
    }

}
