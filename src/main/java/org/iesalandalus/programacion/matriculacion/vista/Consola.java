package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {
        Opcion[] opcionesMenu = Opcion.values();

        for (int i = 0; i < opcionesMenu.length; i++) {
            System.out.println(opcionesMenu[i]);
        }
    }

    public static Opcion elegirOpcion() {
        int opcion = -1;

        do {
            System.out.println("Elija una opción: ");
            opcion = Entrada.entero();

            if (opcion < 0 || opcion >= Opcion.values().length) {
                System.out.println("La opción elegida no es válida.");
            }
        } while (opcion < 0 || opcion >= Opcion.values().length);

        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno() {
        System.out.println("Introduzca el nombre del nuevo alumno o alumna: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca el DNI: ");
        String dni = Entrada.cadena();

        System.out.println("Introduzca el correo: ");
        String correo = Entrada.cadena();

        System.out.println("Introduzca el teléfono: ");
        String telefono = Entrada.cadena();

        System.out.println("Introduzca la fecha de nacimiento en formato dd/mm/yyyy: ");
        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());

        try {
            return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El alumno introducido no es correcto.\n" + e.getMessage());
        }
    }

    public Asignatura leerAsignatura(){

    }

    public Alumno getAlumnoPorDni(){
        try {
            System.out.println("Introduzca el DNI del alumno/a a buscar: ");
            String dniABuscar = Entrada.cadena();

            Alumno alumnoInventado = new Alumno("Filemón", dniABuscar, "busca@busca.com", "000000000", LocalDate.of(1996, 1, 1));
            return alumnoInventado;

        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: "+e.getMessage());
        }
    }

    public LocalDate leerFecha(String mensaje) {
        String fechaIntroducida = Entrada.cadena();

        String FORMATO_FECHA = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})";
        Pattern pattern = Pattern.compile(FORMATO_FECHA);
        Matcher matcher = pattern.matcher(fechaIntroducida);

        do {
            if (!matcher.matches()) {
                throw new IllegalArgumentException("ERROR: La fecha introducida no tiene el formato correcto.");
            }
        } while (!matcher.matches());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaIntroducida, formatter);
    }

    public Grado leerGrado() {
        System.out.println("Los Grados existentes son los siguientes: ");
        for (int i = 0; i < Grado.values().length; i++) {
            System.out.println(Grado.values()[i].imprimir());
        }

        int opcion = -1;
        do {
            try {
                System.out.println("Introduzca el Grado elegido (0, 1 ó 2): ");
                opcion = Entrada.entero();

                if (opcion < 0 || opcion >= Grado.values().length) {
                    throw new IllegalArgumentException("La opción introducida no es válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion < 0 || opcion >= Grado.values().length);

        return Grado.values()[opcion];

    }

    public CicloFormativo leerCicloFormativo(){
        System.out.println("Introduzca código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        System.out.println("Introduzca la familia profesional: ");
        String familiaProfesional = Entrada.cadena();

        Grado grado = leerGrado();

        System.out.println("Introduzca el nombre del Ciclo: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca las horas que tiene: ");
        int horas = Entrada.entero();

        try {
            return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El Ciclo Formativo introducido no es correcto.\n" + e.getMessage());
        }
    }

    public void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos){
        if (ciclosFormativos == null || ciclosFormativos.getTamano()==0){
            System.out.println("No hay Ciclos Formativos registrados.");
        } else {
            System.out.println("Los Ciclos Formativos registrados son los siguientes: ");
            CicloFormativo[] ciclosRegistrados = ciclosFormativos.get();

            for (int i=0; i< ciclosRegistrados.length; i++){
                System.out.println(ciclosRegistrados[i].imprimir());
            }
        }
    }

    public CicloFormativo getCicloFormativoPorCodigo(){
        try {
            System.out.println("Introduzca el código del Ciclo Formativo que desea buscar: ");
            int codigoBuscar = Entrada.entero();

            CicloFormativo cicloInventado = new CicloFormativo(codigoBuscar, "Programacion", Grado.valueOf("GDCFGB"), "DAM", 100);
            return cicloInventado;

        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: "+e.getMessage());
        }
    }

    public Curso leerCurso(){
        System.out.println("Los cursos existentes son los siguientes: ");
        for (int i = 0; i < Curso.values().length; i++) {
            System.out.println(Curso.values()[i].imprimir());
        }

        int opcion=-1;
        do {
           try {
               System.out.println("Introduzca el curso elegido (0 ó 1): ");
               opcion = Entrada.entero();

               if (opcion < 0 || opcion > 1) {
                   throw new IllegalArgumentException("La opción introducida no es válida.");
               }
           } catch (IllegalArgumentException e) {
               System.out.println("ERROR: " + e.getMessage());
           }
        } while (opcion < 0 || opcion > 1);

        return Curso.values()[opcion];
    }

    public EspecialidadProfesorado leerEspecialidadProfesorado(){
        System.out.println("Las especialidades existentes son: ");
        for (int i = 0; i < EspecialidadProfesorado.values().length; i++) {
            System.out.println(EspecialidadProfesorado.values()[i].imprimir());
        }

        int opcion=-1;
        do {
            try {
                System.out.println("Introduzca la especialidad elegida (0, 1 ó 2): ");
                opcion = Entrada.entero();

                if (opcion < 0 || opcion > 2) {
                    throw new IllegalArgumentException("La opción introducida no es válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion < 0 || opcion > 2);

        return EspecialidadProfesorado.values()[opcion];
    }

}
