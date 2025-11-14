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
        int max = findMax(array);

        int radix = 1;
        int n = array.length;
        int[] output = new int[n];

        while (max/radix > 0) {
            int[] count = new int[10];
            for (int i = 0; i < n; i++) {
                int num = (array[i] / radix) % 10;
                count[num]++;
            }
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int num = (array[i] / radix) % 10;
                output[count[num] - 1] = array[i];
                count[num]--;
            }
            System.arraycopy(output, 0, array, 0, n);
            radix *= 10;
        }
    }

    // implementation of your choice of merge, pivot, heap or other O(nlogn) comparisonSort
    public static void comparisonSort(int[] array) {
        // TODO - Replace this selection sort implementation with a comparision sort of your choice.
        mergeSort(array, 0, array.length -1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = m+1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    // implementation of your choice of merge, pivot, heap or other O(nlogn) comparisonSort
    public static void secondComparisonSort(int[] array) {
        // TODO - Replace this selection sort implementation with a comparision sort of your choice.
       heapSort(array);
    }

    private static void heapSort(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
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
            System.out.println("    Merge Sort: "+timer);  //TODO - Change name to your actual sort

            // Second Comparison Sort
            sortArray=array.clone();

            timer.start();
            secondComparisonSort(sortArray);
            timer.stop();
            System.out.println("    Heap Sort: "+timer);  //TODO - Change name to your actual sort

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
