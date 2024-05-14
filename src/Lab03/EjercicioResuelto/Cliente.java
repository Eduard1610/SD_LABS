package Lab03.EjercicioResuelto;
import java.io.*;
import java.net.*;
public class Cliente {
    static final String HOST = "localhost";
    static final int PUERTO = 5000;
    public Cliente() {
        try {
            Socket skCliente = new Socket(HOST, PUERTO); //Nuevo socket del cliente que conecta con el servidor
            InputStream aux = skCliente.getInputStream(); //Obtener el flujo de entrada del cliente
            DataInputStream flujo = new DataInputStream(aux); //Obtener el flujo de datos del cliente
            System.out.println(flujo.readUTF()); //Leer data bajo estandar utf
            skCliente.close(); //Cierra conexion con cliente
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] arg) {
        new Cliente();
    }
}