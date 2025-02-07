package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

public class CiclosFormativos {

    private final int capacidad;
    private int tamano;
    private final CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[getCapacidad()];
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos(coleccionCiclosFormativos);
    }

    private static CicloFormativo[] copiaProfundaCiclosFormativos(CicloFormativo[] coleccionCiclosFormativos) {
        CicloFormativo[] coleccionAux = new CicloFormativo[coleccionCiclosFormativos.length];

        for (int i = 0; i < coleccionCiclosFormativos.length; i++) {
            coleccionAux[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }

        return coleccionAux;
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
        } else if (tamanoSuperado(tamano) || capacidadSuperada(capacidad)) {
            throw new IllegalArgumentException("ERROR: No se aceptan más ciclos formativos.");
        } else {
            coleccionCiclosFormativos[tamano] = cicloFormativo;
            tamano++;
        }
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < coleccionCiclosFormativos.length; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        int indice = -1;
        boolean encontrado = false;

        for (int i=0; i < coleccionCiclosFormativos.length && !encontrado; i++) {
            if (coleccionCiclosFormativos[i] != null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                indice = i;
                encontrado = true;
            }
        }
        return coleccionCiclosFormativos[indice];
    }

    public void borrar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }

        int indice = buscarIndice(cicloFormativo);

        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: No existe ningún ciclo formativo como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        if (indice == coleccionCiclosFormativos.length-1) {
            coleccionCiclosFormativos[indice] = null;
        } else {
            int i;

            for (i = indice; i < coleccionCiclosFormativos.length-1 && coleccionCiclosFormativos[i] != null; i++) {
                coleccionCiclosFormativos[i] = new CicloFormativo(coleccionCiclosFormativos[i+1]);
            }

            coleccionCiclosFormativos[i+1] = null;
        }
    }
}