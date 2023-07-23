import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String SIGNATURES = "C:\\Users\\User\\Desktop\\JAVA_Piscine\\day_02\\EX_00\\signatures.txt";
    private static final String RESULT = "result.txt";
    public static void main(String[] args) throws IOException {

        String string="";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SIGNATURES))){

            Signatures.getSignaturesFromFile(bufferedReader);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }


        Scanner input = new Scanner(System.in);

        while (!string.equals("42")){
            string = input.nextLine();
            try(BufferedInputStream dataFile = new BufferedInputStream(new FileInputStream(string))){
                if(Signatures.CheckInputSignature(dataFile)){
                    System.out.println("PROCESSED");
                    Signatures.writeInFile(RESULT);
                } else {
                    System.out.println("UNDEFINED");
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }
}