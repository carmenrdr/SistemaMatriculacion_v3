package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {

    SALIR("Salir."),
    INSERTAR_ALUMNO("Insertar alumno/a."),
    BUSCAR_ALUMNO("Buscar alumno/a."),
    BORRAR_ALUMNO("Borrar alumno/a."),
    MOSTRAR_ALUMNOS("Mostrar alumno/as."),
    INSERTAR_CICLO_FORMATIVO("Insertar Ciclo Formativo."),
    BUSCAR_CICLO_FORMATIVO("Buscar Ciclo Formativo."),
    BORRAR_CICLO_FORMATIVO("Borrar Ciclo Formativo."),
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar Ciclos Formativos."),
    INSERTAR_ASIGNATURA("Insertar asignaturas."),
    BUSCAR_ASIGNATURA("Buscar asignaturas."),
    BORRAR_ASIGNATURA("Borrar asignaturas."),
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas."),
    INSERTAR_MATRICULA("Insertar matrícula."),
    BUSCAR_MATRICULA("Buscar matrícula."),
    ANULAR_MATRICULA("Anular matrícula."),
    MOSTRAR_MATRICULAS("Mostrar matrículas."),
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matrículas de un/a alumno/a."),
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matrículas por Ciclo Formativo."),
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matrículas por curso académico.");

    private final String cadenaAMostrar;

    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return this.ordinal()+".- "+this.cadenaAMostrar;
    }
}
