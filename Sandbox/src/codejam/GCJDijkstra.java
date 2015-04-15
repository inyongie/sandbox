package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by ichung on 4/10/2015.
 */
public class GCJDijkstra {
    public static void main(String[] args) {
        BufferedReader in;
        try {
            // Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("F:\\code\\dijkstra_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\C-small-attempt2.in"));
            in = new BufferedReader(new FileReader("F:\\code\\C-large.in"));

            // populate the quatemion
            // to save space and use chars instead of strings, 
            // -1 == '2'
            // -i == 'l'
            // -j == 'm'
            // -k == 'n'
            HashMap<Character,HashMap<Character,Character>> qMap = new HashMap<Character,HashMap<Character,Character>>();

            HashMap<Character,Character> x1map = new HashMap<Character,Character>();
            x1map.put('1','1');
            x1map.put('i','i');
            x1map.put('j','j');
            x1map.put('k','k');
            x1map.put('2','2');
            x1map.put('l','l');
            x1map.put('m','m');
            x1map.put('n','n');
            qMap.put('1',x1map);

            HashMap<Character,Character> x2map = new HashMap<Character,Character>();
            x2map.put('1','i');
            x2map.put('i','2');
            x2map.put('j','k');
            x2map.put('k','m');
            x2map.put('2','l');
            x2map.put('l','1');
            x2map.put('m','n');
            x2map.put('n','j');
            qMap.put('i',x2map);

            HashMap<Character,Character> x3map = new HashMap<Character,Character>();
            x3map.put('1','j');
            x3map.put('i','n');
            x3map.put('j','2');
            x3map.put('k','i');
            x3map.put('2','m');
            x3map.put('l','k');
            x3map.put('m','1');
            x3map.put('n','l');
            qMap.put('j',x3map);

            HashMap<Character,Character> x4map = new HashMap<Character,Character>();
            x4map.put('1','k');
            x4map.put('i','j');
            x4map.put('j','l');
            x4map.put('k','2');
            x4map.put('2','n');
            x4map.put('l','m');
            x4map.put('m','i');
            x4map.put('n','1');
            qMap.put('k',x4map);

            HashMap<Character,Character> x5map = new HashMap<Character,Character>();
            x5map.put('2','1');
            x5map.put('l','i');
            x5map.put('m','j');
            x5map.put('n','k');
            x5map.put('1','2');
            x5map.put('i','l');
            x5map.put('j','m');
            x5map.put('k','n');
            qMap.put('2',x5map);

            HashMap<Character,Character> x6map = new HashMap<Character,Character>();
            x6map.put('2','i');
            x6map.put('l','2');
            x6map.put('m','k');
            x6map.put('n','m');
            x6map.put('1','l');
            x6map.put('i','1');
            x6map.put('j','n');
            x6map.put('k','j');
            qMap.put('l',x6map);

            HashMap<Character,Character> x7map = new HashMap<Character,Character>();
            x7map.put('2','j');
            x7map.put('l','n');
            x7map.put('m','2');
            x7map.put('n','i');
            x7map.put('1','m');
            x7map.put('i','k');
            x7map.put('j','1');
            x7map.put('k','l');
            qMap.put('m',x7map);

            HashMap<Character,Character> x8map = new HashMap<Character,Character>();
            x8map.put('2','k');
            x8map.put('l','j');
            x8map.put('m','l');
            x8map.put('n','2');
            x8map.put('1','n');
            x8map.put('i','m');
            x8map.put('j','i');
            x8map.put('k','1');
            qMap.put('n',x8map);

            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String[] valueStr = in.readLine().split(" ");
                long numSequence = Long.parseLong(valueStr[0]);
                long numRepeat = Long.parseLong(valueStr[1]);
                String sequence = in.readLine();

                long finalStrSize = numSequence * numRepeat;
                if(finalStrSize < 3) {
                    System.out.println("Case #" + i + ": " + "NO");
                } else {
                    String finalStr = "";
                    for(int j=0; j<numRepeat; j++) {
                        finalStr += sequence;
                    }

                    // populate the calculations
                    // getting rid of the saved map due to the big case's larger number set being larger than the size
                    // of an int, which is used for indexing.
//                    Character[][] qCalcMap = new Character[numSequence*numRepeat][numSequence*numRepeat];

                    ArrayList<Long> iIndx = new ArrayList<Long>();
                    ArrayList<Long> kIndx = new ArrayList<Long>();
                    HashSet<String> jIndx = new HashSet<String>();
                    for(long j=0; j<finalStrSize; j++) {
                        char temp = ' ';
                        for(long k=j; k<finalStrSize; k++) {
                            char val = ' ';
                            if(k==j) {
                                val = sequence.charAt((int)(j%numSequence));
                            } else {
                                val = qMap.get(temp).get(sequence.charAt((int)(k%numSequence)));
                            }
                            if(val == 'i' && k < numRepeat*numSequence && j == 0) iIndx.add(k);
                            if(k == (numSequence*numRepeat)-1 && val == 'k') kIndx.add(j);
                            if(val == 'j') jIndx.add(j+","+k);
                            temp = val;
                        }
                    }


//                    for(int j=0; j<numSequence*numRepeat; j++) {
//                        for (int k = j; k < numSequence * numRepeat; k++) {
//                            if(qCalcMap[j][k] == 'i' && k < numRepeat*numSequence && j == 0) iIndx.add(k);
//                            if(k == (numSequence*numRepeat)-1 && qCalcMap[j][k] == 'k') kIndx.add(j);
//                        }
//                    }

                    boolean found = false;
                    // find potential combo
                    for(long j : iIndx) {
                        for(long k : kIndx) {
                            if(k-j >= 2 && jIndx.contains(((j+1)+","+(k-1))) && !found) {
                                System.out.println("Case #" + i + ": " + "YES");
                                found = true;
                                break;
                            }
                        }
                        if(found) break;
                    }
                    if(!found) {
                        System.out.println("Case #" + i + ": " + "NO");
                    }

                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
