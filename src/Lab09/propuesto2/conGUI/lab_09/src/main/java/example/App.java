package example;

import javax.swing.SwingUtilities;
import example.gui.ClaveSeguridad;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClaveSeguridad claveSeguridad = new ClaveSeguridad();
            }
        });
    }
}