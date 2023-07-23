import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    static HashSet<String> set = new HashSet<>();
    static HashMap<String, Integer> vectorA = new HashMap<>();
    static HashMap<String, Integer> vectorB = new HashMap<>();
    final static String DICTIONARY = "Dictionary.txt";
    public static void main(String[] args) {
        HashMap<String,Integer> hm1 = new HashMap<>();
        HashMap<String,Integer> hm2 = new HashMap<>();


        if (args.length!=2){
            System.out.println("Wrong arguments!");
            System.exit(-1);
        }


        try(BufferedReader bR1 = new BufferedReader(new FileReader(args[0]));
            BufferedReader bR2 = new BufferedReader(new FileReader(args[1]));
            BufferedWriter bW = new BufferedWriter(new FileWriter(DICTIONARY));) {
            fillMap(bR1, hm1);
            fillMap(bR2, hm2);
            fillVector(hm1, false);
            fillVector(hm2,true);
            writeDictionaryInFile(bW);
            System.out.format("Сходство: %.2f",getFormulaValue());

        } catch (IOException e){
            System.out.println(e.getMessage());
        }


    }

    private static void fillMap(BufferedReader br, HashMap<String,Integer> hm){
        String string;
        try {
            while ((string = br.readLine()) != null){
                String [] args = string.split("\\W");
                for(String arg: args){
                    if(hm.containsKey(arg)){
                        hm.put(arg, hm.get(arg)+1);
                    } else {
                        hm.put(arg, 1);
                    }
                }
                for (Map.Entry<String, Integer> map   : hm.entrySet()){
                    set.add(map.getKey());
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void fillVector(HashMap<String,Integer> map, boolean flag){
        HashMap<String,Integer> vector;
        String item;
        if(!flag){
            vector = vectorA;
        } else {
            vector = vectorB;
        }
        for (String s : set) {
            item = s;
            vector.put(item, map.getOrDefault(item, 0));
        }

    }

    private static double getFormulaValue(){
            int numerator = 0;
            double denominator = 0;
            double sqrtA = 0;
            double sqrtB = 0;

            for (Map.Entry<String,Integer> key : vectorA.entrySet()){
                numerator += key.getValue() * vectorB.get(key.getKey());
            }
            for (Map.Entry<String,Integer> key : vectorA.entrySet()){
                sqrtA += key.getValue() * key.getValue();
                sqrtB += vectorB.get(key.getKey()) * vectorB.get(key.getKey());
            }
            sqrtA = Math.sqrt(sqrtA);
            sqrtB = Math.sqrt(sqrtB);
            denominator = sqrtB * sqrtA;
            return  numerator/denominator;

    }

    private static void writeDictionaryInFile(BufferedWriter bufferedWriter){
        try {
            for (String s : set){
                bufferedWriter.write(s + " ");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

}