package SegundaVersion;

import org.json.JSONObject;

import java.util.ArrayList;

public class SistemaReservas {

    //Listas para almacenar los datos de los usuarios y las cabañas en formato de sus respectivas Clases:
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Cabaña> listaCabañas = new ArrayList<>();

   private Cliente instanciarCliente (JSONObject archivoCliente) {
       return new Cliente(archivoCliente.getString("Usuario"), archivoCliente.getString("Contraseña"), archivoCliente.getInt("Celular"));
   }

   private Cabaña instanciarCabaña (JSONObject archivoCabaña) {
       return new Cabaña(archivoCabaña.getInt("Id"), archivoCabaña.getString("Nombre"), archivoCabaña.getInt("Habitaciones"), archivoCabaña.getInt("Baños"), archivoCabaña.getBoolean("isOcupada"), archivoCabaña.getInt("Arrendatario"));
   }

   private void almacenarCliente(Cliente nuevoCliente){
       this.listaClientes.add(nuevoCliente);
   }

   private void almacenarCabaña(Cabaña nuevaCabaña){
       this.listaCabañas.add(nuevaCabaña);
   }


}
