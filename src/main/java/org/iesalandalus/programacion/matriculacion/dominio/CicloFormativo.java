package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo {

    public static int MAXIMO_NUMERO_HORAS = 2000;
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    public CicloFormativo (int codigo, String familiaProfesional, Grado grado, String nombre, int horas) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null){
            throw new IllegalArgumentException("ERROR: No es posible copiar un ciclo formativo nulo.");
        } else {
            this.codigo = cicloFormativo.codigo;
            this.familiaProfesional = cicloFormativo.familiaProfesional;
            this.grado  = cicloFormativo.grado;
            this.nombre = cicloFormativo.nombre;
            this.horas = cicloFormativo.horas;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("ERROR: El código es incorrecto");
        } else {
            this.codigo = codigo;
        }
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional == null) {
            throw new IllegalArgumentException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        } else {
            this.familiaProfesional = familiaProfesional;
        }
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado == null) {
            throw new IllegalArgumentException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        } else {
            this.grado = grado;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        } else {
            this.nombre = nombre;
        }
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas < 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a " + CicloFormativo.MAXIMO_NUMERO_HORAS + ".");
        } else {
            this.horas = horas;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String imprimir(){
        return String.format("Código ciclo formativo=%d, nombre ciclo formativo=%s", codigo, nombre);
    }

    @Override
    public String toString() {
        return String.format("Código ciclo formativo=%d, familia profesional=%s, grado=%s, nombre ciclo formativo=%s, horas=%s", codigo, familiaProfesional, nombre, horas);
    }
}
