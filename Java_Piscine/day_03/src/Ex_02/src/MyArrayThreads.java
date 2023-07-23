public class MyArrayThreads extends Thread{

    String name;
    int indexStart;

    int indexFinal;

    int[] arr;

    MyArrayThreads(String name, int indexStart, int indexFinal, int[] arr){
        super.setName(name);
        this.indexFinal = indexFinal;
        this.indexStart = indexStart;
        this.name = name;
        this.arr = arr;
    }
    @Override
    public void run(){
        int sum = 0;
        int indexPrint = indexStart;
        for (;indexStart<indexFinal; indexStart++){
            sum+=arr[indexStart];
        }
        System.out.println(this.getName() + ": from " + indexPrint+ " to " +
                indexFinal + " sum is "+ sum);
        Main.sumElements +=sum;
    }
}
