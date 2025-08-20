package _06_Interview_Preparation._06_1_Arrays._0_Basics._04_Maximum_Element_In_Array;

/**
 * MaximumElement - Solution for finding the largest value in an array.
 * <p>
 * Problem: Given an array of integers, find and return the single largest element within it.
 * <p>
 * Algorithm: Linear scan (traversal)
 * Time Complexity: O(n) - A single pass is made through the array, where 'n' is the number of elements.
 * Space Complexity: O(1) - Only a constant amount of extra space is used for a single variable to store the maximum value.
 */
public class MaximumElement {

    /**
     * Finds the largest element in a given integer array.
     * <p>
     * The method iterates through the array, comparing each element to a
     * variable that holds the largest value found so far.
     *
     * @param arr The input integer array. The method assumes the array is not empty,
     * based on the problem constraints.
     * @return The largest element found within the array.
     * <p>
     * Examples:
     * - largest([1, 8, 7, 56, 90]) → 90
     * - largest([5, 5, 5, 5]) → 5
     * - largest([10]) → 10
     */
    public static int largest(int[] arr) {
        // Initialize 'max' with the first element of the array.
        // This sets a starting point for comparison.
        int max = arr[0];

        // Begin the linear scan from the second element (index 1).
        // This is efficient because the first element is already our initial 'max'.
        for(int i = 1; i < arr.length; i++) {
            // Check if the current array element is greater than the current 'max' value.
            if(arr[i] > max) {
                // If it is, update 'max' to the new, larger value.
                max = arr[i];
            }
        }

        // After the loop finishes, 'max' holds the largest element in the array.
        return max;
    }

    /**
     * Main method to test the 'largest' function with a variety of test cases.
     * It prints the results of each test to the console, including the input,
     * expected output, and actual output for easy verification.
     */
    public static void main(String[] args) {
        // --- Test Case 1: Normal array with distinct elements
        int[] arr1 = {1, 8, 7, 56, 90};
        int expected1 = 90;
        int actual1 = largest(arr1);
        System.out.println("Test Case 1: Normal array");
        System.out.println("Input: " + java.util.Arrays.toString(arr1));
        System.out.println("Expected: " + expected1);
        System.out.println("Actual: " + actual1);
        System.out.println("Result: " + (actual1 == expected1 ? "PASSED" : "FAILED"));
        System.out.println("-------------------------");

        // --- Test Case 2: Array with all identical elements
        int[] arr2 = {5, 5, 5, 5};
        int expected2 = 5;
        int actual2 = largest(arr2);
        System.out.println("Test Case 2: Array with identical elements");
        System.out.println("Input: " + java.util.Arrays.toString(arr2));
        System.out.println("Expected: " + expected2);
        System.out.println("Actual: " + actual2);
        System.out.println("Result: " + (actual2 == expected2 ? "PASSED" : "FAILED"));
        System.out.println("-------------------------");

        // --- Test Case 3: Array with a single element
        int[] arr3 = {10};
        int expected3 = 10;
        int actual3 = largest(arr3);
        System.out.println("Test Case 3: Single-element array");
        System.out.println("Input: " + java.util.Arrays.toString(arr3));
        System.out.println("Expected: " + expected3);
        System.out.println("Actual: " + actual3);
        System.out.println("Result: " + (actual3 == expected3 ? "PASSED" : "FAILED"));
        System.out.println("-------------------------");

        // --- Test Case 4: Array with negative numbers
        int[] arr4 = {-1, -8, -7, -56, -90};
        int expected4 = -1;
        int actual4 = largest(arr4);
        System.out.println("Test Case 4: Array with negative numbers");
        System.out.println("Input: " + java.util.Arrays.toString(arr4));
        System.out.println("Expected: " + expected4);
        System.out.println("Actual: " + actual4);
        System.out.println("Result: " + (actual4 == expected4 ? "PASSED" : "FAILED"));
        System.out.println("-------------------------");
    }
}
