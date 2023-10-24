package SegundaVersion;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaReservas {

    //Listas para almacenar los datos en formato de sus respectivas Clases:
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Cabaña> listaCabañas = new ArrayList<>();

    private String lecturaString(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextLine();
    }

    private int lecturaInt(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextInt();
    }

    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes;
    }

    //Metodos para instanciar los objetos, a partir de un Json:
    private Cliente instanciarClienteJson (JSONObject archivoCliente) {
        return new Cliente(archivoCliente.getString("Usuario"), archivoCliente.getString("Contraseña"), archivoCliente.getInt("Celular"));
    }

   /*private Cabaña instanciarCabañaJson (JSONObject archivoCabaña) {
       return new Cabaña(archivoCabaña.getInt("Id"), archivoCabaña.getString("Nombre"), archivoCabaña.getInt("Habitaciones"), archivoCabaña.getInt("Baños"), archivoCabaña.getBoolean("isOcupada"));
   }*/


    public void setListaClientes(){

    }
    public ArrayList<Cabaña> getListaCabañas() {
        return listaCabañas;
    }
    //Metodos para almacenar el objeto en la lista de objetos:

    public void rellenarListaClientesPorDefecto(){
        Cliente Javier = new Cliente("Javier","1234",94484766);
        Cliente Joaquin = new Cliente("Joaquin", "1234", 99999999);
        listaClientes.add(Javier);
        listaClientes.add(Joaquin);
    }

    public void rellenarListaCabañas(){
        Cabaña cabaña1 = new Cabaña(1,"Cabaña 1",2,1,false,null);
        Cabaña cabaña2 = new Cabaña(2,"Cabaña 2",3,2,false,null);
        listaCabañas.add(cabaña1);
        listaCabañas.add(cabaña2);
    }

    public void loginUsario() {
        int posicion;
        String usuario;
        boolean validar = false;
        do {
            System.out.println("\nA continuacion ingrese los datos solicitados");
            System.out.println("Ingrese su nombre previamente registrado: ");
            usuario = lecturaString();
            System.out.println("Ingrese su contraseña");
            String contraseña = lecturaString();
            posicion = 0;
            if (validarUsuario(usuario, contraseña)) {
                posicion = obtenerPosicionUsuario(usuario);
                validar = true;
            }
        } while (!validar);
        new Menu().menuPrincipal(listaClientes.get(posicion));
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        for (int i = 0; i < listaClientes.size(); i++) {

            if ((listaClientes.get(i)).getUsuario().equals(usuario) && (listaClientes.get(i)).getContraseña().equals(contraseña)) {
                return true;
            }
        }
        System.out.println("Usuario y/o contraseña incorrecto");
        return false;
    }

    public int obtenerPosicionUsuario(String Usuario) {

        int posicion = -1;

        for (int i = 0; i < this.listaClientes.size(); i++) {
            if ((this.listaClientes.get(i)).getUsuario().equals(Usuario)) {
                posicion = i;
            }
        }
        return posicion;
    }

    public void menuReservarCabaña(Cliente usr){
        System.out.println("\nReserva de cabañas");
        mostrarCabañasExistentes();
        System.out.println("\nIngrese la ID de la cabaña que desea reservar: ");
        try{
            int elegirID = lecturaInt();
            for (Cabaña cabaña : this.listaCabañas) {
                if (elegirID == cabaña.getId()) {
                    cabaña.reservarCabaña(usr);
                }
            }
        }catch (Exception e) {
            // manejar la excepción
            System.out.println("Opcion inválida");
        }
    }
    public void mostrarCabañasExistentes() {

        System.out.println("\nCabañas existentes: ");

        for (Cabaña cabaña : new SistemaReservas().getListaCabañas()) {

            cabaña.mostrarCabaña();

        }
    }
    public void singUP(){
        String Usuario;
        int Celular = 0;
        String Contraseña;
        String Contraseña2;
        System.out.println("Ingrese el nombre de su nuevo usuario");
        Usuario = lecturaString();
        do {
            System.out.println("Ingrese celular valido. (9 digitos, solo numeros)");
            try{
                Celular = lecturaInt();}
            catch (Exception e){
                System.out.println("Valores no validos");
            }
        } while (!(Integer.toString(Celular).length() == 9));
        do {
            System.out.println("Ingrese Contraseña");
            Contraseña = lecturaString();
            System.out.println("Confirme Contaseña");
            Contraseña2 = lecturaString();
        } while (!Contraseña.equals(Contraseña2));
        if (!new GestorDeArchivos().usuarioExiste(Usuario)) {
            this.listaClientes.add(new Cliente(Usuario, Contraseña, Celular));
            new GestorDeArchivos().escribirArchivoJSON("Cliente", Usuario, new Cliente(Usuario, Contraseña, Celular).toJson());
            System.out.println("Usuario creado correctamente.");
        } else {
            System.out.println("Usuario ya existe.");
        }
    }
    public void menuCheckOutCabaña(Cliente usr){
        System.out.println("\nCheck-Out Cabañas: ");
        new Menu().mostrarCabañasReservadas(usr);
        try{
            System.out.println("\nIngrese la ID de la cabaña que desea hacer check-out ");
            int elegirID = lecturaInt();

            for (Cabaña cabaña : new SistemaReservas().getListaCabañas()) {
                if (elegirID == cabaña.getId()) {
                    cabaña.checkOutCabaña(usr);
                }
            }
        }catch (Exception e) {
            // manejar la excepción
            System.out.println("Opcion inválida");
        }
    }
}

