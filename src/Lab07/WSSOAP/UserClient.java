package WSSOAP;

import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import java.net.URL;

public class UserClient {
    public static void main(String[] args) throws Exception {
        // URL del servicio WSDL
        URL url = new URL("http://localhost:1516/WS/Users?wsdl");

        // Namespace y nombre del servicio
        QName qname = new QName("http://WSSOAP/", "SOAPImplService");

        // Crear el servicio
        Service service = Service.create(url, qname);

        // Obtener el puerto del servicio
        SOAPI soap = service.getPort(SOAPI.class);

        // Llamar a los métodos del servicio
        List<User> users = soap.getUsers();
        for (User user : users) {
            System.out.println("Name: " + user.getName() + ", Username: " + user.getUsername());
        }

        // Añadir un nuevo usuario
        User newUser = new User("Nuevo", "Usuario");
        soap.addUser(newUser);

        // Mostrar la lista de usuarios actualizada
        List<User> updatedUsers = soap.getUsers();
        System.out.println("\nUsuarios actualizados:");
        for (User user : updatedUsers) {
            System.out.println("Name: " + user.getName() + ", Username: " + user.getUsername());
        }
    }
}
