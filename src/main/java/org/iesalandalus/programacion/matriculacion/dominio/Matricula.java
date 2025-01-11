package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;

public class Matricula {

    public static int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    public static String FORMATO_FECHA = "dd-dd";
    private static String ER_CURSO_ACADEMICO = "([0-9]{2})-([0-9]{2})";

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;

    /*private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;*/



    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0){
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        } else {
            this.idMatricula = idMatricula;
        }
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        } else if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        } else {
            this.cursoAcademico = cursoAcademico;
        }
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        }

        LocalDate fechaLimiteMatricula = LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA);
        if (fechaMatriculacion.isBefore(fechaLimiteMatricula)){
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a" + Matricula.MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }

        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        LocalDate fechaHoy = LocalDate.now();

        if (fechaAnulacion.isAfter(fechaHoy)) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }

        if (fechaAnulacion.isBefore(this.fechaMatriculacion)){
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }

        LocalDate limiteAnulacionMatricula = this.fechaMatriculacion.plusMonths(MAXIMO_MESES_ANTERIOR_ANULACION);
        if (fechaAnulacion.isAfter(limiteAnulacionMatricula)){
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser posterior a " + Matricula.MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }

        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno(){
        return this.alumno;
    }

    public void setAlumno(Alumno alumno){
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: El alumno de una matrícula no puede ser nulo.");
        } else {
            this.alumno = alumno;
        }
    }

    public Asignatura[] getColeccionAsignaturas(){
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas){
        if (coleccionAsignaturas == null){
            throw new IllegalArgumentException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        } else if (coleccionAsignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA){
            throw new IllegalArgumentException("ERROR: No puede haber más de 10 asignaturas por matricula.");
        } if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new IllegalArgumentException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA +" horas).");
        } else {
            this.coleccionAsignaturas = coleccionAsignaturas;
        }
    }

    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturasMatricula){
        int totalHorasMatricula = 0;

        for (int i = 0; i < asignaturasMatricula.length; i++){
            totalHorasMatricula += asignaturasMatricula[i].getHorasAnuales();
        }

        return totalHorasMatricula > 1000;
    }

    private String asignaturasMatricula(){
        StringBuilder codigosMatricula = new StringBuilder();

        for (int i = 0; i < coleccionAsignaturas.length; i++){
            codigosMatricula.append(coleccionAsignaturas[i].getCodigo());

            if (i < coleccionAsignaturas.length-1) {
                codigosMatricula.append(", ");
            }
        }

        return codigosMatricula.toString();
    }

}
