package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.sound.midi.MidiMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {
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

    public static Alumno leerAlumno() {
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

        try {
            return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El alumno introducido no es correcto.\n" + e.getMessage());
        }
    }

    public Alumno getAlumnoPorDni(){
        try {
            System.out.println("Introduzca el DNI del alumno/a: ");
            String dniABuscar = Entrada.cadena();

            Alumno alumnoInventado = new Alumno("Filemón", dniABuscar, "busca@busca.com", "000000000", LocalDate.of(1996, 1, 1));
            return alumnoInventado;

        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: "+e.getMessage());
        }
    }

    public LocalDate leerFecha(String mensaje) {
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

    public Grado leerGrado() {
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

    public CicloFormativo leerCicloFormativo(){
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

    public void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos){
        if (ciclosFormativos == null || ciclosFormativos.getTamano()==0){
            System.out.println("No hay Ciclos Formativos registrados.");
        } else {
            System.out.println("Los Ciclos Formativos registrados son los siguientes: ");

            for (int i=0; i< ciclosFormativos.getTamano(); i++){
                CicloFormativo cicloFormativo = ciclosFormativos.get()[i];
                System.out.println(cicloFormativo);
            }
        }
    }

    public CicloFormativo getCicloFormativoPorCodigo(){
        try {
            System.out.println("Introduzca el código del Ciclo Formativo: ");
            int codigoBuscar = Entrada.entero();

            CicloFormativo cicloInventado = new CicloFormativo(codigoBuscar, "Programacion", Grado.GDCFGB, "DAM", 100);
            return cicloInventado;

        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: "+e.getMessage());
        }
    }

    public Curso leerCurso(){
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

    public EspecialidadProfesorado leerEspecialidadProfesorado(){
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

    public Asignatura leerAsignatura(CicloFormativo cicloFormativo){
        System.out.println("Introduzca el código de la asignatura: ");
        String codigo = Entrada.cadena();

        System.out.println("Introduzca el nombre: ");
        String nombre = Entrada.cadena();

        System.out.println("Introduzca las horas anuales: ");
        int horasAnuales = Entrada.entero();

        Curso curso = leerCurso();

        System.out.println("Introduzca las horas de desdoble: ");
        int horasDesdoble = Entrada.entero();

        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();

        try {
            return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERROR: La asignatura introducida no es correcta.\n"+e.getMessage());
        }
    }

    public Asignatura getAsignaturaPorCodigo(){
        try {
            System.out.println("Introduzca el código de la asignatura: ");
            String codigoBuscar = Entrada.cadena();

            CicloFormativo cicloInventado = new CicloFormativo(3333, "Programacion", Grado.GDCFGB, "DAM", 100);

            Asignatura asignaturaInventada = new Asignatura(codigoBuscar, "Programación", 10, Curso.PRIMERO, 10, EspecialidadProfesorado.FOL,cicloInventado);
            return asignaturaInventada;

        } catch (IllegalArgumentException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    public Matricula getMatriculaPorIdentificador(){
        try {
            System.out.println("Introduzca el ID de la Matrícula: ");
            int id = Entrada.entero();

            Alumno alumnoInventado = new Alumno("Filemón", "12345678A", "test@test.com", "000000000", LocalDate.of(1996, 1, 1));
            CicloFormativo cicloInventado = new CicloFormativo(1234, "Programación", Grado.GDCFGB, "DAM", 10);
            Asignatura asignaturaInventada = new Asignatura("1234", "Programación", 10, Curso.PRIMERO, 10, EspecialidadProfesorado.INFORMATICA, cicloInventado);
            Asignatura[] coleccionInventada = new Asignatura[1];
            coleccionInventada[0] = asignaturaInventada;

            Matricula matriculaInventada = new Matricula(id, Curso.PRIMERO.toString(), LocalDate.now(), alumnoInventado, coleccionInventada);

            return matriculaInventada;
        } catch (Exception e){
            System.out.println("ERROR: "+ e.getMessage());
        }
        return null;
    }

    private void mostrarAsignaturas(Asignaturas asignaturas){
        if (asignaturas == null || asignaturas.getTamano()==0){
            System.out.println("No hay asignaturas registradas.");
        } else {
            System.out.println("Las asignaturas registradas son las siguientes: ");

            for (int i = 0; i < asignaturas.getTamano(); i++) {
                Asignatura asignatura = asignaturas.get()[i];
                System.out.println(asignatura);
            }
        }
    }

    private boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura){
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

    public Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) {
        System.out.println("Introduce el ID de la mátricula: ");
        int id = Entrada.entero();

        Curso curso = leerCurso();
        String cursoAcademico = curso.toString();

        System.out.println("Introduzca la fecha matriculación: ");
        LocalDate fechaMatricula = LocalDate.parse(Entrada.cadena());

        Alumno alumnoPorDni = getAlumnoPorDni();
        Alumno alumnoEncontrado = null;
        for (int i = 0; i < alumnos.getTamano(); i++) {
            if (alumnos.get()[i].getDni().equals(alumnoPorDni.getDni())) {
                alumnoEncontrado = alumnos.get()[i];
                break;
            }
        }

        //Array para almacenar las asignaturas que se elijan.
        Asignatura[] asignaturasMatricula = new Asignatura[Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA;]

        int contadorAsignaturas = 0;
        boolean continuar = true;

        while (continuar) {

            Asignatura asignaturaPorCodigo = getAsignaturaPorCodigo();

            //Buscar la asingatura verdadera en el array de asignaturas.
            Asignatura asignaturaEncontrada = null;
            for (int i = 0; i < asignaturas.getTamano(); i++) {
                if (asignaturas.get()[i].getCodigo().equals(asignaturaPorCodigo.getCodigo())) {
                    asignaturaEncontrada = asignaturas.get()[i];
                    break;
                }
            }

            //Comprobar que la asignatura encontrada no esté ya en la matrícula.
            boolean asignaturaYaEnMatricula = false;
            for (int i = 0; i < contadorAsignaturas; i++) {
                if (asignaturasMatricula[i].getCodigo().equals(asignaturaEncontrada.getCodigo())) {
                    asignaturaYaEnMatricula = true;
                    break;
                }
            }
            if (asignaturaYaEnMatricula) {
                throw new IllegalArgumentException("ERROR: La asignatura ya se encuentra en esta matrícula.");
            }

            //Añadir la asignatura encontrada y correcta al array de asignaturas para esta matrícula.
            asignaturasMatricula[contadorAsignaturas] = asignaturaEncontrada;
            contadorAsignaturas++;

            if (contadorAsignaturas >= Matricula.MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
                throw new IllegalArgumentException("ERROR: No se pueden añadir más asignaturas a esta matrícula.");
            }

        }

        Matricula matricula = new Matricula(id, cursoAcademico, fechaMatricula, alumnoEncontrado, asignaturasMatricula);
    }
}
