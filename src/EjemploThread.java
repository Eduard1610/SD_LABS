class CajeraThread extends Thread {
    private String nombre;
    private Cliente cliente;
    private long initialTime;
    public CajeraThread() {}
    public CajeraThread(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public long getInitialTime() {
        return initialTime;
    }
    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public void run() {
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "

            +
            this.cliente.getNombre() + " EN EL TIEMPO: " +
            (System.currentTimeMillis() - this.initialTime) / 1000 +
            "seg");

        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            // Se procesa el pedido en X segundos
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1)

                +
                " del cliente " + this.cliente.getNombre() + "->Tiempo: " +
                (System.currentTimeMillis() - this.initialTime) / 1000 +
                "seg");

        }
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
            this.cliente.getNombre() + " EN EL TIEMPO: " +
            (System.currentTimeMillis() - this.initialTime) / 1000 +
            "seg");

    }
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
class Cliente {
    private String nombre;
    private int[] carroCompra;
    public Cliente() {}
    public Cliente(String nombre, int[] carroCompra) {
        this.nombre = nombre;
        this.carroCompra = carroCompra;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int[] getCarroCompra() {
        return carroCompra;
    }
    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }
}
public class EjemploThread {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {
            2,
            2,
            1,
            5,
            2,
            3
        });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {
            1,
            3,
            5,
            1,
            1
        });
        //Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);
        cajera1.start();
        cajera2.start();
    }
}