package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import java.util.ArrayList;
import java.util.List;

public class CiclosFormativos {

    private final List<CicloFormativo> coleccionCiclosFormativos;

    public CiclosFormativos() {
        this.coleccionCiclosFormativos = new ArrayList<>();
    }

    public List<CicloFormativo> get() throws IllegalArgumentException {
        return copiaProfundaCiclosFormativos();
    }

    private List<CicloFormativo> copiaProfundaCiclosFormativos() throws IllegalArgumentException {
        List<CicloFormativo> coleccionAux = new ArrayList<>(getTamano());

        for (CicloFormativo cicloFormativo : coleccionCiclosFormativos) {
            coleccionAux.add(new CicloFormativo(cicloFormativo));
        }

        return coleccionAux;
    }

    public int getTamano() {
        return coleccionCiclosFormativos.size();
    }

    public void insertar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("No se puede insertar un ciclo formativo nulo.");
        } else if (coleccionCiclosFormativos.contains(cicloFormativo)) {
            throw new IllegalArgumentException("Ya existe un ciclo formativo con ese código.");
        } else {
            coleccionCiclosFormativos.add(cicloFormativo);
        }
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        int indice = coleccionCiclosFormativos.indexOf(cicloFormativo);

        if (indice == -1) {
            throw new IllegalArgumentException("No existe este Ciclo Formativo.");
        } else {
            return new CicloFormativo(coleccionCiclosFormativos.get(indice));
        }

    }

    public void borrar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("No se puede borrar un ciclo formativo nulo.");
        }

        if (!coleccionCiclosFormativos.remove(cicloFormativo)) {
            throw new IllegalArgumentException("El Ciclo Formativo a borrar no existe.");
        }
    }

}