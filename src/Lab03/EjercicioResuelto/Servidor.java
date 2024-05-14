package Lab03.EjercicioResuelto;
import java.io.*;
import java.net.*;
public class Servidor {
    static final int PUERTO = 5000;
    public Servidor() {
        try {
            ServerSocket skServidor = new ServerSocket(PUERTO); //Crea socket para alojar el servidor
            System.out.println("Escucho el puerto " + PUERTO); //Imprime confirmacion
            for (int numCli = 0; numCli < 3; numCli++) { //Bucle para n clientes
                Socket skCliente = skServidor.accept(); // Crea objeto
                System.out.println("Sirvo al cliente " + numCli); //Lado del server, lista el cliente conectado
                OutputStream aux = skCliente.getOutputStream(); //Optiene el flujo de salida del cliente
                DataOutputStream flujo = new DataOutputStream(aux); //Crea el flujo de datos para el cliente
                flujo.writeUTF("Hola cliente " + numCli); //Escribe en el flujo de salida
                skCliente.close(); //Cierra conexion con cliente
            }
            System.out.println("Demasiados clientes por hoy");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] arg) {
        new Servidor();
    }
}