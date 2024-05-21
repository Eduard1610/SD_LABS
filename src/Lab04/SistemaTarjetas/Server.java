package Lab04.SistemaTarjetas;
import java.rmi.Naming;

public class Server {

    public Server () {
        // se publica el objeto remoto
        try {
            ITarjetaCredito tarjetaCredito = new TarjetaCreditoImpl();
            Naming.rebind("rmi://localhost:1099/Service", tarjetaCredito);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}