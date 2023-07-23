import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class MyDownloadThread extends Thread {

    ConcurrentLinkedQueue<String> queue;
    ConcurrentHashMap<String,String> map;
    int treadIndex;


    MyDownloadThread(ConcurrentLinkedQueue<String> queue, ConcurrentHashMap<String, String> map, int threadIndex){
        this.map = map;
        this.queue = queue;
        this.treadIndex = threadIndex;
    }

    @Override
    public void run(){
        if(queue.peek()!=null) {
            String url = queue.poll();
            String fileNumber =  map.remove(url);

            System.out.printf("Thread-%d start download file number %s\n", treadIndex, fileNumber );
            String fileName = url.substring(url.lastIndexOf('/')+1);
            try(InputStream inputStream = new URL(url).openStream()){
                Files.copy(inputStream, Paths.get(fileName), REPLACE_EXISTING);
            }catch (FileAlreadyExistsException e){
                System.out.println("This file: "+e.getMessage()+ " already exists!");
            } catch (IOException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }
            System.out.printf("Thread-%d finish download file number %s\n", treadIndex, fileNumber );
        }
    }
}
