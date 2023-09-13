import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {MenuBienvenida();}



    public static void OpcionesMenuBienvenida() {
        System.out.println("\nBienvenido a R.A \nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }

    public static void MenuBienvenida() {

        ArrayList<JSONObject> listCabañas = new ArrayList<JSONObject>();
        ArrayList<JSONObject> listUsuarios = new ArrayList<JSONObject>();

        crearCabañas(listCabañas);
        crearUsuarios(listUsuarios);

        String Seleccion;

        do {
            OpcionesMenuBienvenida();
            Seleccion = leer.nextLine();

            switch (Seleccion){
                case "1" -> menu(listCabañas, listUsuarios);
                case "2" -> System.out.println("Aun no existe");
            }

        } while (!Seleccion.equals("0"));
    }

}