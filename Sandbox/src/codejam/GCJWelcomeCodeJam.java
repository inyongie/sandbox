package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ichung on 3/30/2015.
 */
public class GCJWelcomeCodeJam {
    private static final String match = "welcome to code jam";

    public static Integer getCombinations(String valueStr, int jVal, int iVal) {
        if(iVal == match.length()) return 1;
        Integer counter = 0;
            for(int j=jVal; j<valueStr.length(); j++) {
                if(valueStr.charAt(j) == match.charAt(iVal)) {
                    counter += getCombinations(valueStr,j,iVal+1);
                }
            }

        return counter;
    }

    public static String toFourDigit(int count) {
        int cutVal = count % 10000;
        String retVal = Integer.toString(cutVal);
        if(retVal.length() == 4) return retVal;
        else {
            while(retVal.length() != 4) retVal = "0"+retVal;
            return retVal;
        }
    }

    public static class ComboThread implements Runnable {
        public int idx;
        public String valueStr;

        public ComboThread(int idx, String valueStr) {
            this.idx = idx;
            this.valueStr = valueStr;
        }

        public void run() {
            int count = getCombinations(valueStr,0,0);

            System.out.println("Case #" + idx + ": " + toFourDigit(count));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BufferedReader in;
        try {
//            in = new BufferedReader(new FileReader("F:\\code\\welcome_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\welcome_C-small-practice.in"));
            in = new BufferedReader(new FileReader("F:\\code\\welcome_C-large-practice.in"));


            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String valueStr = in.readLine();
                (new Thread(new ComboThread(i,valueStr))).start();
//                int count = getCombinations(valueStr, 0, 0);
//
//                System.out.println("Case #" + i + ": " + toFourDigit(count));
            }
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
