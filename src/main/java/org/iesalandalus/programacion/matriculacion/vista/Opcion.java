package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {

    SALIR("Salir.") {
        @Override
        public void ejecutar() {

        }
    },
    INSERTAR_ALUMNO("Insertar alumno/a.") {
        @Override
        public void ejecutar() {

        }
    },
    BUSCAR_ALUMNO("Buscar alumno/a.") {
        @Override
        public void ejecutar() {

        }
    },
    BORRAR_ALUMNO("Borrar alumno/a.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_ALUMNOS("Mostrar alumno/as.") {
        @Override
        public void ejecutar() {

        }
    },
    INSERTAR_CICLO_FORMATIVO("Insertar Ciclo Formativo.") {
        @Override
        public void ejecutar() {

        }
    },
    BUSCAR_CICLO_FORMATIVO("Buscar Ciclo Formativo.") {
        @Override
        public void ejecutar() {

        }
    },
    BORRAR_CICLO_FORMATIVO("Borrar Ciclo Formativo.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar Ciclos Formativos.") {
        @Override
        public void ejecutar() {

        }
    },
    INSERTAR_ASIGNATURA("Insertar asignaturas.") {
        @Override
        public void ejecutar() {

        }
    },
    BUSCAR_ASIGNATURA("Buscar asignaturas.") {
        @Override
        public void ejecutar() {

        }
    },
    BORRAR_ASIGNATURA("Borrar asignaturas.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas.") {
        @Override
        public void ejecutar() {

        }
    },
    INSERTAR_MATRICULA("Insertar matrícula.") {
        @Override
        public void ejecutar() {

        }
    },
    BUSCAR_MATRICULA("Buscar matrícula.") {
        @Override
        public void ejecutar() {

        }
    },
    ANULAR_MATRICULA("Anular matrícula.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_MATRICULAS("Mostrar matrículas.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matrículas de un/a alumno/a.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matrículas por Ciclo Formativo.") {
        @Override
        public void ejecutar() {

        }
    },
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matrículas por curso académico.") {
        @Override
        public void ejecutar() {

        }
    };

    private final String cadenaAMostrar;
    private static Vista vista;

    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public abstract void ejecutar() {

    }

    public static void setVista(Vista vista) {

    }

    @Override
    public String toString() {
        return this.ordinal()+".- "+this.cadenaAMostrar;
    }
}
