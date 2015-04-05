package codejam;

import sorts.IntMergeSort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ichung on 4/3/2015.
 */
public class GCJBribe {


    // This does not pass any test case.
    // Searching in Google suggests a DP solution.
    // Currently implemented a Tree building/traversing solution that assumes that a particular rule/ordering will
    // always give the minimum.
    // Results say otherwise.
    // Do try again next time.


    // DP Attempt after reading up on a solution
    // This will be a top-down approach where the subsequence will be defined as:
    // B(m,n) = minimum bribe between cells m to n
    // Bribe cost of freeing inmate X = Current cost + B(minIndex + index of inmate X-1) + B(index of inmate X+1, maxIndex)
    private static int runDPSolution(Integer[][] cache, Integer[] releaseArray, int minIndex, int maxIndex) {
        if(maxIndex-minIndex<2) return 0;
        if(cache[minIndex][maxIndex] != null) return cache[minIndex][maxIndex];

        int min = -1;
        for(int i=minIndex+1; i<maxIndex; i++) {
            int cost = (releaseArray[i]-releaseArray[minIndex]-1) + (releaseArray[maxIndex]-releaseArray[i]-1);
            cost += runDPSolution(cache,releaseArray,minIndex,i) + runDPSolution(cache,releaseArray,i,maxIndex);
            if(min == -1) min = cost;
            else
                min = Math.min(cost, min);
        }

        cache[minIndex][maxIndex] = min;
        return min;
    }

    //

    /* Failed Methods
    private static int getIndexClosestToMid(int startIndex, int endIndex, final HashSet<Integer> releaseSet) {
        double midIndexDouble = getMidIndex(startIndex, endIndex);

        int closestIndex = -1;
        double closestIndexDistance = -1;
        for(Integer value : releaseSet) {
            int adjustedValue = value-1;
            if(adjustedValue >= startIndex && adjustedValue <= endIndex) {
                if (closestIndex == -1) {
                    closestIndex = adjustedValue;
                    closestIndexDistance = Math.abs(midIndexDouble - adjustedValue);
                } else {
                    double distancefromMid = Math.abs(midIndexDouble - adjustedValue);
                    if (distancefromMid < closestIndexDistance) {
                        closestIndex = adjustedValue;
                        closestIndexDistance = Math.abs(midIndexDouble - adjustedValue);
                    } //else if(distancefromMid == closestIndexDistance) {
                }
            }
        }

        // if there is none
        if(closestIndex == -1) {
            return startIndex;
        }

        return closestIndex;
    }

    // Solution cannot be achieved with a simple balanced tree.
    // A special tree structure is required.
    // Next time, try following the logic with a small case.
    private static double getMidIndex(int startIndex, int endIndex) {
        return startIndex + (endIndex-startIndex)/2.0;
    }

    // Method to create balanced tree
    // What does it need?
    // - Array of indices
    // What does it return?
    // - the root of the Tree
    private static TreeNode<Integer> createBalancedTree(final Integer[] indexArray, int startIndex, int endIndex, final HashSet<Integer> releaseSet) {
        // What are End Cases?
        // if endIndex is less than startIndex, return null
        if(startIndex > endIndex) {
            return null;
        }

        TreeNode<Integer> root = new TreeNode<Integer>();

        int midIndex = getIndexClosestToMid(startIndex, endIndex, releaseSet);

        // 1. Value in the mid index will be the "root" value
        root.setValue(indexArray[midIndex]);

        // 2. indices less than the mid index will be recursed as left children
        //    indices greater than the mid index will be recursed as the right children
        root.setLeftNode(createBalancedTree(indexArray, startIndex, midIndex-1, releaseSet));
        root.setRightNode(createBalancedTree(indexArray, midIndex+1, endIndex, releaseSet));

        return root;
    }


    // Method to count bribes
    // What does it need?
    // - Root of the TreeNode
    // - Values of Released Indexes
    // What does it return?
    // - the minumum number of bribes
    private static int getMinimumBribe(final TreeNode<Integer> root, final HashSet<Integer> releaseSet) {
        if(root == null) {
            return 0;
        }

        // What if this method is inaccurate (doesn't count anything) and therefore returns this initial
        // value of 0? misleading?
        int minBribe = 0;
        if(releaseSet.contains(root.getValue())) {
            // call counting helper
            minBribe = countNodes(root.getLeftNode()) + countNodes(root.getRightNode());
        }

        return minBribe + getMinimumBribe(root.getLeftNode(), releaseSet) + getMinimumBribe(root.getRightNode(), releaseSet);
    }

    private static int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + countNodes(root.getLeftNode()) + countNodes(root.getRightNode());
    }


    // Just for Test, In-order Tree Traversal
    private static void printInOrderTreeTraversal(final TreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        printInOrderTreeTraversal(root.getLeftNode());
        System.out.println(root.getValue());
        printInOrderTreeTraversal(root.getRightNode());
    }
    */

    public static void main(String[] args) {
          BufferedReader in;
        try {
            // Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("F:\\code\\bribe_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\bribe_C-small-practice.in"));
            in = new BufferedReader(new FileReader("F:\\code\\bribe_C-large-practice.in"));

            int cases = Integer.parseInt(in.readLine());

            for(int i = 1; i<=cases; i++) {
                /* The Failed Approach
                String[] valueStr = in.readLine().split(" ");
                int arraySize = Integer.parseInt(valueStr[0]);
                int numReleased = Integer.parseInt(valueStr[1]);

                String[] releaseArray = in.readLine().split(" ");
                HashSet<Integer> releaseSet = new HashSet<Integer>();
                // Create HashSet of released cells
                for(int j=0; j<numReleased; j++) {
                    releaseSet.add(Integer.parseInt(releaseArray[j]));
                }

                Integer[] indexArray = new Integer[arraySize];
                // Create an array of strings of index values
                for(int j=0; j<arraySize; j++) {
                    indexArray[j] = j+1;
                }

                // Create a balanced tree
                TreeNode<Integer> root = createBalancedTree(indexArray, 0, arraySize-1, releaseSet);

                // Test with tree traversal output
//                printInOrderTreeTraversal(root);

                // Count Minumum Bribe
                int minimumBribe = getMinimumBribe(root, releaseSet);
                */


                // Attempting DP Approach
                // Read up a solution, but implementing from understanding
                // (Have to say, I did cheat a bit on index values..)
                String[] valueStr = in.readLine().split(" ");
                int arraySize = Integer.parseInt(valueStr[0]);
                int numReleased = Integer.parseInt(valueStr[1]);

                // cache of answers
                // 101 because maximum number of prisoners freed is 100 + 2 end values.
                Integer[][] cache = new Integer[102][102];

                // populate release indices
                String[] releaseArray = in.readLine().split(" ");
                // +2 for the physical boundary of ends
                Integer[] newReleaseArray = new Integer[numReleased+2];
                for(int j=0; j<numReleased; j++) {
                    newReleaseArray[j] = Integer.parseInt(releaseArray[j]);
                }
                // boundary values of ends
                // This is required for the algorithm to recognize the ends of the "prison", making the released cells
                // behave the same way as an end
                newReleaseArray[numReleased] = 0;
                newReleaseArray[numReleased+1] = arraySize+1;

                // sort the release array
                IntMergeSort.sort(newReleaseArray,true);

                int minimumBribe = runDPSolution(cache,newReleaseArray,0,newReleaseArray.length-1);

                System.out.println("Case #" + i + ": " + minimumBribe);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
