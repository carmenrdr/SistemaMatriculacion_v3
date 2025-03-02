package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Alumnos {

    private final List<Alumno> coleccionAlumnos;

    public Alumnos(){
        this.coleccionAlumnos = new ArrayList<>();
    }

    public List<Alumno> get() throws OperationNotSupportedException {
        return copiaProfundaAlumnos();
    }

    private List<Alumno> copiaProfundaAlumnos() throws OperationNotSupportedException {
        List<Alumno> coleccionAux = new ArrayList<>(getTamano());

        for (Alumno alumno : coleccionAlumnos) {
            coleccionAux.add(new Alumno(alumno));
        }

        return coleccionAux;
    }

    public int getTamano(){
        return coleccionAlumnos.size();
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null){
            throw new OperationNotSupportedException("ERROR: No se puede insertar un alumno nulo.");
        } else if (coleccionAlumnos.contains(alumno)) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        } else {
            coleccionAlumnos.add(alumno);
        }
    }

    public Alumno buscar(Alumno alumno) throws OperationNotSupportedException {
        int indice = coleccionAlumnos.indexOf(alumno);

        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: El/la alumna no existe.");
        } else {
            return new Alumno(coleccionAlumnos.get(indice));
        }
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null){
            throw new OperationNotSupportedException("ERROR: No se puede borrar un alumno nulo.");
        }

        if (!coleccionAlumnos.remove(alumno)) {
            throw new OperationNotSupportedException("ERROR: El alumno a borrar no existe.");
        }
    }

}
