package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import java.util.List;

public class Controlador {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) throws IllegalArgumentException {
        if (modelo == null) {
            throw new IllegalArgumentException("El modelo no puede ser nulo.");
        }

        if (vista == null) {
            throw new IllegalArgumentException("La vista no puede ser nula.");
        }

        this.modelo = modelo;
        this.vista = vista;
        vista.setControlador(this);
    }

    public void comenzar() throws IllegalArgumentException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Le damos la bienvenida al sistema de matriculación del instituto IES Al-Ándalus.");
        System.out.println("--------------------------------------------------------------------------------");

        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
    }

    public void insertar(Alumno alumno) throws IllegalArgumentException {
        modelo.insertar(alumno);
    }

    public Alumno buscar(Alumno alumno) throws IllegalArgumentException {
        return modelo.buscar(alumno);
    }

    public void borrar(Alumno alumno) throws IllegalArgumentException {
        modelo.borrar(alumno);
    }

    public List<Alumno> getAlumnos() throws IllegalArgumentException {
        return modelo.getAlumnos();
    }

    public void insertar(Asignatura asignatura) throws IllegalArgumentException {
        modelo.insertar(asignatura);
    }

    public Asignatura buscar(Asignatura asignatura) throws IllegalArgumentException {
        return modelo.buscar(asignatura);
    }

    public void borrar(Asignatura asignatura) throws IllegalArgumentException {
        modelo.borrar(asignatura);
    }

    public List<Asignatura> getAsignaturas() throws IllegalArgumentException {
        return modelo.getAsignaturas();
    }

    public void insertar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        modelo.insertar(cicloFormativo);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        return modelo.buscar(cicloFormativo);
    }

    public void borrar(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        modelo.borrar(cicloFormativo);
    }

    public List<CicloFormativo> getCiclosFormativos() throws IllegalArgumentException {
        return modelo.getCiclosFormativos();
    }

    public void insertar(Matricula matricula) throws IllegalArgumentException {
        modelo.insertar(matricula);
    }

    public Matricula buscar(Matricula matricula) throws IllegalArgumentException {
        return modelo.buscar(matricula);
    }

    public void borrar(Matricula matricula) throws IllegalArgumentException {
        modelo.borrar(matricula);
    }

    public List<Matricula> getMatriculas() throws IllegalArgumentException {
        return modelo.getMatriculas();
    }

    public List<Matricula> getMatriculas(Alumno alumno) throws IllegalArgumentException {
        return modelo.getMatriculas(alumno);
    }

    public List<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        return modelo.getMatriculas(cicloFormativo);
    }

    public List<Matricula> getMatriculas(String cursoAcademico) throws IllegalArgumentException {
        return modelo.getMatriculas(cursoAcademico);
    }

}
