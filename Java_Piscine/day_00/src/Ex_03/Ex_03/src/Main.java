import java.util.Scanner;

public class Main {
    private static final String WEEKS = "Week ";
    private static final String END = "42";

    public static void main(String[] args) {
        int min;
        int pack = 0;
        Scanner strings = new Scanner(System.in);
        String week = strings.nextLine();
        for (int i = 1; i < 19 && !week.equals(END); ++i){
            if (!week.equals(WEEKS+i)){
                System.err.print("IllegalArgument");
                System.exit(-1);
            }
            min = getMin(strings);
            week = strings.nextLine();
            pack+= packNumber(i, min);

        }
        unpackNumber(pack);

    }
    private static int getMin(Scanner strings){
        int min = strings.nextInt();
        int cur;
        for (int i = 0; i < 4; ++i){
            cur = strings.nextInt();
            min = (min < cur) ? min : cur;
        }
        strings.nextLine();
        return min;
    }
    private static int packNumber(int i, int min) {
        int ten = 1;
        for(int j = 1; j < i; ++j){
            ten*=10;
        }
        return ten * min;
    }
    private  static void unpackNumber(int pack){
        int digit = pack%10;
        int counter = 1;
        while (pack !=0) {
            System.out.print("Week "+ counter + " ");
            while (digit!=0){
                digit--;
                System.out.print("=");
            }
            System.out.print(">\n");
            ++counter;
            pack/=10;
            digit = pack%10;
        }
    }
}