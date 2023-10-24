package SegundaVersion;
import org.json.JSONObject;
import java.util.ArrayList;

public class GestorDeCabañas {

    //Listas para almacenar los datos en formato de sus respectivas Clases:
    private ArrayList<Cabaña> listaCabañas;

    public GestorDeCabañas(){
        listaCabañas = setListaCabaña(new GestorDeArchivos().listaJsonCabañas());
    }

    public ArrayList<Cabaña> getListaCabañas() {
        return this.listaCabañas;
    }

    //Metodo para instanciar los objetos, a partir de un Json:
    private Cabaña instanciarCabañaJson (JSONObject archivoCabaña) {
        GestorDeClientes gestorDeClientes = new GestorDeClientes();
        if (archivoCabaña.getBoolean("isOcupada")){
            int pos = gestorDeClientes.obtenerPosicionUsuario(archivoCabaña.getString("arrendatarios"));
            return new Cabaña(
                    archivoCabaña.getInt("id"),
                    archivoCabaña.getString("nombre"),
                    archivoCabaña.getInt("habitaciones"),
                    archivoCabaña.getInt("baños"),
                    archivoCabaña.getBoolean("isOcupada"),
                    gestorDeClientes.getListaClientes().get(pos));
        }
        return new Cabaña(
                archivoCabaña.getInt("id"),
                archivoCabaña.getString("nombre"),
                archivoCabaña.getInt("habitaciones"),
                archivoCabaña.getInt("baños"));
    }

    // genera una lista de cabañas a partir de una lista de archivos json
    private ArrayList<Cabaña> setListaCabaña(ArrayList<JSONObject> cabañas){
        ArrayList<Cabaña> newListCabaña = new ArrayList<>();
        for (JSONObject cabaña : cabañas){
            newListCabaña.add(instanciarCabañaJson(cabaña));
        }
        return newListCabaña;
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

    public void menuCheckOutCabaña(Cliente usr){
        System.out.println("\nCheck-Out Cabañas: ");
        mostrarCabañasReservadas(usr);
        try{
            System.out.println("\nIngrese la ID de la cabaña que desea hacer check-out ");
            int elegirID = lecturaInt();

            for (Cabaña cabaña : listaCabañas) {
                if ( elegirID == cabaña.getId()  ) {
                    cabaña.checkOutCabaña(usr);
                }
            }
        }catch (Exception e) {
            // manejar la excepción
            System.out.println("Opcion inválida");
        }
    }

    public void mostrarCabañasExistentes() {
        System.out.println("\nCabañas existentes: ");
        for (Cabaña cabaña : this.listaCabañas) {
            cabaña.mostrarCabaña();
        }
    }

    public void mostrarCabañasReservadas(Cliente usr) {
        int contador = 0;
        for (int i = 0; i < this.listaCabañas.size(); i++) {
            try{
                if (this.listaCabañas.get(i).getArrendatario().getUsuario().equals(usr.getUsuario())){
                    contador += 1;
                    this.listaCabañas.get(i).mostrarCabaña();
                }
            } catch (NullPointerException error){
            }
        }
        if (contador == 0) {
            System.out.println(usr.getUsuario() + " Aun no ha reservado ninguna cabaña.");
        }
    }

    //Scanners:
    private int lecturaInt(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextInt();
    }
    public void registrarCabañasEnArchivoJson(){
        for (Cabaña cabaña : listaCabañas){
            new GestorDeArchivos().escribirArchivoJSON("Cabañas", Integer.toString(cabaña.getId()), cabaña.cabañaToJson());
        }
    }

}

