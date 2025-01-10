package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {

    GDCFGB("Grado De Ciclo Formativo de Grado Básico"),
    GDCFGM ("Grado De Ciclo Formativo de Grado Medio"),
    GDCFGS("Grado De Ciclo Formativo de Grado Superior");
    private String cadenaAMostrar;

    private Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir(){
        if (this == GDCFGB) {
            return "0.-"+this.cadenaAMostrar;
        } else if (this == GDCFGM) {
            return "1.-"+this.cadenaAMostrar;
        } else if (this == GDCFGS) {
            return "2.-"+this.cadenaAMostrar;
        } else {
            throw new IllegalArgumentException("El grado del ciclo formativo no es válido.");
        }
    }

    @Override
    public String toString() {
        return this.cadenaAMostrar;
    }
}
