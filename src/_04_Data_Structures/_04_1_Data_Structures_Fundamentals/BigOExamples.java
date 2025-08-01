package _04_Data_Structures._04_1_Data_Structures_Fundamentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides examples of common time complexities
 * used in Big O notation, from constant time to factorial time.
 * Each method demonstrates a different complexity class with a
 * clear explanation of its performance characteristics.
 */
public class BigOExamples {

    // 1. O(1) – Constant Time Complexity

    /**
     * Determines if a number is even.
     * This method has a constant time complexity, O(1).
     * The time it takes to execute does not depend on the size of the input 'n'.
     * It performs a fixed number of operations: one modulo division and one comparison.
     *
     * @param n The integer to check.
     * @return true if the number is even, false otherwise.
     */
    public static boolean isEven(int n) {
        // A single arithmetic operation and a comparison. The runtime is always the same.
        return n % 2 == 0;
    }

    // 2. O(log n) – Logarithmic Time Complexity

    /**
     * Performs a binary search on a sorted array.
     * This method has a logarithmic time complexity, O(log n).
     * With each iteration of the while loop, the search space (the portion of the array being considered)
     * is cut in half. This means the number of operations grows very slowly as the input array size 'n' increases.
     *
     * @param arr The sorted array to search.
     * @param key The value to search for.
     * @return The index of the key if found, otherwise -1.
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;

        // Loop continues as long as the search space is valid.
        while (low <= high) {
            // Calculate the middle index. The (high - low) / 2 prevents potential integer overflow.
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                // Key found at the midpoint. This is the base case.
                return mid;
            } else if (arr[mid] < key) {
                // Key is in the right half, so we discard the left half.
                low = mid + 1;
            } else {
                // Key is in the left half, so we discard the right half.
                high = mid - 1;
            }
        }
        // Key not found in the array.
        return -1;
    }

    // 3. O(n) – Linear Time Complexity

    /**
     * Finds the maximum value in an array.
     * This method has a linear time complexity, O(n), where 'n' is the number of elements in the array.
     * The algorithm must iterate through every single element of the array once to guarantee it finds the maximum.
     *
     * @param arr The array of integers.
     * @return The maximum integer value found in the array.
     */
    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        // The for-each loop iterates through each of the 'n' elements exactly once.
        for (int x : arr) {
            // A constant number of operations are performed for each element.
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    // 4. O(n log n) – Linearithmic Time Complexity

    /**
     * Sorts an array using the Merge Sort algorithm.
     * This is a classic example of an O(n log n) algorithm.
     * The 'log n' part comes from the recursive splitting of the array (like a binary tree).
     * The 'n' part comes from the 'merge' step, which takes linear time to combine the sorted sub-arrays.
     *
     * @param arr The array to be sorted.
     */
    public static void mergeSort(int[] arr) {
        // Base case: an array with less than 2 elements is already sorted.
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        // Split the array into two halves. This is the 'log n' part of the complexity.
        int[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        int[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);

        // Recursively sort the left and right sub-arrays.
        mergeSort(left);
        mergeSort(right);

        // Merge the two sorted halves back into the original array.
        // This is the 'n' part, as it processes each element once.
        merge(arr, left, right);
    }

    /**
     * Merges two sorted arrays into one sorted array.
     * This helper method has a linear time complexity, O(n), where 'n' is the total number of elements
     * in the left and right arrays combined.
     */
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compare elements from both arrays and place the smaller one into the merged array.
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }

        // Copy any remaining elements from the left array.
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Copy any remaining elements from the right array.
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // 5. O(n²) – Quadratic Time Complexity

    /**
     * Checks if an array contains duplicate elements.
     * This method has a quadratic time complexity, O(n²), due to the nested loops.
     * For each element in the array, it iterates through the rest of the array to check for a match.
     * The number of operations is roughly proportional to n * n.
     *
     * @param arr The array of integers.
     * @return true if a duplicate is found, false otherwise.
     */
    public static boolean hasDuplicate(int[] arr) {
        // Outer loop iterates from the first to the last element (n times).
        for (int i = 0; i < arr.length; i++) {
            // Inner loop iterates from the next element to the last (up to n times).
            for (int j = i + 1; j < arr.length; j++) {
                // The comparison inside the loop is a constant-time operation.
                if (arr[i] == arr[j]) {
                    // A duplicate is found, so we can exit early.
                    return true;
                }
            }
        }
        return false;
    }

    // 6. O(2^n) – Exponential Time Complexity

    /**
     * Generates all possible subsets of an array.
     * This method has an exponential time complexity, O(2^n).
     * For each element in the input array, there are two choices: either to include it in a subset or not.
     * This branching process leads to 2^n total subsets.
     *
     * @param arr     The array of elements to generate subsets from.
     * @param index   The current index being considered.
     * @param result  A list to store all generated subsets.
     * @param current A temporary list representing the current subset being built.
     */
    public static void generateSubsets(int[] arr, int index, List<List<Integer>> result, List<Integer> current) {
        // Base case: When we have considered all elements, add the current subset to the result list.
        if (index == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Recursive step 1: Exclude the element at the current index.
        // We move to the next index without adding the current element.
        generateSubsets(arr, index + 1, result, current);

        // Recursive step 2: Include the element at the current index.
        // We add the current element to our temporary list and then recurse.
        current.add(arr[index]);
        generateSubsets(arr, index + 1, result, current);

        // Backtrack: Remove the element to explore other possibilities.
        // This is crucial for correctly generating all unique subsets.
        current.remove(current.size() - 1);
    }

    // 7. O(n!) – Factorial Time Complexity

    /**
     * Generates all permutations of an array.
     * This method has a factorial time complexity, O(n!), because it must generate every possible ordering
     * of the 'n' elements. For an array of size n, there are n! (n factorial) permutations.
     * This is the slowest growth rate among the common complexities.
     *
     * @param arr    The array to generate permutations from.
     * @param l      The starting index of the subarray to permute.
     * @param r      The ending index of the subarray to permute.
     * @param result A list to store all generated permutations.
     */
    public static void permute(int[] arr, int l, int r, List<int[]> result) {
        // Base case: If the left and right indices are the same, we have a complete permutation.
        if (l == r) {
            // Add a clone of the current array to the result list.
            result.add(arr.clone());
        } else {
            // Loop through the subarray to swap elements and generate new permutations.
            for (int i = l; i <= r; i++) {
                // Swap the current element with the element at the starting position.
                swap(arr, l, i);
                // Recursively call permute on the rest of the array.
                permute(arr, l + 1, r, result);
                // Backtrack: Undo the swap to restore the array to its previous state.
                swap(arr, l, i);
            }
        }
    }

    /**
     * A helper method to swap two elements in an array.
     * This is a constant-time operation, O(1).
     */
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * The main method to demonstrate and test all the Big O examples.
     */
    public static void main(String[] args) {

        System.out.println("--- 1. O(1) Constant Time ---");
        System.out.println("Is 4 even? " + isEven(4)); // true
        System.out.println("Is 7 even? " + isEven(7)); // false
        System.out.println();

        System.out.println("--- 2. O(log n) Logarithmic Time (Binary Search) ---");
        int[] sortedArray = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        System.out.println("Array: " + Arrays.toString(sortedArray));
        System.out.println("Index of 50: " + binarySearch(sortedArray, 50)); // 4
        System.out.println("Index of 99: " + binarySearch(sortedArray, 99)); // -1
        System.out.println();

        System.out.println("--- 3. O(n) Linear Time (Find Max) ---");
        int[] unsortedArray = {5, 2, 9, 1, 5, 6};
        System.out.println("Array: " + Arrays.toString(unsortedArray));
        System.out.println("Maximum value: " + findMax(unsortedArray)); // 9
        System.out.println();

        System.out.println("--- 4. O(n log n) Linearithmic Time (Merge Sort) ---");
        int[] arrayToSort = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array: " + Arrays.toString(arrayToSort));
        mergeSort(arrayToSort);
        System.out.println("Sorted array:   " + Arrays.toString(arrayToSort));
        System.out.println();

        System.out.println("--- 5. O(n²) Quadratic Time (Has Duplicate) ---");
        int[] withDuplicates = {1, 2, 3, 4, 3, 5};
        int[] withoutDuplicates = {1, 2, 3, 4, 5};
        System.out.println("Array with duplicates " + Arrays.toString(withDuplicates) + ": " + hasDuplicate(withDuplicates)); // true
        System.out.println("Array without duplicates " + Arrays.toString(withoutDuplicates) + ": " + hasDuplicate(withoutDuplicates)); // false
        System.out.println();

        System.out.println("--- 6. O(2^n) Exponential Time (Generate Subsets) ---");
        int[] subsetArray = {1, 2, 3};
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(subsetArray, 0, subsets, new ArrayList<>());
        System.out.println("Subsets of {1, 2, 3}: " + subsets);
        // Expected output: [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
        System.out.println();

        System.out.println("--- 7. O(n!) Factorial Time (Generate Permutations) ---");
        int[] permuteArray = {1, 2, 3};
        List<int[]> permutations = new ArrayList<>();
        permute(permuteArray, 0, permuteArray.length - 1, permutations);
        System.out.println("Permutations of {1, 2, 3}:");
        for (int[] p : permutations) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();
    }
}
