package Sversion;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

public class GestorDeArchivos {



    public void crearCarpeta(String Nombre){
        File Carpeta = new File(Nombre);
        Carpeta.mkdirs();
    }

    public void eliminarArchivo(File file){
        file.delete();
    }

    public File[] listaArchivos(String carpeta){
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

    public Boolean esArchivoJson(File ruta) {
        return getExtension(ruta).equals(".json");
    }

    public Boolean archivoJsonExiste(String carpeta, String archivo){
        File Archivo = new File(carpeta + File.separator + archivo + ".json");
        return Archivo.exists();
    }

    public void escribirArchivoJSON(String carpeta, String Nombre, JSONObject json){
        crearCarpeta(carpeta);

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
    public Boolean usuarioExiste(String usuario) {
        return archivoJsonExiste("Cliente", usuario + ".json");
    }

    public ArrayList<JSONObject> listaJsonCliente(){
        return  listaArchivosJson("Cliente");
    }

    //Cabaña

    public ArrayList<JSONObject> listaJsonCabañas(){
        return  listaArchivosJson("Cabañas");
    }

}
