package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Asignaturas {

    private final List<Asignatura> coleccionAsignaturas;

    public Asignaturas(){
        this.coleccionAsignaturas = new ArrayList<>();
    }

    public List<Asignatura> get() throws OperationNotSupportedException {
        return copiaProfundaAsignaturas(coleccionAsignaturas);
    }

    private static List<Asignatura> copiaProfundaAsignaturas(List<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
        List<Asignatura> coleccionAux = new ArrayList<>();

        for (Asignatura asignatura : coleccionAsignaturas) {
            coleccionAux.add(new Asignatura(asignatura));
        }

        return coleccionAux;
    }

    public int getTamano(){
        return coleccionAsignaturas.size();
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura==null){
            throw new OperationNotSupportedException("ERROR: No se puede insertar una asignatura nula.");
        } else if (buscar(asignatura) != null){
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        } else {
            coleccionAsignaturas.add(asignatura);
        }
    }

    public Asignatura buscar(Asignatura asignatura) throws OperationNotSupportedException {
        int indice = -1;
        boolean encontrado = false;

        for (int i=0; i < coleccionAsignaturas.size() && !encontrado; i++) {
            if (coleccionAsignaturas.get(i) != null && coleccionAsignaturas.get(i).equals(asignatura)) {
                indice = i;
                encontrado = true;
            }
        }

        if (!encontrado) {
            throw new OperationNotSupportedException("ERROR: No existe esta asignatura.");
        } else {
            return new Asignatura(coleccionAsignaturas.get(indice));
        }
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura==null){
            throw new OperationNotSupportedException("ERROR: No se puede borrar una asignatura nula.");
        }

        if(!coleccionAsignaturas.remove(asignatura)) {
            throw new OperationNotSupportedException("ERROR: La asignatura a borrar no existe.");
        }
    }

}
