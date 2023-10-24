package SegundaVersion;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner leer = new Scanner(System.in);

    //METODOS MENU

    public void OpcionesMenuBienvenida() {
        System.out.println("\nBienvenido a R.A \nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }

    public void MenuBienvenida() {
        SistemaReservas sistemaClientes = new SistemaReservas();
        sistemaClientes.generarClientes();

        String Seleccion;

        do {
            OpcionesMenuBienvenida();
            Seleccion = leer.nextLine();

            switch (Seleccion){
                case "1" -> sistemaClientes.loginUsario();
                case "2" -> sistemaClientes.singUP();
            }

        } while (!Seleccion.equals("0"));
    }
    public void opcionesMenu(Cliente usuarioIngresado) {
        System.out.println("\nMenu Cabañas. Usuario: " + usuarioIngresado.getUsuario());
        System.out.println("[0] Salir\n[1] Mostrar Cabañas existentes\n[2] Reservar Cabaña\n[3] Ver Mis Cabañas Reservadas\n[4] Realizar Check-Out\nQue desea hacer?: ");
    }

    public void menuPrincipal(Cliente usuario) {

        SistemaReservas sistemaCabañas = new SistemaReservas();
        sistemaCabañas.generarCabañas();

        String seleccion;

        do {
            opcionesMenu(usuario);
            seleccion = leer.nextLine();
            switch (seleccion) {

                case "1" -> sistemaCabañas.mostrarCabañasExistentes();
                case "2" -> sistemaCabañas.menuReservarCabaña(usuario);
                case "3" -> sistemaCabañas.mostrarCabañasReservadas(usuario);
                case "4" -> sistemaCabañas.menuCheckOutCabaña(usuario);
            }
        } while (!seleccion.equals("0"));

    }



}
