package cliente.hilos;

public class TestThread {

    public static void main(String[] args) {

        Test t1 = new Test("Thread 1");

        Test t2 = new Test("Thread 2");

        new Thread(t1).start();
        new Thread(t2).start();
        
    }
}

class Test implements Runnable {

    String cadena;

    public Test(String cadena) {
        this.cadena = cadena;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(this.cadena);
                Thread.currentThread().sleep(1000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
