package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.Objects;

public class CicloFormativo {

    public static final int MAXIMO_NUMERO_HORAS = 2000;
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) throws IllegalArgumentException {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo cicloFormativo) throws IllegalArgumentException {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("No es posible copiar un ciclo formativo nulo.");
        } else {
            setCodigo(cicloFormativo.getCodigo());
            setFamiliaProfesional(cicloFormativo.getFamiliaProfesional());
            setGrado(cicloFormativo.getGrado());
            setNombre(cicloFormativo.getNombre());
            setHoras(cicloFormativo.getHoras());
        }
    }

    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) throws IllegalArgumentException {
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("El código es incorrecto");
        } else {
            this.codigo = codigo;
        }
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) throws IllegalArgumentException {
        if (familiaProfesional == null) {
            throw new IllegalArgumentException("La familia profesional de un ciclo formativo no puede ser nula.");
        } else {
            this.familiaProfesional = familiaProfesional;
        }
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) throws IllegalArgumentException {
        if (grado == null) {
            throw new IllegalArgumentException("El grado de un ciclo formativo no puede ser nulo.");
        } else {
            this.grado = grado;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre de un ciclo formativo no puede ser nulo.");
        } else {
            this.nombre = nombre;
        }
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) throws IllegalArgumentException {
        if (horas <= 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a " + CicloFormativo.MAXIMO_NUMERO_HORAS + ".");
        } else {
            this.horas = horas;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CicloFormativo other = (CicloFormativo) o;

        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String imprimir() {
        return "Código ciclo formativo= " + codigo + ", nombre ciclo formativo= " + nombre + ".";
    }

    @Override
    public String toString() {
        return "Código ciclo formativo= " + codigo + ", familia profesional= " + familiaProfesional +
                ", grado= " + grado + ", nombre ciclo formativo=" + nombre + ", horas= " + horas + ".";
    }
}
