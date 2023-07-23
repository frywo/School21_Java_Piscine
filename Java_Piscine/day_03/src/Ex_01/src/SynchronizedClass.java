public class SynchronizedClass {

    public synchronized void put() {
        try {
            notify();
            wait();
        } catch (InterruptedException e){
            System.out.println("error wait!");
        }

    }
}
