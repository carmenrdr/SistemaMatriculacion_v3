package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;

public class MainApp {

    public static void main(String[] args) {

        try {

            Modelo modelo = new Modelo();
            Vista vista = new Vista();
            Controlador controlador = new Controlador(modelo, vista);
            modelo.comenzar();
            controlador.comenzar();

        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: "+e.getMessage());
        }

    }

}
