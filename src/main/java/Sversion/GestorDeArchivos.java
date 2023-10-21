package Sversion;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

public class GestorDeArchivos {

    public void CrearCarpeta(String Nombre){
        File Carpeta = new File(Nombre);
        Carpeta.mkdirs();
    }

    public void eliminarArchivo(File file){
        file.delete();
    }

    public File[] listaArchivos(String carpeta){
        CrearCarpeta(carpeta);
        File Carpeta = new File(carpeta);
        return Carpeta.listFiles();
    }

    private static String getExtension(File file) {
        String fileName = file.getName();
        int extension = fileName.lastIndexOf('.');
        return (extension == -1) ? "" : fileName.substring(extension);
    }

    //Archivos Json

    public Boolean esArchivoJson(File ruta) {
        return getExtension(ruta).equals(".json");
    }

    public Boolean archivoJsonExiste(String carpeta, String archivo){
        File Archivo = new File(carpeta + File.separator + archivo + ".json");
        return Archivo.exists();
    }

    public void escribirArchivoJSON(String carpeta, String Nombre, JSONObject json){
        CrearCarpeta(carpeta);

        try (FileWriter file = new FileWriter(carpeta + File.separator + Nombre + ".json")) {
            file.write(json.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject leerArchivoJson(String ruta){
        BufferedReader Lector;
        JSONObject Contenido = new JSONObject();

        try {
            Lector = new BufferedReader(new FileReader(ruta));
            Contenido = new JSONObject(Lector.readLine());
            Lector.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
        return Contenido;
    }

    public ArrayList<JSONObject> listaArchivosJson(String carpeta) {
        ArrayList<JSONObject> listaArchivosJson = new ArrayList<>();
        for (File archivo : listaArchivos(carpeta)){
            if   (esArchivoJson(archivo)){
                listaArchivosJson.add(leerArchivoJson(archivo.toString()));
            }
        }
        return  listaArchivosJson;
    }


    //Usuario
    public JSONObject crearNewAJSONUsuario(String Usuario, String Celular, String Contraseña) {
        JSONObject usr = new JSONObject();
        usr.put("Usuario", Usuario);
        usr.put("Celular", Celular);
        usr.put("Contraseña", Contraseña);
        escribirArchivoJSON("Usuarios", Usuario, usr);
        return usr;
    }

    public boolean usuarioExiste(String usuario) {
        return archivoJsonExiste("Cliente", usuario + ".json");
    }

    public ArrayList<JSONObject> listaJsonCliente(){
        return  listaArchivosJson("Cliente");
    }

    //Cabaña
    public JSONObject crearNewAJsonCabaña(int id, String nombre, int habitaciones, int baños){
        JSONObject cabaña = new JSONObject();
        cabaña.put("Id", id);
        cabaña.put("Nombre", nombre);
        cabaña.put("Habitaciones", habitaciones);
        cabaña.put("Baños", baños);
        cabaña.put("isOcupada", false);
        cabaña.put("Arrendatario", 0);
        escribirArchivoJSON("Usuarios", Integer.toString(id), cabaña);
        return cabaña;
    }

    public JSONObject modAJsonCabaña(int id, String nombre, int habitaciones,
                                     int baños, Boolean estado, Cliente cliente){
        JSONObject cabaña = new JSONObject();
        cabaña.put("Id", id);
        cabaña.put("Nombre", nombre);
        cabaña.put("Habitaciones", habitaciones);
        cabaña.put("Baños", baños);
        cabaña.put("isOcupada", estado);
        cabaña.put("Arrendatario", cliente);
        escribirArchivoJSON("Usuarios", Integer.toString(id), cabaña);
        return cabaña;
    }

    public ArrayList<JSONObject> listaJsonCabañas(){
        return  listaArchivosJson("Cabañas");
    }
//

}
