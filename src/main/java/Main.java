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

<<<<<<< HEAD
=======
    public static void mostrarCabaña(JSONObject Cabaña) {

        System.out.println();
        System.out.println("Id: " + Cabaña.get("Id"));
        System.out.println("Nombre: " + Cabaña.get("Nombre"));
        System.out.println("Cantidad de habitaciones: " + Cabaña.get("Habitaciones"));
        System.out.println("Cantidad de baños: " + Cabaña.get("Baños"));
        System.out.println("Esta ocupada: " + Cabaña.get("isOcupada"));
    }

    public static void mostrarCabañasExistentes(ArrayList<JSONObject> listCabañas) {

        System.out.println("\nCabañas existentes: ");

        for (int i = 0; i < listCabañas.size(); i++) {

            mostrarCabaña(listCabañas.get(i));

        }
    }

    //Agregue este metodo
    public static void mostrarCabañasReservadas(JSONObject usuarioIngresado, ArrayList<JSONObject> listCabañas) {

        int contador = 0;

        for (int i = 0; i < listCabañas.size(); i++) {

            if (listCabañas.get(i).get("Arrendatario").equals(usuarioIngresado)){
                contador += 1;
                mostrarCabaña(listCabañas.get(i));
            }
        }

        if (contador == 0) {
            System.out.println(usuarioIngresado.get("Usuario") + " aun no ha reservado ninguna cabaña.");
        }
    }

    public static void reservarCabaña(JSONObject usuarioIngresado, ArrayList<JSONObject> listCabañas){

        System.out.println("\nReserva de cabañas");

        mostrarCabañasExistentes(listCabañas);

        try{ System.out.println("\nIngrese la ID de la cabaña que desea reservar: ");
            int elegirID = leer.nextInt();

            for (int i = 0; i < listCabañas.size(); i++) {

                JSONObject cabañaSeleccionada = listCabañas.get(i);

                if (idExiste(elegirID, cabañaSeleccionada)) {

                    if (isLibre(cabañaSeleccionada)) {

                        cabañaSeleccionada.put("isOcupada", true);
                        cabañaSeleccionada.put("Arrendatario", usuarioIngresado);

                        System.out.println(usuarioIngresado.get("Usuario") + "! Su cabaña fue reservada exitosamente");
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

    public static void checkOutCabaña(JSONObject usuarioIngresado, ArrayList<JSONObject> listCabañas){

        System.out.println("\nCheck-Out Cabañas: ");

        mostrarCabañasReservadas(usuarioIngresado, listCabañas);

        System.out.println("\nIngrese la ID de la cabaña que desea hacer check-out ");
        int elegirID = leer.nextInt();

        for (int i = 0; i < listCabañas.size(); i++) {

            JSONObject cabañaSeleccionada = listCabañas.get(i);

            if (idExiste(elegirID, cabañaSeleccionada)) {

                if (!isLibre(cabañaSeleccionada)) {

                    cabañaSeleccionada.put("isOcupada", false);
                    cabañaSeleccionada.put("Arrendatario", 0);

                    System.out.println(usuarioIngresado.get("Usuario") + "! El check-out fue realizado exitosamente");
                }
            }
        }
    }

    public static boolean isLibre(JSONObject cabaña) {

        if (cabaña.get("isOcupada").equals(false)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean idExiste(int id, JSONObject cabañaSeleccionada) {
        if ( id == (int) cabañaSeleccionada.get("Id")){
            return true;
        }
        return false;
    }


    //METODOS MENU
    public static void opcionesMenu(JSONObject usuarioIngresado) {
        System.out.println("\nMenu Cabañas. Usuario: " + usuarioIngresado.get("Usuario"));
        System.out.println("[0] Salir\n[1] Mostrar Cabañas existentes\n[2] Reservar Cabaña\n[3] Ver Mis Cabañas Reservadas\n[4] Realizar Check-Out\nQue desea hacer?: ");
    }

    public static void menu(ArrayList<JSONObject> listCabañas, ArrayList<JSONObject> listUsuarios) {

        JSONObject usuarioIngresado = listUsuarios.get(LoginUsario(listUsuarios));

        String seleccion;

        do {
            opcionesMenu(usuarioIngresado);
            seleccion = leer.nextLine();

            switch (seleccion) {

                case "1" -> mostrarCabañasExistentes(listCabañas);
                case "2" -> reservarCabaña(usuarioIngresado, listCabañas);
                case "3" -> mostrarCabañasReservadas(usuarioIngresado, listCabañas);
                case "4" -> checkOutCabaña(usuarioIngresado, listCabañas);

            }

        } while (!seleccion.equals("0"));

    }

    public static void OpcionesMenuBienvenida() {
        System.out.println("\nBienvenido a R.A \nElija una opcion:");
        System.out.println("[0] Salir\n[1] Iniciar sesión\n[2] Registrar usuario\nQue desea hacer?: ");
    }



}

