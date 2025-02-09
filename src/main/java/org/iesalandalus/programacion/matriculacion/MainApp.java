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
        Opcion opcion;

        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

        System.out.println("¡Hasta la próxima!");
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
                    buscarAlumno();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BORRAR_ALUMNO:
                try {
                    borrarAlumno();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_ALUMNOS:
                try {
                    mostrarAlumnos();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case INSERTAR_ASIGNATURA:
                try {
                    insertarAsignatura();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BUSCAR_ASIGNATURA:
                try {
                    buscarAsignatura();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BORRAR_ASIGNATURA:
                try {
                    borrarAsignatura();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_ASIGNATURAS:
                try {
                    mostrarAsignaturas();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case INSERTAR_CICLO_FORMATIVO:
                try {
                    insertarCicloFormativo();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BUSCAR_CICLO_FORMATIVO:
                try {
                    buscarCicloFormativo();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BORRAR_CICLO_FORMATIVO:
                try {
                    borrarCicloFormativo();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                try {
                    mostrarCiclosFormativos();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case INSERTAR_MATRICULA:
                try {
                    insertarMatricula();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case BUSCAR_MATRICULA:
                try {
                    buscarMatricula();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case ANULAR_MATRICULA:
                try {
                    anularMatricula();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS:
                try {
                    mostrarMatriculas();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                try {
                    mostrarMatriculasPorAlumno();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                try {
                    mostrarMatriculasPorCicloFormativo();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                try {
                    mostrarMatriculasPorCursoAcademico();
                } catch (Exception e) {
                    throw new IllegalArgumentException("ERROR: " + e.getMessage());
                }
                break;
        }
    }

    private static void insertarAlumno() throws Exception {
            Alumno alumnoNuevo = new Alumno(Consola.leerAlumno());
            alumnos.insertar(alumnoNuevo);
    }

    private static void buscarAlumno() {
        Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoEncontrado = new Alumno(alumnos.buscar(alumnoABuscar));
        System.out.println(alumnoEncontrado);
    }

    private static void borrarAlumno() throws Exception {
        Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoEncontrado = new Alumno(alumnos.buscar(alumnoABuscar));
        alumnos.borrar(alumnoEncontrado);
    }

    private static void mostrarAlumnos() {
        if (alumnos == null || alumnos.getTamano()==0) {
            System.out.println("No hay alumnos/as almacenados.");
        } else {
            System.out.println("Los alumnos y alumnas registradas son:");
            for (int i=0; i < alumnos.getTamano(); i++) {
                Alumno alumno = alumnos.get()[i];
                System.out.println(alumno);
            }
        }
    }

    private static void insertarAsignatura() throws Exception {
        Asignatura asignaturaNueva = new Asignatura(Consola.leerAsignatura());
        asignaturas.insertar(asignaturaNueva);
    }

    private static void buscarAsignatura() throws Exception {
        Asignatura asignaturaABuscar = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asignaturaEncontrada = new Asignatura(asignaturas.buscar(asignaturaABuscar));
        System.out.println(asignaturaEncontrada);
    }

    private static void borrarAsignatura() throws Exception {
        Asignatura asignaturaABuscar = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asignaturaEncontrada = new Asignatura(asignaturas.buscar(asignaturaABuscar));
        asignaturas.borrar(asignaturaEncontrada);
    }

    private static void mostrarAsignaturas() {
        Consola.mostrarAsignaturas(asignaturas);
    }

    private static void insertarCicloFormativo() throws Exception {
        CicloFormativo cicloFormativoNuevo = new CicloFormativo(Consola.leerCicloFormativo());
        ciclosFormativos.insertar(cicloFormativoNuevo);
    }

    private static void buscarCicloFormativo() {
        CicloFormativo cicloFormativoABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloFormativoEncontrado = new CicloFormativo(ciclosFormativos.buscar(cicloFormativoABuscar));
        System.out.println(cicloFormativoEncontrado);
    }

    private static void borrarCicloFormativo() throws Exception {
        CicloFormativo cicloFormativoABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloFormativoEncontrado = new CicloFormativo(ciclosFormativos.buscar(cicloFormativoABuscar));
        ciclosFormativos.borrar(cicloFormativoEncontrado);
    }

    private static void mostrarCiclosFormativos() {
        Consola.mostrarCiclosFormativos(ciclosFormativos);
    }

    private static void insertarMatricula() throws Exception {
        Matricula matriculaNueva = new Matricula(Consola.leerMatricula(alumnos,asignaturas));
        matriculas.insertar(matriculaNueva);
    }

    private static void buscarMatricula() throws Exception {
        Matricula matriculaABuscar = new Matricula(Consola.getMatriculaPorIdentificador());
        Matricula matriculaEncontrada = new Matricula(matriculas.buscar(matriculaABuscar));
        System.out.println(matriculaEncontrada);
    }

    private static void anularMatricula() throws Exception {
        for (int i = 0; i < matriculas.getTamano(); i++) {
            Matricula matricula = matriculas.get()[i];
            System.out.println(matricula.imprimir());
        }

        Matricula matriculaAAnular = new Matricula(Consola.getMatriculaPorIdentificador());

        System.out.println("Introduzca la fecha de anulación:");
        String fecha = Entrada.cadena();

        LocalDate fechaAnulacion = Consola.leerFecha(fecha);

        matriculaAAnular.setFechaAnulacion(fechaAnulacion);
    }

    private static void mostrarMatriculas() {
        if (matriculas == null || matriculas.getTamano()==0) {
            System.out.println("No hay matrículas almacenadas.");
        } else {
            System.out.println("Las matrículas registradas son:");
            for (int i=0; i < matriculas.getTamano(); i++) {
                Matricula matricula = matriculas.get()[i];
                System.out.println(matricula);
            }
        }
    }

    private static void mostrarMatriculasPorAlumno() throws Exception {
        Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoEncontrado = new Alumno(alumnos.buscar(alumnoABuscar));

        Matricula[] matriculasAlumno = matriculas.get(alumnoEncontrado);

        if (matriculasAlumno == null) {
            System.out.println("No hay matrículas para este/a alumno/a.");
        } else {
            for (int i = 0; i < matriculasAlumno.length; i++) {
                System.out.println(matriculasAlumno[i]);
            }
        }
    }

    private static void mostrarMatriculasPorCicloFormativo() {
        Consola.mostrarCiclosFormativos(ciclosFormativos);
        CicloFormativo cicloFormativoABuscar = Consola.getCicloFormativoPorCodigo();
        CicloFormativo cicloFormativoEncontrado = ciclosFormativos.buscar(cicloFormativoABuscar);

        Matricula[] matriculasCiclo = matriculas.get(cicloFormativoEncontrado);

        if (matriculasCiclo == null) {
            System.out.println("No hay matrículas para este Ciclo Formativo.");
        } else {
            for (int i = 0; i < matriculasCiclo.length; i++) {
                System.out.println(matriculasCiclo[i]);
            }
        }
    }

    private static void mostrarMatriculasPorCursoAcademico() throws Exception {
        Curso curso = Consola.leerCurso();
        String cursoElegido = curso.toString();

        Matricula[] matriculasCurso = matriculas.get(cursoElegido);

        if (matriculasCurso == null) {
            System.out.println("No hay matrículas para este curso académico.");
        } else {
            for (int i=0; i<matriculasCurso.length; i++) {
                System.out.println(matriculasCurso[i]);
            }
        }
    }

}
