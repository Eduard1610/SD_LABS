// interfaz para el objeto remoto TarjetaCredito


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITarjetaCredito extends Remote{
    // métodos para sobrescribir de forma remota
    boolean validarTarjeta(int numeroTarjeta) throws RemoteException;
    String realizarPago(int numeroTarjeta, int monto) throws RemoteException;
    double consultarSaldo(int numeroTarjeta) throws RemoteException;
}