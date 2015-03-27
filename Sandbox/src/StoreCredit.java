package sandbox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class StoreCredit {

    public static void main(String[] args) {

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("C:\\Users\\ichung\\Downloads\\A-large-practice (1).in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                String sumStr = in.readLine();
                String arraySizeStr = in.readLine();
                String arrayStr = in.readLine();
                int sum = Integer.parseInt(sumStr);
                int arraySize = Integer.parseInt(arraySizeStr);
                String[] chargeStrArray = arrayStr.split(" ");
                HashMap<Integer, Vector<Integer>> chargeMap = new HashMap<Integer,Vector<Integer>>();

                // populate int array / map
                for(int j=0; j<arraySize; j++) {
                    int val = Integer.parseInt(chargeStrArray[j]);
                    if (!chargeMap.containsKey(val)) {
                        Vector temp = new Vector();
                        temp.add(j);
                        chargeMap.put(val,temp);
                    } else {
                        chargeMap.get(val).add(j);
                    }
                }

                int val1 = -1, val2 = -1;

                // do main work
                for(int j=0; j<arraySize; j++) {
                    int val = Integer.parseInt(chargeStrArray[j]);
                    int remaining = sum - val;
                    if(remaining <= 0) {
                        continue;
                    }
                    if(chargeMap.containsKey(remaining)){
                        val1 = j+1;
                        if(chargeMap.get(remaining).size() == 1) {
                            if(chargeMap.get(remaining).firstElement() == j) 
                            	continue;
                            val2 = chargeMap.get(remaining).firstElement()+1;
                        }
                        else {
                            for(int k=0; k<chargeMap.get(remaining).size(); k++) {
                                if(chargeMap.get(remaining).get(k) != j) {
                                    val2 = chargeMap.get(remaining).get(k)+1;
                                    break;
                                }
                            }
                        }

                        // This is be the cleaner way to check for same index;
                        if(val1 == val2) {
                        	continue;
                        }
                        break;
                    }
                }

                
                System.out.println("Case #" + i + ": " + val1 + " "  + val2);// + " sum: " + tot);
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