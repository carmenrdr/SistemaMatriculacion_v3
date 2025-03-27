package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import java.util.ArrayList;
import java.util.List;

public class Matriculas {

    private final List<Matricula> coleccionMatriculas;

    public Matriculas(){
        this.coleccionMatriculas = new ArrayList<>();
    }

    public List<Matricula> get() throws IllegalArgumentException{
        return copiaProfundaMatricula();
    }

    private List<Matricula> copiaProfundaMatricula() throws IllegalArgumentException {
        List<Matricula> coleccionAux = new ArrayList<>(getTamano());

        for (Matricula matricula : coleccionMatriculas) {
            coleccionAux.add(new Matricula(matricula));
        }

        return coleccionAux;
    }

    public int getTamano(){
        return coleccionMatriculas.size();
    }

    public void insertar(Matricula matricula) throws IllegalArgumentException {
        if (matricula == null){
            throw new IllegalArgumentException("No se puede insertar una matricula nula.");
        } else if (coleccionMatriculas.contains(matricula)) {
            throw new IllegalArgumentException("Ya existe una matrícula con ese identificador.");
        } else {
            coleccionMatriculas.add(matricula);
        }
    }

    public Matricula buscar(Matricula matricula) throws IllegalArgumentException {
        int indice = coleccionMatriculas.indexOf(matricula);

        if (indice == -1) {
            throw new IllegalArgumentException("No existe esta matrícula");
        } else {
            return new Matricula(coleccionMatriculas.get(indice));
        }
    }

    public void borrar(Matricula matricula) throws IllegalArgumentException {
        if (matricula == null){
            throw new IllegalArgumentException("No se puede borrar una matrícula nula.");
        }

        if(!coleccionMatriculas.remove(matricula)) {
            throw new IllegalArgumentException("La matricula a borrar no existe.");
        }
    }

    public List<Matricula> get(Alumno alumno) throws IllegalArgumentException {
        if (alumno==null){
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }

        List<Matricula> resultado = new ArrayList<>();

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getAlumno().equals(alumno)) {
                resultado.add(matricula);
            }
        }

        return resultado;
    }

    public List<Matricula> get(String cursoAcademico) throws IllegalArgumentException {
        if (cursoAcademico==null){
            throw new IllegalArgumentException("El curso académico no puede ser nulo.");
        }

        List<Matricula> resultado = new ArrayList<>();

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                resultado.add(matricula);
            }
        }

        return resultado;
    }

    public List<Matricula> get(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        if (cicloFormativo==null) {
            throw new IllegalArgumentException("El ciclo formativo no puede ser nulo.");
        }

        List<Matricula> resultado = new ArrayList<>();

        for (Matricula matricula : coleccionMatriculas) {
            boolean matriculasAsociadas = false;

            for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    matriculasAsociadas = true;
                    break;
                }
            }

            if (matriculasAsociadas) {
                resultado.add(matricula);
            }
        }

        return resultado;
    }

}
