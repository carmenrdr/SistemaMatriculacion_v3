package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;

public class Modelo {

    public int CAPACIDAD;

    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;

    public void comenzar() {
        alumnos = new Alumnos(CAPACIDAD);
        matriculas = new Matriculas(CAPACIDAD);
        asignaturas = new Asignaturas(CAPACIDAD);
        ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Alumno alumno) throws Exception {
        alumnos.insertar(alumno);
    }

    public void insertar(Asignatura asignatura) throws Exception {
        asignaturas.insertar(asignatura);
    }

    public void insertar(CicloFormativo cicloFormativo) throws Exception {
        ciclosFormativos.insertar(cicloFormativo);
    }

    public void insertar(Matricula matricula) throws Exception {
        matriculas.insertar(matricula);
    }

    public Alumno buscar(Alumno alumno) {
        return alumnos.buscar(alumno);
    }

    public Asignatura buscar(Asignatura asignatura) {
        return asignaturas.buscar(asignatura);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return ciclosFormativos.buscar(cicloFormativo);
    }

    public Matricula buscar(Matricula matricula) {
        return matriculas.buscar(matricula);
    }

    public void borrar(Alumno alumno) throws Exception {
        alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws Exception {
        asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws Exception {
        ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws Exception {
        matriculas.borrar(matricula);
    }

    public Alumno[] getAlumnos() {
        return alumnos.get();
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas.get();
    }

    public CicloFormativo[] getCiclosFormativos() {
        return ciclosFormativos.get();
    }

    public Matricula[] getMatriculas() {
        return matriculas.get();
    }

    public Matricula[] getMatriculas(Alumno alumno) throws Exception {
        return matriculas.get(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) throws Exception {
        return matriculas.get(cicloFormativo);
    }

    public Matricula[] getMatriculas(String cursoAcademico) throws Exception {
        return matriculas.get(cursoAcademico);
    }


}
