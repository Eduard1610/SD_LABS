package Lab01;
// Definicion de la clase cajera

public class cajera {
    private String nombre;
    public cajera() {}
    public cajera(String nombre) {
        this.nombre = nombre;
    }
    
    // metodos getter y setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // Muestra el tiempo que tarda en procesar la compra de un cliente
    public void procesarCompra(Cliente cliente, long timeStamp) {
        System.out.println("La cajera " + this.nombre +

            " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() +
            " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 +
            "seg");

        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) +

                " ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                "seg");

        }
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
            cliente.getNombre() + " EN EL TIEMPO: " +
            (System.currentTimeMillis() - timeStamp) / 1000 + "seg");

    }

    // Metodo para pausar la ejecucion del programa durante x segundos
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}