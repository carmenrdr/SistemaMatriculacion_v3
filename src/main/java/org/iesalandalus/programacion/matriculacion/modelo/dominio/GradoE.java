package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public class GradoE extends Grado {

    private int numEdiciones;

    public GradoE(String nombre, int numAnios, int numEdiciones) throws OperationNotSupportedException {
        super(nombre);
        this.setNumAnios(numAnios);
        setNumEdiciones(numEdiciones);
    }

    public int getNumEdiciones() {
        return numEdiciones;
    }

    public void setNumEdiciones(int numEdiciones) throws OperationNotSupportedException {
        if (numEdiciones <= 0) {
            throw new OperationNotSupportedException("ERROR: El número de ediciones no puede ser menor o igual a 0.");
        } else {
            this.numEdiciones = numEdiciones;
        }
    }

    @Override
    protected void setNumAnios(int numAnios) throws OperationNotSupportedException {
        if (numAnios!=1) {
            throw new OperationNotSupportedException("ERROR: El número de años para este grado tiene que ser 1.");
        } else {
            this.numAnios = numAnios;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.numAnios + ", " + getNumEdiciones();
    }

}
