import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    //--threadsCount=3
    private static final  String nameFile = "C:/Users/User/Desktop/JAVA_Piscine/day_03/EX_03/src/files_urls.txt";
    private static final ConcurrentHashMap<String,String> mapURL = new ConcurrentHashMap<>();
    private static final ConcurrentLinkedQueue<String> queueURL = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {



        if(args.length != 1){
            System.out.println("Wrong number of arguments!");
            System.exit(-1);
        }
        String[] str = args[0].split("=");

        if(!str[0].equals("--threadsCount")){
            System.out.println("Wrong data!");
            System.exit(-1);
        }

        int threadsCount = 0;
        try {
            threadsCount = Integer.parseInt(str[1]);
        } catch (NumberFormatException e){
            System.out.println("Error, wrong format! " + e.getMessage());
            System.exit(-1);
        }

        try (BufferedReader stringURL = new BufferedReader(new FileReader(nameFile));){
            String URL;
            while ((URL = stringURL.readLine())!= null){
                String[] fileStr = URL.split(" ");
                mapURL.put(fileStr[1],fileStr[0]);
                queueURL.add(fileStr[1]);

            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        MyDownloadThread[] qwe = new MyDownloadThread[threadsCount];
        for(int i = 1; i<=threadsCount; ++i){
            qwe[i-1] = new MyDownloadThread(queueURL, mapURL, i);
            qwe[i-1].start();
        }

        while (!queueURL.isEmpty()){
            for(int i =0; i< threadsCount; ++i){
                if(!qwe[i].isAlive()){
                    qwe[i].run();
                }
            }
        }




    }
}