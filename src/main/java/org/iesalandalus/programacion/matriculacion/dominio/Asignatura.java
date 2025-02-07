package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class Asignatura {

    public static int MAX_NUM_HORAS_ANUALES = 300;
    public static int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "[0-9]{4}";
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    public Asignatura(String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo){
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura){
        if (asignatura == null){
            throw new IllegalArgumentException("ERROR: No es posible copiar una asignatura nula.");
        } else {
            this.codigo = asignatura.getCodigo();
            this.nombre = asignatura.getNombre();
            this.horasAnuales = asignatura.getHorasAnuales();
            this.curso = asignatura.getCurso();
            this.horasDesdoble = asignatura.getHorasDesdoble();
            this.especialidadProfesorado = asignatura.getEspecialidadProfesorado();
            this.cicloFormativo = asignatura.getCicloFormativo();
        }
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null){
            throw new IllegalArgumentException("ERROR: El ciclo formativo de una asignatura no puede ser nulo.");
        } else {
            this.cicloFormativo = cicloFormativo;
        }
    }

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura no puede ser nulo.");
        } else if (!codigo.matches(ER_CODIGO)) {
            throw new IllegalArgumentException("ERROR: El código de la asignatura no tiene un formato válido.");
        } else {
            this.codigo = codigo;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede ser nulo.");
        } else {
            this.nombre = nombre;;
        }
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales < 0 || horasAnuales > MAX_NUM_HORAS_ANUALES){
            throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a" + Asignatura.MAX_NUM_HORAS_ANUALES +".");
        } else {
            this.horasAnuales = horasAnuales;
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null){
            throw new IllegalArgumentException("ERROR: El curso de una asignatura no puede ser nulo");
        } else {
            this.curso = curso;
        }
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES){
            throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a "+ Asignatura.MAX_NUM_HORAS_DESDOBLES+ ".");
        } else {
            this.horasDesdoble = horasDesdoble;
        }
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null){
            throw new IllegalArgumentException("ERROR: La especialidad del profesorado de una asignatura no puede ser nula.");
        } else {
            this.especialidadProfesorado = especialidadProfesorado;
        }
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        Asignatura other = (Asignatura) o;

        if (!Objects.equals(this.codigo, other.codigo)) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String imprimir(){
        return String.format("Código asignatura=%s, nombre asignatura=%s, ciclo formativo=%s", codigo, nombre, cicloFormativo.imprimir());
    }

    @Override
    public String toString() {
        return String.format("Código=%s, nombre=%s, horas anuales=%d, curso=%s, horas desdoble=%d, ciclo formativo=%s, especialidad profesorado=%s",
        codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado.imprimir());
    }
}
