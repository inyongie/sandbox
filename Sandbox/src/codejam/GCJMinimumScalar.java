package codejam;

import sorts.IntMergeSort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GCJMinimumScalar {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("C:\\Users\\ichung\\Downloads\\A-large-practice (2).in"));
//            in = new BufferedReader(new FileReader("C:\\code\\msPracticeIn.txt"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                int size = Integer.parseInt(in.readLine());
                String arrayStr1 = in.readLine();
                String arrayStr2 = in.readLine();
                String[] strArray1 = arrayStr1.split(" ");
                String[] strArray2 = arrayStr2.split(" ");

                // populate int array / map
                Integer[] intArray1 = new Integer[strArray1.length];
                Integer[] intArray2 = new Integer[strArray2.length];
                for(int j=0; j<strArray1.length; j++) {
//                	stack.push(strArray[j]);
                	intArray1[j] = Integer.parseInt(strArray1[j]);
                	intArray2[j] = Integer.parseInt(strArray2[j]);
                }
                
                IntMergeSort.sort(intArray1, true);
                IntMergeSort.sort(intArray2, false);
                
                int sum = 0;

                for(int j=0; j<strArray1.length; j++) {
                	sum += intArray1[j] * intArray2[j];
                }

//                String res = "";
                System.out.println("Case #" + i + ": " + sum);// + val1 + " "  + val2);// + " sum: " + tot);
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
