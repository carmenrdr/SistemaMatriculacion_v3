package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.Objects;

public class Asignatura {

    public static final int MAX_NUM_HORAS_ANUALES = 300;
    public static final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "[0-9]{4}";
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    public Asignatura(String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) throws IllegalArgumentException {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura) throws IllegalArgumentException {
        if (asignatura == null){
            throw new IllegalArgumentException("No es posible copiar una asignatura nula.");
        } else {
            setCodigo(asignatura.getCodigo());
            setNombre(asignatura.getNombre());
            setHorasAnuales(asignatura.getHorasAnuales());
            setCurso(asignatura.getCurso());
            setHorasDesdoble(asignatura.getHorasDesdoble());
            setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
            setCicloFormativo(asignatura.getCicloFormativo());
        }
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        if (cicloFormativo == null){
            throw new IllegalArgumentException("El ciclo formativo de una asignatura no puede ser nulo.");
        } else {
            this.cicloFormativo = cicloFormativo;
        }
    }

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) throws IllegalArgumentException {
        if (codigo == null) {
            throw new IllegalArgumentException("El código de una asignatura no puede ser nulo.");
        } else if (!codigo.matches(ER_CODIGO)) {
            throw new IllegalArgumentException("El código de la asignatura no tiene un formato válido.");
        } else {
            this.codigo = codigo;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null){
            throw new IllegalArgumentException("El nombre de una asignatura no puede ser nulo.");
        } else {
            this.nombre = nombre;
        }
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) throws IllegalArgumentException {
        if (horasAnuales < 0 || horasAnuales > MAX_NUM_HORAS_ANUALES){
            throw new IllegalArgumentException("El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a" + Asignatura.MAX_NUM_HORAS_ANUALES +".");
        } else {
            this.horasAnuales = horasAnuales;
        }
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) throws IllegalArgumentException {
        if (curso == null){
            throw new IllegalArgumentException("El curso de una asignatura no puede ser nulo");
        } else {
            this.curso = curso;
        }
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) throws IllegalArgumentException {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES){
            throw new IllegalArgumentException("El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a "+ Asignatura.MAX_NUM_HORAS_DESDOBLES+ ".");
        } else {
            this.horasDesdoble = horasDesdoble;
        }
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) throws IllegalArgumentException {
        if (especialidadProfesorado == null){
            throw new IllegalArgumentException("La especialidad del profesorado de una asignatura no puede ser nula.");
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
        return "Código asignatura= "+codigo+", nombre asignatura= "+nombre+
                ", ciclo formativo= "+cicloFormativo.imprimir()+".";
    }

    @Override
    public String toString() {
        return "Código= "+codigo+", nombre= "+nombre+", horas anuales= "+horasAnuales+
                ", curso= "+curso+", horas desdoble= "+horasDesdoble+
                ", ciclo formativo= "+cicloFormativo.imprimir()+", especialidad profesorado= "+especialidadProfesorado+".";
    }
}
