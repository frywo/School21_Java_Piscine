import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Signatures {

    private static final HashMap<String,String> mapSignatures = new HashMap<>();
    private static String outcome;

    public static void getSignaturesFromFile(BufferedReader bufferedReader){
        String stringInput;
        try {
            while ((stringInput = bufferedReader.readLine()) != null){
                String [] args = stringInput.split(", ");
                mapSignatures.put(args[0],args[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean CheckInputSignature(BufferedInputStream input){
        ArrayList<Integer> arrayListHexBytes = new ArrayList<>();
        try {
            int j;
            for (int i = 0; (j = input.read()) !=-1 && i< 8; ++i ){
                arrayListHexBytes.add(j);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return compareInputAndSignature(arrayListHexBytes);
    }

    private static boolean compareInputAndSignature(ArrayList<Integer> list){
        boolean res = false;
        for (Map.Entry<String, String> item: mapSignatures.entrySet()){
            String[] bytesSignature = item.getValue().split(" ");
            int check = 0;

            for (int i = 0; i<bytesSignature.length; ++i){
                if(Integer.parseInt(bytesSignature[i],16) == list.get(i)){
                    check++;
                }
            }

            if (check == bytesSignature.length){
                res = true;
                outcome = item.getKey();
                break;
            }
        }
        return res;
    }

    public static void writeInFile(String name){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(name, true))) {
            writer.write(outcome + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
