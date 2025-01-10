package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {

    PRIMERO("Primero"), SEGUNDO("Segundo");
    private String cadenaAMostrar;

    private Curso (String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        if (this == PRIMERO) {
            return "0.-"+this.cadenaAMostrar;
        } else if (this == SEGUNDO) {
            return "1.-"+this.cadenaAMostrar;
        } else {
            throw new IllegalArgumentException("El nombre del curso no es válido.");
        }
    }

    @Override
    public String toString() {
        return this.cadenaAMostrar;
    }
}
