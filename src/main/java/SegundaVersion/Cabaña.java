package SegundaVersion;

import org.json.JSONObject;

public class Cabaña {

    //Atributos:

    private int id;
    private String nombre;
    private int habitaciones;
    private int baños;
    private boolean isOcupada;
    private Cliente arrendatario;

    //Constructores:

    //Este contructor es para instanciar el objeto a partir de un Json:
    public Cabaña(int id, String nombre, int habitaciones, int baños, boolean isOcupada, Cliente arrendatario) {
        this.id = id;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.isOcupada = isOcupada;
        this.arrendatario = arrendatario;
    }

    //ESte contructor es para instanciar el objetos en la ejecucion del programa:
    public Cabaña(int id, String nombre, int habitaciones, int baños) {
        this.id = id;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.isOcupada = false;
    }


    //Metodos getter:

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public int getBaños() {
        return baños;
    }

    public Cliente getArrendatario() {
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

    public void setArrendatario(Cliente Arrendatario) {
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

    //Transforma el objeto cabaña a objeto json
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("id" , this.id);
        json.put("nombre", this.nombre);
        json.put("habitaciones", this.habitaciones);
        json.put("baños", this.baños);
        json.put("isOcupada", this.isOcupada);
        if (this.arrendatario != null) {
            json.put("arrendatarios", this.arrendatario.getUsuario());}
        return json;
    }

}
