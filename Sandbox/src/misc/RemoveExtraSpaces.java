package misc;

/**
 * Created by ichung on 4/3/2015.
 */
public class RemoveExtraSpaces {

    private static String pruneSpaces(String inputVal) {
        String retVal = "";

        char prevValue = '\n';
        for(int i=0; i<inputVal.length(); i++) {
            if(inputVal.charAt(i) != ' ') {
                if(prevValue == ' ') retVal += ' ';
                retVal += inputVal.charAt(i);
            }

            prevValue = inputVal.charAt(i);
        }
        // if String ends with spaces
        if(prevValue == ' ') retVal += ' ';

        return retVal;
    }

    public static void main(String[] args) {
        // static input
        String inputVal = "I    have     unnecessary   spaces.";
        System.out.println("Original String: " + inputVal);

        String prunedString = pruneSpaces(inputVal);

        System.out.println("Pruned Result: " + prunedString);
    }
}
