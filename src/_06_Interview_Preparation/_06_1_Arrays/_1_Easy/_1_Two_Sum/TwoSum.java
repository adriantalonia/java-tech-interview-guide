package _06_Interview_Preparation._06_1_Arrays._1_Easy._1_Two_Sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {

    public static int[] twoNumberSumForSolution(int[] array, int targetSum) {
        int x = 0;
        int y = 0;
        boolean change = false;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == targetSum) {
                    x = array[i];
                    y = array[j];
                    change = true;
                    break;
                }
            }
            if (change) break;
        }

        return change ? new int[]{x, y} : new int[]{};
    }

    public static int[] twoNumberSumHashSetSolution(int[] array, int targetSum) {

        Set<Integer> nums = new HashSet<Integer>();
        for (int num : array) {
            if (nums.contains(targetSum - num)) {
                return new int[]{targetSum - num, num};
            } else {
                nums.add(num);
            }
        }
        return new int[0];
    }

    /**
     * Finds two numbers in an array that sum up to a target sum and returns their indices.
     * Assumes exactly one solution exists and no element can be used twice.
     *
     * @param array The input array of integers.
     * @param targetSum The target sum to find.
     * @return An array containing the 0-based indices of the two numbers, or an empty array if no solution is found.
     */
    public static int[] twoNumberSumArrayIndexReturn(int[] array, int targetSum) {
        // Use a HashMap to store numbers encountered so far and their indices.
        // Key: The number itself
        // Value: The index of that number in the original array
        Map<Integer, Integer> numsMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int currentNum = array[i];
            // Calculate the 'complement' needed to reach the targetSum
            int complement = targetSum - currentNum;
            // Check if the complement already exists in our map.
            // If it does, we've found our pair!
            if (numsMap.containsKey(complement)) {
                // Return the index of the complement (from the map) and the current number's index (i)
                return new int[]{numsMap.get(complement), i};
            } else {
                // If the complement hasn't been seen, add the current number and its index to the map
                // This makes it available as a complement for future numbers.
                numsMap.put(currentNum, i);
            }
        }
        // If the loop finishes, no such pair was found (though the problem guarantees one solution)
        return new int[0];
    }

    public static void main(String[] args) {
        var array1 = new int[]{2, 5, 8, 3, 9};
        var target1 = 12;
        var array2 = new int[]{-1, 6, 5, 3, 2};
        var target2 = 4;

        System.out.println(Arrays.toString(twoNumberSumHashSetSolution(array1, target1)));
        System.out.println(Arrays.toString(twoNumberSumHashSetSolution(array2, target2)));
        System.out.println(Arrays.toString(twoNumberSumArrayIndexReturn(array1, target1)));
    }

}
