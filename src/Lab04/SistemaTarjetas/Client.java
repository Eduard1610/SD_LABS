import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class Client {
    public static void main(String[] args){

        // se declara la interfaz remota
        ITarjetaCredito tarjetaCredito = null;

        try{
            // se obtiene la referencia al objeto remoto
            tarjetaCredito = (ITarjetaCredito)Naming.lookup("rmi://localhost/TarjetaCredito");
            // se invoca el método remoto
            boolean tarjetaValida = tarjetaCredito.validarTarjeta(123456789);
            if(tarjetaValida){
                System.out.println("Tarjeta válida");
                // consultar saldo
                double saldo = tarjetaCredito.consultarSaldo(123456789);
                System.out.println("Saldo: " + saldo);
                // realizar pago
                String mensaje = tarjetaCredito.realizarPago(123456789, 500);
                System.out.println(mensaje);
            }else{
                System.out.println("Tarjeta inválida");
            }
        }catch(RemoteException e){
            e.printStackTrace();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(NotBoundException e){
            e.printStackTrace();
        }
    }
}
