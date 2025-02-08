package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {

        System.out.println("Le damos la bienvenida al sistema de matriculación del instituto IES Al-Ándalus.");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Menú:");

        Opcion[] opcionesMenu = Opcion.values();
        for (int i = 0; i < opcionesMenu.length; i++) {
            System.out.println(opcionesMenu[i]);
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

    public static Alumno leerAlumno() throws Exception {
        System.out.println("Introduzca el nombre del nuevo alumno o alumna: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca el DNI: ");
        String dni = Entrada.cadena();

        System.out.println("Introduzca el correo: ");
        String correo = Entrada.cadena();

        System.out.println("Introduzca el teléfono: ");
        String telefono = Entrada.cadena();

        System.out.println("Introduzca la fecha de nacimiento en formato dd/mm/yyyy: ");
        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());

        Alumno alumnoNuevo;

        try {
            alumnoNuevo = new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El alumno introducido no es correcto.\n" + e.getMessage());
        }

        return alumnoNuevo;
    }

    public static Alumno getAlumnoPorDni(){
            System.out.println("Introduzca el DNI del alumno/a: ");
            String dniABuscar = Entrada.cadena();

            try {
                Alumno alumnoInventado = new Alumno("Filemón", dniABuscar, "busca@busca.com", "000000000", LocalDate.of(1996, 1, 1));
                return alumnoInventado;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("ERROR: "+ e.getMessage());
            }


            /*Alumno[] alumnosActuales = new alumnos.get();
            Alumno alumnoEncontrado = alumnos.buscar(alumnoInventado);
            Alumnos alumnoEncontrado = alumnos.buscar(alumnoInventado);
            for (int i = 0; i < Alumnos.getTamano(); i++){
                Alumno alumno = Alumnos.get()[i];

                if (alumno.getDni().equals(dniABuscar)) {
                    return alumno;
                }
            }

            return alumnoInventado;*/
          /*  throw new IllegalArgumentException("No se ha encontrado ningún alumno/a con este DNI.");
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: "+e.getMessage());
        }*/
    }

    public static LocalDate leerFecha(String mensaje) {
        String fechaIntroducida = Entrada.cadena();

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

    public static Grado leerGrado() throws Exception {
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
                    throw new IllegalArgumentException("La opción introducida no es válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion < 0 || opcion >= Grado.values().length);

        return Grado.values()[opcion];

    }

    public static CicloFormativo leerCicloFormativo() throws Exception {
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
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El Ciclo Formativo introducido no es correcto.\n" + e.getMessage());
        }
    }

    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        if (ciclosFormativos == null || ciclosFormativos.getTamano() == 0) {
            System.out.println("No hay Ciclos Formativos registrados.");
        } else {
            System.out.println("Los Ciclos Formativos registrados son los siguientes: ");

            for (int i = 0; i < ciclosFormativos.getTamano(); i++) {
                CicloFormativo cicloFormativo = ciclosFormativos.get()[i];
                System.out.println(cicloFormativo);
            }
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.println("Introduzca el código del Ciclo Formativo: ");
        int codigoBuscar = Entrada.entero();

        try {
            CicloFormativo cicloInventado = new CicloFormativo(codigoBuscar, "Programacion", Grado.GDCFGB, "DAM", 100);
            return cicloInventado;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR: " + e.getMessage());
        }
    }

    public static Curso leerCurso() throws Exception {
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
                   throw new IllegalArgumentException("La opción introducida no es válida.");
               }
           } catch (IllegalArgumentException e) {
               System.out.println("ERROR: " + e.getMessage());
           }
        } while (opcion < 0 || opcion > 1);

        return Curso.values()[opcion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() throws Exception {
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
                    throw new IllegalArgumentException("La opción introducida no es válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion < 0 || opcion > 2);

        return EspecialidadProfesorado.values()[opcion];
    }

    public static Asignatura leerAsignatura() throws Exception {
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
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: La asignatura introducida no es correcta.\n"+e.getMessage());
        }
    }

    public static Asignatura getAsignaturaPorCodigo() throws Exception {
        System.out.println("Introduzca el código de la asignatura: ");
        String codigoBuscar = Entrada.cadena();

        try {
            CicloFormativo cicloInventado = new CicloFormativo(3333, "Programacion", Grado.GDCFGB, "DAM", 100);

            Asignatura asignaturaInventada = new Asignatura(codigoBuscar, "Programación", 10, Curso.PRIMERO, 10, EspecialidadProfesorado.FOL, cicloInventado);
            return asignaturaInventada;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR: " + e.getMessage());
        }
    }

    public static void mostrarAsignaturas(Asignaturas asignaturas){
        if (asignaturas == null || asignaturas.getTamano()==0){
            System.out.println("No hay asignaturas registradas.");
        } else {
            System.out.println("Las asignaturas registradas son las siguientes: ");

            for (int i = 0; i < asignaturas.getTamano(); i++) {
                Asignatura asignatura = asignaturas.get()[i];
                System.out.println(asignatura.imprimir());
            }
        }
    }

    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura){
        if (asignaturasMatricula == null || asignatura == null){
            return false;
        }

        for (int i=0; i < asignaturasMatricula.length; i++){
            if (asignaturasMatricula[i] != null && asignaturasMatricula[i].equals(asignatura)){
                return true;
            }
        }

        return false;
    }

    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) throws Exception {
        System.out.println("Introduce el ID de la mátricula: ");
        int id = Entrada.entero();

        Curso curso = leerCurso();
        String cursoAcademico = curso.toString();

        System.out.println("Introduzca la fecha matriculación: ");
        LocalDate fechaMatricula = LocalDate.parse(Entrada.cadena());

        Alumno alumnoPorDni = getAlumnoPorDni();
        Alumno alumnoEncontrado = null;
        for (int i = 0; i < alumnos.getTamano(); i++) {
            if (alumnos.get()[i].equals(alumnoPorDni)) {
                alumnoEncontrado = alumnos.get()[i];
                break;
            }
        }

        //Muestro las asignaturas que hay.
        mostrarAsignaturas(asignaturas);
        //Array para almacenar las asignaturas que se elijan.
        Asignatura[] asignaturasMatricula = new Asignatura[Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA];

        int contadorAsignaturas = 0;

        while (!(contadorAsignaturas >= Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA)) {

            Asignatura asignaturaPorCodigo = getAsignaturaPorCodigo();

            //Buscar la asingatura verdadera en el array de asignaturas.
            Asignatura asignaturaEncontrada = null;
            for (int i = 0; i < asignaturas.getTamano(); i++) {
                if (asignaturas.get()[i].equals(asignaturaPorCodigo)) {
                    asignaturaEncontrada = asignaturas.get()[i];
                    break;
                }
            }

            //Comprobar que la asignatura encontrada no esté ya en la matrícula.
            if (asignaturaYaMatriculada(asignaturasMatricula, asignaturaEncontrada)) {
                throw new IllegalArgumentException("ERROR: La asignatura ya se encuentra en esta matrícula.");
            } else {
                asignaturasMatricula[contadorAsignaturas] = asignaturaEncontrada;
                contadorAsignaturas++;
            }

            if (contadorAsignaturas >= Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
                throw new IllegalArgumentException("ERROR: No se pueden añadir más asignaturas a esta matrícula.");
            }

        }

        return new Matricula(id, cursoAcademico, fechaMatricula, alumnoEncontrado, asignaturasMatricula);
    }

    public static Matricula getMatriculaPorIdentificador() throws Exception {
        System.out.println("Introduzca el ID de la Matrícula: ");
        int idABuscar = Entrada.entero();

        try {
            Alumno alumnoInventado = new Alumno("Filemón", "12345678A", "test@test.com", "000000000", LocalDate.of(1996, 1, 1));
            CicloFormativo cicloInventado = new CicloFormativo(1234, "Programación", Grado.GDCFGB, "DAM", 10);
            Asignatura asignaturaInventada = new Asignatura("1234", "Programación", 10, Curso.PRIMERO, 10, EspecialidadProfesorado.INFORMATICA, cicloInventado);
            Asignatura[] coleccionInventada = new Asignatura[1];
            coleccionInventada[0] = asignaturaInventada;

            Matricula matriculaInventada = new Matricula(idABuscar, Curso.PRIMERO.toString(), LocalDate.now(), alumnoInventado, coleccionInventada);
            return matriculaInventada;
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: " + e.getMessage());
        }
    }

}
