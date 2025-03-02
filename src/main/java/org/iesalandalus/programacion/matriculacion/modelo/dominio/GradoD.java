package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;

public class GradoD extends Grado {

    private Modalidad modalidad;

    public GradoD(String nombre, int numAnios, Modalidad modalidad) throws OperationNotSupportedException {
        super(nombre);
        this.setNumAnios(numAnios);
        setModalidad(modalidad);
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) throws OperationNotSupportedException {
        if (modalidad == null) {
            throw new OperationNotSupportedException("ERROR: La modalidad no puede ser nula.");
        } else {
            this.modalidad = modalidad;
        }
    }

    @Override
    protected void setNumAnios(int numAnios) throws OperationNotSupportedException {
        if (numAnios != 2 && numAnios != 3) {
            throw new OperationNotSupportedException("ERROR: El número de años para este grado solo puede ser 2 ó 3.");
        } else {
            this.numAnios = numAnios;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.numAnios + ", " + modalidad;
    }
}
