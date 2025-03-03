package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class Vista {

    private Controlador controlador;

    public Vista() {
        Opcion.setVista(this);
    }

    public void setControlador(Controlador controlador) throws OperationNotSupportedException {
        if (controlador == null) {
            throw new OperationNotSupportedException("ERROR: El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() throws OperationNotSupportedException {

        Opcion opcionElegida;

        do {
            Consola.mostrarMenu();
            opcionElegida = Consola.elegirOpcion();
            opcionElegida.ejecutar();
        } while (opcionElegida != Opcion.SALIR);

        terminar();
    }

    public void terminar() {
        controlador.terminar();
    }

    public void insertarAlumno() throws OperationNotSupportedException {
        Alumno alumnoNuevo = new Alumno(Consola.leerAlumno());
        controlador.insertar(alumnoNuevo);
    }

    public void buscarAlumno() throws OperationNotSupportedException {
        Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoEncontrado = controlador.buscar(alumnoABuscar);

        if (alumnoEncontrado != null) {
            System.out.println(alumnoEncontrado);
        } else {
            System.out.println("No existe ningún/a alumno/a con este DNI.");
        }

    }

    public void borrarAlumno() throws OperationNotSupportedException {
        Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoEncontrado = controlador.buscar(alumnoABuscar);

        if (alumnoEncontrado != null) {
                controlador.borrar(alumnoEncontrado);
            } else {
            System.out.println("No existe ningún/a alumno/a con este DNI.");
        }

    }

    public void mostrarAlumnos() throws OperationNotSupportedException {
        List<Alumno> alumnos = controlador.getAlumnos();

        Comparator<Alumno> comparadorAlumnos = Comparator.comparing(Alumno :: getNombre);

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos/as almacenados.");
        } else {
            System.out.println("Los alumnos y alumnas registradas son:");
            alumnos.sort(comparadorAlumnos);
            alumnos.forEach(System.out :: println);
        }
    }

    public void insertarAsignatura() throws OperationNotSupportedException {
        Asignatura asignaturaNueva = new Asignatura(Consola.leerAsignatura());
        controlador.insertar(asignaturaNueva);
    }

    public void buscarAsignatura() throws OperationNotSupportedException {
        Asignatura asignaturaABuscar = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asignaturaEncontrada = controlador.buscar(asignaturaABuscar);

        if (asignaturaEncontrada != null) {
            System.out.println(asignaturaEncontrada);
        } else {
            System.out.println("No existe asignatura con este código.");
        }
    }

    public void borrarAsignatura() throws OperationNotSupportedException {
        Asignatura asignaturaABuscar = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asignaturaEncontrada = controlador.buscar(asignaturaABuscar);

        if (asignaturaEncontrada != null) {
                controlador.borrar(asignaturaEncontrada);
            } else {
            System.out.println("No existe asignatura con este código.");
        }
    }

    public void mostrarAsignaturas() throws OperationNotSupportedException {
        List<Asignatura> asignaturas = controlador.getAsignaturas();

        Comparator<Asignatura> comparadorAsignaturas = Comparator.comparing(Asignatura :: getNombre);

        if (asignaturas.isEmpty()) {
            System.out.println("No hay asginaturas almacenadas.");
        } else {
            asignaturas.sort(comparadorAsignaturas);
            Consola.mostrarAsignaturas(asignaturas);
        }
    }

    public void insertarCicloFormativo() throws OperationNotSupportedException {
        CicloFormativo cicloFormativoNuevo = new CicloFormativo(Consola.leerCicloFormativo());
        controlador.insertar(cicloFormativoNuevo);
    }

    public void buscarCicloFormativo() throws OperationNotSupportedException {
        CicloFormativo cicloFormativoABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloFormativoEncontrado = controlador.buscar(cicloFormativoABuscar);

        if (cicloFormativoEncontrado != null) {
            System.out.println(cicloFormativoEncontrado);
        } else {
            System.out.println("No existe Ciclo Foramtivo con este código.");
        }
    }

    public void borrarCicloFormativo() throws OperationNotSupportedException {
        CicloFormativo cicloFormativoABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloFormativoEncontrado = controlador.buscar(cicloFormativoABuscar);

        if (cicloFormativoEncontrado != null) {
                controlador.borrar(cicloFormativoEncontrado);
            } else {
            System.out.println("No existe Ciclo Foramtivo con este código.");
        }

    }

    public void mostrarCiclosFormativos() throws OperationNotSupportedException {
        List<CicloFormativo> ciclosFormativos = controlador.getCiclosFormativos();

        Comparator<CicloFormativo> comparadorCiclos = Comparator.comparing(CicloFormativo :: getNombre);

        if (ciclosFormativos.isEmpty()) {
            System.out.println("No existen Ciclos Formativos registrados.");
        } else {
            ciclosFormativos.sort(comparadorCiclos);
            Consola.mostrarCiclosFormativos(ciclosFormativos);
        }
    }

    public void insertarMatricula() throws OperationNotSupportedException {
        Alumno alumno = Consola.leerAlumno();
        List<Asignatura> asignaturas = controlador.getAsignaturas();
        List<Asignatura> asignaturasElegidas;

        if (asignaturas != null) {
            asignaturasElegidas = Consola.elegirAsignaturasMatricula(asignaturas);
        } else {
            asignaturasElegidas = null;
        }

        Matricula matriculaNueva = Consola.leerMatricula(alumno, asignaturasElegidas);
        controlador.insertar(matriculaNueva);

    }

    public void buscarMatricula() throws OperationNotSupportedException {
        Matricula matriculaABuscar = new Matricula(Consola.getMatriculaPorIdentificador());
        Matricula matriculaEncontrada = controlador.buscar(matriculaABuscar);


        if (matriculaEncontrada != null) {
            System.out.println(matriculaEncontrada);
        } else {
            System.out.println("No existe matrícula con este identificador.");
        }
    }

    public void anularMatricula() throws OperationNotSupportedException {
        mostrarMatriculas();

        Matricula matriculaABuscar = Consola.getMatriculaPorIdentificador();
        Matricula matriculaEncontrada = controlador.buscar(matriculaABuscar);

        if (matriculaEncontrada == null) {
            System.out.println("No existe la matrícula que se quiere anular.");
        } else {
            System.out.println("Introduzca la fecha de anulación en formato dd/mm/yyyy:");
            String fecha = Entrada.cadena();

            LocalDate fechaAnulacion = Consola.leerFecha(fecha);

            matriculaEncontrada.setFechaAnulacion(fechaAnulacion);
        }
    }

    public void mostrarMatriculas() throws OperationNotSupportedException {
        List<Matricula> matriculas = controlador.getMatriculas();

        Comparator<Alumno> comparadorAlumnos = Comparator.comparing(Alumno :: getNombre);
        Comparator<Matricula> comparadorMatriculas = Comparator.comparing(Matricula :: getFechaMatriculacion).reversed().thenComparing(Matricula :: getAlumno, comparadorAlumnos);

        if (matriculas.isEmpty()) {
            System.out.println("No hay matrículas registradas.");
        } else {
            System.out.println("Las matrículas registradas son:");
            matriculas.sort(comparadorMatriculas);

            for (Matricula matricula : matriculas) {
                System.out.println(matricula.imprimir());
            }
        }
    }

    public void mostrarMatriculasPorAlumno() throws OperationNotSupportedException {
        mostrarAlumnos();
        Alumno alumnoABuscar = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoEncontrado = controlador.buscar(alumnoABuscar);

        List<Matricula> matriculasPorAlumno = controlador.getMatriculas(alumnoEncontrado);

        Comparator<Matricula> comparadorMatriculasAlumno = Comparator.comparing(Matricula :: getFechaMatriculacion).reversed();

        if (matriculasPorAlumno.isEmpty()) {
            System.out.println("No hay matrículas de este/a alumno/a.");
        } else {
            System.out.println("Las matrículas de este alumno son:");
            matriculasPorAlumno.sort(comparadorMatriculasAlumno);

            for (Matricula matricula : matriculasPorAlumno) {
                System.out.println(matricula.imprimir());
            }
        }
    }

    public void mostrarMatriculasPorCicloFormativo() throws OperationNotSupportedException {
        mostrarCiclosFormativos();
        CicloFormativo cicloABuscar = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloEncontrado = controlador.buscar(cicloABuscar);

        List<Matricula> matriculasPorCiclos = controlador.getMatriculas(cicloEncontrado);

        Comparator<Alumno> comparadorAlumnos = Comparator.comparing(Alumno :: getNombre);
        Comparator<Matricula> comparadorMatriculasCiclo = Comparator.comparing(Matricula :: getFechaMatriculacion).reversed().thenComparing(Matricula :: getAlumno, comparadorAlumnos);


        if (matriculasPorCiclos.isEmpty()) {
            System.out.println("No hay matrículas de este Ciclo Formativo.");
        } else {
            System.out.println("Las matrículas de este Ciclo Formativo son:");
            matriculasPorCiclos.sort(comparadorMatriculasCiclo);

            for (Matricula matricula : matriculasPorCiclos) {
                System.out.println(matricula.imprimir());
            }
        }
    }

    public void mostrarMatriculasPorCursoAcademico() throws OperationNotSupportedException {
        Curso curso = Consola.leerCurso();
        String cursoElegido = curso.toString();

        List<Matricula> matriculasPorCursos = controlador.getMatriculas(cursoElegido);

        Comparator<Alumno> comparadorAlumnos = Comparator.comparing(Alumno :: getNombre);
        Comparator<Matricula> comparadorMatriculasCursos = Comparator.comparing(Matricula :: getFechaMatriculacion).reversed().thenComparing(Matricula :: getAlumno, comparadorAlumnos);


        if (matriculasPorCursos.isEmpty()) {
            System.out.println("No hay matrículas del curso elegido.");
        } else {
            System.out.println("Las matrículas de este curso son:");
            matriculasPorCursos.sort(comparadorMatriculasCursos);

            for (Matricula matricula : matriculasPorCursos) {
                System.out.println(matricula.imprimir());
            }
        }
    }


}
