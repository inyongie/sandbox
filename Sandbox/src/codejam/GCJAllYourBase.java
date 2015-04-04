package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

// GCJAllYourBase
// I believe all cases passed with this

public class GCJAllYourBase
{
	public static Integer getMinBaseValue(String inputStr, HashMap<Character,Integer> convertMap) {
		HashSet<Character> uniqueChars = new HashSet<Character>();
		for(int i=0; i<inputStr.length(); i++) {
			uniqueChars.add(inputStr.charAt(i));
		}
		
		int baseVal = uniqueChars.size();
		if(baseVal == 1) baseVal = 2; 
		
		Stack<Integer> convertMapPopulator = new Stack<Integer>();
		for(int i=baseVal-1; i>=0; i--) {
			if(i != 1) {
				convertMapPopulator.push(i);
			}
		}
		// First Value of Number should not be 0, so next lowest number is 1.
		convertMapPopulator.push(1);
		
		for(int i=0; i<inputStr.length(); i++) {
			if(!convertMap.containsKey(inputStr.charAt(i))) {
				convertMap.put(inputStr.charAt(i),convertMapPopulator.peek());
				convertMapPopulator.pop();
			}
		}
		
		return baseVal;
	}
	
	public static long getBaseTenValue(String inputStr, int baseVal, HashMap<Character,Integer> convertMap) {
		long retVal = 0;
		double powVal = 0;
		for(int i=inputStr.length()-1; i>=0; i--) {
			retVal += convertMap.get(inputStr.charAt(i)) * Math.pow(baseVal,powVal);
			powVal++;
		}
		
		return retVal;
	}
	
	public static void main(String[] args) {
        System.out.println("Hello World!");

        BufferedReader in;
        try {
        	// Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("C:\\code\\welcome_test.txt"));
//            in = new BufferedReader(new FileReader("C:\\code\\welcome_C-small-practice.in"));
            // in = new BufferedReader(new FileReader("C:\\code\\welcome_C-large-practice.in"));
            in = new BufferedReader(new InputStreamReader(System.in));

            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String valueStr = in.readLine();
				
				// Find Minimum Base and Map
				HashMap<Character,Integer> convertMap = new HashMap<Character,Integer>();
				int baseVal = getMinBaseValue(valueStr, convertMap);
				
				// Get Base 10 number
				long baseTen = getBaseTenValue(valueStr, baseVal, convertMap);

                System.out.println("Case #" + i + ": " + baseTen);
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
