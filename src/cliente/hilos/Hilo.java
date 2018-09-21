package cliente.hilos;

/**
 * Funcion para implementar hilos
 * 
 * @author daniel
 */
public class Hilo implements Runnable {

    private String nombre;
    private long slpTime;
    private Writer writer;
    
    /**
     * Constructor que asigna nombre a hilo
     * 
     * @param nombre
     * @param slpTime 
     */
    public Hilo(String nombre, long slpTime, Writer writer) {
        this.nombre = nombre;
        this.slpTime = slpTime;
        this.writer = writer;
    }
    
    
    
    @Override
    public void run() {
        
        Thread.currentThread().setName(this.nombre);
        
        for (int i = 10; i > 0; i--) {
            //System.out.println(Thread.currentThread().getName() + " - " + i);
            this.writer.write(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(this.slpTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
