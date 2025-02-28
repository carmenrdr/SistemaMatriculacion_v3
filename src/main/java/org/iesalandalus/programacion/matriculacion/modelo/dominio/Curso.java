package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public enum Curso {

    PRIMERO("Primero"), SEGUNDO("Segundo");
    private final String cadenaAMostrar;

    private Curso (String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() throws OperationNotSupportedException {
        if (this == PRIMERO) {
            return "0.-"+this.cadenaAMostrar;
        } else if (this == SEGUNDO) {
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
