package _06_Interview_Preparation._06_1_Arrays._2_Medium._52_Maximum_Subarray;

import java.util.Arrays;

/**
 * This class provides a solution to the "Maximum Subarray" problem
 * using Kadane's Algorithm.
 * <p>
 * The problem asks to find the contiguous subarray within a one-dimensional array
 * of numbers (containing at least one number) which has the largest sum.
 */
public class MaximumSubarray {

    /**
     * Finds the maximum sum of a contiguous subarray within a given array of integers.
     * This implementation uses Kadane's Algorithm, which is an optimal
     * dynamic programming approach.
     * <p>
     * Time Complexity: O(n) - The algorithm performs a single pass through the array.
     * Space Complexity: O(1) - It uses a constant amount of extra space.
     *
     * @param nums The input array of integers. Assumed to contain at least one number.
     * @return The largest sum of any contiguous subarray found in the input array.
     */
    public static int maxSubArray(int[] nums) {
        // Handle edge case for an empty or null array, though problem usually guarantees at least one number.
        if (nums == null || nums.length == 0) {
            // Depending on problem constraints, could throw IllegalArgumentException or return 0.
            // For max subarray, usually means no positive numbers, so 0 is not necessarily correct.
            // Problem statement typically says array contains at least one number.
            return 0; // Or throw an exception
        }

        // currentMax: Stores the maximum sum of a subarray ending at the current position.
        // It represents the best possible sum if we must include the current element.
        int currentMax = nums[0];

        // maxSoFar: Stores the overall maximum sum found anywhere in the array so far.
        // This will be our final answer.
        int maxSoFar = nums[0];

        // Iterate through the array starting from the second element (index 1).
        // The first element (index 0) is already used for initialization.
        for (int i = 1; i < nums.length; i++) {
            // For the current element nums[i], decide whether:
            // 1. To extend the previous subarray: (currentMax + nums[i])
            // 2. To start a new subarray from nums[i]: (nums[i])
            // We choose the one that yields a larger sum ending at the current position.
            // If currentMax was negative, adding nums[i] to it might make it smaller than nums[i] itself.
            currentMax = Math.max(currentMax + nums[i], nums[i]);

            // Update the overall maximum sum found so far.
            // Compare maxSoFar with the best sum ending at the current position (currentMax).
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        // After iterating through all elements, maxSoFar holds the largest contiguous subarray sum.
        return maxSoFar;
    }

    /**
     * Main method to test the maxSubArray function with various examples.
     */
    public static void main(String[] args) {
        // Test Case 1: Standard example with mixed positive and negative numbers
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        // Expected: [4, -1, 2, 1] sum = 6
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums1)); // Expected: 6
        System.out.println("---");

        // Test Case 2: All positive numbers
        int[] nums2 = {1, 2, 3, 4, 5};
        // Expected: [1, 2, 3, 4, 5] sum = 15
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums2)); // Expected: 15
        System.out.println("---");

        // Test Case 3: All negative numbers
        // In this case, the subarray with the largest sum will be the single largest (least negative) number.
        int[] nums3 = {-10, -1, -5, -3};
        // Expected: [-1] sum = -1
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums3)); // Expected: -1
        System.out.println("---");

        // Test Case 4: Single element array
        int[] nums4 = {7};
        // Expected: [7] sum = 7
        System.out.println("Test Case 4:");
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums4)); // Expected: 7
        System.out.println("---");

        // Test Case 5: Array starting with a positive number
        int[] nums5 = {5, 4, -1, 7, 8};
        // Expected: [5, 4, -1, 7, 8] sum = 23
        System.out.println("Test Case 5:");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums5)); // Expected: 23
        System.out.println("---");

        // Test Case 6: Array with zeros
        int[] nums6 = {-2, 0, -1, 0, 3, 0};
        // Expected: [3, 0] sum = 3
        System.out.println("Test Case 6:");
        System.out.println("Input: " + Arrays.toString(nums6));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums6)); // Expected: 3
        System.out.println("---");
    }
}
