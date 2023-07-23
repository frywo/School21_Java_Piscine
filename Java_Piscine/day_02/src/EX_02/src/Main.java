import java.io.File;

public class Main {
    //--current-folder=
    public static void main(String[] args) {

        if(args.length == 1){
           String[] path = args[0].split("=");
           if(!path[0].equals("--current-folder") || !(new File(path[1]).exists())){
               System.out.println("Wrong arguments!");
               System.exit(-1);
           }
           Menu menu = new Menu(path[1]);
           menu.start();


        } else {
            System.out.println("Wrong argument!");
            System.exit(-1);
        }
    }
}