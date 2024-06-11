package wscompras;

import java.io.Serializable;

//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlValue;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private double precioUnit;
    private int cantStock;
    private boolean disponible = false;
    public static int dimesionArray;

    public Producto() {
    }

    public Producto(String nombre, double precioUnit, int cantStock, boolean disponible) {
        this.nombre = nombre;
        this.precioUnit = precioUnit;
        this.cantStock = cantStock;
        this.disponible = disponible;
        dimesionArray++;
    }
    public void mostrar() {
        System.out.println("Nombre: " + nombre + "\nPrecio unitario: " + precioUnit + "\nStock: " + cantStock);
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecioUnitario() {
        return precioUnit;
    }
    public void setPrecioUnitario(double precio) {
        this.precioUnit = precio;
    }
    public int getStock() {
        return cantStock;
    }
    public void setStock(int stock) {
        this.cantStock = stock;
    }
    public boolean disponible() {
        if(getStock() > 0)
            this.disponible = true;
        return this.disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }   
}