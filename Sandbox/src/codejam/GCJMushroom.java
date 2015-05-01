package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ichung on 4/17/2015.
 *
 * Didn't get it right due to misreading instructions.
 * And then keep misreading it throughout the round.
 * Post Mortem done on 4/30/2015,
 *
 */
public class GCJMushroom {
    public static void main(String[] args) {
        BufferedReader in;

        try {
//            in = new BufferedReader(new FileReader("F:\\code\\mushroom_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\A-small-attempt100.in"));
            in = new BufferedReader(new FileReader("F:\\code\\A-large-attempt1.in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                // number of time lapse
                int n = Integer.parseInt(in.readLine());

                // record of Mushrooms
                String[] mushrooms = in.readLine().split(" ");

                // Method 1: "Kaylin could eat any number of mushroom pieces at any time"
                // Minimal amount
                int minVal = 0;
                for(int j=1; j<n; j++) {
                    int diff = Integer.parseInt(mushrooms[j-1]) - Integer.parseInt(mushrooms[j]);
                    if(diff > 0) minVal += diff;
                }

                // Method 2: "Kaylin eats mushrooms at a constant rate"
                // - find largest interval difference for rate
                // - if minuend is < largest interval diff, just add up
                // - else find interval difference
                // -- if positive, add difference
                // -- if negative, just add minuend
                int maxRate = 0;
                int rateVal = 0;

                // finding max difference
                // ^ this comment probably cost me this, along with calling variable "maxRate"
                // meant find the maximum diff so that that is the "unfortunate" rate for min.
                // ended up driving to find max totals..
                // (post-mortem, 4/30/2015)
                for(int j=1; j<n; j++) {
                    int minuend = Integer.parseInt(mushrooms[j-1]);
//                    int diff = Integer.parseInt(mushrooms[j-1]) - Integer.parseInt(mushrooms[j]);
                    // strictly off the sample description.
                    // ^ no it wasn't-- read instructions more carefully next time (4/30)
//                    if(j < n-1 && minuend > maxRate) maxRate = minuend;
//                    if(j == n-1 && diff > maxRate) maxRate = diff;
//                    if(j < n-1 && Integer.parseInt(mushrooms[j-1]) > maxRate)
//                        maxRate = Integer.parseInt(mushrooms[j-1]);

                    int diff = Integer.parseInt(mushrooms[j-1]) - Integer.parseInt(mushrooms[j]);
                    if(diff > 0 && diff > maxRate) maxRate = diff;
                }

                //
                for(int j=1; j<n; j++) {
                    int minuend = Integer.parseInt(mushrooms[j-1]);
                    int subtrahend = Integer.parseInt(mushrooms[j]);
                    int diff = minuend - subtrahend;
//                    if(j < n-1) {
                        if(minuend > maxRate) {
                            rateVal += maxRate;
                        } else {
                            rateVal += minuend;
                        }
//                    }
//                    else if(j==n-1) {
//                        if(diff > 0)
//                            rateVal += diff;
//                        else rateVal += minuend;
//
//                    }
//                    else if(minuend <= maxRate) rateVal += minuend;
//                    else if(minuend > maxRate) rateVal += maxRate;
//                    else if(diff > 0) rateVal += diff;
//                    else if(diff < 0) rateVal += minuend;
//                    else if(j<n-1 && diff == 0) rateVal += minuend;
                }


                System.out.println("Case #" + i + ": " + minVal + " " + rateVal);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
