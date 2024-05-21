import java.rmi.Naming;

public class Server {

    public Server () {
        // se publica el objeto remoto
        try {
            TarjetaCreditoImpl tarjetaCredito = new TarjetaCreditoImpl();
            Naming.rebind("rmi://localhost/TarjetaCredito", tarjetaCredito);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
