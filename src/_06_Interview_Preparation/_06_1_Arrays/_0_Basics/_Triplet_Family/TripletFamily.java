package _06_Interview_Preparation._06_1_Arrays._0_Basics._Triplet_Family;

import java.util.Arrays;

/**
 * TripletFamily - Solution for finding if three numbers exist where sum of two equals the third
 * <p>
 * Problem: Given an array of integers, find if there exist three numbers such that a + b = c
 * Approach: Sort array, then for each element as potential sum, use two pointers to find pair
 * <p>
 * Time Complexity: O(n²) - O(n log n) for sorting + O(n²) for main algorithm
 * Space Complexity: O(1) - only using constant extra space
 */
public class TripletFamily {

    /**
     * Finds if a triplet exists where two elements sum to the third element
     * <p>
     * Algorithm:
     * 1. Sort the array first (as required by problem statement)
     * 2. For each element starting from largest, use it as target sum
     * 3. Use two pointers technique to find if any two other elements sum to target
     * 4. Return true immediately if found, false if no triplet exists
     *
     * @param arr The input array of integers
     * @return true if triplet (a,b,c) exists where a+b=c, false otherwise
     */
    public static boolean findTriplet(int[] arr) {
        // Input validation - ensure array exists and has at least 3 elements
        if (arr == null || arr.length < 3) return false;

        // Sort the array first (required by problem statement)
        // This enables the two-pointers technique to work efficiently
        Arrays.sort(arr);

        // Get the last valid index for cleaner code
        int len = arr.length - 1;

        // Iterate through each element as potential target sum (starting from largest)
        // We start from the largest because if a + b = c, then c should be >= max(a,b)
        for (int i = 0; i <= len; i++) {
            // Current target sum - we're looking for two elements that add to this
            int target = arr[len - i];

            // Two pointers approach on the remaining elements
            int left = 0;                    // Start from smallest remaining element
            int right = len - (1 + i);       // End before target element (avoid reusing same element)

            // Search for two elements that sum to target using two pointers
            while (left < right) {
                // Calculate sum of current pair
                int sum = arr[left] + arr[right];

                if (sum == target) {
                    // Found triplet: arr[left] + arr[right] = arr[target_index]
                    return true;
                } else if (sum < target) {
                    // Sum too small, need larger sum
                    // Move left pointer right to increase sum (array is sorted)
                    left++;
                } else {
                    // Sum too large, need smaller sum
                    // Move right pointer left to decrease sum (array is sorted)
                    right--;
                }
            }
            // Continue to next potential target if no pair found for current target
        }

        // No triplet found after checking all possibilities
        return false;
    }

    /**
     * Test method to demonstrate the algorithm with examples
     */
    public static void main(String[] args) {
        // Test case 1: Should return true (1 + 2 = 3)
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test 1 - [1,2,3,4,5]: " + findTriplet(arr1)); // true

        // Test case 2: Should return false (no valid triplet)
        int[] arr2 = {3, 4, 5};
        System.out.println("Test 2 - [3,4,5]: " + findTriplet(arr2)); // false

        // Test case 3: Unsorted array that needs sorting first
        int[] arr3 = {5, 3, 1, 4, 2};
        System.out.println("Test 3 - [5,3,1,4,2]: " + findTriplet(arr3)); // true (same as test 1 after sorting)

        // Test case 4: Edge case - exactly 3 elements
        int[] arr4 = {1, 2, 3};
        System.out.println("Test 4 - [1,2,3]: " + findTriplet(arr4)); // true
    }
}
