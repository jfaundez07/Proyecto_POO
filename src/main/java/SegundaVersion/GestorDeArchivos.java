package SegundaVersion;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

public class GestorDeArchivos {

    private void crearCarpeta(String Nombre){
        File Carpeta = new File(Nombre);
        Carpeta.mkdirs();
    }

    private void eliminarArchivo(File file){
        file.delete();
    }

    private File[] listaArchivos(String carpeta){
        crearCarpeta(carpeta);
        File Carpeta = new File(carpeta);
        return Carpeta.listFiles();
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        int extension = fileName.lastIndexOf('.');
        return (extension == -1) ? "" : fileName.substring(extension);
    }

    //Archivos Json

    //Metodo que verifica que un archivo sea .json
    private Boolean esArchivoJson(File ruta) {
        return getExtension(ruta).equals(".json");
    }

    //Metodo que verifica si existe un .json en especifico
    public Boolean archivoJsonExiste(String carpeta, String archivo){
        File Archivo = new File(carpeta + File.separator + archivo + ".json");
        return Archivo.exists();
    }

    //Metodo que toma un .json (si no existe crea el .json) y sobreescribe en su interior el contenido de un objeto json
    public void escribirArchivoJSON(String carpeta, String Nombre, JSONObject json){
        crearCarpeta(carpeta);

        try (FileWriter file = new FileWriter(carpeta + File.separator + Nombre + ".json")) {
            file.write(json.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo que toma un archivo .json y regresa un objeto json
    private JSONObject leerArchivoJson(String ruta){
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

    // Metodo que regresa una lista de jsons con todos los archivos .json dentro de una carpeta
    private ArrayList<JSONObject> listaArchivosJson(String carpeta) {
        ArrayList<JSONObject> listaArchivosJson = new ArrayList<>();
        for (File archivo : listaArchivos(carpeta)){
            if   (esArchivoJson(archivo)){
                listaArchivosJson.add(leerArchivoJson(archivo.toString()));
            }
            else{
                eliminarArchivo(archivo);
            }
        }
        return  listaArchivosJson;
    }


    //Usuario:

    //Metodo que verifica si un .json con el nombre del usuario existe
    public Boolean usuarioExiste(String usuario) {
        return archivoJsonExiste("Cliente", usuario);
    }

    //Metodo que regresa todos los usuarios dentro de una carpeta como un array de jsons
    public ArrayList<JSONObject> listaJsonCliente(){
        return  listaArchivosJson("Cliente");
    }

    //Cabaña:

    //Metodo que regresa todos los cabañas dentro de una carpeta como un array de jsons
    public ArrayList<JSONObject> listaJsonCabañas(){
        return  listaArchivosJson("Cabañas");
    }

}