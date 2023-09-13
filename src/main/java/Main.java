import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {MenuBienvenida();}


    //METODOS DE LOS USUARIOS
    public static void crearUsuarios(ArrayList<JSONObject> listraUsuarios) {

        JSONObject Usuario1 = new JSONObject();
        JSONObject Usuario2 = new JSONObject();

        Usuario1.put("Usuario", "Javier");
        Usuario1.put("Celular", "962336730");
        Usuario1.put("Contraseña", "987654321");

        Usuario2.put("Usuario", "Klima");
        Usuario2.put("Celular", "978356652");
        Usuario2.put("Contraseña", "123456789");

        agregarUsuario(listraUsuarios, Usuario1);
        agregarUsuario(listraUsuarios, Usuario2);
    }

    public static void agregarUsuario(ArrayList<JSONObject> listaUsuarios, JSONObject json) {
        listaUsuarios.add(json);
    }

    public static int LoginUsario(ArrayList<JSONObject> listUsuarios) {

        int posicion;

        boolean validar = false;

        do {

            System.out.println("\nA continuacion ingrese los datos solicitados");

            System.out.println("Ingrese su nombre previamente registrado: ");
            String usuario = leer.nextLine();

            System.out.println("Ingrese su contraseña");
            String contraseña = leer.nextLine();

            posicion = 0;

            if (validarUsuario(listUsuarios, usuario, contraseña)) {
                posicion = obtenerPosicionUsuario(listUsuarios, usuario, contraseña);
                validar = true;
            }


        } while (!validar);

        return posicion;

    }

    public static boolean validarUsuario(ArrayList<JSONObject> listaUsuarios, String Usuario, String Contraseña) {

        for (int i = 0; i < listaUsuarios.size(); i++) {

            if ((listaUsuarios.get(i)).get("Usuario").equals(Usuario) && (listaUsuarios.get(i)).get("Contraseña").equals(Contraseña)) {
                return true;
            }
        }
        System.out.println("Usuario y/o contraseña incorrecto");

        return false;
    }

    public static int obtenerPosicionUsuario(ArrayList<JSONObject> listaUsuarios, String Usuario, String Contraseña) {

        int posicion = 0;

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if ((listaUsuarios.get(i)).get("Usuario").equals(Usuario) && (listaUsuarios.get(i)).get("Contraseña").equals(Contraseña)) {
                posicion = i;
            }
        }
        return posicion;
    }


    //METODOS DE LAS CABAÑS
    public static void crearCabañas(ArrayList<JSONObject> listCabañas) {

        JSONObject cabana1 = new JSONObject();
        JSONObject cabana2 = new JSONObject();

        cabana1.put("Id", 1);
        cabana1.put("Nombre", "Cabaña 1");
        cabana1.put("Habitaciones", "2");
        cabana1.put("Baños", "1");
        cabana1.put("isOcupada", false);
        cabana1.put("Arrendatario", 0);

        cabana2.put("Id", 2);
        cabana2.put("Nombre", "Cabaña 2");
        cabana2.put("Habitaciones", "3");
        cabana2.put("Baños", "2");
        cabana2.put("isOcupada", false);
        cabana2.put("Arrendatario", 0);

        agregarCabaña(listCabañas, cabana1);
        agregarCabaña(listCabañas, cabana2);

    }

    public static void agregarCabaña(ArrayList<JSONObject> listCabañas, JSONObject newCabaña) {
        listCabañas.add(newCabaña);
    }

