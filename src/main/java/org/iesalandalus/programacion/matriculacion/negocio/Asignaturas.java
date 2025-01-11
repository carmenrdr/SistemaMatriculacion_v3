package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

public class Asignaturas {

    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    public Asignatura[] get(){
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas(){
        Asignatura[] copia = new Asignatura[tamano];

        for (int i=0; i<tamano; i++){
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
        }

        return copia;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void insertar(Asignatura asignatura){
        if (asignatura==null){
            throw new IllegalArgumentException("ERROR: No se puede insertar una asignatura nula.");
        } else if (buscar(asignatura) != null){
            throw new IllegalArgumentException("ERROR: Ya existe una asignatura con ese código.");
        } else if (tamano>=capacidad){
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
        if (asignatura==null){
            return null;
        }

        int indice = buscarIndice(asignatura);
        if (indice == -1){
            return null;
        } else {
            return coleccionAsignaturas[indice];
        }
    }

    public void borrar(Asignatura asignatura){
        if (asignatura==null){
            throw new IllegalArgumentException("ERROR: No se puede borrar una asignatura nula.");
        }

        int indice = buscarIndice(asignatura);
        if (indice==-1){
            throw new IllegalArgumentException("ERROR: No existe ninguna asignatura como la indicada.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        coleccionAsignaturas[tamano-1] = null;
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i= indice; i<tamano -1 && coleccionAsignaturas[i] != null; i++){
            coleccionAsignaturas[i] = coleccionAsignaturas[i+1];
        }
    }

}
