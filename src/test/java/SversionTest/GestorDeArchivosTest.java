package SversionTest;
import SegundaVersion.GestorDeArchivos;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorDeArchivosTest {

    GestorDeArchivos gestor = new GestorDeArchivos();
    public void eliminarCarpeta(File carpeta) {
        File[] archivos = carpeta.listFiles();
        if(archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    eliminarCarpeta(archivo);
                } else {
                    archivo.delete();
                }
            }
        }
        carpeta.delete();
    }

    String carpeta;
    String archivo;
    JSONObject json;
    ArrayList<JSONObject> listaArchivosJson;
    JSONObject contenido;

    @BeforeEach
    void crear(){
        carpeta = "carpetaPrueba";
        archivo = "archivoPrueba";
        json = new JSONObject();
        json.put("clave", "valor");
    }

    @Test
    void testEsArchivoJson() {
        File archivo = new File("prueba.json");
        assertTrue(gestor.esArchivoJson(archivo));
    }

    @Test
    void testArchivoJsonExiste() {
        gestor.escribirArchivoJSON(carpeta, archivo, json);
        assertTrue(gestor.archivoJsonExiste(carpeta, archivo));
    }

    @Test
    void testLeerArchivoJson() {
        contenido = gestor.leerArchivoJson(carpeta + File.separator + archivo + ".json");
        assertEquals("valor", contenido.get("clave"));
    }

    @Test
    void testListaArchivosJson() {
        listaArchivosJson = gestor.listaArchivosJson(carpeta);
        assertFalse(listaArchivosJson.isEmpty());
    }
    

}