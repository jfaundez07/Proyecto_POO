import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    ArrayList<JSONObject> ListaCabañas = new ArrayList<>();
    ArrayList<JSONObject> ListaUsuarios = new ArrayList<>();
    JSONObject Usuario = new JSONObject();
    JSONObject Usuario2 = new JSONObject();
    JSONObject cabana1 = new JSONObject();
    JSONObject cabana2 = new JSONObject();

    String expectedOutput =
            "\n" +
                    "Id: 1\n" +
                    "Nombre: Cabaña 1\n" +
                    "Cantidad de habitaciones: 2\n" +
                    "Cantidad de baños: 1\n" +
                    "Esta ocupada: false\n";

    String textmostrarCabaña;


    @BeforeEach
    void setUp() {
        Usuario.put("Usuario", "Javier");
        Usuario.put("Celular", "962336730");
        Usuario.put("Contraseña", "987654321");
        ListaUsuarios.add(Usuario);
        Usuario2.put("Usuario", "Klima");
        Usuario2.put("Celular", "978356652");
        Usuario2.put("Contraseña", "135792468");
        ListaUsuarios.add(Usuario2);

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

        Main.agregarCabaña(ListaCabañas, cabana1);
        Main.agregarCabaña(ListaCabañas, cabana2);

        try {
            textmostrarCabaña = SystemLambda.tapSystemOut(() -> {
                Main.mostrarCabaña(cabana1);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validarUsuarioCorrecto() {
        assertTrue(Main.validarUsuario(ListaUsuarios, "Javier", "987654321"));
        assertTrue(Main.validarUsuario(ListaUsuarios, "Klima", "135792468"));
    }

    @Test
    void validarUsuarioIncorrecto(){
        assertFalse(Main.validarUsuario(ListaUsuarios, "Javier", "123456789"));
        assertFalse(Main.validarUsuario(ListaUsuarios, "Klima", "123456789"));
        assertFalse(Main.validarUsuario(ListaUsuarios, "Xiao", "123456789"));
    }

    @Test
    void PosicionUsuario(){
        assertEquals( 0,Main.obtenerPosicionUsuario(ListaUsuarios, "Javier", "987654321"));
        assertEquals( 1,Main.obtenerPosicionUsuario(ListaUsuarios, "Klima", "135792468"));
    }

    @Test
    public void testAgregarUsuarios() {
        ArrayList<JSONObject> listaUs = new ArrayList<>();
        Main.agregarUsuario(listaUs, Usuario);

        assertEquals(1, listaUs.size());
    }

    @Test
    public void testCrearUsuarios() {
        ArrayList<JSONObject> listaUs = new ArrayList<>();
        Main.crearUsuarios(listaUs);

        assertEquals(2, listaUs.size());
    }

    @Test
    public void testCrearCabañas() {
        ArrayList<JSONObject> listaCabañas = new ArrayList<>();
        Main.crearCabañas(listaCabañas);

        assertEquals(2, listaCabañas.size());
    }

    @Test
    public void testAgregarCabaña() {
        ArrayList<JSONObject> listaCa = new ArrayList<>();
        Main.agregarCabaña(listaCa, cabana1);

        assertEquals(1, listaCa.size());
    }

    @Test
    public void mostrarcabañasExistente(){
        expectedOutput = expectedOutput.replace("\r\n", "\n");
        textmostrarCabaña = textmostrarCabaña.replace("\r\n", "\n");

        assertEquals(expectedOutput, textmostrarCabaña);
    }
}