package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

public class Consola {

    private Consola(){
    }

    public static void mostrarMenu(){
        Opcion[] opcionesMenu = Opcion.values();

        for(int i = 0; i< opcionesMenu.length; i++){
            System.out.println(opcionesMenu[i]);
        }
    }

    public static Opcion elegirOpcion(){
        int opcion = -1;

        do {
            System.out.println("Elija una opción: ");
            opcion = Entrada.entero();

            if (opcion < 0 || opcion >= Opcion.values().length){
                System.out.println("La opción elegida no es válida.");
            }
        } while (opcion < 0 || opcion >= Opcion.values().length);

        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno(){
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
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("El alumno introducido no es correcto.\n"+ e.getMessage());
        }
    }

    /*public Alumno getAlumnoPorDni(){
        System.out.println("Introduzca el DNI del alumno/a a buscar: ");
        String dniABuscar = Entrada.cadena();

        Alumno alumnoInventado = new Alumno("Filemón", dniABuscar, "busca@busca.com", "000000000", LocalDate.of(1996,1,1));

        int indice = -1;
        boolean encontrado = false;

        Alumno[] alumnos = Alumnos.get();

        for (int i=0; i<alumnos.length && !encontrado; i++){
            if (alumnos[i] != null && alumnos[i].equals(alumnoInventado)){
                indice = i;
                encontrado = true;
            }
        }
        return alumnos[indice];

    }*/




}
