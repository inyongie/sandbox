package sandbox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {
	
	public MergeSort() {}
	
	
	public static void merge(Integer[] array, Integer[] copy, int start, int mid, int end, boolean asc) {
		for(int i = start; i <= end; i++) {
			copy[i] = array[i];
		}
		
		int ptr1 = start;
		int ptr2 = mid+1;
		
		for(int i = start; i<=end; i++) {
			if(ptr1 > mid)								array[i] = copy[ptr2++];
			else if(ptr2 > end)							array[i] = copy[ptr1++];
			else if(asc && copy[ptr1] < copy[ptr2]) 	array[i] = copy[ptr1++];
			else if(!asc && copy[ptr1] > copy[ptr2]) 	array[i] = copy[ptr1++];
			else 										array[i] = copy[ptr2++];
		}
	}
	
	public static void sort_h(Integer[] array, Integer[] copy, int start, int end, boolean asc) {
		if(end <= start) return;
		int mid = start + (end-start)/2;
		
		sort_h(array, copy, start, mid, asc);
		sort_h(array, copy, mid+1, end, asc);
		
		//merge
		merge(array, copy, start, mid, end, asc);
	}
	
	public static void sort(Integer[] array, boolean asc) {
		Integer[] copy = new Integer[array.length];
		
		sort_h(array, copy, 0, array.length-1, asc);
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("C:\\code\\mergeSortPractice.txt"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                String arrayStr1 = in.readLine();
                String[] strArray1 = arrayStr1.split(" ");

                // populate int array / map
                Integer[] intArray = new Integer[strArray1.length];
                for(int j=0; j<strArray1.length; j++) {
//                	stack.push(strArray[j]);
                	intArray[j] = Integer.parseInt(strArray1[j]);
                }

                System.out.print("Unsorted: ");
                for(int j=0; j<strArray1.length; j++) {
                	System.out.print(intArray[j] + " ");
                }
                System.out.print("\n");

                
                sort(intArray, false);

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
