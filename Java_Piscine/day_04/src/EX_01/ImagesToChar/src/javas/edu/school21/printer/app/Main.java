package javas.edu.school21.printer.app;
import javas.edu.school21.printer.logic.Logic;
//src/it.bmp
public class Main {
    public static void main(String[] args){
        if(args.length!=2 || args[0].length()!=1 || args[1].length()!=1 ){
            System.out.println("Wrong data, use \" . 0 \"");
            System.exit(-1);
        }
        char WHITE_PIXEL =args[0].charAt(0);
        char BLACK_PIXEL =args[1].charAt(0);
        new Logic(WHITE_PIXEL,BLACK_PIXEL).print();
    }
}
