// interfaz para el objeto remoto TarjetaCredito

package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TarjetaCredito extends Remote{
    // métodos para sobrescribir de forma remota
    boolean validarTarjeta(int numeroTarjeta) throws RemoteException;
    String realizarPago(int numeroTarjeta, int monto) throws RemoteException;
    double consultarSaldo(int numeroTarjeta) throws RemoteException;
}
