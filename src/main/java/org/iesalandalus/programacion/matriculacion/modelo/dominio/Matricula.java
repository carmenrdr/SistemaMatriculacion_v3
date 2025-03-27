package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matricula {

    public static final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    public static final String FORMATO_FECHA = "dd-dd";
    private static final String ER_CURSO_ACADEMICO = "([0-9]{2})-([0-9]{2})";

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;

    private Alumno alumno;
    private List<Asignatura> coleccionAsignaturas = new ArrayList<>();

    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, List<Asignatura> coleccionAsignaturas) throws IllegalArgumentException {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    public Matricula(Matricula matricula) throws IllegalArgumentException {
        if (matricula==null){
            throw new IllegalArgumentException("No es posible copiar una matrícula nula.");
        } else {
            setIdMatricula(matricula.getIdMatricula());
            setCursoAcademico(matricula.getCursoAcademico());
            setFechaMatriculacion(matricula.getFechaMatriculacion());
            setAlumno(matricula.getAlumno());
            setColeccionAsignaturas(matricula.getColeccionAsignaturas());
            setFechaAnulacion(getFechaAnulacion());
        }
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) throws IllegalArgumentException {
        if (idMatricula <= 0){
            throw new IllegalArgumentException("El identificador de una matrícula no puede ser menor o igual a 0.");
        } else {
            this.idMatricula = idMatricula;
        }
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) throws IllegalArgumentException {
        if (cursoAcademico == null) {
            throw new IllegalArgumentException("El curso académico de una matrícula no puede ser nulo.");
        } else if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("El formato del curso académico no es correcto.");
        } else {
            this.cursoAcademico = cursoAcademico;
        }
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) throws IllegalArgumentException {
        if (fechaMatriculacion == null) {
            throw new IllegalArgumentException("La fecha de matriculación de una mátricula no puede ser nula.");
        }

        LocalDate fechaLimiteMatricula = LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA);
        if (fechaMatriculacion.isBefore(fechaLimiteMatricula)){
            throw new IllegalArgumentException("La fecha de matriculación no puede ser anterior a" + Matricula.MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }

        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) throws IllegalArgumentException {
        LocalDate fechaHoy = LocalDate.now();

        if (fechaAnulacion.isAfter(fechaHoy)) {
            throw new IllegalArgumentException("La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }

        if (fechaAnulacion.isBefore(this.fechaMatriculacion)){
            throw new IllegalArgumentException("La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }

        LocalDate limiteAnulacionMatricula = this.fechaMatriculacion.plusMonths(MAXIMO_MESES_ANTERIOR_ANULACION);
        if (fechaAnulacion.isAfter(limiteAnulacionMatricula)){
            throw new IllegalArgumentException("La fecha de anulación no puede ser posterior a " + Matricula.MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }

        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno(){
        return this.alumno;
    }

    public void setAlumno(Alumno alumno) throws IllegalArgumentException {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno de una matrícula no puede ser nulo.");
        } else {
            this.alumno = alumno;
        }
    }

    public List<Asignatura> getColeccionAsignaturas(){
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(List<Asignatura> coleccionAsignaturas) throws IllegalArgumentException {
        if (coleccionAsignaturas == null){
            throw new IllegalArgumentException("La lista de asignaturas de una matrícula no puede ser nula.");
        } else if (coleccionAsignaturas.size() > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA){
            throw new IllegalArgumentException("No puede haber más de 10 asignaturas por matricula.");
        } if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new IllegalArgumentException("No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA +" horas).");
        } else {
            this.coleccionAsignaturas = coleccionAsignaturas;
        }
    }

    private boolean superaMaximoNumeroHorasMatricula(List<Asignatura> asignaturasMatricula){
        int totalHorasMatricula = 0;

        for (Asignatura asignatura : asignaturasMatricula) {
            totalHorasMatricula += asignatura.getHorasAnuales();
        }

        return (totalHorasMatricula > 1000);
    }

    private String asignaturasMatricula(){
        StringBuilder codigosMatricula = new StringBuilder();

        for (int i = 0; i < coleccionAsignaturas.size(); i++){
            codigosMatricula.append(coleccionAsignaturas.get(i).getCodigo());

            if (i < coleccionAsignaturas.size()-1) {
                codigosMatricula.append(", ");
            }
        }

        return codigosMatricula.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        final Matricula other = (Matricula) o;

        if (!Objects.equals(this.idMatricula, other.idMatricula)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    public String imprimir() {
        return "idMatricula= "+idMatricula+", curso académico= "+cursoAcademico+
                ", fecha matriculación= "+fechaMatriculacion.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA))+
                ", alumno= "+alumno.imprimir()+".";
    }

    @Override
    public String toString() {
        return "idMatricula= "+idMatricula+", curso académico= "+cursoAcademico+
                ", fecha matriculación= "+fechaMatriculacion.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA))+
                ", alumno= "+alumno.imprimir()+", Asignaturas= "+coleccionAsignaturas+".";
    }

}
