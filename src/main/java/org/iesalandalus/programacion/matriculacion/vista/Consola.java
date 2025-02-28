package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {

        System.out.println("Menú:");

        for (Opcion opciones : Opcion.values()) {
            System.out.println(opciones);
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

    public static Alumno leerAlumno() throws OperationNotSupportedException {
        System.out.println("Introduzca el nombre del nuevo alumno o alumna: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca el DNI: ");
        String dni = Entrada.cadena();

        System.out.println("Introduzca el correo: ");
        String correo = Entrada.cadena();

        System.out.println("Introduzca el teléfono: ");
        String telefono = Entrada.cadena();

        System.out.println("Introduzca la fecha de nacimiento en formato dd/mm/yyyy: ");
        String fecha = Entrada.cadena();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);

        Alumno alumnoNuevo;

        try {
            alumnoNuevo = new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
        } catch (OperationNotSupportedException e) {
            throw new OperationNotSupportedException("El alumno introducido no es correcto.\n" + e.getMessage());
        }

        return alumnoNuevo;
    }

    public static Alumno getAlumnoPorDni() throws OperationNotSupportedException {
            System.out.println("Introduzca el DNI del alumno/a: ");
            String dniABuscar = Entrada.cadena();

            try {
                Alumno alumnoInventado = new Alumno("Filemón", dniABuscar, "busca@busca.com", "000000000", LocalDate.of(1996, 1, 1));
                return alumnoInventado;
            } catch (OperationNotSupportedException e) {
                throw new OperationNotSupportedException("ERROR: "+ e.getMessage());
            }
    }

    public static LocalDate leerFecha(String mensaje) throws OperationNotSupportedException {
        String fechaIntroducida = mensaje;

        String FORMATO_FECHA = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})";
        Pattern pattern = Pattern.compile(FORMATO_FECHA);
        Matcher matcher = pattern.matcher(fechaIntroducida);

        do {
            if (!matcher.matches()) {
                throw new OperationNotSupportedException("ERROR: La fecha introducida no tiene el formato correcto.");
            }
        } while (!matcher.matches());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaIntroducida, formatter);
    }

    public static Grado leerGrado() throws OperationNotSupportedException {
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
                    throw new OperationNotSupportedException("La opción introducida no es válida.");
                }
            } catch (OperationNotSupportedException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion < 0 || opcion >= Grado.values().length);

        return Grado.values()[opcion];

    }

    public static CicloFormativo leerCicloFormativo() throws OperationNotSupportedException {
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
        } catch (OperationNotSupportedException e) {
            throw new OperationNotSupportedException("El Ciclo Formativo introducido no es correcto.\n" + e.getMessage());
        }
    }

    public static void mostrarCiclosFormativos(List<CicloFormativo> ciclosFormativos) {
        if (ciclosFormativos.isEmpty()) {
            System.out.println("No hay Ciclos Formativos registrados.");
        } else {
            System.out.println("Los Ciclos Formativos registrados son los siguientes: ");

            for (CicloFormativo cicloFormativo : ciclosFormativos) {
                System.out.println(cicloFormativo);
            }
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() throws OperationNotSupportedException {
        System.out.println("Introduzca el código del Ciclo Formativo: ");
        int codigoBuscar = Entrada.entero();

        try {
            CicloFormativo cicloInventado = new CicloFormativo(codigoBuscar, "Programacion", Grado.GDCFGB, "DAM", 100);
            return cicloInventado;
        } catch (OperationNotSupportedException e) {
            throw new OperationNotSupportedException("ERROR: " + e.getMessage());
        }
    }

    public static Curso leerCurso() throws OperationNotSupportedException {
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
                   throw new OperationNotSupportedException("La opción introducida no es válida.");
               }
           } catch (OperationNotSupportedException e) {
               System.out.println("ERROR: " + e.getMessage());
           }
        } while (opcion < 0 || opcion > 1);

        return Curso.values()[opcion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() throws OperationNotSupportedException {
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
                    throw new OperationNotSupportedException("La opción introducida no es válida.");
                }
            } catch (OperationNotSupportedException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion < 0 || opcion > 2);

        return EspecialidadProfesorado.values()[opcion];
    }

    public static Asignatura leerAsignatura() throws OperationNotSupportedException {
        System.out.println("Introduzca el código de la asignatura: ");
        String codigo = Entrada.cadena();

        System.out.println("Introduzca el nombre: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca las horas anuales: ");
        int horasAnuales = Entrada.entero();

        Curso curso = leerCurso();

        CicloFormativo cicloFormativo = leerCicloFormativo();

        System.out.println("Introduzca las horas de desdoble: ");
        int horasDesdoble = Entrada.entero();

        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();

        try {
            return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
        } catch (OperationNotSupportedException e){
            throw new OperationNotSupportedException("ERROR: La asignatura introducida no es correcta.\n"+e.getMessage());
        }
    }

    public static Asignatura getAsignaturaPorCodigo() throws OperationNotSupportedException {
        System.out.println("Introduzca el código de la asignatura: ");
        String codigoBuscar = Entrada.cadena();

        try {
            CicloFormativo cicloInventado = new CicloFormativo(3333, "Programacion", Grado.GDCFGB, "DAM", 100);

            Asignatura asignaturaInventada = new Asignatura(codigoBuscar, "Programación", 10, Curso.PRIMERO, 10, EspecialidadProfesorado.FOL, cicloInventado);
            return asignaturaInventada;
        } catch (OperationNotSupportedException e) {
            throw new OperationNotSupportedException("ERROR: " + e.getMessage());
        }
    }

    public static void mostrarAsignaturas(List<Asignatura> asignaturas){
        if (asignaturas.isEmpty()){
            System.out.println("No hay asignaturas registradas.");
        } else {
            System.out.println("Las asignaturas registradas son las siguientes: ");

            for (Asignatura asignatura : asignaturas) {
                System.out.println(asignatura.imprimir());
            }
        }
    }

    private static boolean asignaturaYaMatriculada(List<Asignatura> asignaturasMatricula, Asignatura asignatura){
        if (asignaturasMatricula.isEmpty() || asignatura == null){
            return false;
        }

        for (int i=0; i < asignaturasMatricula.size(); i++){
            if (asignaturasMatricula.get(i) != null && asignaturasMatricula.get(i).equals(asignatura)){
                return true;
            }
        }

        return false;
    }

    public static Matricula leerMatricula(Alumno alumno, List<Asignatura> asignaturas) throws OperationNotSupportedException {
        System.out.println("Introduce el ID de la mátricula: ");
        int id = Entrada.entero();

        System.out.println("Introduzca el curso académico en (formato aa-aa)");
        String cursoAcademico = Entrada.cadena();

        System.out.println("Introduzca la fecha matriculación: ");
        LocalDate fechaMatricula = LocalDate.parse(Entrada.cadena());

        List<Asignatura> asignaturasMatricula = elegirAsignaturasMatricula(asignaturas);

        return new Matricula(id, cursoAcademico, fechaMatricula, alumno, asignaturasMatricula);
    }

    public static List<Asignatura> elegirAsignaturasMatricula(List<Asignatura> asignaturas) {
        mostrarAsignaturas(asignaturas);

        List<Asignatura> asignaturasMatricula = new ArrayList<>();
        int contadorAsignaturas = 0;

        while (contadorAsignaturas < Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            System.out.println("Elija las asignaturas que quiere matricular (introduzca el número o -1 para terminar):");
            int numeroAsignatura = Entrada.entero();

            if (numeroAsignatura == -1) {
                break;
            }

            if (numeroAsignatura < 0 || numeroAsignatura >= asignaturas.size()) {
                System.out.println("La opción elegida no es válida.");
            }

            Asignatura asignaturaElegida = asignaturas.get(numeroAsignatura);

            if (asignaturaYaMatriculada(asignaturasMatricula, asignaturaElegida)) {
                System.out.println("La asignatura elegida ya se encuentra en la matrícula.");
            } else {
                asignaturasMatricula.add(asignaturaElegida);
                contadorAsignaturas++;
            }

            if (contadorAsignaturas >= Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
                System.out.println("No se pueden añadir más asignaturas a esta matrícula.");
                break;
            }
        }

        return asignaturasMatricula;

    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        System.out.println("Introduzca el ID de la Matrícula: ");
        int idABuscar = Entrada.entero();

        try {
            Alumno alumnoInventado = new Alumno("Filemón", "12345678A", "test@test.com", "000000000", LocalDate.of(1996, 1, 1));
            List<Asignatura> coleccionInventada = new ArrayList<>();

            Matricula matriculaInventada = new Matricula(idABuscar, Curso.PRIMERO.toString(), LocalDate.now(), alumnoInventado, coleccionInventada);
            return matriculaInventada;
        } catch (OperationNotSupportedException e) {
            throw new OperationNotSupportedException("ERROR: " + e.getMessage());
        }
    }

}
