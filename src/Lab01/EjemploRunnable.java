package Lab01;
public class EjemploRunnable implements Runnable {
    private Cliente cliente;
    private cajera cajera;
    private long initialTime;
    public EjemploRunnable(Cliente cliente, cajera cajera, long initialTime) {
        this.cajera = cajera;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    @Override
    public void run() {
        this.cajera.procesarCompra(this.cliente, this.initialTime);
    }
}
