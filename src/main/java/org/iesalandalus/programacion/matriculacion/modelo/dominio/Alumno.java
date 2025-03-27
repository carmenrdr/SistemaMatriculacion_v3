package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alumno {

    public static final String FORMATO_FECHA = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})";
    private static final String ER_TELEFONO = "[6-9][0-9]{8}";
    private static final String ER_CORREO = "[a-zA-Z0-9_+&*-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}";
    private static final String ER_DNI = "[0-9]{8}[a-zA-Z]";
    private static final String ER_NIA = "[a-z]{4}[0-9]{3}";
    public static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) throws IllegalArgumentException {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();
    }

    public Alumno(Alumno alumno) {
        if (alumno == null){
            throw new IllegalArgumentException("No es posible copiar un alumno nulo.");
        } else {
            setNombre(alumno.getNombre());
            setDni(alumno.getDni());
            setCorreo(alumno.getCorreo());
            setTelefono(alumno.getTelefono());
            setFechaNacimiento(alumno.getFechaNacimiento());
            setNia(alumno.getNia());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        String nombreFormateado = formateaNombre(nombre);
        if (nombreFormateado.isEmpty()) {
            throw new IllegalArgumentException("El nombre de un alumno no puede ser nulo.");
        } else {
            this.nombre = nombreFormateado;
        }
    }

    public String getNia() {
        return nia;
    }

    public void setNia() {
        this.nia = this.nombre.substring(0, 4).toLowerCase()+this.dni.substring(5, 8);
    }

    private void setNia(String nia) throws IllegalArgumentException {
        if (!nia.matches(ER_NIA)) {
            throw new IllegalArgumentException("El Nia introducido no es válido.");
        } else {
            this.nia = nia;
        }
    }

    private String formateaNombre(String nombre) {

        String[] palabras = nombre.split("\\s");

        StringBuilder nombreFormateado = new StringBuilder();

        for (int i = 0; i < palabras.length; i++) {
            String palabraFormateada = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
            nombreFormateado.append(palabraFormateada);

            if (i < palabras.length-1) {
                nombreFormateado.append(" ");
            }
        }

        return nombreFormateado.toString().trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws IllegalArgumentException {
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono del alumno no tiene un formato válido.");
        } else {
            this.telefono = telefono;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws IllegalArgumentException {
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("El correo del alumno no tiene un formato válido.");
        } else {
            this.correo = correo;
        }
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) throws IllegalArgumentException {
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del dni del alumno no es correcta.");
        }

        this.dni = dni;
        setNia();
    }

    private boolean comprobarLetraDni(String dni) {
        if (!dni.matches(ER_DNI)) {
            return false;
        }

        String numeroDni = dni.substring(0,8);
        char letraDni = Character.toUpperCase(dni.charAt(8));

        int dniNumeroEntero = Integer.parseInt(numeroDni);

        char[] letrasDni = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        int resto = dniNumeroEntero % 23;
        char letraCalculada = letrasDni[resto];

        return letraDni == letraCalculada;

    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) throws IllegalArgumentException {
        if (fechaNacimiento == null){
            throw new IllegalArgumentException("La fecha de nacimiento de un alumno no puede ser nula.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaEnString = fechaNacimiento.format(formatter);

        if (!fechaEnString.matches(FORMATO_FECHA)){
            throw new IllegalArgumentException("El formato de la fecha introducida no es correcto.");
        }

        LocalDate fechaAhora = LocalDate.now();

        if (fechaAhora.minusYears(MIN_EDAD_ALUMNADO).isBefore(fechaNacimiento)) {
            throw new IllegalArgumentException("La edad del alumno debe ser mayor o igual a 16 años.");
        }

        this.fechaNacimiento = fechaNacimiento;

    }

    private String getIniciales() {
        StringBuilder iniciales = new StringBuilder();

        String[] palabras = this.nombre.split(" ");

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                iniciales.append(palabra.charAt(0));
            }
        }

        return iniciales.toString().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        final Alumno other = (Alumno) o;

        if (!Objects.equals(this.dni.toUpperCase(), other.dni.toUpperCase())) {return false;}

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.dni);
    }

    public String imprimir() {
        return "Número de Identificación del Alumnado (NIA)= "+nia+ ", nombre= "+nombre+", DNI= "+dni+
                ", correo= "+correo+", teléfono= "+telefono+", fecha nacimiento= "+fechaNacimiento+".";
    }

    @Override
    public String toString() {
        return "Número de Identificación del Alumnado (NIA)= "+nia+ ", nombre= "+nombre+" (" +getIniciales()+
                "), DNI= "+dni+ ", correo= "+correo+", teléfono= "+telefono+", fecha nacimiento= "+fechaNacimiento+".";
    }
}
