package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {

    SALIR("Salir.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.terminar();
        }
    },
    INSERTAR_ALUMNO("Insertar alumno/a.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.insertarAlumno();
        }
    },
    BUSCAR_ALUMNO("Buscar alumno/a.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.buscarAlumno();
        }
    },
    BORRAR_ALUMNO("Borrar alumno/a.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.borrarAlumno();
        }
    },
    MOSTRAR_ALUMNOS("Mostrar alumno/as.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarAlumnos();
        }
    },
    INSERTAR_CICLO_FORMATIVO("Insertar Ciclo Formativo.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.insertarCicloFormativo();
        }
    },
    BUSCAR_CICLO_FORMATIVO("Buscar Ciclo Formativo.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.buscarCicloFormativo();
        }
    },
    BORRAR_CICLO_FORMATIVO("Borrar Ciclo Formativo.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.borrarCicloFormativo();
        }
    },
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar Ciclos Formativos.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarCiclosFormativos();
        }
    },
    INSERTAR_ASIGNATURA("Insertar asignaturas.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.insertarAsignatura();
        }
    },
    BUSCAR_ASIGNATURA("Buscar asignaturas.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.buscarAsignatura();
        }
    },
    BORRAR_ASIGNATURA("Borrar asignaturas.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.borrarAsignatura();
        }
    },
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarAsignaturas();
        }
    },
    INSERTAR_MATRICULA("Insertar matrícula.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.insertarMatricula();
        }
    },
    BUSCAR_MATRICULA("Buscar matrícula.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.buscarMatricula();
        }
    },
    ANULAR_MATRICULA("Anular matrícula.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.anularMatricula();
        }
    },
    MOSTRAR_MATRICULAS("Mostrar matrículas.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarMatriculas();
        }
    },
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matrículas de un/a alumno/a.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarMatriculasPorAlumno();
        }
    },
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matrículas por Ciclo Formativo.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarMatriculasPorCicloFormativo();
        }
    },
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matrículas por curso académico.") {
        public void ejecutar() throws IllegalArgumentException {
            vista.mostrarMatriculasPorCursoAcademico();
        }
    };

    private final String cadenaAMostrar;
    private static Vista vista;

    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public abstract void ejecutar() throws IllegalArgumentException;

    public static void setVista(Vista vista) {
        Opcion.vista = vista;
    }

    @Override
    public String toString() {
        return this.ordinal()+".- "+this.cadenaAMostrar;
    }
}
