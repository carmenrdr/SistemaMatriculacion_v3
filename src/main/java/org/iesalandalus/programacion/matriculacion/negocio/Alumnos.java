package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

public class Alumnos {

    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.coleccionAlumnos = new Alumno[capacidad];
        this.tamano = 0;
    }

    public Alumno[] get(){
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos(){
        Alumno[] copia = new Alumno[tamano];

        for (int i = 0; i<tamano; i++){
            copia[i] = new Alumno(coleccionAlumnos[i]);
        }

        return copia;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void insertar(Alumno alumno){
        if (alumno == null){
            throw new IllegalArgumentException("ERROR: No se puede insertar un alumno nulo.");
        } else if (buscar(alumno) != null) {
            throw new IllegalArgumentException("ERROR: Ya existe un alumno con ese dni.");
        } else if (tamano >= capacidad){
            throw new IllegalArgumentException("ERROR: No se aceptan más alumnos.");
        } else {
            coleccionAlumnos[tamano] = alumno;
            tamano++;
        }
    }

    private int buscarIndice(Alumno alumno){
        for (int i = 0; i<tamano; i++){
            if (coleccionAlumnos[i].equals(alumno)){
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

    public Alumno buscar(Alumno alumno){
        if (alumno == null){
            return null;
        }

        int indice = buscarIndice(alumno);
        if (indice == -1){
            return null;
        } else {
            return coleccionAlumnos[indice];
        }
    }

    public void borrar(Alumno alumno){
        if (alumno == null){
            throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
        }

        int indice = buscarIndice(alumno);
        if (indice == -1){
            throw new IllegalArgumentException("ERROR: No existe ningún alumno como el indicado.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        coleccionAlumnos[tamano -1] = null; //eliminar el último alumno (duplicado al final)
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i < tamano -1 && coleccionAlumnos[i] != null; i++){
            coleccionAlumnos[i] = coleccionAlumnos[i+1];
        }
    }
}
