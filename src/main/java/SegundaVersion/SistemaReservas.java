package SegundaVersion;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaReservas {

    //Listas para almacenar los datos en formato de sus respectivas Clases:
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Cabaña> listaCabañas = new ArrayList<>();

    public static Scanner leer = new Scanner(System.in);

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

    public int loginUsario(ArrayList<Cliente> listaClientes) {

        int posicion;

        boolean validar = false;

        do {

            System.out.println("\nA continuacion ingrese los datos solicitados");

            System.out.println("Ingrese su nombre previamente registrado: ");
            String usuario = leer.nextLine();

            System.out.println("Ingrese su contraseña");
            String contraseña = leer.nextLine();

            posicion = 0;

            if (validarUsuario(listaClientes, usuario, contraseña)) {
                posicion = obtenerPosicionUsuario(listaClientes, usuario, contraseña);
                validar = true;
            }


        } while (!validar);

        return posicion;

    }

    public boolean validarUsuario(ArrayList<Cliente> listaClientes, String usuario, String contraseña) {

        for (int i = 0; i < listaClientes.size(); i++) {

            if ((listaClientes.get(i)).getUsuario().equals(usuario) && (listaClientes.get(i)).getContraseña().equals(contraseña)) {
                return true;
            }
        }
        System.out.println("Usuario y/o contraseña incorrecto");

        return false;
    }

    public int obtenerPosicionUsuario(ArrayList<Cliente> listaUsuarios, String Usuario, String Contraseña) {

        int posicion = 0;

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if ((listaUsuarios.get(i)).getUsuario().equals(Usuario) && (listaUsuarios.get(i)).getContraseña().equals(Contraseña)) {
                posicion = i;
            }
        }
        return posicion;
    }

}