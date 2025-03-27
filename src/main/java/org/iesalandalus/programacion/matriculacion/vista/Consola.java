package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private Consola() {}

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

    public static Alumno leerAlumno() throws IllegalArgumentException {
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

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);

    }

    public static Alumno getAlumnoPorDni() throws IllegalArgumentException {
            System.out.println("Introduzca el DNI del alumno/a: ");
            String dniABuscar = Entrada.cadena();

            return new Alumno("Filemón", dniABuscar, "busca@busca.com", "666000000", LocalDate.of(1996, 1, 1));

    }

    public static LocalDate leerFecha(String mensaje) throws IllegalArgumentException {
        String fechaIntroducida = mensaje;

        String FORMATO_FECHA = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})";
        Pattern pattern = Pattern.compile(FORMATO_FECHA);
        Matcher matcher = pattern.matcher(fechaIntroducida);

        do {
            if (!matcher.matches()) {
                throw new IllegalArgumentException("ERROR: La fecha introducida no tiene el formato correcto.");
            }
        } while (!matcher.matches());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaIntroducida, formatter);
    }

    public static TiposGrado leerTipoGrado() {
        System.out.println("Los tipos de Grado que hay son los siguientes:");
        for (TiposGrado gradosTipos : TiposGrado.values()) {
            System.out.println(gradosTipos.imprimir());
        }

        int opcion = -1;
        do {
            System.out.println("Introduzca el Grado elegido (0 ó 1):");
            opcion = Entrada.entero();

            if (opcion != 0 && opcion != 1) {
               System.out.println("La opción introducida no es válida.");
            }

        } while (opcion!=0 && opcion!=1);

        return TiposGrado.values()[opcion];
    }

    public static Modalidad leerModalidad() {
        System.out.println("Las modalidades que hay son las siguientes:");
        for (Modalidad modalidadTipos : Modalidad.values()) {
            System.out.println(modalidadTipos.imprimir());
        }

        int opcion = -1;
        do {
            System.out.println("Introduzca la modalidad elegida (0 ó 1): ");
            opcion = Entrada.entero();

            if (opcion != 0 && opcion != 1) {
                System.out.println("La opción introducida no es válida.");
            }

        } while (opcion!=0 && opcion!=1);

        return Modalidad.values()[opcion];
    }

    public static CicloFormativo leerCicloFormativo() throws IllegalArgumentException {
        System.out.println("Introduzca código del Ciclo Formativo: ");
        int codigo = Entrada.entero();

        System.out.println("Introduzca la familia profesional: ");
        String familiaProfesional = Entrada.cadena();

        Grado grado = leerGrado();

        System.out.println("Introduzca el nombre del Ciclo: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca las horas que tiene: ");
        int horas = Entrada.entero();

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);

    }

    public static Grado leerGrado() throws IllegalArgumentException {
       TiposGrado gradoElegido = leerTipoGrado();
       Grado gradoNuevo = null;

       if (gradoElegido == TiposGrado.GRADOD) {
           System.out.println("Introduzca el nombre del Grado: ");
           String nombre = Entrada.cadena();

           System.out.println("Introduzca el número de años: ");
           int numAnios = Entrada.entero();

           Modalidad modalidadElegida = leerModalidad();

           gradoNuevo = new GradoD(nombre, numAnios, modalidadElegida);
       }

       if (gradoElegido == TiposGrado.GRADOE) {
           System.out.println("Introduzca el nombre del Grado: ");
           String nombre = Entrada.cadena();

           System.out.println("Introduzca el número de años: ");
           int numAnios = Entrada.entero();

           System.out.println("Introduzca el número de ediciones: ");
           int numEdiciones = Entrada.entero();

           gradoNuevo = new GradoE(nombre, numAnios, numEdiciones);
       }

       return gradoNuevo;
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

    public static CicloFormativo getCicloFormativoPorCodigo() throws IllegalArgumentException {
        System.out.println("Introduzca el código del Ciclo Formativo: ");
        int codigoBuscar = Entrada.entero();

        Grado gradoInventado = new GradoE("Grado", 1, 1);

        return new CicloFormativo(codigoBuscar, "Programacion", gradoInventado, "DAM", 100);
    }

    public static Curso leerCurso() {
        System.out.println("Los cursos existentes son los siguientes: ");
        for (Curso cursosTipos : Curso.values()) {
            System.out.println(cursosTipos.imprimir());
        }

        int opcion=-1;
        do {
            System.out.println("Introduzca el curso elegido (0 ó 1): ");
            opcion = Entrada.entero();

            if (opcion < 0 || opcion > 1) {
                System.out.println("La opción introducida no es válida.");
            }

        } while (opcion < 0 || opcion > 1);

        return Curso.values()[opcion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Las especialidades existentes son: ");
        for (int i = 0; i < EspecialidadProfesorado.values().length; i++) {
            System.out.println(EspecialidadProfesorado.values()[i].imprimir());
        }

        int opcion=-1;

        do {
            System.out.println("Introduzca la especialidad elegida (0, 1 ó 2): ");
            opcion = Entrada.entero();

            if (opcion < 0 || opcion > 2) {
                System.out.println("La opción introducida no es válida.");
            }

        } while (opcion < 0 || opcion > 2);

        return EspecialidadProfesorado.values()[opcion];
    }

    public static Asignatura leerAsignatura() throws IllegalArgumentException {
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

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    public static Asignatura getAsignaturaPorCodigo() throws IllegalArgumentException {
        System.out.println("Introduzca el código de la asignatura: ");
        String codigoBuscar = Entrada.cadena();

        Grado gradoInventado = new GradoE("Grado", 1, 1);

        CicloFormativo cicloInventado = new CicloFormativo(3333, "Programacion", gradoInventado, "DAM", 100);

        return new Asignatura(codigoBuscar, "Programación", 10, Curso.PRIMERO, 3, EspecialidadProfesorado.FOL, cicloInventado);
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

    public static Matricula leerMatricula(Alumno alumno, List<Asignatura> asignaturas) throws IllegalArgumentException {
        System.out.println("Introduce el ID de la mátricula: ");
        int id = Entrada.entero();

        System.out.println("Introduzca el curso académico (en formato aa-aa)");
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
            System.out.println("Elija la asignatura que quiere añadir a la matrícula (introduzca el código de la asignatura o -1 para terminar):");
            int numeroAsignatura = Entrada.entero();

            if (numeroAsignatura == -1) {
                break;
            }

            /*for (Asignatura asignatura : asignaturas) {
                String codigo = asignatura.getCodigo();

                if (asignaturaYaMatriculada(asignaturasMatricula, asignatura)) {
                    System.out.println("La asignatura elegida ya se encuentra en la matrícula.");

                } else if (codigoAsignatura == codigo) {
                    asignaturasMatricula.add(asignatura);
                    contadorAsignaturas++;
                }

                if (contadorAsignaturas >= Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
                    System.out.println("No se pueden añadir más asignaturas a esta matrícula.");
                }
            }
            */
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


    public static Matricula getMatriculaPorIdentificador () {
        System.out.println("Introduzca el ID de la Matrícula: ");
        int idABuscar = Entrada.entero();

        Alumno alumnoInventado = new Alumno("Filemón", "12345678A", "test@test.com", "000000000", LocalDate.of(1996, 1, 1));
        List<Asignatura> coleccionInventada = new ArrayList<>();

        return new Matricula(idABuscar, Curso.PRIMERO.toString(), LocalDate.now(), alumnoInventado, coleccionInventada);

    }


}
