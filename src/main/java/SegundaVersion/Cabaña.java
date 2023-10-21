package SegundaVersion;

public class Cabaña {

    //Atributos:

    private int id;
    private String nombre;
    private int habitaciones;
    private int baños;
    private boolean isOcupada;
    private int arrendatario;

    //COnstructores:

    public Cabaña(int id, String nombre, int habitaciones, int baños, boolean isOcupada, int arrendatario) {
        this.id = id;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.isOcupada = isOcupada;
        this.arrendatario = arrendatario;
    }

    public Cabaña() {
        this.id = 0;
        this.nombre = "";
        this.habitaciones = 0;
        this.baños = 0;
        this.isOcupada = false;
        this.arrendatario = 0;
    }

    //Metodos getter:

    public int getId() {
        return id;
    }

    public int getArrendatario() {
        return arrendatario;
    }

    public boolean getIsOcupada() {
        return isOcupada;
    }

    //Metodos setter:

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setIsOcupada(boolean isOcupada) {
        this.isOcupada = isOcupada;
    }

    public void setArrendatario(int Arrendatario) {
        this.arrendatario = Arrendatario;
    }

    //Metodos de la clase:

    public void mostrarCabaña() {

        System.out.println();
        System.out.println("Id: " + this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Cantidad de habitaciones: " + this.habitaciones);
        System.out.println("Cantidad de baños: " + this.baños);
        System.out.println("Esta ocupada: " + this.isOcupada);
    }

}
