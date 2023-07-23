public class Main {
    public static void main(String[] args) {
        String[] count = args[0].split("=");
        int cnt = Integer.parseInt(count[1]);
        SynchronizedClass sC = new SynchronizedClass();
        MyThread egg = new MyThread("Egg111", cnt, sC);
        MyThread Hen = new MyThread("Hen222222", cnt, sC);
        egg.start();
        Hen.start();
        try {
            egg.join();
            Hen.join();
        } catch (InterruptedException e){
            System.out.println("error");
        }

    }
}
class MyThread extends Thread{

    SynchronizedClass Sc;
    int count;
    String name;
    MyThread(String name, int count, SynchronizedClass Sc){
        this.name = name;
        this.count = count;
        this.Sc = Sc;
    }

    @Override
    public void run(){
        for (int i = 0; i<count;++i){
            System.out.println(name);
            Sc.put();
        }
    }

}