package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

public class MainApp {
    public static final int CAPACIDAD=3;


    public static void main(String[] args) {
        Opcion opcion;

        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

        System.out.println("Hasta luego!!!!");
    }

    private static void ejecutarOpcion(Opcion opcion) {

        switch (opcion) {
            case INSERTAR_ALUMNO:
                try {
                    Consola.leerAlumno();
                } catch (IllegalArgumentException e) {
                    System.out.println("EEROR: " + e.getMessage());
                }
                break;
            case BUSCAR_ALUMNO:
                try {
                    Alumno alumno = Consola.getAlumnoPorDni();

                } catch ( ) {

                }
                break;
            /* case BORRAR_ALUMNO:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_ALUMNOS:
                try {

                } catch ( ) {

                }
                break;
            case INSERTAR_ASIGNATURA:
                try {

                } catch ( ) {

                }
                break;
            case BUSCAR_ASIGNATURA:
                try {

                } catch ( ) {

                }
                break;
            case BORRAR_ASIGNATURA:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_ASIGNATURAS:
                try {

                } catch ( ) {

                }
                break;
            case INSERTAR_CICLO_FORMATIVO:
                try {

                } catch ( ) {

                }
                break;
            case BUSCAR_CICLO_FORMATIVO:
                try {

                } catch ( ) {

                }
                break;
            case BORRAR_CICLO_FORMATIVO:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                try {

                } catch ( ) {

                }
                break;
            case INSERTAR_MATRICULA:
                try {

                } catch ( ) {

                }
                break;
            case BUSCAR_MATRICULA:
                try {

                } catch ( ) {

                }
                break;
            case ANULAR_MATRICULA:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_MATRICULAS:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                try {

                } catch ( ) {

                }
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                try {

                } catch ( ) {

                }
                break; */
        }
    }


}
