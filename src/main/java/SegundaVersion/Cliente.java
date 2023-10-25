package SegundaVersion;
import org.json.JSONObject;
public class Cliente {
    //Atributros:
    private String usuario;
    private String contraseña;
    private int celular;

    //Constructores:
    public Cliente(String usuario, String contraseña, int celular) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.celular = celular;
    }
    //Metodos getter:
    public String getUsuario() {
        return usuario;
    }
    public String getContraseña(){
        return this.contraseña;
    }

    public JSONObject clienteToJson(){
        JSONObject json = new JSONObject();
        json.put("usuario" , this.usuario);
        json.put("celular", this.celular);
        json.put("contraseña", this.contraseña);
        return json;
    }
}