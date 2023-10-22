package SegundaVersion;

public class Launcher {
    public static void main(String[] args) {
        SistemaReservas s = new SistemaReservas();
        s.rellenarListaCabañas();//para crear lor archivos por defecto, pero solo por mientras.
        Menu lanzar = new Menu();
        lanzar.MenuBienvenida();
    }
}