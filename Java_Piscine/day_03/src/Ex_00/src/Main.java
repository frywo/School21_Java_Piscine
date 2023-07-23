public class Main {
    public static void main(String[] args) {
        String[] count = args[0].split("=");
        int cnt = Integer.parseInt(count[1]);
        MyThread egg = new MyThread("Egg", cnt);
        MyThread Hen = new MyThread("Hen", cnt);
        egg.start();
        Hen.start();
        try {
            egg.join();
            Hen.join();
        } catch (InterruptedException e){
            System.out.println("error");
        }
        for (int i = 0; i<cnt; ++i){
            System.out.println("Human");
        }
    }
}
class MyThread extends Thread{

    int count;
    String name;
    MyThread(String name, int count){
        this.name = name;
        this.count = count;
    }

    @Override
    public void run(){
        for (int i = 0; i<count;++i){
            System.out.println(name);
        }
    }
}