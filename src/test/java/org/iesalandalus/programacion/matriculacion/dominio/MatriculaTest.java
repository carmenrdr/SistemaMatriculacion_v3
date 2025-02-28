package org.iesalandalus.programacion.matriculacion.dominio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MatriculaTest {

    private static final String ERROR_EXCEPCION = "Deberia haber saltado la excepcion.";
    private static final String ERROR_NO_EXCEPCION = "No deberia haber saltado la excepcion.";
    private static final String ALUMNO_NO_ESPERADO = "El alumno devuelto no es el que deberia ser.";
    private static final String ASIGNATURA_NO_ESPERADA = "La asignatura devuelta no es la que deberia ser.";

    private static final String OBJETO_DEBERIA_SER_NULO = "No se deberia haber creado el objeto matricula.";
    private static final String MENSAJE_EXCEPCION_NO_CORRECTO = "El mensaje devuelto por la excepcion no es correcto.";
    private static final String ERROR_ALUMNO_NULO="ERROR: El alumno de una matricula no puede ser nulo.";
    private static final String ERROR_LISTA_ASIGNATURAS_NULA="ERROR: La lista de asignaturas de una matricula no puede ser nula.";
    private static final String ERROR_ID_MATRICULA_INCORRECTO="ERROR: El identificador de una matricula no puede ser menor o igual a 0.";
    private static final String ERROR_CURSO_ACADEMICO_NULO="ERROR: El curso academico de una matricula no puede ser nulo.";
    private static final String ERROR_CURSO_ACADEMICO_INCORRECTO="ERROR: El curso academico de una matricula no puede estar vacio.";
    private static final String ERROR_CURSO_ACADEMICO_FORMATO_INVALIDO="ERROR: El formato del curso academico no es correcto.";
    private static final String ERROR_FECHA_MATRICULACION_NULA="ERROR: La fecha de matriculacion de una matricula no puede ser nula.";
    private static final String ERROR_FECHA_MATRICULACION_INCORRECTA="ERROR: La fecha de matriculacion no puede ser posterior a hoy.";
    private static final String ERROR_FECHA_MATRICULACION_INCORRECTA_SUPERA_DIAS_PREVIOS="ERROR: La fecha de matriculacion no puede ser anterior a " + Matricula.MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.";
    private static final String ERROR_MATRICULA_SUPERA_LIMITE_HORAS="ERROR: No se puede realizar la matricula ya que supera el maximo de horas permitidas (" + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA + " horas).";
    private static final String MATRICULA_NULA = "Deberia haber saltado una excepcion indicando que no se puede copiar una matricula nula.";
    private static final String FECHA_ANULACION_INCORRECTA="Deberia haber saltado una excepcion indicando que la fecha de anulacion no es correcta.";
    private static final String FECHA_ANULACION_POSTERIOR_HOY="ERROR: La fecha de anulacion de una matricula no puede ser posterior a hoy.";
    private static final String FECHA_ANULACION_ANTERIOR_FECHA_MATRICULACION="ERROR: La fecha de anulacion no puede ser anterior a la fecha de matriculacion.";
    private static final String FECHA_ANULACION_ANTERIOR_FECHA_MAXIMA_ANTERIOR_ANULACION="ERROR: La fecha de anulacion no puede ser anterior a " + Matricula.MAXIMO_MESES_ANTERIOR_ANULACION + " meses.";
    private static final String ERROR_COPIAR_MATRICULA_NULA = "ERROR: No es posible copiar una matricula nula.";
    private static final String TIPO_EXCEPCION_NO_CORRECTA = "El tipo de la excepcion no es correcto.";

    private static final String NOMBRE_JRJR = "Jose Ramon Jimenez Reyes";
    private static final String DNI_JRJR = "11223344B";
    private static final String TELEFONO_JRJR = "950112233";
    private static final String CORREO_JRJR = "joseramon.jimenez@iesalandalus.org";
    private static final LocalDate FECHA_NACIMIENTO_JRJR=LocalDate.of(2002, 9, 15);

    private static final int ID_MATRICULA=100;
    private static final String CURSO_ACADEMICO="24-25";
    private static final LocalDate FECHA_MATRICULACION=LocalDate.now().minusDays(3);
    private static final LocalDate FECHA_ANULACION=LocalDate.now();


    private static final String CODIGO_ASIGNATURA="0100";
    private static final String NOMBRE_ASIGNATURA="Programacion";
    private static final int HORAS_ASIGNATURA=256;
    private static final Curso CURSO_ASIGNATURA=Curso.PRIMERO;
    private static final int HORAS_DESDOBLE_ASIGNATURA=4;
    private static final EspecialidadProfesorado ESPECIALIDAD_PROFESORADO_ASIGNATURA=EspecialidadProfesorado.INFORMATICA;

    private static final String CODIGO_ASIGNATURA_2="0200";
    private static final String NOMBRE_ASIGNATURA_2="Base de Datos";
    private static final int HORAS_ASIGNATURA_2=100;
    private static final int HORAS_DESDOBLE_ASIGNATURA_2=3;

    private static final String CODIGO_ASIGNATURA_3="0300";
    private static final String NOMBRE_ASIGNATURA_3="Administracion de Sistemas Operativos";
    private static final int HORAS_ASIGNATURA_3=120;
    private static final Curso CURSO_ASIGNATURA_3=Curso.SEGUNDO;
    private static final int HORAS_DESDOBLE_ASIGNATURA_3=0;
    private static final EspecialidadProfesorado ESPECIALIDAD_PROFESORADO_ASIGNATURA_2=EspecialidadProfesorado.SISTEMAS;

    private static final int CODIGO_CF_1=1225;
    private static final String FAMILIA_PROFESIONAL_CF="Informatica y Comunicaciones";
    private static final String NOMBRE_CICLO_FORMATIVO="DAW";
    private static final Grado GRADO_CF=Grado.GDCFGS;
    private static final int HORAS_CICLO_FORMATIVO=1000;

    private static final int CODIGO_CF_2=1226;
    private static final String NOMBRE_CICLO_FORMATIVO_2="DAM";
    private static final int CODIGO_CF_3=1227;
    private static final String NOMBRE_CICLO_FORMATIVO_3="ASIR";
    private static CicloFormativo cf1, cf2, cf3;
    private static Asignatura asignatura1, asignatura2, asignatura3,asignatura4,asignatura5,asignatura6;
    private static Alumno alumno;
    private static Asignatura[] coleccionAsignaturas=new Asignatura[6];

    @BeforeAll
    public static void asignarValoresAtributos() {
        alumno=new Alumno(NOMBRE_JRJR, DNI_JRJR, CORREO_JRJR, TELEFONO_JRJR, FECHA_NACIMIENTO_JRJR);
        cf1 =new CicloFormativo(CODIGO_CF_1, FAMILIA_PROFESIONAL_CF, GRADO_CF, NOMBRE_CICLO_FORMATIVO, HORAS_CICLO_FORMATIVO);
        cf2 = new CicloFormativo(CODIGO_CF_2, FAMILIA_PROFESIONAL_CF, GRADO_CF, NOMBRE_CICLO_FORMATIVO_2, HORAS_CICLO_FORMATIVO);
        cf3 = new CicloFormativo(CODIGO_CF_3, FAMILIA_PROFESIONAL_CF, GRADO_CF, NOMBRE_CICLO_FORMATIVO_3, HORAS_CICLO_FORMATIVO);

        asignatura1 = new Asignatura(CODIGO_ASIGNATURA, NOMBRE_ASIGNATURA,HORAS_ASIGNATURA,CURSO_ASIGNATURA,HORAS_DESDOBLE_ASIGNATURA,ESPECIALIDAD_PROFESORADO_ASIGNATURA,cf1);
        asignatura2 = new Asignatura(CODIGO_ASIGNATURA_2,NOMBRE_ASIGNATURA_2,HORAS_ASIGNATURA_2,CURSO_ASIGNATURA,HORAS_DESDOBLE_ASIGNATURA_2,ESPECIALIDAD_PROFESORADO_ASIGNATURA,cf2);
        asignatura3= new Asignatura(CODIGO_ASIGNATURA_3, NOMBRE_ASIGNATURA_3,HORAS_ASIGNATURA_3,CURSO_ASIGNATURA_3,HORAS_DESDOBLE_ASIGNATURA_3,ESPECIALIDAD_PROFESORADO_ASIGNATURA_2,cf3);
        asignatura4= new Asignatura(CODIGO_ASIGNATURA_3, NOMBRE_ASIGNATURA_3,HORAS_ASIGNATURA_3,CURSO_ASIGNATURA_3,HORAS_DESDOBLE_ASIGNATURA_3,ESPECIALIDAD_PROFESORADO_ASIGNATURA_2,cf3);
        asignatura5 = new Asignatura(CODIGO_ASIGNATURA, NOMBRE_ASIGNATURA,HORAS_ASIGNATURA,CURSO_ASIGNATURA,HORAS_DESDOBLE_ASIGNATURA,ESPECIALIDAD_PROFESORADO_ASIGNATURA,cf1);
        asignatura6 = new Asignatura(CODIGO_ASIGNATURA, NOMBRE_ASIGNATURA,HORAS_ASIGNATURA,CURSO_ASIGNATURA,HORAS_DESDOBLE_ASIGNATURA,ESPECIALIDAD_PROFESORADO_ASIGNATURA,cf1);

        Asignatura[] coleccionAsignaturas=new Asignatura[5];
        coleccionAsignaturas[0]=asignatura1;
        coleccionAsignaturas[1]=asignatura2;
        coleccionAsignaturas[2]=asignatura3;
    }

    @Test
    public void constructorParametrosValidoCreaMatriculaCorrectamenteTest() {
        Matricula matricula = null;
        try {
            matricula=new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));

            assertEquals(ID_MATRICULA, matricula.getIdMatricula());
            assertEquals(CURSO_ACADEMICO, matricula.getCursoAcademico());
            assertEquals(FECHA_MATRICULACION, matricula.getFechaMatriculacion());
            assertEquals(alumno, matricula.getAlumno(),ALUMNO_NO_ESPERADO);
            assertEquals(coleccionAsignaturas[0], matricula.getColeccionAsignaturas().get(0),ASIGNATURA_NO_ESPERADA);
            assertEquals(coleccionAsignaturas[1], matricula.getColeccionAsignaturas().get(1),ASIGNATURA_NO_ESPERADA);
            assertEquals(coleccionAsignaturas[2], matricula.getColeccionAsignaturas().get(2),ASIGNATURA_NO_ESPERADA);

        } catch (Exception e) {
            fail(ERROR_NO_EXCEPCION);
        }
    }

    @Test
    public void constructorCopiaValidoCopiaMatriculaCorrectamente() {
        Matricula matricula1;
        try {
            Matricula matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));

            matricula1 = new Matricula(matricula);
            assertEquals(ID_MATRICULA, matricula1.getIdMatricula());
            assertEquals(CURSO_ACADEMICO, matricula1.getCursoAcademico());
            assertEquals(FECHA_MATRICULACION, matricula1.getFechaMatriculacion());
            assertEquals(alumno, matricula1.getAlumno(),ALUMNO_NO_ESPERADO);
            assertEquals(coleccionAsignaturas[0], matricula1.getColeccionAsignaturas().get(0),ASIGNATURA_NO_ESPERADA);
            assertEquals(coleccionAsignaturas[1], matricula1.getColeccionAsignaturas().get(1),ASIGNATURA_NO_ESPERADA);
            assertEquals(coleccionAsignaturas[2], matricula1.getColeccionAsignaturas().get(2),ASIGNATURA_NO_ESPERADA);;

        } catch (Exception e) {
            fail(ERROR_NO_EXCEPCION);
        }
    }

    @Test
    public void constructorParametrosNoValidoTest() {
        Matricula matricula = null;
        try {
            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, null, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (NullPointerException e) {
            assertEquals(ERROR_ALUMNO_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, null);
            fail(ERROR_EXCEPCION);
        } catch (NullPointerException e) {
            assertEquals(ERROR_LISTA_ASIGNATURAS_NULA, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(0, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_ID_MATRICULA_INCORRECTO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(-2, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_ID_MATRICULA_INCORRECTO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, null, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (NullPointerException e) {
            assertEquals(ERROR_CURSO_ACADEMICO_NULO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, "", FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CURSO_ACADEMICO_INCORRECTO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, "     ", FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CURSO_ACADEMICO_INCORRECTO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, "23", FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_CURSO_ACADEMICO_FORMATO_INVALIDO, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, null, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (NullPointerException e) {
            assertEquals(ERROR_FECHA_MATRICULACION_NULA, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, LocalDate.now().plusDays(5), alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_FECHA_MATRICULACION_INCORRECTA, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);

        }

        try {
            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, LocalDate.now().minusDays(Matricula.MAXIMO_DIAS_ANTERIOR_MATRICULA).minusDays(3), alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (IllegalArgumentException e) {
            assertEquals(ERROR_FECHA_MATRICULACION_INCORRECTA_SUPERA_DIAS_PREVIOS, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch(Exception e)
        {
            fail(ERROR_NO_EXCEPCION);
        }

        try {
            Asignatura[] coleccionAsignaturas=new Asignatura[6];
            coleccionAsignaturas[0]=asignatura1;
            coleccionAsignaturas[1]=asignatura2;
            coleccionAsignaturas[2]=asignatura3;
            coleccionAsignaturas[3]=asignatura4;
            coleccionAsignaturas[4]=asignatura5;
            coleccionAsignaturas[5]=asignatura6;

            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));
            fail(ERROR_EXCEPCION);
        } catch (OperationNotSupportedException e) {
            assertEquals(ERROR_MATRICULA_SUPERA_LIMITE_HORAS, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        } catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }

        Asignatura[] coleccionAsignaturas=new Asignatura[5];
        coleccionAsignaturas[0]=asignatura1;
        coleccionAsignaturas[1]=asignatura2;
        coleccionAsignaturas[2]=asignatura3;

    }



    @Test
    public void constructorMatriculaNulaLanzaExcepcion() {
        Matricula matricula = null;
        try {
            matricula = new Matricula(null);
            fail(MATRICULA_NULA);
        } catch (NullPointerException e) {
            assertEquals(ERROR_COPIAR_MATRICULA_NULA, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }
        catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }

    @Test
    public void asignaFechaAnulacionValidaAMatriculaCorrectamenteTest() {
        Matricula matricula = null;
        try {
            matricula=new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));

            matricula.setFechaAnulacion(FECHA_ANULACION);

        } catch (Exception e) {
            fail(ERROR_NO_EXCEPCION);
        }
    }

    @Test
    public void fechaAnulacionNoValidaNoAsignadaAMatriculaTest() {
        Matricula matricula = null;
        try {
            matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));

        } catch (Exception e) {
            fail(ERROR_NO_EXCEPCION);
        }

        try {
            matricula.setFechaAnulacion(LocalDate.now().plusDays(5));
            fail(FECHA_ANULACION_INCORRECTA);

        } catch (IllegalArgumentException e) {
            assertEquals(FECHA_ANULACION_POSTERIOR_HOY, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            //assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }

        try {
            matricula.setFechaAnulacion(matricula.getFechaMatriculacion().minusDays(5));
            fail(FECHA_ANULACION_INCORRECTA);

        } catch (IllegalArgumentException e) {
            assertEquals(FECHA_ANULACION_ANTERIOR_FECHA_MATRICULACION, e.getMessage(), MENSAJE_EXCEPCION_NO_CORRECTO);
            //assertNull(matricula, OBJETO_DEBERIA_SER_NULO);
        }





    }

    @Test
    void toStringDevuelveLaCadenaEsperada() {

        try
        {
            Matricula matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));

            StringBuilder resultado= new StringBuilder();

            for(Asignatura asignatura : coleccionAsignaturas)
            {
                if (asignatura!=null)
                    resultado.append(asignatura.imprimir());
            }




            String cadenaEsperada=String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno=%s, Asignaturas={ %s}",
                    ID_MATRICULA, CURSO_ACADEMICO,
                    FECHA_MATRICULACION.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    alumno.imprimir(), resultado.toString());

            assertEquals(cadenaEsperada, matricula.toString());

            matricula.setFechaAnulacion(FECHA_ANULACION);

            cadenaEsperada=String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, fecha anulación=%s, alumno=%s, Asignaturas={ %s}",
                    ID_MATRICULA, CURSO_ACADEMICO,
                    FECHA_MATRICULACION.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    FECHA_ANULACION.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    alumno.imprimir(), resultado.toString());

            assertEquals(cadenaEsperada, matricula.toString());


        }
        catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }
    }


    @Test
    void imprimirDevuelveLaCadenaEsperada() {


        try
        {
            Matricula matricula = new Matricula(ID_MATRICULA, CURSO_ACADEMICO, FECHA_MATRICULACION, alumno, Arrays.asList(coleccionAsignaturas));

            String cadenaEsperada=String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno={%s}",
                    ID_MATRICULA, CURSO_ACADEMICO,
                    FECHA_MATRICULACION.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)),
                    alumno.imprimir());

            assertEquals(cadenaEsperada, matricula.imprimir());
        }
        catch (Exception e) {
            fail(TIPO_EXCEPCION_NO_CORRECTA);
        }



    }

}
