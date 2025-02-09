package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

public class Asignaturas {

    private final int capacidad;
    private int tamano;
    private final Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[getCapacidad()];
    }

    public Asignatura[] get(){
        return copiaProfundaAsignaturas(coleccionAsignaturas);
    }

    private Asignatura[] copiaProfundaAsignaturas(Asignatura[] coleccionAsignaturas){
        Asignatura[] coleccionAux = new Asignatura[coleccionAsignaturas.length];

        for (int i = 0; i < coleccionAsignaturas.length; i++) {
            coleccionAux[i] = new Asignatura(coleccionAsignaturas[i]);
        }

        return coleccionAux;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void insertar(Asignatura asignatura) throws Exception {
        if (asignatura==null){
            throw new IllegalArgumentException("ERROR: No se puede insertar una asignatura nula.");
        } else if (buscar(asignatura) != null){
            throw new IllegalArgumentException("ERROR: Ya existe una asignatura con ese código.");
        } else if (tamanoSuperado(tamano) || capacidadSuperada(capacidad)){
            throw new IllegalArgumentException("ERROR: No se aceptan más asignaturas.");
        } else {
            coleccionAsignaturas[tamano] = asignatura;
            tamano++;
        }
    }

    private int buscarIndice(Asignatura asignatura){
        for (int i=0;i<tamano; i++){
            if (coleccionAsignaturas[i].equals(asignatura)){
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice){
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice){
        return indice >= capacidad;
    }

    public Asignatura buscar(Asignatura asignatura){
        int indice = -1;
        boolean encontrado = false;

        for (int i=0; i < coleccionAsignaturas.length && !encontrado; i++) {
            if (coleccionAsignaturas[i] != null && coleccionAsignaturas[i].equals(asignatura)) {
                indice = i;
                encontrado = true;
            }
        }
        return coleccionAsignaturas[indice];
    }

    public void borrar(Asignatura asignatura) throws Exception {
        if (asignatura==null){
            throw new IllegalArgumentException("ERROR: No se puede borrar una asignatura nula.");
        }

        int indice = buscarIndice(asignatura);

        if (indice==-1){
            throw new IllegalArgumentException("ERROR: No existe ninguna asignatura como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        if (indice == coleccionAsignaturas.length-1) {
            coleccionAsignaturas[indice] = null;
        } else {
            int i;

            for (i = indice; i < coleccionAsignaturas.length-1 && coleccionAsignaturas[i] != null; i++) {
                coleccionAsignaturas[i] = new Asignatura(coleccionAsignaturas[i+1]);
            }

            coleccionAsignaturas[i+1] = null;
        }
    }

}
