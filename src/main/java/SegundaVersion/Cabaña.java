package SegundaVersion;

public class Cabaña {

    //Atributos:

        private int id;
        private String nombre;
        private int habitaciones;
        private int baños;
        private boolean isOcupada;
        private int arrendatario;

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

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String Nombre) {
            this.nombre = Nombre;
        }

        public boolean isOcupada() {
            return isOcupada;
        }

        public void setIsOcupada(boolean isOcupada) {
            this.isOcupada = isOcupada;
        }

        public int getArrendatario() {
            return arrendatario;
        }

        public void setArrendatario(int Arrendatario) {
            this.arrendatario = Arrendatario;
        }

        public void mostrarCabaña() {

            System.out.println();
            System.out.println("Id: " + this.id);
            System.out.println("Nombre: " + this.nombre);
            System.out.println("Cantidad de habitaciones: " + this.habitaciones);
            System.out.println("Cantidad de baños: " + this.baños);
            System.out.println("Esta ocupada: " + this.isOcupada);
        }

}
