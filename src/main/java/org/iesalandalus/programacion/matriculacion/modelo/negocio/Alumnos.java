package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import java.util.ArrayList;
import java.util.List;

public class Alumnos {

    private final List<Alumno> coleccionAlumnos;

    public Alumnos(){
        this.coleccionAlumnos = new ArrayList<>();
    }

    public List<Alumno> get() throws IllegalArgumentException {
        return copiaProfundaAlumnos();
    }

    private List<Alumno> copiaProfundaAlumnos() throws IllegalArgumentException {
        List<Alumno> coleccionAux = new ArrayList<>(getTamano());

        for (Alumno alumno : coleccionAlumnos) {
            coleccionAux.add(new Alumno(alumno));
        }

        return coleccionAux;
    }

    public int getTamano(){
        return coleccionAlumnos.size();
    }

    public void insertar(Alumno alumno) throws IllegalArgumentException {
        if (alumno == null){
            throw new IllegalArgumentException("No se puede insertar un alumno nulo.");
        } else if (coleccionAlumnos.contains(alumno)) {
            throw new IllegalArgumentException("Ya existe un alumno con ese dni.");
        } else {
            coleccionAlumnos.add(alumno);
        }
    }

    public Alumno buscar(Alumno alumno) throws IllegalArgumentException {
        int indice = coleccionAlumnos.indexOf(alumno);

        if (indice == -1) {
            throw new IllegalArgumentException("El/la alumna no existe.");
        } else {
            return new Alumno(coleccionAlumnos.get(indice));
        }
    }

    public void borrar(Alumno alumno) throws IllegalArgumentException {
        if (alumno == null){
            throw new IllegalArgumentException("No se puede borrar un alumno nulo.");
        }

        if (!coleccionAlumnos.remove(alumno)) {
            throw new IllegalArgumentException("El alumno a borrar no existe.");
        }
    }

}
