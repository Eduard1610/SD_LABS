package Lab01;
// Definiendo la clase Cliente
public class Cliente {
    private String nombre;
    private int[] carroCompra;
    public Cliente() {}
    public Cliente(String nombre, int[] carroCompra) {
        this.nombre = nombre;
        this.carroCompra = carroCompra;
    }

    // metodos getter y setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int[] getCarroCompra() {
        return carroCompra;
    }
    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }
}