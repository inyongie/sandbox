package misc;

/**
 * Created by InYong on 5/30/2015.
 */
public class LongestIncreasingSubsequence {

    private static int getLISLength(int[] array, int[] lisList, int currIdx) {
        if(currIdx == 0) {
            lisList[currIdx] = 1;
            return 1;
        }

        // LIS is...
        // 1 + max(LIS(j)) if there exists a j that
        // - j < currIdx
        // - array[j] < array[currIdx]
        // else it's 1

        int maxLisVal = 1;
        for(int i=0; i<currIdx; i++) {
            int currLisVal;
            if(lisList[i] != -1) currLisVal = lisList[i];
            else currLisVal = getLISLength(array, lisList, i);
            if(array[i] < array[currIdx] && currLisVal > maxLisVal) {
                maxLisVal = currLisVal;
            }
        }
        lisList[currIdx] = maxLisVal + 1;

        return lisList[currIdx];
    }

    public static void main(String[] args) {
        int[] array = {1,64,2,65,3,66,4,5};
        int[] lisList = new int[array.length];

        // initialize the memoization list
        for(int i=0; i<lisList.length; i++) {
            lisList[i] = -1;
        }

        int lisVal = -1;
        // Always ALWAYSS remember base case
        if(array == null || array.length == 0) lisVal = 0;
        else if(array.length == 1) lisVal = 1;
        else {
            lisVal = getLISLength(array,lisList,array.length-1);
        }

        System.out.println(lisVal);
    }

}
