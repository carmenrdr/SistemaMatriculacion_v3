package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import java.util.List;

public class Modelo {

    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;

    public void comenzar() {
        alumnos = new Alumnos();
        matriculas = new Matriculas();
        asignaturas = new Asignaturas();
        ciclosFormativos = new CiclosFormativos();
    }

    public void terminar() {
        System.out.println("¡Hasta la próxima!");
    }

    public void insertar(Alumno alumno) throws IllegalArgumentException {
        alumnos.insertar(alumno);
    }

    public void insertar(Asignatura asignatura) throws IllegalArgumentException {
        asignaturas.insertar(asignatura);
    }

    public void insertar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        ciclosFormativos.insertar(cicloFormativo);
    }

    public void insertar(Matricula matricula) throws IllegalArgumentException {
        matriculas.insertar(matricula);
    }

    public Alumno buscar(Alumno alumno) throws IllegalArgumentException {
        return alumnos.buscar(alumno);
    }

    public Asignatura buscar(Asignatura asignatura) throws IllegalArgumentException {
        return asignaturas.buscar(asignatura);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        return ciclosFormativos.buscar(cicloFormativo);
    }

    public Matricula buscar(Matricula matricula) throws IllegalArgumentException {
        return matriculas.buscar(matricula);
    }

    public void borrar(Alumno alumno) throws IllegalArgumentException {
        alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws IllegalArgumentException {
        asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws IllegalArgumentException {
        matriculas.borrar(matricula);
    }

    public List<Alumno> getAlumnos() throws IllegalArgumentException {
        return alumnos.get();
    }

    public List<Asignatura> getAsignaturas() throws IllegalArgumentException {
        return asignaturas.get();
    }

    public List<CicloFormativo> getCiclosFormativos() throws IllegalArgumentException {
        return ciclosFormativos.get();
    }

    public List<Matricula> getMatriculas() throws IllegalArgumentException {
        return matriculas.get();
    }

    public List<Matricula> getMatriculas(Alumno alumno) throws IllegalArgumentException {
        return matriculas.get(alumno);
    }

    public List<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        return matriculas.get(cicloFormativo);
    }

    public List<Matricula> getMatriculas(String cursoAcademico) throws IllegalArgumentException {
        return matriculas.get(cursoAcademico);
    }


}
