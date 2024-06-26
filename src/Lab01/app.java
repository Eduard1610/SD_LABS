package Lab01;
public class app {
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
        cajera cajera1 = new cajera("Cajera 1");
        cajera cajera2 = new cajera("Cajera 2");
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        Runnable proceso1 = new EjemploRunnable(cliente1, cajera1, initialTime);
        Runnable proceso2 = new EjemploRunnable(cliente2, cajera2, initialTime);
        new Thread(proceso1).start();
        new Thread(proceso2).start();
    }
}
