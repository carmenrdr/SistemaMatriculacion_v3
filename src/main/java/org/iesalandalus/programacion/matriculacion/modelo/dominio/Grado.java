package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public enum Grado {

    GDCFGB("Grado D Ciclo Formativo de Grado Básico"),
    GDCFGM ("Grado D Ciclo Formativo de Grado Medio"),
    GDCFGS("Grado D Ciclo Formativo de Grado Superior");
    private final String cadenaAMostrar;

    private Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() throws OperationNotSupportedException {
        if (this == GDCFGB) {
            return "0.-"+this.cadenaAMostrar;
        } else if (this == GDCFGM) {
            return "1.-"+this.cadenaAMostrar;
        } else if (this == GDCFGS) {
            return "2.-"+this.cadenaAMostrar;
        } else {
            throw new OperationNotSupportedException("El grado del ciclo formativo no es válido.");
        }
    }

    @Override
    public String toString() {
        return this.cadenaAMostrar;
    }
}
