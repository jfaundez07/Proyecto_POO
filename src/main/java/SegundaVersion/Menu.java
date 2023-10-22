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

    public void menu(ArrayList<Cabaña> listaCabañas, ArrayList<Cliente> listaClientes, SistemaReservas sistemaReservas) {

        Cliente clienteIngresado = listaClientes.get(sistemaReservas.loginUsario());

        String seleccion;

        do {
            opcionesMenu(clienteIngresado);
            seleccion = leer.nextLine();

            switch (seleccion) {

                /*case "1" ->
                case "2" ->
                case "3" ->
                case "4" ->*/

            }

        } while (!seleccion.equals("0"));

    }

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


}