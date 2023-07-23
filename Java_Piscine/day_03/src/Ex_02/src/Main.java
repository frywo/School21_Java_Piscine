

public class Main {
    public static int sumElements = 0;

    public static void main(String[] args) {

        String[] arrSize = args[0].split("=");
        String[] threadSize = args[1].split("=");
        int sizeArray = Integer.parseInt(arrSize[1]);
        int sizeThread = Integer.parseInt(threadSize[1]);

        int[] array = new int[sizeArray];
        int sum = 0;

        for (int i = 0; i< array.length; ++i){
           array[i] = (int) (Math.random()*1000);
           sum += array[i];
        }
        System.out.println(sum);

        int sectionSize =  sizeArray/sizeThread;


        for (int i = 0; i < sizeThread; ++i){
            if(i < sizeThread-1){
               MyArrayThreads thread = new MyArrayThreads("Thread "+i, i*sectionSize,
                              (i+1)* sectionSize, array);
               try {
                   thread.start();
                   thread.join();
               } catch (InterruptedException e){
                   System.out.println(e.getMessage());
               }
            } else {
                MyArrayThreads threads = new MyArrayThreads("Thread "+i, i*sectionSize,
                                         array.length, array);
                try {
                    threads.start();
                    threads.join();
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(sumElements);


    }
}