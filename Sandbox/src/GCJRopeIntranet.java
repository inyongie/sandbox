import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ichung on 3/26/2015.
 */
public class GCJRopeIntranet {

    public static int addWire(Pair<Integer,Integer> wire, ArrayList<Pair<Integer,Integer>> wires) {
        // Assumptions from spec
        // no point have the more than one starting/ending point
        // all intersections are counted unique, no merging

        int returnVal = 0;
        for(Pair<Integer,Integer> singleWire : wires) {
            if((singleWire.getKey() > wire.getKey() && singleWire.getValue() < wire.getValue())
                    || (singleWire.getKey() < wire.getKey() && singleWire.getValue() > wire.getValue())) {
                returnVal++;
            }

        }
        wires.add(wire);
        return returnVal;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");


        BufferedReader in;
        try {
//            in = new BufferedReader(new FileReader("F:\\code\\rope_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\rope_A-small-practice.in"));
            in = new BufferedReader(new FileReader("F:\\code\\rope_A-large-practice.in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String numWiresStr = in.readLine();
                int numWires = Integer.parseInt(numWiresStr);

                int count = 0;
                ArrayList<Pair<Integer,Integer>> wires = new ArrayList<Pair<Integer, Integer>>();
                for(int j=0;j<numWires;j++) {
                    String wirePair = in.readLine();
                    String[] splitWire = wirePair.split(" ");
                    Pair<Integer,Integer> wire = new Pair<Integer, Integer>(Integer.parseInt(splitWire[0]),
                            Integer.parseInt(splitWire[1]));
                    count += addWire(wire,wires);
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
