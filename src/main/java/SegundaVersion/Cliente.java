package SegundaVersion;

public class Cliente {
    private String usuario;
    private String contraseña;
    private int celular;

    public Cliente(String usuario, String contraseña, int celular) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.celular = celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getCelular() {
        return celular;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }


}
