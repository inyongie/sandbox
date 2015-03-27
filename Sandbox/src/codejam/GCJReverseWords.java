package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class GCJReverseWords {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("C:\\Users\\ichung\\Downloads\\B-large-practice.in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                String arrayStr = in.readLine();
                String[] strArray = arrayStr.split(" ");
                Stack<String> stack = new Stack<String>();

                // populate int array / map
                for(int j=0; j<strArray.length; j++) {
                	stack.push(strArray[j]);
                }

                String res = "";
                while(!stack.isEmpty()) {
                	res += stack.pop();
                	if(!stack.isEmpty()) {
                		res += " ";
                	}
                }
                System.out.println("Case #" + i + ": " + res);// + val1 + " "  + val2);// + " sum: " + tot);
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
