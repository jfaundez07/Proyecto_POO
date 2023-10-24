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

                case "1" -> mostrarCabañasExistentes();
                case "2" -> reservarCabaña(usr);
                case "3" -> mostrarCabañasReservadas(usr);
                case "4" -> checkOutCabaña(usr);

            }

        } while (!seleccion.equals("0"));

    }

    //Caracteristicas Menu
    public void mostrarCabañasExistentes() {

        System.out.println("\nCabañas existentes: ");

        for (Cabaña cabaña : new SistemaReservas().getListaCabañas()) {

            cabaña.mostrarCabaña();

        }
    }

    public void reservarCabaña(Cliente usr){

        System.out.println("\nReserva de cabañas");

        mostrarCabañasExistentes();

        try{ System.out.println("\nIngrese la ID de la cabaña que desea reservar: ");
            int elegirID = leer.nextInt();

            for (Cabaña cabaña : new SistemaReservas().getListaCabañas()) {

                if (idExiste(elegirID, cabaña)) {

                    if (!cabaña.getIsOcupada()) {

                        cabaña.setIsOcupada(true);
                        cabaña.setArrendatario(usr);
                        new GestorDeArchivos().escribirArchivoJSON("Cabaña", Integer.toString(cabaña.getId()), cabaña.toJson());

                        System.out.println(usr.getUsuario() + "! Su cabaña fue reservada exitosamente");
                    } else{
                        System.out.println("\nCabaña ocupada");
                    }
                }
            }
        }catch (Exception e) {
            // manejar la excepción
            System.out.println("Opcion inválida");
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
            System.out.println(usr.getUsuario() + " aun no ha reservado ninguna cabaña.");
        }
    }

    public void checkOutCabaña(Cliente usr){

        System.out.println("\nCheck-Out Cabañas: ");

        mostrarCabañasReservadas(usr);

        try{
            System.out.println("\nIngrese la ID de la cabaña que desea hacer check-out ");
            int elegirID = leer.nextInt();

            for (Cabaña cabaña : new SistemaReservas().getListaCabañas()) {

                if (idExiste(elegirID, cabaña)) {

                    if (cabaña.getIsOcupada()) {

                        cabaña.setIsOcupada(false);
                        cabaña.setArrendatario(null);
                        new GestorDeArchivos().escribirArchivoJSON("Cabaña", Integer.toString(cabaña.getId()), cabaña.toJson());

                        System.out.println(usr.getUsuario() + "! El check-out fue realizado exitosamente");
                    }
                }
            }
        }catch (Exception e) {
            // manejar la excepción
            System.out.println("Opcion inválida");
        }
    }

    public boolean idExiste(int id, Cabaña cabañaSeleccionada) {
        return id == cabañaSeleccionada.getId();
    }
}