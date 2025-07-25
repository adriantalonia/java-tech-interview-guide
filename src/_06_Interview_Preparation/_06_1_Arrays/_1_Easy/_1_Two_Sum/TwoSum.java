package _06_Interview_Preparation._06_1_Arrays._1_Easy._1_Two_Sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class provides different solutions to the classic "Two Sum" problem.
 * The problem typically asks to find two numbers in an array that sum up to a target,
 * and return their *indices*. Some variations might ask for the *values* themselves.
 */
public class TwoSum {

    /**
     * Solves the Two Sum problem using a brute-force approach with nested loops.
     * This method iterates through all possible pairs of numbers in the array.
     * It returns the *values* of the two numbers that sum up to the target.
     * <p>
     * Time Complexity: O(n^2) - Due to the nested loops, where 'n' is the length of the array.
     * Space Complexity: O(1) - Uses a constant amount of extra space.
     *
     * @param array     The input array of integers.
     * @param targetSum The target sum to find.
     * @return An array containing the two numbers (values) that sum to the target,
     * or an empty array if no such pair is found.
     */
    public static int[] twoNumberSumForSolution(int[] array, int targetSum) {
        // Variables to store the found numbers.
        // These are initialized but will be overwritten if a pair is found.
        int x = 0;
        int y = 0;
        // Flag to indicate if a pair has been found, allowing early exit from outer loop.
        boolean change = false;

        // Outer loop iterates from the first element up to the second to last.
        for (int i = 0; i < array.length; i++) {
            // Inner loop iterates from the element *after* 'i' to the end of the array.
            // This ensures we consider unique pairs and don't use the same element twice.
            for (int j = i + 1; j < array.length; j++) {
                // Check if the sum of the current pair equals the targetSum.
                if (array[i] + array[j] == targetSum) {
                    // If a pair is found, store their values.
                    x = array[i];
                    y = array[j];
                    // Set the flag to true to signal that a solution has been found.
                    change = true;
                    // Break out of the inner loop since we found the pair.
                    break;
                }
            }
            // If a pair was found in the inner loop, break out of the outer loop as well.
            // This is an optimization to stop unnecessary iterations.
            if (change) break;
        }

        // Return the found pair if 'change' is true, otherwise return an empty array.
        // Note: This method returns the *values* of the numbers, not their indices.
        return change ? new int[]{x, y} : new int[]{};
    }

    /**
     * Solves the Two Sum problem using a HashSet for efficient lookups.
     * This method iterates through the array once, storing numbers in a set as it goes.
     * It returns the *values* of the two numbers that sum up to the target.
     * <p>
     * Time Complexity: O(n) on average - Due to single pass and O(1) average time for HashSet operations.
     * Space Complexity: O(n) on average - In the worst case, the HashSet may store all 'n' elements.
     *
     * @param array     The input array of integers.
     * @param targetSum The target sum to find.
     * @return An array containing the two numbers (values) that sum to the target,
     * or an empty array if no such pair is found.
     */
    public static int[] twoNumberSumHashSetSolution(int[] array, int targetSum) {
        // Create a HashSet to store numbers encountered so far.
        // A HashSet allows for O(1) average time complexity for add and contains operations.
        Set<Integer> nums = new HashSet<>();

        // Iterate through each number in the array.
        // Using a for-each loop is convenient here as we only need the value of the number,
        // not its index for this specific return type (values).
        for (int num : array) {
            // Calculate the 'complement' needed to reach the targetSum.
            // If 'num' + 'complement' = targetSum, then 'complement' = targetSum - 'num'.
            int complement = targetSum - num;

            // Check if the calculated 'complement' already exists in our HashSet.
            if (nums.contains(complement)) {
                // If the complement is found, we have successfully identified the two numbers.
                // Return them as an array.
                // Note: The order might depend on which was encountered first (complement or num).
                return new int[]{complement, num};
            } else {
                // If the complement is not found, add the current number to the HashSet.
                // This makes it available for future numbers to find their complement.
                nums.add(num);
            }
        }
        // If the loop completes and no pair is found, return an empty array.
        // The problem statement usually guarantees a solution, so this might not be reached.
        return new int[0];
    }

    /**
     * Finds two numbers in an array that sum up to a target sum and returns their **indices**.
     * This is the standard and most efficient solution when indices are required.
     * It uses a HashMap to store numbers encountered so far along with their indices.
     * <p>
     * Time Complexity: O(n) on average - Due to single pass and O(1) average time for HashMap operations.
     * Space Complexity: O(n) on average - In the worst case, the HashMap may store all 'n' elements.
     *
     * @param array     The input array of integers.
     * @param targetSum The target sum to find.
     * @return An array containing the 0-based indices of the two numbers,
     * or an empty array if no solution is found (though the problem guarantees one solution).
     */
    public static int[] twoNumberSumArrayIndexReturn(int[] array, int targetSum) {
        // Use a HashMap to store numbers encountered so far and their indices.
        // Key: The number itself (Integer)
        // Value: The index of that number in the original array (Integer)
        Map<Integer, Integer> numsMap = new HashMap<>();

        // Iterate through the array using a traditional for loop to get access to element indices.
        for (int i = 0; i < array.length; i++) {
            int currentNum = array[i];
            // Calculate the 'complement' needed to reach the targetSum.
            // If 'currentNum' + 'complement' = targetSum, then 'complement' = targetSum - 'currentNum'.
            int complement = targetSum - currentNum;

            // Check if the complement already exists in our map's keys.
            // This lookup is very efficient (O(1) average time).
            if (numsMap.containsKey(complement)) {
                // If the complement is found, we've identified the two numbers.
                // The first index is retrieved from the map (the index of the complement).
                // The second index is the current index 'i'.
                return new int[]{numsMap.get(complement), i};
            } else {
                // If the complement hasn't been seen yet,
                // add the current number and its index to the map.
                // This makes it available as a potential complement for future numbers.
                numsMap.put(currentNum, i);
            }
        }
        // If the loop finishes without finding a pair, return an empty array.
        // This line would typically only be reached if there's no solution,
        // but the problem statement usually guarantees exactly one solution.
        return new int[0];
    }

    /**
     * Main method for testing the Two Sum solutions.
     * Demonstrates the usage and prints the results for various test cases.
     */
    public static void main(String[] args) {
        // Test cases
        var array1 = new int[]{2, 5, 8, 3, 9};
        var target1 = 12; // Expected pairs: (3, 9)
        var array2 = new int[]{-1, 6, 5, 3, 2};
        var target2 = 4;  // Expected pairs: (-1, 5) or (6, -2 if applicable, but 2 is there)

        System.out.println("--- twoNumberSumForSolution (Brute Force, returns values) ---");
        System.out.println("Array: " + Arrays.toString(array1) + ", Target: " + target1 + " -> Result: " + Arrays.toString(twoNumberSumForSolution(array1, target1)));
        System.out.println("Array: " + Arrays.toString(array2) + ", Target: " + target2 + " -> Result: " + Arrays.toString(twoNumberSumForSolution(array2, target2)));
        System.out.println("---");

        System.out.println("--- twoNumberSumHashSetSolution (HashSet, returns values) ---");
        System.out.println("Array: " + Arrays.toString(array1) + ", Target: " + target1 + " -> Result: " + Arrays.toString(twoNumberSumHashSetSolution(array1, target1)));
        System.out.println("Array: " + Arrays.toString(array2) + ", Target: " + target2 + " -> Result: " + Arrays.toString(twoNumberSumHashSetSolution(array2, target2)));
        System.out.println("---");

        System.out.println("--- twoNumberSumArrayIndexReturn (HashMap, returns indices) ---");
        // For array1: (3,9) -> indices (3,4) (if 3 is at index 3 and 9 at index 4)
        // For array2: (-1,5) -> indices (0,2) (if -1 is at index 0 and 5 at index 2)
        System.out.println("Array: " + Arrays.toString(array1) + ", Target: " + target1 + " -> Result: " + Arrays.toString(twoNumberSumArrayIndexReturn(array1, target1)));
        System.out.println("Array: " + Arrays.toString(array2) + ", Target: " + target2 + " -> Result: " + Arrays.toString(twoNumberSumArrayIndexReturn(array2, target2)));
        System.out.println("---");

        // Example with no solution (though problem usually guarantees one)
        var array3 = new int[]{1, 2, 3};
        var target3 = 10;
        System.out.println("--- Example with no solution (should return empty array) ---");
        System.out.println("Array: " + Arrays.toString(array3) + ", Target: " + target3 + " -> Result: " + Arrays.toString(twoNumberSumArrayIndexReturn(array3, target3)));
    }

}