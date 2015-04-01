package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by ichung on 3/30/2015.
 */
public class GCJWelcomeCodeJam {
    private static final String match = "welcome to code jam";
    private static final HashSet<Character> matchSet = new HashSet<Character>();
    
    static {
    	matchSet.add('w');
    	matchSet.add('e');
    	matchSet.add('l');
    	matchSet.add('c');
    	matchSet.add('o');
    	matchSet.add('m');
    	matchSet.add(' ');
    	matchSet.add('d');
    	matchSet.add('j');
    	matchSet.add('a');
    	matchSet.add('t');
    }
    
    // Brute Force Recursion
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

    public static String toFourDigit(Integer count) {
        int cutVal = count % 10000;
        String retVal = Integer.toString(cutVal);
        if(retVal.length() == 4) return retVal;
        else {
            while(retVal.length() != 4) retVal = "0"+retVal;
            return retVal;
        }
    }
    
    public static String cleanString(String valueStr) {
    	String retStr = "";
        for(int i=0; i<valueStr.length(); i++) {
            if(matchSet.contains(valueStr.charAt(i))) {
            	retStr += valueStr.charAt(i);
            }
        }
    	return retStr;
    }
    // End of Brute Force Recursion

    // Threading (not much help)
    public static class ComboThread implements Runnable {
        public int idx;
        public String valueStr;

        public ComboThread(int idx, String valueStr) {
            this.idx = idx;
            this.valueStr = valueStr;
        }

        public void run() {
        	// Uncomment for Brute Force
//            Integer count = getCombinations(valueStr,0,0);
        	
        	// Uncomment for DP
            Integer count = getComboDP(valueStr);

            System.out.println("Case #" + idx + ": " + toFourDigit(count));
        }
    }
    
    // Stab at DP
    public static Integer getComboDP(String inputStr) {
    	Integer[][] store = new Integer[match.length()+1][inputStr.length()+1];
    	for(int i=0; i<=match.length(); i++) {
    		store[i][0] = 0;
    	}
		for(int j=0; j<=inputStr.length(); j++) {
			store[0][j] = 1;
		}
		store[0][0] = 1;

    	for(int i=0; i<match.length(); i++) {
    		for(int j=0; j<inputStr.length(); j++) {
    			if(match.charAt(i) == inputStr.charAt(j)) {
    				// The %10000 actually saves the program from going nuts, from memory?
    				store[(i+1)][(j+1)] = (store[(i+1)-1][(j+1)-1] + store[(i+1)][(j+1)-1])%10000;
    			} else {
    				store[(i+1)][(j+1)] = store[(i+1)][(j+1)-1];
    			}
    		}
    	}

    	return store[match.length()][inputStr.length()];
    }
    

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BufferedReader in;
        try {
        	// Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("C:\\code\\welcome_test.txt"));
//            in = new BufferedReader(new FileReader("C:\\code\\welcome_C-small-practice.in"));
            in = new BufferedReader(new FileReader("C:\\code\\welcome_C-large-practice.in"));


            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String valueStr = in.readLine();

                // Uncomment for Threading 
//                (new Thread(new ComboThread(i,valueStr))).start();

                // Uncomment for Brute Force
//                int count = getCombinations(valueStr, 0, 0);
                
                // Uncomment for DP
                int count = getComboDP(valueStr);

                System.out.println("Case #" + i + ": " + toFourDigit(count));
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
