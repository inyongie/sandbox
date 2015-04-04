package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by ichung on 3/26/2015.
 * GCJ AlienLanguage
 * https://code.google.com/codejam/contest/90101/dashboard#s=p0
 * All cases passed with this
 */
public class GCJAlienLanguage {

    public static void combinationHelper(String currentValue, Vector<ArrayList<String>> remainingInput, ArrayList<String> combinations) {
        ArrayList<String> currentArray = remainingInput.firstElement();
        remainingInput.remove(remainingInput.indexOf(remainingInput.firstElement()));
        if(remainingInput.isEmpty()) {
            for(String s : currentArray) {
                String value = currentValue + s;
                combinations.add(value);
                System.out.println(value);
            }
            return;
        } else {
            for(String s : currentArray) {
                Vector<ArrayList<String>> copy = (Vector<ArrayList<String>>) remainingInput.clone();
                String addedValue = currentValue + s;
                combinationHelper(addedValue, copy, combinations);
            }
        }
    }

    // Ditched getting the combinations since it is basically Brute Force.
    // This approach cannot even solve the small case.
    public static void getCombinations(Vector<ArrayList<String>> inputList, ArrayList<String> combinations) {
        String currentValue = "";
        combinationHelper(currentValue, inputList, combinations);
    }

    public static Vector<ArrayList<String>> getCleanedList(String input) {
        Vector<ArrayList<String>> resultQ = new Vector<ArrayList<String>>();
        for(int i = 0; i<input.length(); i++) {
            ArrayList<String> temp = new ArrayList<String>();
            if(input.charAt(i) == '(') {
                i++;
                while(input.charAt(i) != ')') {
                    temp.add(input.charAt(i)+"");
                    i++;
                }
            } else {
                temp.add(input.charAt(i)+"");
            }
            resultQ.add(temp);
        }
        return resultQ;
    }

    // Preferred approach of fitting each dictionary word into each ambiguous word.
    public static boolean containsWord(String dictionaryWord, Vector<ArrayList<String>> cleanedList) {
        for(int i=0; i<dictionaryWord.length();i++) {
            ArrayList<String> temp = cleanedList.get(i);
            if(temp.size() == 1) {
                if(dictionaryWord.charAt(i) != temp.get(0).charAt(0)) {
                    return false;
                }
            } else {
                boolean foundMatch = false;
                for(String s : temp) {
                    if(s.charAt(0) == dictionaryWord.charAt(i)) {
                        foundMatch = true;
                    }
                }
                if(!foundMatch) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int idx_WORDSIZE = 0;
        int idx_NUMWORDS = 1;
        int idx_NUMCASES = 2;
        BufferedReader in;
        try {
//            in = new BufferedReader(new FileReader("F:\\code\\alienInput_test.txt"));
            in = new BufferedReader(new FileReader("F:\\code\\alien_A-large-practice.in"));
            String values = in.readLine();
            String[] valuesArray = values.split(" ");
            int wordSize = Integer.parseInt(valuesArray[idx_WORDSIZE]);
            int numWords = Integer.parseInt(valuesArray[idx_NUMWORDS]);
            int numCases = Integer.parseInt(valuesArray[idx_NUMCASES]);

            HashMap<String, Boolean> dictionary = new HashMap<String, Boolean>();
            for(int i = 0; i < numWords; i++) {
                String word = in.readLine();
                dictionary.put(word, true);
            }

            for(int i = 1; i<=numCases; i++) {
                String messedWord = in.readLine();
                Vector<ArrayList<String>> cleanedList = getCleanedList(messedWord);
//                ArrayList<String> combinations = new ArrayList<String>();
//                getCombinations(cleanedList,combinations);

                int counter = 0;
                for(String j : dictionary.keySet()) {
                    if(containsWord(j,cleanedList)) {
                        counter++;
                    }
                }
                System.out.println("Case #" + i + ": " + counter);
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
