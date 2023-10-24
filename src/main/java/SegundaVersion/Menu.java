package SegundaVersion;

import java.util.Scanner;

public class Menu {
    public static Scanner leer = new Scanner(System.in);


    public void MenuBienvenida() {

        GestorDeCabañas instanciador = new GestorDeCabañas();

        String Seleccion;

        do {
            OpcionesMenuBienvenida();
            Seleccion = leer.nextLine();

            switch (Seleccion){
                case "1" -> menuPrincipal(instanciador.loginUsario(), instanciador);
                case "2" -> instanciador.singUP();
            }

        } while (!Seleccion.equals("0"));
    }

    public void menuPrincipal(Cliente usuario, GestorDeCabañas instanciador) {;

        String seleccion;

        do {
            opcionesMenuPrincipal(usuario);
            seleccion = leer.nextLine();
            switch (seleccion) {

                case "1" -> instanciador.mostrarCabañasExistentes();
                case "2" -> instanciador.menuReservarCabaña(usuario);
                case "3" -> instanciador.mostrarCabañasReservadas(usuario);
                case "4" -> instanciador.menuCheckOutCabaña(usuario);
            }
        } while (!seleccion.equals("0"));

    }

    public void opcionesMenuPrincipal(Cliente usuarioIngresado) {
        System.out.println("\nMenu Cabañas. Usuario: " + usuarioIngresado.getUsuario());
        System.out.println("[0] Salir\n[1] Mostrar Cabañas existentes\n[2] Reservar Cabaña\n[3] Ver Mis Cabañas Reservadas\n[4] Realizar Check-Out\nQue desea hacer?: ");
    }

    public void OpcionesMenuBienvenida() {
        System.out.println("\nBienvenido a R.A \nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }

}
