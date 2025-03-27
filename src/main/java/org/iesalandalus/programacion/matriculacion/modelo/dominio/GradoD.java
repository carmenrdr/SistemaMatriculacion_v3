package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoD extends Grado {

    private Modalidad modalidad;

    public GradoD(String nombre, int numAnios, Modalidad modalidad) throws IllegalArgumentException {
        super(nombre);
        this.setNumAnios(numAnios);
        setModalidad(modalidad);
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) throws IllegalArgumentException {
        if (modalidad == null) {
            throw new IllegalArgumentException("La modalidad no puede ser nula.");
        } else {
            this.modalidad = modalidad;
        }
    }

    @Override
    protected void setNumAnios(int numAnios) throws IllegalArgumentException {
        if (numAnios != 2 && numAnios != 3) {
            throw new IllegalArgumentException("El número de años para este grado solo puede ser 2 ó 3.");
        } else {
            this.numAnios = numAnios;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.numAnios + ", " + getModalidad();
    }
}
