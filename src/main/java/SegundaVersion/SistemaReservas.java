package SegundaVersion;
import org.json.JSONObject;
import java.util.ArrayList;

public class SistemaReservas {

    //Listas para almacenar los datos en formato de sus respectivas Clases:
    private ArrayList<Cliente> listaClientes = setListaClientes(new GestorDeArchivos().listaJsonCliente());
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
        return new Cliente(archivoCliente.getString("usuario"), archivoCliente.getString("contraseña"), archivoCliente.getInt("celular"));
    }

    // genera una lista de clientes a partir de una lista de archivos json
    private ArrayList<Cliente> setListaClientes(ArrayList<JSONObject> clientes){
        ArrayList<Cliente> newListClientes = new ArrayList<>();
        for (JSONObject cliente : clientes){
            newListClientes.add(instanciarClienteJson(cliente));
        }
        return newListClientes;
    }


   /*private Cabaña instanciarCabañaJson (JSONObject archivoCabaña) {
       return new Cabaña(archivoCabaña.getInt("Id"), archivoCabaña.getString("Nombre"), archivoCabaña.getInt("Habitaciones"), archivoCabaña.getInt("Baños"), archivoCabaña.getBoolean("isOcupada"));
   }*/


    public void rellenarListaClientesPorDefecto(){
        Cliente Javier = new Cliente("Javier","1234",994484766);
        Cliente Joaquin = new Cliente("Joaquin", "1234", 999999999);
        Cliente Christian = new Cliente("Christian", "1111", 911111111);
        listaClientes.add(Javier);
        listaClientes.add(Joaquin);
        listaClientes.add(Christian);
        new GestorDeArchivos().escribirArchivoJSON("Cliente", Javier.getUsuario(), Javier.toJson());
        new GestorDeArchivos().escribirArchivoJSON("Cliente", Joaquin.getUsuario(), Joaquin.toJson());
        new GestorDeArchivos().escribirArchivoJSON("Cliente", Christian.getUsuario(), Christian.toJson());
    }

    public void rellenarListaCabañas(){
        Cabaña cabaña1 = new Cabaña(1,"Cabaña 1",2,1,false,null);
        Cabaña cabaña2 = new Cabaña(2,"Cabaña 2",3,2,false,null);
        listaCabañas.add(cabaña1);
        listaCabañas.add(cabaña2);
    }

    public int loginUsario() {

        int posicion;

        boolean validar = false;

        do {

            System.out.println("\nA continuacion ingrese los datos solicitados");

            System.out.println("Ingrese su nombre previamente registrado: ");
            String usuario = lecturaString();

            System.out.println("Ingrese su contraseña");
            String contraseña = lecturaString();

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
}