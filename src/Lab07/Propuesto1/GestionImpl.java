package wscompras;

import javax.jws.WebService;
import java.util.Scanner;

@WebService(endpointInterface = "com.wscompras.IGestion")
public class GestionImpl implements IGestion {

    private Producto[] productos;
    private double caja;

    public GestionImpl() {
    }

    public GestionImpl(Producto[] productos) {
        this.productos = productos;
    }

    @Override
    public int menu() {
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Mostrar productos");
        System.out.println("2. Comprar producto");
        System.out.println("3. Mostrar caja");
        System.out.println("4. Salir");
        System.out.println("Ingrese opción:");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        sc.close();
        return opcion;
    }

    @Override
    public Producto[] cargarProductos() {
        return productos;
    }

    @Override
    public void mostrarProductos(Producto[] productos) {
        for (Producto producto : productos) {
            producto.mostrar();
        }
    }

    @Override
    public double comprarProducto(Producto[] productos, int num, int cantidadUnidades) {
        if (productos[num - 1].disponible()) {
            if (productos[num - 1].getStock() >= cantidadUnidades) {
                productos[num - 1].setStock(productos[num - 1].getStock() - cantidadUnidades);
                caja += productos[num - 1].getPrecioUnitario() * cantidadUnidades;
                System.out.println("¡Compra realizada con éxito!");
                return caja;
            } else {
                System.out.println("No hay suficiente stock");
                return 0;
            }
        } else {
            System.out.println("Este artículo no está disponible");
            return 0;
        }
    }

    @Override
    public double mostrarCaja() {
        System.out.println("Total de caja: " + caja);
        caja = Math.round(caja * 100) / 100;
        return caja;
    }
}