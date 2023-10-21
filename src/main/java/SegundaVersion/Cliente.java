package SegundaVersion;

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

}
