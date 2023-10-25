package SegundaVersion;
import java.util.Scanner;
public class Menu {
    public static Scanner leer = new Scanner(System.in);
    private GestorDeClientes gestorDeClientes = new GestorDeClientes();
    private GestorDeCabañas gestorDeCabañas =  new GestorDeCabañas();
    public void menuBienvenida() {
        String Seleccion;
        do {
            opcionesMenuBienvenida();
            Seleccion = leer.nextLine();
            switch (Seleccion){
                case "1" -> menuPrincipal(gestorDeClientes.loginUsario());
                case "2" -> gestorDeClientes.singUP();
            }
        } while (!Seleccion.equals("0"));
        gestorDeCabañas.registrarCabañasEnArchivoJson();
        gestorDeClientes.registrarClientesEnArchivoJson();
    }

    public void menuPrincipal(Cliente usuario) {
        String seleccion;
        do {
            opcionesMenuPrincipal(usuario);
            seleccion = leer.nextLine();
            switch (seleccion) {
                case "1" -> gestorDeCabañas.mostrarCabañasExistentes();
                case "2" -> gestorDeCabañas.menuReservarCabaña(usuario);
                case "3" -> gestorDeCabañas.mostrarCabañasReservadas(usuario);
                case "4" -> gestorDeCabañas.menuCheckOutCabaña(usuario);
            }
        } while (!seleccion.equals("0"));
    }
    public void opcionesMenuPrincipal(Cliente usuarioIngresado) {
        System.out.println("\n#-----MENÚ CABAÑAS-----#\nUsuario: " + usuarioIngresado.getUsuario());
        System.out.println("[0] Salir\n[1] Mostrar Cabañas existentes\n[2] Reservar Cabaña\n[3] Ver Mis Cabañas Reservadas\n[4] Realizar Check-Out\nQue desea hacer?: ");
    }

    public void opcionesMenuBienvenida() {
        System.out.println("\n#-----Bienvenido a R.A-----#\nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }
}
