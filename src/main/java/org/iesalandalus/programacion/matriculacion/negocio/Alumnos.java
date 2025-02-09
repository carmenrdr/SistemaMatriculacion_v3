package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

public class Alumnos {

    private final int capacidad;
    private int tamano;
    private final Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[getCapacidad()];
    }

    public Alumno[] get(){
        return copiaProfundaAlumnos(coleccionAlumnos);
    }

    private static Alumno[] copiaProfundaAlumnos(Alumno[] coleccionAlumnos){
        Alumno[] coleccionAux = new Alumno[coleccionAlumnos.length];

        for (int i = 0; i < coleccionAlumnos.length; i++) {
            coleccionAux[i] = new Alumno(coleccionAlumnos[i]);
        }

        return coleccionAux;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void insertar(Alumno alumno) throws Exception {
        if (alumno == null){
            throw new IllegalArgumentException("ERROR: No se puede insertar un alumno nulo.");
        } else if (buscar(alumno) != null) {
            throw new IllegalArgumentException("ERROR: Ya existe un alumno con ese dni.");
        } else if (tamanoSuperado(tamano) || capacidadSuperada(capacidad)){
            throw new IllegalArgumentException("ERROR: No se aceptan más alumnos.");
        } else {
            coleccionAlumnos[tamano] = alumno;
            tamano++;
        }
    }

    private int buscarIndice(Alumno alumno){
        for (int i = 0; i<coleccionAlumnos.length; i++){
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
        int indice = -1;
        boolean encontrado = false;

        for (int i=0; i < coleccionAlumnos.length && !encontrado; i++) {
            if (coleccionAlumnos[i] != null && coleccionAlumnos[i].equals(alumno)) {
                indice = i;
                encontrado = true;
            }
        }
        return coleccionAlumnos[indice];
    }

    public void borrar(Alumno alumno) throws Exception {
        if (alumno == null){
            throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
        }

        int indice = buscarIndice(alumno);

        if (indice == -1){
            throw new IllegalArgumentException("ERROR: No existe ningún alumno como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        if (indice == coleccionAlumnos.length-1) {
            coleccionAlumnos[indice] = null;
        } else {
            int i;

            for (i = indice; i < coleccionAlumnos.length-1 && coleccionAlumnos[i] != null; i++) {
                coleccionAlumnos[i] = new Alumno(coleccionAlumnos[i+1]);
            }

            coleccionAlumnos[i+1] = null;
        }
    }
}
