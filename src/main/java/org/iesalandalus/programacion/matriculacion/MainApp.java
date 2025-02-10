package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

public class MainApp {
    public static final int CAPACIDAD=3;

    private static Alumnos alumnos;
    private static Matriculas matriculas;
    private static Asignaturas asignaturas;
    private static CiclosFormativos ciclosFormativos;

    public static void main(String[] args) {
        /*Opcion opcion;

        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

        System.out.println("¡Hasta la próxima!");
    }*/

}
