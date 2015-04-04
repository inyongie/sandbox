package codejam;

import structures.TreeNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

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

    public static void main(String[] args) {
          BufferedReader in;
        try {
            // Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("F:\\code\\bribe_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\bribe_C-small-practice.in"));
            in = new BufferedReader(new FileReader("F:\\code\\bribe_C-large-practice.in"));

            int cases = Integer.parseInt(in.readLine());

            for(int i = 1; i<=cases; i++) {
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
