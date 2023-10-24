package SegundaVersion;

import org.json.JSONObject;

public class Cliente {

    //Atributros:
    private String usuario;
    private String contraseña;
    private int celular;

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("usuario" , this.usuario);
        json.put("celular", this.celular);
        json.put("contraseña", this.contraseña);
        return json;
    }

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

    public int getCelular() {
        return celular;
    }

    //Metodos setter:

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getContraseña(){
        return this.contraseña;
    }

}