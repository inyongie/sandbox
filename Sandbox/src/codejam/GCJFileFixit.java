package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by ichung on 3/26/2015.
 */

public class GCJFileFixit {
    // Function could be used for both adding and counting purposes.
    public static int addDir(ArrayList<HashSet<String>> existingDirs, String[] directory) {
        int retVal = 0;
        // Split probably made index 0 a "", gotta check
        // if so, should I just count from 1 and subtract 1 for parallel check with existing directories?
        for(int i=1; i<directory.length; i++) {
            if(existingDirs.size() < i) {
                existingDirs.add(new HashSet<String>());
            }
            if(!existingDirs.get(i-1).contains(directory[i])) {
                existingDirs.get(i-1).add(directory[i]);
                retVal++;
            }
        }

        return retVal;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("F:\\code\\file_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\rope_A-small-practice.in"));
//            in = new BufferedReader(new FileReader("F:\\code\\rope_A-large-practice.in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String[] valueStr = in.readLine().split(" ");
                int numExisting = Integer.parseInt(valueStr[0]);
                int numToMake = Integer.parseInt(valueStr[1]);

                ArrayList<HashSet<String>> existingDirs = new ArrayList<HashSet<String>>();
                // populating existing directories
                for(int j=0;j<numExisting;j++) {
                    String[] directory = in.readLine().split("/");
                    addDir(existingDirs,directory);
                }

                int count = 0;
                for(int j=0;j<numToMake;j++) {
                    String[] directory = in.readLine().split("/");
                    count += addDir(existingDirs,directory);
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
