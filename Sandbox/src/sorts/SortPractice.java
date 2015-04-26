package sorts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ichung on 4/25/2015.
 *
 * Sorting Practice,
 * First wrote all on a simple text editor without auto complete
 * End up making mistakes like "from(int i=0...)" and misspellings, etc.
 *
 */
public class SortPractice {

    // bubble sort
    private static void bubble_sort(int[] arrayToSort, int start, int end) {
        if(arrayToSort.length < 2) return;

        for(int i=1; i<arrayToSort.length; i++) {
            for(int j=0; j<arrayToSort.length-i; j++) {
                if(arrayToSort[j] > arrayToSort[j+1]) {
                    int temp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j+1];
                    arrayToSort[j+1] = temp;
                }
            }
        }
    }

    // insertion sort
    private static void insertion_sort(int[] arrayToSort, int start, int end) {
        if(arrayToSort.length < 2) return;

        for(int i=0; i<arrayToSort.length; i++) {
            for(int j=i; j<arrayToSort.length; j++) {
                if(arrayToSort[j] < arrayToSort[i]) {
                    int temp = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[j];
                    arrayToSort[j] = temp;
                }
            }
        }
    }

    /* Quicksort */
    //quicksort
    private static void quick_sort(int[] arrayToSort, int start, int end) {
        if(arrayToSort.length < 2) return;

        if(start < end) {
            int part_index = partition(arrayToSort, start, end);
            quick_sort(arrayToSort, start, part_index-1);
            quick_sort(arrayToSort, part_index+1,end);
        }
    }

    private static int partition(int[] arrayToSort, int start, int end) {
        int pivotIndex = getPivot(arrayToSort, start, end);
        int pivotValue = arrayToSort[pivotIndex];
        // move pivot index to the end
        arrayToSort[pivotIndex] = arrayToSort[end];
        arrayToSort[end] = pivotValue;

        int trackIndex = start;
        for(int i=start; i<=end-1; i++) {
            if(arrayToSort[i] < arrayToSort[end]) {
                int temp = arrayToSort[trackIndex];
                arrayToSort[trackIndex] = arrayToSort[i];
                arrayToSort[i] = temp;
                trackIndex++;
            }
        }
        int temp = arrayToSort[end];
        arrayToSort[end] = arrayToSort[trackIndex];
        arrayToSort[trackIndex] = temp;
        return trackIndex;
    }

    private static int getPivot(int[] arrayToSort, int start, int end) {
        return end;
    }

    /* --------------------------------------------------------------- */


    /* Merge Sort */

    public static void mergeSort(int[] arrayToSort, int start, int end) {
        if(arrayToSort.length < 2) return;

        int[] copy = new int[arrayToSort.length];
        mergeSort_h(arrayToSort, copy, start, end);
    }

    private static void mergeSort_h(int[] arrayToSort, int[] copy, int start, int end) {
        int midIdx = start + (end-start)/2;

        if(start < end) {
            mergeSort_h(arrayToSort,copy,start,midIdx);
            mergeSort_h(arrayToSort,copy,midIdx+1,end);
            merge(arrayToSort,copy,start,midIdx,end);
        }
    }

    private static void merge(int[] arrayToSort, int[] copy, int start, int mid, int end) {
        // we¡¯re going to merge the contents of start > end
        // keep pointers from start and mid+1
        // refill start index -> end index with lesser of values being pointed.

        int pointer1 = start;
        int pointer2 = mid+1;

        copy = arrayToSort.clone();

        for(int i = start; i<=end; i++) {
            if(pointer1 > mid) {
                arrayToSort[i] = copy[pointer2];
                pointer2++;
            } else if(pointer2 > end) {
                arrayToSort[i] = copy[pointer1];
                pointer1++;
            } else  if(copy[pointer1] < copy[pointer2]) {
                arrayToSort[i] = copy[pointer1];
                pointer1++;
            } else if(copy[pointer2] < copy[pointer1]) {
                arrayToSort[i] = copy[pointer2];
                pointer2++;
            } else {
                arrayToSort[i] = copy[pointer2];
                pointer2++;
            }
        }
    }

    /* ---------------------------------------------------------- */

    public static void main(String[] args) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("F:\\code\\sortPractice.txt"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                String arrayStr1 = in.readLine();
                String[] strArray1 = arrayStr1.split(" ");

                // populate int array / map
                int[] intArray = new int[strArray1.length];
                for(int j=0; j<strArray1.length; j++) {
//                	stack.push(strArray[j]);
                    intArray[j] = Integer.parseInt(strArray1[j]);
                }

                System.out.print("Unsorted: ");
                for(int j=0; j<strArray1.length; j++) {
                    System.out.print(intArray[j] + " ");
                }
                System.out.print("\n");


                mergeSort(intArray, 0, intArray.length-1);

                System.out.print("Sorted: ");
                for(int j=0; j<strArray1.length; j++) {
                    System.out.print(intArray[j] + " ");
                }
                System.out.print("\n");

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
