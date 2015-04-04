package codejam;

import structures.MultiNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ichung on 3/26/2015.
 * GCJFileFixit
 * https://code.google.com/codejam/contest/635101/dashboard#s=p0
 * All test cases pass with this
 */

public class GCJFileFixit {
    // Function could be used for both adding and counting purposes.
    public static int addDir(MultiNode<String> root, String[] directory) {
        int retVal = 0;
        // Split probably made index 0 a "", gotta check
        // if so, should I just count from 1 and subtract 1 for parallel check with existing directories?
        MultiNode<String> dirCrawlNode = root;
        for(int i=1; i<directory.length; i++) {
            if(!dirCrawlNode.containsInNext(directory[i])) {
                dirCrawlNode.addToNext(new MultiNode<String>(directory[i]));
                retVal++;
            }
            dirCrawlNode = dirCrawlNode.getNextNode(directory[i]);
        }

        return retVal;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BufferedReader in;
        try {
//            in = new BufferedReader(new FileReader("F:\\code\\file_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\file_A-small-practice.in"));
            in = new BufferedReader(new FileReader("F:\\code\\file_A-large-practice.in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String[] valueStr = in.readLine().split(" ");
                int numExisting = Integer.parseInt(valueStr[0]);
                int numToMake = Integer.parseInt(valueStr[1]);

                MultiNode<String> root = new MultiNode("");
                // populating existing directories
                for(int j=0;j<numExisting;j++) {
                    String[] directory = in.readLine().split("/");
                    addDir(root,directory);
                }

                int count = 0;
                for(int j=0;j<numToMake;j++) {
                    String[] directory = in.readLine().split("/");
                    count += addDir(root,directory);
                }

                System.out.println("Case #" + i + ": " + count);
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
