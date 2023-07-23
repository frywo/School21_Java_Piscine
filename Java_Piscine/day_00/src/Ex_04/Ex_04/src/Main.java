import java.util.Scanner;

public class Main {
    private final static int MAX_CODE_VALUE = 65535;
    private final static int MAX_TOP_NUMBERS = 10;
    public static void main(String[] args) {
        String str;
        Scanner getStr = new Scanner(System.in);
        str = getStr.nextLine();
        short [] sumArr = getSumLetters(str);
        char [] topTen = getTopTen(sumArr);
        printGraph(sumArr, topTen);


    }
    private static short[] getSumLetters(String str){
        short [] rez  = new short[MAX_CODE_VALUE];
        char [] inputChar = str.toCharArray();
        for (char c : inputChar) {
            rez[c]++;
        }
        return rez;
    }

    private static char [] getTopTen ( short [] sum) {
       char[] topLetters = new char[MAX_TOP_NUMBERS];

       for (int i = 0; i< sum.length; ++i) {
           short symbolCount = sum[i];
           if (symbolCount!=0) {
               for (int j = 0; j< MAX_TOP_NUMBERS;++j) {
                   if (sum[topLetters[j]]< symbolCount){
                       topLetters = insertArr(topLetters, (char) i, j);
                       break;
                   }
               }
           }
       }
       return topLetters;
    }
    private static char [] insertArr (char[] topLetters, char asciiIndex, int index){
        char [] res = new char[MAX_TOP_NUMBERS];
        for (int i = 0; i < index; ++i) {
            res[i] = topLetters[i];
        }
        res[index] = asciiIndex;
        for (int i = index; i<9; ++i){
            res[i+1] = topLetters[i];
        }
        return res;
    }

    private static void printGraph(short[] sumArr, char[] topTen) {
        short max = sumArr[topTen[0]];
        short maxHeight = (max <= 10 ? max : 10);
        short maxLines  = (short) (maxHeight + 2);
        short [] dotsOnGraphs =  new short[MAX_TOP_NUMBERS];

        for (int i = 0; i < MAX_TOP_NUMBERS; ++i) {
            if (max <=10) {
                dotsOnGraphs[i] = sumArr[topTen[i]];
            } else {
                dotsOnGraphs[i] = (short) (sumArr[topTen[i]] * 10 / max);
            }
        }
        System.out.println();
        for (int i = 0; i<maxLines; ++i) {
            for (int j = 0; j < MAX_TOP_NUMBERS; ++j) {
                if (topTen[j]!=0) {
                    if (i + dotsOnGraphs[j] + 2 == maxLines) {
                        System.out.printf("%3d", sumArr[topTen[j]]);
                    } else if (i+1 == maxLines) {
                        System.out.printf("%3c", topTen[j]);
                    } else if (i + dotsOnGraphs[j] >= maxHeight) {
                        System.out.printf("%3c", '#');
                    }
                    if (j != MAX_TOP_NUMBERS-1 && topTen[j+1] !=0 && i + dotsOnGraphs[j+1] >= maxHeight) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }

    }
}