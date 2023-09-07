import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class txt {
    public static void main(String[] args) {
        CrearCarpeta("TXT");
    }


    public static void CrearCarpeta(String Nombre){
        File Carpeta = new File(Nombre);
        Carpeta.mkdirs();
    }

    public static void EliminarCarpeta(String Nombre){
        File Carpeta = new File(Nombre);
        Carpeta.delete();
    }

    public static File[] ListaArchivos(String carpeta){
        CrearCarpeta(carpeta);
        File Carpeta = new File(carpeta);
        return Carpeta.listFiles();
    }

    public static void MostrarCarpeta(String carpeta){
        for (int i = 0; i < ListaArchivos(carpeta).length; i++) {
            if (ListaArchivos(carpeta)[i].isFile()) {
                System.out.println( (i+1) + " " + ListaArchivos(carpeta)[i].getName());
            }
        }
    }


    public static void CrearTXT(String carpeta, String NombreTXT){
        CrearCarpeta(carpeta);//Crea la carpeta si no existe
        File Archivo = new File(carpeta, NombreTXT + ".txt");
        try {
            Archivo.createNewFile();
        } catch (IOException error) {//Exception en caso de que no se cree correctamente el archivo. (o eso entendi)
            error.printStackTrace();
        }
    }

    public static void EliminarTXT(String carpeta, String NombreTXT){
        File Archivo = new File(carpeta, NombreTXT + ".txt");
        Archivo.delete();
    }

    public static void LeerTXT(String carpeta, String NombreTXT){
        BufferedReader Lector;
        try {
            Lector = new BufferedReader(new FileReader( carpeta + "/" + NombreTXT + ".txt"));
            String Contenido = Lector.readLine();
            while (Contenido != null) {
                System.out.println(Contenido);
                Contenido = Lector.readLine();
            }
            Lector.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static ArrayList<String> TransformarTXT(String carpeta, String NombreTXT){
        BufferedReader Lector;
        CrearTXT(carpeta, NombreTXT);
        try {
            Lector = new BufferedReader(new FileReader( carpeta + "/" + NombreTXT + ".txt"));
            ArrayList<String> TXT= new ArrayList<String>();
            while (Lector.readLine() != null) {;
                TXT.add(Lector.readLine());
            }
            Lector.close();
            return TXT;
        } catch (IOException error) {
            error.printStackTrace();
        }
        return null;
    }

    public static void EscribirTXT(String carpeta, String NombreTXT, ArrayList<String> contenido){
        BufferedWriter writer;
        CrearTXT(carpeta, NombreTXT);
        try {
            writer = new BufferedWriter(new FileWriter(carpeta + "/" + NombreTXT + ".txt"));
            for (String Dato : contenido) {
                //Cada elemento del arraylist seria una nueva linea del txt
                writer.write(Dato);
                writer.newLine();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}