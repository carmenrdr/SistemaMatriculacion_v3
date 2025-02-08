package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
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
                    insertarAlumno();
                } catch (Exception e) {
                    throw new IllegalArgumentException("EEROR: " + e.getMessage());
                }
                break;
            case BUSCAR_ALUMNO:
                try {
                    Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
                    Alumno alumnoEncontrado = new Alumno(alumnos.buscar(alumnoABuscar));
                    System.out.println(alumnoEncontrado);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BORRAR_ALUMNO:
                try {
                    Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
                    Alumno alumnoEncontrado = new Alumno(alumnos.buscar(alumnoABuscar));
                    alumnos.borrar(alumnoEncontrado);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_ALUMNOS:
                try {
                    if (alumnos == null || alumnos.getTamano()==0) {
                        System.out.println("No hay alumnos/as almacenados.");
                    } else {
                        System.out.println("Los alumnos y alumnas registradas son:");
                        for (int i=0; i < alumnos.getTamano(); i++) {
                            Alumno alumno = alumnos.get()[i];
                            System.out.println(alumno);
                        }
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case INSERTAR_ASIGNATURA:
                try {
                    Asignatura asignaturaNueva = new Asignatura(Consola.leerAsignatura());
                    asignaturas.insertar(asignaturaNueva);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BUSCAR_ASIGNATURA:
                try {
                    Asignatura asignaturaABuscar = new Asignatura(Consola.getAsignaturaPorCodigo());
                    Asignatura asignaturaEncontrada = new Asignatura(asignaturas.buscar(asignaturaABuscar));
                    System.out.println(asignaturaEncontrada);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BORRAR_ASIGNATURA:
                try {
                    Asignatura asignaturaABuscar = new Asignatura(Consola.getAsignaturaPorCodigo());
                    Asignatura asignaturaEncontrada = new Asignatura(asignaturas.buscar(asignaturaABuscar));
                    asignaturas.borrar(asignaturaEncontrada);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_ASIGNATURAS:
                try {
                    Consola.mostrarAsignaturas(asignaturas);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case INSERTAR_CICLO_FORMATIVO:
                try {
                    CicloFormativo cicloFormativoNuevo = new CicloFormativo(Consola.leerCicloFormativo());
                    ciclosFormativos.insertar(cicloFormativoNuevo);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BUSCAR_CICLO_FORMATIVO:
                try {
                    CicloFormativo cicloFormativoABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
                    CicloFormativo cicloFormativoEncontrado = new CicloFormativo(ciclosFormativos.buscar(cicloFormativoABuscar));
                    System.out.println(cicloFormativoEncontrado);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BORRAR_CICLO_FORMATIVO:
                try {
                    CicloFormativo cicloFormativoABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
                    CicloFormativo cicloFormativoEncontrado = new CicloFormativo(ciclosFormativos.buscar(cicloFormativoABuscar));
                    ciclosFormativos.borrar(cicloFormativoEncontrado);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                try {
                    Consola.mostrarCiclosFormativos(ciclosFormativos);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case INSERTAR_MATRICULA:
                try {
                    Matricula matriculaNueva = new Matricula(Consola.leerMatricula(alumnos,asignaturas));
                    matriculas.insertar(matriculaNueva);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BUSCAR_MATRICULA:
                try {
                    Matricula matriculaABuscar = new Matricula(Consola.getMatriculaPorIdentificador());
                    Matricula matriculaEncontrada = new Matricula(matriculas.buscar(matriculaABuscar));
                    System.out.println(matriculaEncontrada);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case ANULAR_MATRICULA:
                try {
                    for (int i = 0; i < matriculas.getTamano(); i++) {
                        Matricula matricula = matriculas.get()[i];
                        System.out.println(matricula.imprimir());
                    }

                    Matricula matriculaAAnular = new Matricula(Consola.getMatriculaPorIdentificador());

                    System.out.println("Introduzca la fecha de anulación:");
                    String fecha = Entrada.cadena();
                    LocalDate fechaAnulacion = LocalDate.parse(fecha);

                    matriculaAAnular.setFechaAnulacion(fechaAnulacion);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS:
                try {
                    if (matriculas == null || matriculas.getTamano()==0) {
                        System.out.println("No hay matrículas almacenadas.");
                    } else {
                        System.out.println("Las matrículas registradas son:");
                        for (int i=0; i < matriculas.getTamano(); i++) {
                            Matricula matricula = matriculas.get()[i];
                            System.out.println(matricula);
                        }
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                try {

                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            /*case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                try {

                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                try {

                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break; */
        }
    }

    private static void insertarAlumno() {
        try {
            Alumno alumnoNuevo = new Alumno(Consola.leerAlumno());
            alumnos.insertar(alumnoNuevo);
        } catch (Exception e) {
            throw new IllegalArgumentException("EEROR: " + e.getMessage());
        }
    }
}
