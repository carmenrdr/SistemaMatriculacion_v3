package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

public class Matriculas {

    private final int capacidad;
    private int tamano;
    private final Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[getCapacidad()];
    }

    public Matricula[] get(){
        return copiaProfundaMatricula(coleccionMatriculas);
    }

    private Matricula[] copiaProfundaMatricula(Matricula[] coleccionMatriculas){
        Matricula[] coleccionAux = new Matricula[coleccionMatriculas.length];

        for (int i = 0; i < coleccionMatriculas.length; i++) {
            coleccionAux[i] = new Matricula(coleccionMatriculas[i]);
        }

        return coleccionAux;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void insertar(Matricula matricula){
        if (matricula == null){
            throw new IllegalArgumentException("ERROR: No se puede insertar una matricula nula.");
        } else if (buscar(matricula) != null) {
            throw new IllegalArgumentException("ERROR: Ya existe una matrícula con ese identificador.");
        } else if (tamanoSuperado(tamano) || capacidadSuperada(capacidad)){
            throw new IllegalArgumentException("ERROR: No se aceptan más matrículas.");
        } else {
            coleccionMatriculas[tamano] = matricula;
            tamano++;
        }
    }

    private int buscarIndice(Matricula matricula){
        for (int i = 0; i<tamano; i++){
            if (coleccionMatriculas[i].equals(matricula)){
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice){
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice){
        return indice >= capacidad;
    }

    public Matricula buscar(Matricula matricula){
        int indice = -1;
        boolean encontrado = false;

        for (int i=0; i < coleccionMatriculas.length && !encontrado; i++) {
            if (coleccionMatriculas[i] != null && coleccionMatriculas[i].equals(matricula)) {
                indice = i;
                encontrado = true;
            }
        }
        return coleccionMatriculas[indice];
    }

    public void borrar(Matricula matricula){
        if (matricula == null){
            throw new IllegalArgumentException("ERROR: No se puede borrar una matrícula nula.");
        }

        int indice = buscarIndice(matricula);

        if (indice == -1){
            throw new IllegalArgumentException("ERROR: No existe ninguna matrícula como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        if (indice == coleccionMatriculas.length-1) {
            coleccionMatriculas[indice] = null;
        } else {
            int i;

            for (i = indice; i < coleccionMatriculas.length-1 && coleccionMatriculas[i] != null; i++) {
                coleccionMatriculas[i] = new Matricula(coleccionMatriculas[i+1]);
            }

            coleccionMatriculas[i+1] = null;
        }
    }

    public Matricula[] get(Alumno alumno){
        if (alumno==null){
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo.");
        }

        int contador = 0;
        for (int i=0; i < tamano; i++){
            if (coleccionMatriculas[i].getAlumno().equals(alumno)){
                contador++;
            }
        }

        Matricula[] resultado = new Matricula[contador];

        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                resultado[indice] = coleccionMatriculas[i];
                indice++;
            }
        }

        return resultado;
    }

    public Matricula[] get(String cursoAcademico){
        if (cursoAcademico==null){
            throw new IllegalArgumentException("ERROR: El curso académico no puede ser nulo.");
        }

        int contador = 0;
        for (int i=0; i < tamano; i++){
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)){
                contador++;
            }
        }

        Matricula[] resultado = new Matricula[contador];

        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                resultado[indice] = coleccionMatriculas[i];
                indice++;
            }
        }

        return resultado;
    }

    public Matricula[] get(CicloFormativo cicloFormativo){
        if (cicloFormativo==null) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        int contador = 0;

        for (int i = 0; i<tamano; i++){
            boolean matriculaAsociadaACiclo = false;

            for (int j=0; j < coleccionMatriculas[i].getColeccionAsignaturas().length; j++){
                Asignatura asignatura = coleccionMatriculas[i].getColeccionAsignaturas()[j];

                if (asignatura.getCicloFormativo().equals(cicloFormativo)){
                    matriculaAsociadaACiclo = true;
                    break;
                }
            }

            if (matriculaAsociadaACiclo) {
                contador++;
            }
        }

        Matricula[] resultado = new Matricula[contador];

        int indice = 0;
        for (int i = 0; i<tamano; i++) {
            boolean matriculaAsociada = false;

            for (int j = 0; j < coleccionMatriculas[i].getColeccionAsignaturas().length; j++) {
                Asignatura asignatura = coleccionMatriculas[i].getColeccionAsignaturas()[j];

                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    resultado[indice] = coleccionMatriculas[i];
                    matriculaAsociada = true;
                    break;
                }
            }
            if (matriculaAsociada) {
                indice++;
            }
        }

        return resultado;
    }

}
