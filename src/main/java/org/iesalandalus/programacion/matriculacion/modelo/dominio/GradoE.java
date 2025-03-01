package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public class GradoE extends Grado {

    private int numEdiciones;

    public GradoE(String nombre, int numAnios, int numEdiciones) throws OperationNotSupportedException {
        super(nombre);

    }

    public int getNumEdiciones() {
        return numEdiciones;
    }

    public void setNumEdiciones(int numEdiciones) {
        //if
        //this.numEdiciones = numEdiciones;
    }

    @Override
    protected void setNumAnios(int numAnios) throws OperationNotSupportedException {
        if (numAnios!=1) {
            throw new OperationNotSupportedException("ERROR: El número de años para este grado no es válido.");
        } else {
            this.numAnios = numAnios;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + numEdiciones;
    }

}
