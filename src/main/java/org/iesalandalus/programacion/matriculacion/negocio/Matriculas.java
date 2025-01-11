package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

public class Matriculas {

    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    public Matricula[] get(){
        return copiaProfundaMatricula();
    }

    private Matricula[] copiaProfundaMatricula(){
        Matricula[] copia = new Matricula[tamano];

        for (int i = 0; i<tamano; i++){
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }

        return copia;
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
        } else if (tamano >= capacidad){
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
        if (matricula == null){
            return null;
        }

        int indice = buscarIndice(matricula);
        if (indice == -1){
            return null;
        } else {
            return coleccionMatriculas[indice];
        }
    }

    public void borrar(Matricula matricula){
        if (matricula == null){
            throw new IllegalArgumentException("ERROR: No se puede borrar una matrícula nula.");
        }

        int indice = buscarIndice(matricula);
        if (indice == -1){
            throw new IllegalArgumentException("ERROR: No existe ninguna matrícula como la indicada.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        coleccionMatriculas[tamano -1] = null; //eliminar el último alumno (duplicado al final)
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i = indice; i < tamano -1 && coleccionMatriculas[i] != null; i++){
            coleccionMatriculas[i] = coleccionMatriculas[i+1];
        }
    }

    public Matricula[] get(Alumno alumno){
        if (alumno==null){
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo.");
        }

        Matricula[] resultado = new Matricula[tamano];

        int contador = 0;
        for (int i=0; i < tamano; i++){
            if (coleccionMatriculas[i].getAlumno().equals(alumno)){
                resultado[contador] = coleccionMatriculas[i];
                contador++;
            }
        }

        return resultado;
    }

    public Matricula[] get(String cursoAcademico){
        if (cursoAcademico==null){
            throw new IllegalArgumentException("ERROR: El curso académico no puede ser nulo.");
        }

        Matricula[] resultado = new Matricula[tamano];

        int contador = 0;
        for (int i=0; i < tamano; i++){
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)){
                resultado[contador] = coleccionMatriculas[i];
                contador++;
            }
        }
        return resultado;
    }

    public Matricula[] get(CicloFormativo cicloFormativo){
        if (cicloFormativo==null){
            throw new IllegalArgumentException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        Matricula[] resultado = new Matricula[tamano];
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
                resultado[contador++] = coleccionMatriculas[i];
            }
        }
        return resultado;
    }

}
