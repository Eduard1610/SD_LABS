package wscompras;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import java.net.MalformedURLException;
import java.net.URL;

// Usa el servicio
public class Client {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:1516/WS/Producto?wsdl");

        QName qname = new QName("http://example.com/", "GestionImplService");

        Service service = Service.create(url, qname);

        IGestion gestion = service.getPort(IGestion.class);

        // Llamar a los metodos del servicio
        // Menu de opciones
        System.out.println("¡Bienvenido!\nEsta es la tienda onlina de productos de limpieza.");

        int opcion = gestion.menu();

        while (opcion != 4) {
            opcion = gestion.menu();
            switch (opcion) {
                case 1:
                    gestion.mostrarProductos(gestion.cargarProductos());
                    break;
                case 2:
                    System.out.println("¿Qué producto desea comprar?");
                    int num = gestion.menu();
                    System.out.println("¿Cuántas unidades desea comprar?");
                    int cantidadUnidades = gestion.menu();
                    System.out.println("Total de caja: " + gestion.comprarProducto(gestion.cargarProductos(), num, cantidadUnidades));
                    break;
                case 3:
                    System.out.println("Total de caja: " + gestion.mostrarCaja());
                    break;
                case 4:
                    System.out.println("¡Gracias por su visita!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}