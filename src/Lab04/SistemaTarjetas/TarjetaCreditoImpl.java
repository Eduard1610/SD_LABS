package Lab04.SistemaTarjetas;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class TarjetaCreditoImpl extends UnicastRemoteObject implements ITarjetaCredito{
    public TarjetaCreditoImpl() throws RemoteException{
        super();
    }

    @Override
    public boolean validarTarjeta(int numeroTarjeta) throws RemoteException{
        // aleatorio entre 0 y 1 para ver si la tarjeta es válida
        int aleatorio = (int)(Math.random() * 2);
        if(aleatorio == 0){
            return false;
        }
        return true;
    }

    @Override
    public String realizarPago(int numeroTarjeta, int monto) throws RemoteException{
        // se realiza el pago
        return "Pago realizado";
    }

    @Override
    public double consultarSaldo(int numeroTarjeta) throws RemoteException{
        // se consulta el saldo
        return 1000.0;
    }
}
