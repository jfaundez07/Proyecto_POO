package SegundaVersion;

import org.json.JSONObject;
import java.util.ArrayList;

public class SistemaReservas {

    //Listas para almacenar los datos en formato de sus respectivas Clases:
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Cabaña> listaCabañas = new ArrayList<>();


    //Metodos para instanciar los objetos, a partir de un Json:
   private Cliente instanciarClienteJson (JSONObject archivoCliente) {
       return new Cliente(archivoCliente.getString("Usuario"), archivoCliente.getString("Contraseña"), archivoCliente.getInt("Celular"));
   }

   /*private Cabaña instanciarCabañaJson (JSONObject archivoCabaña) {
       return new Cabaña(archivoCabaña.getInt("Id"), archivoCabaña.getString("Nombre"), archivoCabaña.getInt("Habitaciones"), archivoCabaña.getInt("Baños"), archivoCabaña.getBoolean("isOcupada"));
   }*/


   //Metodos para instanciar los objetos en la ejecucion del programa:
    public Cliente instanciarCliente(String usuario, String contraseña, int celular){
        return new Cliente(usuario, contraseña, celular);
    }

    public Cabaña instanciarCabaña(int id, String nombre, int habitaciones, int baños){
        return new Cabaña(id, nombre, habitaciones, baños);
   }


   //Metodos para almacenar el objeto en la lista de objetos:
   private void almacenarCliente(Cliente nuevoCliente){
       this.listaClientes.add(nuevoCliente);
   }

   private void almacenarCabaña(Cabaña nuevaCabaña){
       this.listaCabañas.add(nuevaCabaña);
   }


}
