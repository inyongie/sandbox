package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GCJOvation {
	
	private static int getNumFriends(Integer[] V, Integer[] S, int maxShy) {
		int numFriends = 0;
		
		for(int i=0; i<=maxShy; i++) {
			if(i == 0) {
				S[i] = 0;
			} else if(i == 1) {
				S[i] = V[i-1];
			} else {
				S[i] = V[i-1] + S[i-1];
			}
			
			while(S[i] < i) {
				numFriends++;
				S[i] += 1;
			}
		}
		
		return numFriends;
	}
	
	public static void main(String[] args) {
        BufferedReader in;
        try {
        	// Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("C:\\code\\ovation_test.txt"));
//            in = new BufferedReader(new FileReader("C:\\code\\A-small-attempt1.in"));
            in = new BufferedReader(new FileReader("C:\\code\\A-large.in"));


            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String[] valueStr = in.readLine().split(" ");
                int maxShy = Integer.parseInt(valueStr[0]);
                String values = valueStr[1];
                
                Integer[] V = new Integer[maxShy+1];
                Integer[] S = new Integer[maxShy+1];
                
                // Populate the V array
                for(int j=maxShy; j>=0; j--) {
                	V[j] = Integer.parseInt(values.charAt(j)+"")%10;
                }
                
                int numFriends = getNumFriends(V,S,maxShy);
                
                System.out.println("Case #" + i + ": " + numFriends);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
