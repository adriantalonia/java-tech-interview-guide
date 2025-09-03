package _06_Interview_Preparation._06_0_GFG_160_problems._01_Arrays._05_Next_Permutation;

/**
 * NextPermutation
 * <p>
 * Problem: Given an array of integers, rearrange the array to the next lexicographically
 * greater permutation. If no such permutation exists (array is in descending order),
 * rearrange it to the lowest possible order (ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Examples:
 * [1,2,3] → [1,3,2]
 * [3,2,1] → [1,2,3] (wrap around to first permutation)
 * [1,1,5] → [1,5,1]
 * [1] → [1] (no change for single element)
 * <p>
 * Time Complexity Solutions:
 * 1. Brute Force (Generate All): O(n! × n) time, O(n!) space - Impractical
 * 2. STL-style Algorithm: O(n) time, O(1) space - Standard ⭐⭐
 * 3. Two-Pass Optimized: O(n) time, O(1) space - Interview Optimized ⭐⭐⭐
 */

import java.util.*;

public class NextPermutation {

    /**
     * Approach 1: Brute Force - Generate All Permutations
     * <p>
     * Algorithm:
     * 1. Generate all possible permutations of the array
     * 2. Sort them lexicographically
     * 3. Find current permutation and return the next one
     * 4. If current is last, return first permutation
     * <p>
     * Time Complexity: O(n! × n) - n! permutations, each comparison takes O(n)
     * Space Complexity: O(n!) - storing all permutations
     * <p>
     * Warning: Extremely inefficient! Only works for very small arrays (n ≤ 8)
     *
     * @param nums input array to find next permutation
     */
    public void nextPermutationBruteForce(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return; // No next permutation possible
        }

        // Generate all permutations
        List<List<Integer>> allPermutations = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int num : nums) {
            current.add(num);
        }

        // Convert to list for easier manipulation
        List<Integer> numsList = new ArrayList<>(current);
        boolean[] used = new boolean[nums.length];
        generatePermutations(numsList, new ArrayList<>(), used, allPermutations);

        // Sort all permutations lexicographically
        allPermutations.sort((a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });

        // Find current permutation
        int currentIndex = -1;
        for (int i = 0; i < allPermutations.size(); i++) {
            if (arraysEqual(allPermutations.get(i), current)) {
                currentIndex = i;
                break;
            }
        }

        // Get next permutation (wrap around if necessary)
        int nextIndex = (currentIndex + 1) % allPermutations.size();
        List<Integer> nextPerm = allPermutations.get(nextIndex);

        // Copy result back to original array
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nextPerm.get(i);
        }
    }

    /**
     * Helper method to generate all permutations recursively
     */
    private void generatePermutations(List<Integer> nums, List<Integer> current,
                                      boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums.get(i));
                generatePermutations(nums, current, used, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * Helper method to check if two lists are equal
     */
    private boolean arraysEqual(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) return false;
        }
        return true;
    }

    /**
     * Approach 2: STL-style Algorithm (Standard Implementation)
     * <p>
     * Algorithm (4 steps):
     * 1. Find largest index i such that nums[i] < nums[i+1]
     *    If no such i exists, reverse entire array (last permutation case)
     * 2. Find largest index j such that nums[i] < nums[j] (j > i)
     * 3. Swap nums[i] and nums[j]
     * 4. Reverse the suffix starting at nums[i+1]
     * <p>
     * Time Complexity: O(n) - single pass to find i, single pass to find j, O(n) reverse
     * Space Complexity: O(1) - only using constant extra variables
     * <p>
     * This is the standard algorithm used in C++ STL next_permutation
     *
     * @param nums input array to find next permutation
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return; // No next permutation possible
        }

        int n = nums.length;

        // Step 1: Find the largest index i such that nums[i] < nums[i+1]
        // This finds the rightmost element that can be increased
        int i = n - 2; // Start from second last element
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // If no such index exists, array is in descending order (last permutation)
        if (i == -1) {
            // Reverse entire array to get first permutation
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 2: Find the largest index j such that nums[i] < nums[j]
        // This finds the smallest element greater than nums[i] to the right
        int j = n - 1; // Start from last element
        while (nums[j] <= nums[i]) {
            j--;
        }

        // Step 3: Swap nums[i] and nums[j]
        swap(nums, i, j);

        // Step 4: Reverse the suffix starting at nums[i+1]
        // This gives us the lexicographically smallest arrangement of the suffix
        reverse(nums, i + 1, n - 1);
    }

    /**
     * Approach 3: Two-Pass Optimized Algorithm (Interview Optimized)
     * <p>
     * Algorithm:
     * 1. Scan from right to find first decreasing element (pivot)
     * 2. If no pivot found, it's the last permutation - reverse all
     * 3. Scan from right to find smallest element larger than pivot
     * 4. Swap pivot with found element
     * 5. Reverse everything after pivot position
     * <p>
     * Time Complexity: O(n) - two scans + one reverse operation
     * Space Complexity: O(1) - in-place with constant extra space
     * <p>
     * This version is optimized for interview clarity with detailed comments
     *
     * @param nums input array to find next permutation
     */
    public void nextPermutationOptimized(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return; // Single element or null - no next permutation
        }

        int n = nums.length;
        int pivotIndex = -1;

        // Pass 1: Find the rightmost element that violates descending order
        // We need nums[i] < nums[i+1] to have a next permutation
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivotIndex = i;
                break; // Found the pivot - rightmost increasable position
            }
        }

        // Special case: Array is in descending order (e.g., [3,2,1])
        // This means we have the last permutation, wrap to first
        if (pivotIndex == -1) {
            reverseArray(nums, 0, n - 1);
            return;
        }

        // Pass 2: Find the smallest element to the right that's larger than pivot
        // We scan from right to find the rightmost element > nums[pivotIndex]
        int swapIndex = -1;
        for (int j = n - 1; j > pivotIndex; j--) {
            if (nums[j] > nums[pivotIndex]) {
                swapIndex = j;
                break; // Found the smallest valid candidate
            }
        }

        // Swap the pivot with the found element
        swapElements(nums, pivotIndex, swapIndex);

        // Reverse everything after the pivot to get lexicographically next arrangement
        reverseArray(nums, pivotIndex + 1, n - 1);
    }

    /**
     * Helper method to swap two elements in array
     *
     * @param nums array to modify
     * @param i    first index
     * @param j    second index
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Helper method to reverse a portion of the array using two pointers
     *
     * @param nums  array to reverse
     * @param start starting index (inclusive)
     * @param end   ending index (inclusive)
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * Alternative helper method for swapping (used in optimized version)
     */
    private void swapElements(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Alternative helper method for reversing (used in optimized version)
     */
    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            swapElements(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * XOR-based swap version (no temporary variable needed)
     * Note: Only works when swapping different positions with different values
     *
     * @param nums input array to find next permutation
     */
    public void nextPermutationXOR(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        int i = n - 2;

        // Find pivot
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i == -1) {
            reverseXOR(nums, 0, n - 1);
            return;
        }

        // Find swap candidate
        int j = n - 1;
        while (nums[j] <= nums[i]) {
            j--;
        }

        // XOR swap (only if elements are different and at different positions)
        if (i != j && nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }

        reverseXOR(nums, i + 1, n - 1);
    }

    /**
     * Helper method for XOR-based reversal
     */
    private void reverseXOR(int[] nums, int start, int end) {
        while (start < end) {
            if (nums[start] != nums[end]) { // Avoid XOR with same value
                nums[start] ^= nums[end];
                nums[end] ^= nums[start];
                nums[start] ^= nums[end];
            }
            start++;
            end--;
        }
    }

    /**
     * Recursive implementation (for educational purposes)
     * Not recommended for production due to O(n) space complexity
     *
     * @param nums input array to find next permutation
     */
    public void nextPermutationRecursive(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int pivotIndex = findPivotRecursive(nums, nums.length - 2);

        if (pivotIndex == -1) {
            reverseRecursive(nums, 0, nums.length - 1);
            return;
        }

        int swapIndex = findSwapCandidateRecursive(nums, nums.length - 1, pivotIndex);
        swap(nums, pivotIndex, swapIndex);
        reverseRecursive(nums, pivotIndex + 1, nums.length - 1);
    }

    /**
     * Recursive helper to find pivot
     */
    private int findPivotRecursive(int[] nums, int index) {
        if (index < 0) return -1;
        if (nums[index] < nums[index + 1]) return index;
        return findPivotRecursive(nums, index - 1);
    }

    /**
     * Recursive helper to find swap candidate
     */
    private int findSwapCandidateRecursive(int[] nums, int index, int pivotIndex) {
        if (nums[index] > nums[pivotIndex]) return index;
        return findSwapCandidateRecursive(nums, index - 1, pivotIndex);
    }

    /**
     * Recursive helper to reverse array section
     */
    private void reverseRecursive(int[] nums, int start, int end) {
        if (start >= end) return;
        swap(nums, start, end);
        reverseRecursive(nums, start + 1, end - 1);
    }

    /**
     * Alternative implementation: Find Previous Permutation
     * Previous permutation is the lexicographically smaller permutation
     *
     * @param nums input array to find previous permutation
     */
    public void previousPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        int i = n - 2;

        // Find largest i such that nums[i] > nums[i+1] (opposite of next)
        while (i >= 0 && nums[i] <= nums[i + 1]) {
            i--;
        }

        if (i == -1) {
            // Array is in ascending order - wrap to last permutation (descending)
            reverse(nums, 0, n - 1);
            return;
        }

        // Find largest j such that nums[i] > nums[j]
        int j = n - 1;
        while (nums[j] >= nums[i]) {
            j--;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, n - 1);
    }

    /**
     * Utility method to check if array represents the last permutation
     *
     * @param nums input array
     * @return true if array is in descending order (last permutation)
     */
    public boolean isLastPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Utility method to check if array represents the first permutation
     *
     * @param nums input array
     * @return true if array is in ascending order (first permutation)
     */
    public boolean isFirstPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Measures execution time of a method call in nanoseconds
     *
     * @param method the method to execute and measure
     * @return execution time in nanoseconds
     */
    private static long measureExecutionTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Performance benchmark with different array sizes and patterns
     */
    private static void performanceBenchmark() {
        NextPermutation solution = new NextPermutation();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE BENCHMARK - NEXT PERMUTATION");
        System.out.println("=".repeat(80));

        // Test with different array sizes and patterns
        int[] sizes = {10, 100, 1000, 10000};

        for (int size : sizes) {
            System.out.printf("\n📊 Array Size: %,d elements\n", size);
            System.out.println("-".repeat(60));

            // Test different patterns
            String[] patterns = {"Random", "Ascending", "Descending", "Nearly Sorted"};

            for (String pattern : patterns) {
                // Generate test array based on pattern
                int[] testArray = generateTestArray(size, pattern);

                System.out.printf("\n🔢 Pattern: %s\n", pattern);

                // Skip brute force for large arrays
                if (size <= 8) {
                    // Warm up JVM
                    for (int i = 0; i < 10; i++) {
                        int[] warmup = testArray.clone();
                        solution.nextPermutation(warmup);
                    }

                    // Measure approaches
                    int iterations = 100;
                    long totalTime1 = 0, totalTime2 = 0, totalTime3 = 0, totalTime4 = 0;

                    for (int i = 0; i < iterations; i++) {
                        // Brute Force (only for very small arrays)
                        int[] arr1 = testArray.clone();
                        totalTime1 += measureExecutionTime(() ->
                                solution.nextPermutationBruteForce(arr1));

                        // STL-style Algorithm
                        int[] arr2 = testArray.clone();
                        totalTime2 += measureExecutionTime(() ->
                                solution.nextPermutation(arr2));

                        // Optimized Algorithm
                        int[] arr3 = testArray.clone();
                        totalTime3 += measureExecutionTime(() ->
                                solution.nextPermutationOptimized(arr3));

                        // Recursive Algorithm
                        int[] arr4 = testArray.clone();
                        totalTime4 += measureExecutionTime(() ->
                                solution.nextPermutationRecursive(arr4));
                    }

                    double avgTime1 = totalTime1 / (double) iterations;
                    double avgTime2 = totalTime2 / (double) iterations;
                    double avgTime3 = totalTime3 / (double) iterations;
                    double avgTime4 = totalTime4 / (double) iterations;

                    System.out.printf("Brute Force:            %8.2f μs\n", avgTime1 / 1000.0);
                    System.out.printf("STL Algorithm:          %8.2f μs ⭐⭐\n", avgTime2 / 1000.0);
                    System.out.printf("Optimized Algorithm:    %8.2f μs ⭐⭐⭐\n", avgTime3 / 1000.0);
                    System.out.printf("Recursive Algorithm:    %8.2f μs\n", avgTime4 / 1000.0);

                } else {
                    // For larger arrays, test only efficient algorithms
                    int iterations = 1000;
                    long totalTime2 = 0, totalTime3 = 0;

                    for (int i = 0; i < iterations; i++) {
                        int[] arr2 = testArray.clone();
                        totalTime2 += measureExecutionTime(() ->
                                solution.nextPermutation(arr2));

                        int[] arr3 = testArray.clone();
                        totalTime3 += measureExecutionTime(() ->
                                solution.nextPermutationOptimized(arr3));
                    }

                    double avgTime2 = totalTime2 / (double) iterations;
                    double avgTime3 = totalTime3 / (double) iterations;

                    System.out.printf("Brute Force:            Skipped (Too Slow)\n");
                    System.out.printf("STL Algorithm:          %8.2f μs ⭐⭐\n", avgTime2 / 1000.0);
                    System.out.printf("Optimized Algorithm:    %8.2f μs ⭐⭐⭐\n", avgTime3 / 1000.0);
                    System.out.printf("Recursive Algorithm:    Skipped (Stack Risk)\n");
                }
            }
        }
    }

    /**
     * Generate test arrays with different patterns
     */
    private static int[] generateTestArray(int size, String pattern) {
        int[] arr = new int[size];
        Random random = new Random(42); // Fixed seed for reproducibility

        switch (pattern) {
            case "Random":
                for (int i = 0; i < size; i++) {
                    arr[i] = random.nextInt(100);
                }
                break;
            case "Ascending":
                for (int i = 0; i < size; i++) {
                    arr[i] = i + 1;
                }
                break;
            case "Descending":
                for (int i = 0; i < size; i++) {
                    arr[i] = size - i;
                }
                break;
            case "Nearly Sorted":
                for (int i = 0; i < size; i++) {
                    arr[i] = i + 1;
                }
                // Swap a few elements to make it nearly sorted
                if (size > 1) arr[size - 1] = arr[size - 2] - 1;
                if (size > 2) arr[size - 2] = size;
                break;
        }
        return arr;
    }

    /**
     * Helper method to check if arrays are equal
     */
    private static boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    /**
     * Test all approaches with comprehensive test cases
     */
    public static void main(String[] args) {
        NextPermutation solution = new NextPermutation();

        // Test cases: {input array, expected result}
        Object[][] testCases = {
                // Basic cases
                {new int[]{1, 2, 3}, new int[]{1, 3, 2}},
                {new int[]{1, 3, 2}, new int[]{2, 1, 3}},
                {new int[]{3, 2, 1}, new int[]{1, 2, 3}}, // Last permutation wraps to first

                // Edge cases
                {new int[]{1}, new int[]{1}}, // Single element
                {new int[]{1, 2}, new int[]{2, 1}}, // Two elements
                {new int[]{2, 1}, new int[]{1, 2}}, // Two elements descending
                {new int[]{}, new int[]{}}, // Empty array

                // Special cases with duplicates
                {new int[]{1, 1, 2}, new int[]{1, 2, 1}},
                {new int[]{1, 2, 1}, new int[]{2, 1, 1}},
                {new int[]{1, 1, 1}, new int[]{1, 1, 1}}, // All same elements

                // Medium complexity
                {new int[]{1, 2, 3, 4}, new int[]{1, 2, 4, 3}},
                {new int[]{4, 3, 2, 1}, new int[]{1, 2, 3, 4}}, // Last to first
                {new int[]{1, 3, 2, 4}, new int[]{1, 4, 2, 3}},

                // Complex cases
                {new int[]{2, 3, 1, 3, 3}, new int[]{2, 3, 3, 1, 3}},
                {new int[]{1, 5, 4, 3, 2}, new int[]{2, 1, 3, 4, 5}},
                {new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}}, // Largest to smallest

                // Cases with zeros
                {new int[]{0, 1, 2}, new int[]{0, 2, 1}},
                {new int[]{2, 1, 0}, new int[]{0, 1, 2}},

                // Negative numbers
                {new int[]{-1, 0, 1}, new int[]{-1, 1, 0}},
                {new int[]{1, 0, -1}, new int[]{-1, 0, 1}}
        };

        System.out.println("=".repeat(90));
        System.out.println("NEXT PERMUTATION - TEST RESULTS");
        System.out.println("=".repeat(90));

        for (int i = 0; i < testCases.length; i++) {
            int[] testArray = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];

            System.out.printf("\nTest Case %d: %s\n",
                    i + 1, Arrays.toString(testArray));
            System.out.printf("Expected: %s\n", Arrays.toString(expected));

            // Test all approaches (skip brute force for larger arrays)
            int[] arr1 = testArray.clone();
            int[] arr2 = testArray.clone();
            int[] arr3 = testArray.clone();
            int[] arr4 = testArray.clone();

            // Measure execution times
            long time1 = 0, time2 = 0, time3 = 0, time4 = 0;

            // Skip brute force for arrays larger than 6 elements
            if (testArray.length <= 6 && testArray.length > 0) {
                long start = System.nanoTime();
                solution.nextPermutationBruteForce(arr1);
                time1 = System.nanoTime() - start;
            }

            long start = System.nanoTime();
            solution.nextPermutation(arr2);
            time2 = System.nanoTime() - start;

            start = System.nanoTime();
            solution.nextPermutationOptimized(arr3);
            time3 = System.nanoTime() - start;

            start = System.nanoTime();
            solution.nextPermutationRecursive(arr4);
            time4 = System.nanoTime() - start;

            // Verify correctness
            boolean correct1 = testArray.length > 6 || testArray.length == 0 || arraysEqual(arr1, expected);
            boolean correct2 = arraysEqual(arr2, expected);
            boolean correct3 = arraysEqual(arr3, expected);
            boolean correct4 = arraysEqual(arr4, expected);

            // Display results
            if (testArray.length <= 6 && testArray.length > 0) {
                System.out.printf("Brute Force:            %s %s (%,d ns)\n",
                        Arrays.toString(arr1), correct1 ? "✓" : "✗", time1);
            } else {
                System.out.printf("Brute Force:            Skipped (array too large or empty)\n");
            }
            System.out.printf("STL Algorithm:          %s %s (%,d ns)\n",
                    Arrays.toString(arr2), correct2 ? "✓" : "✗", time2);
            System.out.printf("Optimized Algorithm:    %s %s (%,d ns)\n",
                    Arrays.toString(arr3), correct3 ? "✓" : "✗", time3);
            System.out.printf("Recursive Algorithm:    %s %s (%,d ns)\n",
                    Arrays.toString(arr4), correct4 ? "✓" : "✗", time4);

            // Overall verification
            boolean allCorrect = correct1 && correct2 && correct3 && correct4;
            if (allCorrect) {
                System.out.println("All approaches correct! ✓");
            } else {
                System.out.println("⚠️  SOME APPROACHES FAILED!");
            }
        }

        // Run performance benchmark for small arrays only
        performanceBenchmark();

        // Display complexity comparison
        System.out.println("\n" + "=".repeat(90));
        System.out.println("COMPLEXITY COMPARISON - NEXT PERMUTATION");
        System.out.println("=".repeat(90));
        System.out.println("Approach                | Time Complexity | Space Complexity | Best For");
        System.out.println("-".repeat(90));
        System.out.println("Brute Force (All Perms) | O(n! × n)       | O(n!)            | Theory only (n≤6)");
        System.out.println("STL Algorithm           | O(n)            | O(1)             | Standard library ⭐⭐");
        System.out.println("Two-Pass Optimized      | O(n)            | O(1)             | Interviews ⭐⭐⭐");
        System.out.println("Recursive Version       | O(n)            | O(n)             | Educational only");
        System.out.println("Previous Permutation    | O(n)            | O(1)             | Utility function");
        System.out.println("=".repeat(90));

        System.out.println("\n💡 Algorithm Selection Guide:");
        System.out.println("• Two-Pass Optimized: Best for interviews (clear logic, optimal complexity)");
        System.out.println("• STL Algorithm: Standard implementation, widely recognized");
        System.out.println("• Brute Force: Only for understanding concept (impractical for n > 6)");
        System.out.println("• Recursive Version: Avoid in production (stack overflow risk)");

        System.out.println("\n🔍 Algorithm Deep Dive:");
        System.out.println("The key insight: Next permutation involves finding the rightmost 'increasable' digit");
        System.out.println("1. Find pivot: rightmost nums[i] where nums[i] < nums[i+1]");
        System.out.println("2. If no pivot exists, array is in descending order (last permutation)");
        System.out.println("3. Find smallest number greater than pivot to its right");
        System.out.println("4. Swap them, then reverse everything after pivot position");

        System.out.println("\n🎯 Interview Tips:");
        System.out.println("• Always handle edge cases: empty array, single element, all same elements");
        System.out.println("• Explain the 4-step algorithm clearly with examples");
        System.out.println("• Walk through [1,2,3] → [1,3,2] → [2,1,3] progression");
        System.out.println("• Mention that this is the algorithm used in C++ STL");
        System.out.println("• Handle the wrap-around case: last permutation → first permutation");
        System.out.println("• Time complexity: O(n), Space complexity: O(1) - optimal!");

        System.out.println("\n📝 Step-by-Step Example: [1,2,3,4] → [1,2,4,3]");
        System.out.println("1. Find pivot: i=2 (nums[2]=3 < nums[3]=4) ✓");
        System.out.println("2. Find swap target: j=3 (nums[3]=4 > nums[2]=3) ✓");
        System.out.println("3. Swap: [1,2,3,4] → [1,2,4,3] ✓");
        System.out.println("4. Reverse suffix: nothing to reverse after position 2");
        System.out.println("Result: [1,2,4,3] ✓");

        System.out.println("\n📝 Complex Example: [1,5,4,3,2] → [2,1,3,4,5]");
        System.out.println("1. Find pivot: i=0 (nums[0]=1 < nums[1]=5) ✓");
        System.out.println("2. Find swap target: j=4 (nums[4]=2 > nums[0]=1, rightmost valid) ✓");
        System.out.println("3. Swap: [1,5,4,3,2] → [2,5,4,3,1] ✓");
        System.out.println("4. Reverse suffix: [2,|5,4,3,1] → [2,1,3,4,5] ✓");
        System.out.println("Result: [2,1,3,4,5] ✓");

        System.out.println("\n⚠️ Common Mistakes to Avoid:");
        System.out.println("• Forgetting to handle the last permutation case (all descending)");
        System.out.println("• Not finding the rightmost valid pivot (scanning from left instead of right)");
        System.out.println("• Not finding the rightmost valid swap candidate");
        System.out.println("• Forgetting to reverse the suffix after swapping");
        System.out.println("• Not handling arrays with duplicate elements correctly");

        System.out.println("\n🚀 Optimization Notes:");
        System.out.println("• The algorithm is already optimal: O(n) time, O(1) space");
        System.out.println("• Early termination when pivot is found saves comparisons");
        System.out.println("• In-place operations minimize memory usage");
        System.out.println("• Works correctly with duplicate elements (stable behavior)");
        System.out.println("• Can be extended to find previous permutation by reversing logic");
    }
}