package cliente.hilos;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Principal {

    public Principal() {
        
        JFrame miVentana = new JFrame("Ejercicio hilos");
        miVentana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        miVentana.setSize(800, 600);
        miVentana.setVisible(true);
    }

    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaHilos vh = new VentanaHilos();
            vh.setVisible(true);
        });
        
        /*long[] timers = {0, 100, 500, 1000};
        for (int i = 1; i <= 3; i++) {
            new Thread(new Hilo("Soy el Hilo " + i, timers[i])).start();
        }*/
    }
}
