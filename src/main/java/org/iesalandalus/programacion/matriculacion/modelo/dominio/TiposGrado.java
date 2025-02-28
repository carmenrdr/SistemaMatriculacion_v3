package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public enum TiposGrado {

    GRADOD("Grado D"), GRADOE("Grado E");
    private final String cadenaAMostrar;

    private TiposGrado (String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() throws OperationNotSupportedException {
        if (this == GRADOD) {
            return "0.-"+this.cadenaAMostrar;
        } else if (this == GRADOE) {
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
