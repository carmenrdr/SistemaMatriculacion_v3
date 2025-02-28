package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class CiclosFormativos {

    private final List<CicloFormativo> coleccionCiclosFormativos;

    public CiclosFormativos() {
        this.coleccionCiclosFormativos = new ArrayList<>();
    }

    public List<CicloFormativo> get() {
        return copiaProfundaCiclosFormativos(coleccionCiclosFormativos);
    }

    private static List<CicloFormativo> copiaProfundaCiclosFormativos(List<CicloFormativo> coleccionCiclosFormativos) {
        List<CicloFormativo> coleccionAux = new ArrayList<>();

        for (CicloFormativo cicloFormativo : coleccionCiclosFormativos) {
            coleccionAux.add(new CicloFormativo(cicloFormativo));
        }

        return coleccionAux;
    }

    public int getTamano() {
        return coleccionCiclosFormativos.size();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new OperationNotSupportedException("ERROR: No se puede insertar un ciclo formativo nulo.");
        } else if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        } else {
            coleccionCiclosFormativos.add(cicloFormativo);
        }
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        int indice = -1;
        boolean encontrado = false;

        for (int i=0; i < coleccionCiclosFormativos.size() && !encontrado; i++) {
            if (coleccionCiclosFormativos.get(i) != null && coleccionCiclosFormativos.get(i).equals(cicloFormativo)) {
                indice = i;
                encontrado = true;
            }
        }
        return new CicloFormativo(coleccionCiclosFormativos.get(indice));
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new OperationNotSupportedException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }

        if (!coleccionCiclosFormativos.remove(cicloFormativo)) {
            throw new OperationNotSupportedException("ERROR: El Ciclo Formativo a borrar no existe.");
        }
    }

}