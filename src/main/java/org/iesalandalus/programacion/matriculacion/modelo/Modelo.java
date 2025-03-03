package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;
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

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        alumnos.insertar(alumno);
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        asignaturas.insertar(asignatura);
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ciclosFormativos.insertar(cicloFormativo);
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        matriculas.insertar(matricula);
    }

    public Alumno buscar(Alumno alumno) throws OperationNotSupportedException {
        return alumnos.buscar(alumno);
    }

    public Asignatura buscar(Asignatura asignatura) throws OperationNotSupportedException {
        return asignaturas.buscar(asignatura);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return ciclosFormativos.buscar(cicloFormativo);
    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        return matriculas.buscar(matricula);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        matriculas.borrar(matricula);
    }

    public List<Alumno> getAlumnos() throws OperationNotSupportedException {
        return alumnos.get();
    }

    public List<Asignatura> getAsignaturas() throws OperationNotSupportedException {
        return asignaturas.get();
    }

    public List<CicloFormativo> getCiclosFormativos() throws OperationNotSupportedException {
        return ciclosFormativos.get();
    }

    public List<Matricula> getMatriculas() throws OperationNotSupportedException {
        return matriculas.get();
    }

    public List<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return matriculas.get(alumno);
    }

    public List<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return matriculas.get(cicloFormativo);
    }

    public List<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return matriculas.get(cursoAcademico);
    }


}
