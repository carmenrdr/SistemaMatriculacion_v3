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
            controlador.comenzar();

        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }

    }

}
