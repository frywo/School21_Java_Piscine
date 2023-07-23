import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu {
    private String currentPath;
    private final Scanner scanner = new Scanner(System.in);
    Menu(String currentPath){
        this.currentPath = currentPath;
    }
    public void start(){
        System.out.println(currentPath);
        String input;
        while (!(input = scanner.nextLine()).equals("exit")){
            String[] args = input.split(" ");
            switch (args[0]){
                case "ls":
                    if(args.length == 1) {
                        ls();
                    } else {
                        System.out.println("Wrong number of arguments");
                    }
                    break;
                case "mv":
                    if(args.length == 3){
                        mv(args[1], args[2]);
                    } else {
                        System.out.println("Wrong number of arguments");
                    }
                    break;
                case "cd":
                    if(args.length == 2){
                        cd(args[1]);
                    } else {
                        System.out.println("Wrong number of arguments");
                    }
                    break;
            }
        }
    }

    private void ls(){
            File [] files = new File(currentPath).listFiles();
            if(files == null){
                File qwe =  new File(currentPath);
                System.out.println(qwe.getName() + " " +  qwe.length()/1024 + " KB");
                return;
            }
            for(File file: files){
                System.out.println(file.getName() + " " + fileWeight(file,0)/1024 + " KB");
            }
    }

    private long fileWeight(File inputFile, long weight){
        long res = weight;

        File[] files = inputFile.listFiles();

        if(files == null){
            return inputFile.length() + weight;
        }


        for(File file : files){
            if(!file.isDirectory()){
                res += file.length();
            } else {
                fileWeight(file,res);
            }
        }
        return res;
    }

    private void mv(String fromFile, String toFile){
        Path current = Paths.get(currentPath);
        Path from = current.resolve(Paths.get(fromFile));
        Path to = current.resolve(Paths.get(toFile));
        String[] isName = toFile.split("/");

        if(!Files.isDirectory(from) && isName.length == 1 && !isName[0].equals("..")){
            File oldName =  new File(from.toString());
            File newName =  new File(to.toString());
            oldName.renameTo(newName);
        } else {
            for (String s : isName) {
                if (s.equals("..")) {
                    current = current.getParent();
                } else {
                    current = current.resolve(s);
                }
            }
            current = current.resolve(fromFile);
            try {
                Files.move(from,current);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }


        }


    }
    private void cd(String path){
        String[] folders = path.split("/");
        Path current = Paths.get(currentPath);
        for (String folder: folders){
            if(folder.equals("..")){
               current = current.getParent();
            } else {
                current = current.resolve(folder);
            }
        }
        if(current.toFile().isDirectory()){
            currentPath = current.toString();
            System.out.println(currentPath);
        } else {
            System.out.println("Wrong path!");
        }
    }
}
