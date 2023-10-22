package SegundaVersion;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner leer = new Scanner(System.in);

    //METODOS MENU BIENVENIDA

    public void OpcionesMenuBienvenida() {
        System.out.println("\nBienvenido a R.A \nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }

    public void MenuBienvenida() {
        SistemaReservas sistemaReservas = new SistemaReservas();

        String Seleccion;

        do {
            OpcionesMenuBienvenida();
            Seleccion = leer.nextLine();

            switch (Seleccion){
                case "1" -> sistemaReservas.loginUsario();
                case "2" -> sistemaReservas.singUP();
            }

        } while (!Seleccion.equals("0"));
    }

    //METODOS MENU PRINCIPAL
    public void opcionesMenu(Cliente usuarioIngresado) {
        System.out.println("\nMenu Cabañas. Usuario: " + usuarioIngresado.getUsuario());
        System.out.println("[0] Salir\n[1] Mostrar Cabañas existentes\n[2] Reservar Cabaña\n[3] Ver Mis Cabañas Reservadas\n[4] Realizar Check-Out\nQue desea hacer?: ");
    }

    public void menu(Cliente usr) {

        String seleccion;

        do {
            opcionesMenu(usr);
            seleccion = leer.nextLine();

            switch (seleccion) {

                case "1" -> mostrarCabañasExistentes(new SistemaReservas().getListaCabañas());
                /*case "2" -> reservarCabaña(usuarioIngresado, listCabañas);
                case "3" -> mostrarCabañasReservadas(usuarioIngresado, listCabañas);
                case "4" -> checkOutCabaña(usuarioIngresado, listCabañas);*/

            }

        } while (!seleccion.equals("0"));

    }

    //Caracteristicas Menu
    public void mostrarCabañasExistentes(ArrayList<Cabaña> listCabañas) {

        System.out.println("\nCabañas existentes: ");

        for (Cabaña cabaña : listCabañas) {

            cabaña.mostrarCabaña();

        }
    }

}