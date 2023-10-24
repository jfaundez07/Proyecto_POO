package SegundaVersion;
import org.json.JSONObject;
import java.util.ArrayList;

public class SistemaReservas {

    //Listas para almacenar los datos en formato de sus respectivas Clases:
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cabaña> listaCabañas;

    public SistemaReservas(){
        listaClientes = setListaClientes(new GestorDeArchivos().listaJsonCliente());
        listaCabañas = setListaCabaña(new GestorDeArchivos().listaJsonCabañas());
    }


    public ArrayList<Cliente> getListaClientes(){
        return this.listaClientes;
    }

    public ArrayList<Cabaña> getListaCabañas() {
        return this.listaCabañas;
    }

    //Metodos para instanciar los objetos, a partir de un Json:
    private Cliente instanciarClienteJson (JSONObject archivoCliente) {
        return new Cliente(archivoCliente.getString("usuario"), archivoCliente.getString("contraseña"), archivoCliente.getInt("celular"));
    }

    private Cabaña instanciarCabañaJson (JSONObject archivoCabaña) {
        if (archivoCabaña.getBoolean("isOcupada")){
            int pos = obtenerPosicionUsuario(archivoCabaña.getString("arrendatarios"));
            return new Cabaña(
                    archivoCabaña.getInt("id"),
                    archivoCabaña.getString("nombre"),
                    archivoCabaña.getInt("habitaciones"),
                    archivoCabaña.getInt("baños"),
                    archivoCabaña.getBoolean("isOcupada"),
                    listaClientes.get(pos));
        }
        return new Cabaña(
                archivoCabaña.getInt("id"),
                archivoCabaña.getString("nombre"),
                archivoCabaña.getInt("habitaciones"),
                archivoCabaña.getInt("baños"));
    }

    // genera una lista de clientes a partir de una lista de archivos json
    private ArrayList<Cliente> setListaClientes(ArrayList<JSONObject> clientes){
        ArrayList<Cliente> newListClientes = new ArrayList<>();
        for (JSONObject cliente : clientes){
            newListClientes.add(instanciarClienteJson(cliente));
        }
        return newListClientes;
    }

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
    private String lecturaString(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextLine();
    }

    private int lecturaInt(){
        java.util.Scanner leer = new java.util.Scanner(System.in);
        return leer.nextInt();
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
            new GestorDeArchivos().escribirArchivoJSON("Cliente", Usuario, new Cliente(Usuario, Contraseña, Celular).clienteToJson());
            System.out.println("Usuario creado correctamente.");
        } else {
            System.out.println("Usuario ya existe.");
        }
    }

    public Cliente loginUsario() {
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
        //new Menu().menuPrincipal(listaClientes.get(posicion));
        return listaClientes.get(posicion);
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
}

