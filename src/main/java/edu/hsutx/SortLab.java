package edu.hsutx;
/**
 * A class for comparing sorting algorithms
 *
 * @author  Todd Dole
 * @version for Data Structures
 *
 */

public class SortLab {

    // Creates an array of size n, with elements of 0 - maxValue
    public static int[] createArray(int n, int maxValue) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random()*maxValue);

        }
        return array;
    }

    // Helper method to find the maximum value in the array
    private static int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // Implementation of countingSort
    public static void countingSort(int[] array) {
        // Find the maximum value in the array to determine the range
        int max = findMax(array);

        // Create the count array with a size of (max + 1)
        int[] count = new int[max + 1];

        // Step 1: Count the occurrences of each element
        for (int num : array) {
            count[num]++;
        }

        // Step 2: Modify the count array to store the cumulative counts
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Create an output array to store the sorted elements
        int[] output = new int[array.length];

        // Step 4: Place elements into the output array in sorted order
        for (int i = array.length - 1; i >= 0; i--) {
            int num = array[i];
            output[count[num] - 1] = num;
            count[num]--;
        }

        // Step 5: Copy the sorted elements back to the original array
        System.arraycopy(output, 0, array, 0, array.length);
    }

    // implementation of Radix Sort
    public static void radixSort(int[] array) {
        // TODO - Implement Radix Sort
    }

    // implementation of your choice of merge, pivot, heap or other O(nlogn) comparisonSort
    public static void comparisonSort(int[] array) {
        // TODO - Replace this selection sort implementation with a comparision sort of your choice.
        int maxIndex = 0;
        for (int i = 0; i < array.length-1; i++) {
            maxIndex = i;
            for (int j = i+1; j < array.length; j++)
                if (array[j] > array[maxIndex]) maxIndex = j;

            // swap using bitwise operations
            array[i] = array[i] ^ array[maxIndex];
            array[maxIndex] = array[i] ^ array[maxIndex];
            array[i] = array[i] ^ array[maxIndex];
        }
    }

    // implementation of your choice of merge, pivot, heap or other O(nlogn) comparisonSort
    public static void secondComparisonSort(int[] array) {
        // TODO - Replace this selection sort implementation with a comparision sort of your choice.
        int maxIndex = 0;
        for (int i = 0; i < array.length-1; i++) {
            maxIndex = i;
            for (int j = i+1; j < array.length; j++)
                if (array[j] > array[maxIndex]) maxIndex = j;

            // swap using bitwise operations
            array[i] = array[i] ^ array[maxIndex];
            array[maxIndex] = array[i] ^ array[maxIndex];
            array[i] = array[i] ^ array[maxIndex];
        }
    }

    // TODO (optional) - Add additional sorting methods here if desired

    public static void main(String[] args) {
        // TODO - Modify paramList --
        // The goal is to find at least one combination of values for each sort that
        //    will demonstrate that sort outperforming the other options.
        int[][] paramList = {
                // max int is 2147483647
                {1000, 1_999_999_999},
                {100000, 1000},
                {1000000, 100000}
        };

        int count=1;
        CodeTimer timer = new CodeTimer();
        int[] sortArray;
        for (int[] param : paramList) {
            int[] array = createArray(param[0], param[1]);
            System.out.printf("==============================\nTest Run %d (n=%d, range = 0-%d)\n",count++, param[0], param[1]);

            // Comparison Sort
            sortArray=array.clone();

            timer.start();
            comparisonSort(sortArray);
            timer.stop();
            System.out.println("    Comparison Sort: "+timer);  //TODO - Change name to your actual sort

            // Second Comparison Sort
            sortArray=array.clone();

            timer.start();
            secondComparisonSort(sortArray);
            timer.stop();
            System.out.println("    Comparison Sort: "+timer);  //TODO - Change name to your actual sort

            // Counting Sort
            sortArray=array.clone();

            timer.start();
            countingSort(sortArray);
            timer.stop();
            System.out.println("      Counting Sort: "+timer);

            // Radix Sort
            sortArray=array.clone();

            timer.start();
            radixSort(sortArray);
            timer.stop();
            System.out.println("         Radix Sort: "+timer);

            // TODO (optional) - add additional sorting method tests here if desired

            System.out.println("==============================\n\n");
        }
    }
}
