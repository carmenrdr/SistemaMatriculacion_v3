package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

public class CiclosFormativos {

    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copia = new CicloFormativo[tamano];

        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }

        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar un ciclo formativo nulo.");
        } else if (buscar(cicloFormativo) != null) {
            throw new IllegalArgumentException("ERROR: Ya existe un ciclo formativo con ese código.");
        } else if (tamano >= capacidad) {
            throw new IllegalArgumentException("ERROR: No se aceptan más ciclos formativos.");
        } else {
            coleccionCiclosFormativos[tamano] = cicloFormativo;
            tamano++;
        }
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        //Si no lo encuentra devuelve -1.
        return -1;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            return null;
        }

        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            return null;
        } else {
            return coleccionCiclosFormativos[indice];
        }
    }

    public void borrar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }

        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        coleccionCiclosFormativos[tamano - 1] = null;
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = 0; i < tamano - 1 && coleccionCiclosFormativos[i] !=null; i++){
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i+1];
        }
    }
}