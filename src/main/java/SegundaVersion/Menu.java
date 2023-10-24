package SegundaVersion;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner leer = new Scanner(System.in);

    //METODOS MENU
    public void opcionesMenu(Cliente usuarioIngresado) {
        System.out.println("\nMenu Cabañas. Usuario: " + usuarioIngresado.getUsuario());
        System.out.println("[0] Salir\n[1] Mostrar Cabañas existentes\n[2] Reservar Cabaña\n[3] Ver Mis Cabañas Reservadas\n[4] Realizar Check-Out\nQue desea hacer?: ");
    }

    public void menuPrincipal(Cliente usuario) {

        SistemaReservas sistemaReservas = new SistemaReservas();
        sistemaReservas.leerTodo();

        String seleccion;

        do {
            opcionesMenu(usuario);
            seleccion = leer.nextLine();
            switch (seleccion) {

                case "1" -> mostrarCabañasExistentes();
                case "2" -> new SistemaReservas().menuReservarCabaña(usuario);
                case "3" -> mostrarCabañasReservadas(usuario);
                case "4" -> new SistemaReservas().menuCheckOutCabaña(usuario);
            }
        } while (!seleccion.equals("0"));

    }

    public void OpcionesMenuBienvenida() {
        System.out.println("\nBienvenido a R.A \nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }

    public void MenuBienvenida() {
        SistemaReservas sistemaReservas = new SistemaReservas();
        /*sistemaReservas.rellenarListaCabañas();
        sistemaReservas.rellenarListaClientesPorDefecto();*/

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
    public void mostrarCabañasExistentes() {
        System.out.println("\nCabañas existentes: ");
        for (Cabaña cabaña : new SistemaReservas().getListaCabañas()) {
            cabaña.mostrarCabaña();
        }
    }
    public void mostrarCabañasReservadas(Cliente usr) {
        int contador = 0;
        for (int i = 0; i < new SistemaReservas().getListaCabañas().size(); i++) {
            try{
                if (new SistemaReservas().getListaCabañas().get(i).getArrendatario().getUsuario().equals(usr.getUsuario())){
                    contador += 1;
                    new SistemaReservas().getListaCabañas().get(i).mostrarCabaña();
                }
            } catch (NullPointerException error){
            }
        }
        if (contador == 0) {
            System.out.println(usr.getUsuario() + " Aun no ha reservado ninguna cabaña.");
        }
    }
}
