package SegundaVersion;

public class Launcher {
    public static void main(String[] args) {
        SistemaReservas s = new SistemaReservas();
        for(Cliente c : s.getListaClientes()){
            System.out.println(c.getUsuario());
        }
        for(Cabaña b : s.getListaCabañas()){
            System.out.println(b.getArrendatario());
            System.out.println(b);
        }
    }
}