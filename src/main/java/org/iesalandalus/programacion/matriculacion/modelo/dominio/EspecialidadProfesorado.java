package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public enum EspecialidadProfesorado {

    INFORMATICA("Informática"),
    SISTEMAS("Sistemas y Aplicaciones informáticas"),
    FOL("Formación y Orientación Laboral");
    private final String cadenaAMostrar;

    private EspecialidadProfesorado(String cadenaAMostrar){
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
