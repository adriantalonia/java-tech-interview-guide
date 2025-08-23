package _06_Interview_Preparation._06_0_GFG_160_problems._01_Arrays._01_Second_Largest_Element_in_an_Array;

import java.util.Arrays;

/**
 * SecondLargestElementInAnArray
 * <p>
 * Problem: Given an array of positive integers arr[] of size n,
 * find the second largest distinct element in the array.
 * <p>
 * Note: If the second largest element does not exist, return -1.
 * <p>
 * Time Complexity Solutions:
 * 1. Sorting Approach: O(n log n)
 * 2. Two Pass Approach: O(n)
 * 3. One Pass Approach: O(n) - Optimal
 */
public class SecondLargestElementInAnArray {

    /**
     * Approach 1: Sorting Approach
     * <p>
     * Algorithm:
     * 1. Sort the array in ascending order
     * 2. Traverse from second last element backwards
     * 3. Find first element that's different from largest
     * <p>
     * Time Complexity: O(n log n) - due to sorting
     * Space Complexity: O(1) - only using constant extra space
     *
     * @param arr input array of positive integers
     * @return second largest distinct element, or -1 if doesn't exist
     */
    public int getSecondLargestSortingApproach(int[] arr) {
        int len = arr.length;

        // Edge case: array too small
        if (len < 2) {
            return -1;
        }

        // Sort array in ascending order
        Arrays.sort(arr);

        // Start from second last element (largest is at last index)
        for (int i = len - 2; i >= 0; i--) {
            // Return first element that's different from largest
            if (arr[i] != arr[len - 1]) {
                return arr[i];
            }
        }

        // All elements are same
        return -1;
    }

    /**
     * Approach 2: Two Pass Approach
     * <p>
     * Algorithm:
     * 1. First pass: Find the maximum element
     * 2. Second pass: Find largest element excluding the max from first pass
     * <p>
     * Time Complexity: O(n) - two linear scans
     * Space Complexity: O(1) - constant extra space
     *
     * @param arr input array of positive integers
     * @return second largest distinct element, or -1 if doesn't exist
     */
    public int getSecondLargestTwoPassApproach(int[] arr) {
        int len = arr.length;

        // Edge case: array too small
        if (len < 2) {
            return -1;
        }

        int max = -1, second = -1;

        // First pass: Find the maximum element
        for (int element : arr) {
            if (element > max) {
                max = element;
            }
        }

        // Second pass: Find second largest (largest excluding max)
        for (int element : arr) {
            // Update second if current element is greater than second
            // but not equal to max (to ensure distinctness)
            if (element > second && element != max) {
                second = element;
            }
        }

        return second;
    }

    /**
     * Approach 3: One Pass Approach (Optimal Solution) ⭐
     * <p>
     * Algorithm:
     * 1. Initialize max = -1, second = -1
     * 2. For each element:
     * - If element > max: second = max, max = element
     * - Else if element > second && element != max: second = element
     * <p>
     * Time Complexity: O(n) - single linear scan
     * Space Complexity: O(1) - constant extra space
     * <p>
     * This is your original solution with detailed comments!
     *
     * @param arr input array of positive integers
     * @return second largest distinct element, or -1 if doesn't exist
     */
    public int getSecondLargestOptimalApproach(int[] arr) {
        int len = arr.length;

        // Edge case: array with less than 2 elements cannot have second largest
        if (len < 2) {
            return -1;
        }

        // Initialize both max and second to -1 (works for positive integers)
        int max = -1, second = -1;

        // Single pass through array using enhanced for loop
        for (int element : arr) {
            // Case 1: Current element is larger than current max
            if (element > max) {
                // Previous max becomes second largest
                second = max;
                // Current element becomes new max
                max = element;
            }
            // Case 2: Current element is not max but larger than current second
            // Also ensure it's not equal to max (for distinctness)
            else if (element > second && element != max) {
                second = element;
            }
            // Case 3: element <= second || element == max
            // Do nothing, continue to next element
        }

        // Return second largest (-1 if all elements were same)
        return second;
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
     * Performs performance benchmarking with larger datasets
     */
    private static void performanceBenchmark() {
        SecondLargestElementInAnArray solution = new SecondLargestElementInAnArray();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE BENCHMARK");
        System.out.println("=".repeat(80));

        // Test with different array sizes
        int[] sizes = {1000, 10000, 100000, 500000};

        for (int size : sizes) {
            System.out.printf("\n📊 Array Size: %,d elements\n", size);
            System.out.println("-".repeat(50));

            // Generate test array (worst case: reverse sorted)
            int[] testArray = new int[size];
            for (int i = 0; i < size; i++) {
                testArray[i] = size - i; // Reverse sorted: [size, size-1, ..., 2, 1]
            }

            // Warm up JVM (important for accurate measurements)
            for (int i = 0; i < 100; i++) {
                solution.getSecondLargestOptimalApproach(testArray.clone());
            }

            // Measure each approach multiple times and take average
            int iterations = 50;
            long totalTime1 = 0, totalTime2 = 0, totalTime3 = 0;

            for (int i = 0; i < iterations; i++) {
                // Sorting Approach
                int[] arr1 = testArray.clone();
                totalTime1 += measureExecutionTime(() ->
                        solution.getSecondLargestSortingApproach(arr1));

                // Two Pass Approach
                int[] arr2 = testArray.clone();
                totalTime2 += measureExecutionTime(() ->
                        solution.getSecondLargestTwoPassApproach(arr2));

                // Optimal Approach
                int[] arr3 = testArray.clone();
                totalTime3 += measureExecutionTime(() ->
                        solution.getSecondLargestOptimalApproach(arr3));
            }

            double avgTime1 = totalTime1 / (double) iterations;
            double avgTime2 = totalTime2 / (double) iterations;
            double avgTime3 = totalTime3 / (double) iterations;

            System.out.printf("Sorting Approach:   %8.2f μs\n", avgTime1 / 1000.0);
            System.out.printf("Two Pass Approach:  %8.2f μs\n", avgTime2 / 1000.0);
            System.out.printf("Optimal Approach:   %8.2f μs ⭐\n", avgTime3 / 1000.0);

            // Calculate speedup ratios
            double speedup1 = avgTime1 / avgTime3;
            double speedup2 = avgTime2 / avgTime3;

            System.out.printf("Optimal vs Sorting:  %.1fx faster\n", speedup1);
            System.out.printf("Optimal vs Two Pass: %.1fx faster\n", speedup2);
        }
    }

    /**
     * Test all approaches and print results
     */
    public static void main(String[] args) {
        SecondLargestElementInAnArray solution = new SecondLargestElementInAnArray();

        // Test cases with expected results
        int[][] testCases = {
                {12, 35, 1, 10, 34, 1}, // Expected: 34
                {10, 5, 10},            // Expected: 5
                {10, 10, 10},           // Expected: -1
                {1, 2},                 // Expected: 1
                {5, 4, 3, 2, 1},       // Expected: 4
                {1},                    // Expected: -1
                {},                     // Expected: -1
                {7, 7, 7, 7, 6},       // Expected: 6
                {1, 2, 3, 4, 5},       // Expected: 4
                {100, 50, 75, 25}      // Expected: 75
        };

        int[] expected = {34, 5, -1, 1, 4, -1, -1, 6, 4, 75};

        System.out.println("=".repeat(80));
        System.out.println("SECOND LARGEST ELEMENT IN ARRAY - TEST RESULTS");
        System.out.println("=".repeat(80));

        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i];
            int expectedResult = expected[i];

            System.out.printf("\nTest Case %d: %s\n", i + 1, Arrays.toString(testCase));
            System.out.printf("Expected: %d\n", expectedResult);

            // Test all three approaches with timing
            int[] arr1 = testCase.clone();
            long time1 = measureExecutionTime(() -> {
            });
            long startTime = System.nanoTime();
            int result1 = solution.getSecondLargestSortingApproach(arr1);
            time1 = System.nanoTime() - startTime;

            int[] arr2 = testCase.clone();
            startTime = System.nanoTime();
            int result2 = solution.getSecondLargestTwoPassApproach(arr2);
            long time2 = System.nanoTime() - startTime;

            int[] arr3 = testCase.clone();
            startTime = System.nanoTime();
            int result3 = solution.getSecondLargestOptimalApproach(arr3);
            long time3 = System.nanoTime() - startTime;

            System.out.printf("Sorting Approach:   %d %s (%,d ns)\n", result1,
                    result1 == expectedResult ? "✓" : "✗", time1);
            System.out.printf("Two Pass Approach:  %d %s (%,d ns)\n", result2,
                    result2 == expectedResult ? "✓" : "✗", time2);
            System.out.printf("Optimal Approach:   %d %s (%,d ns)\n", result3,
                    result3 == expectedResult ? "✓" : "✗", time3);

            // Verify all approaches give same result
            if (result1 == result2 && result2 == result3) {
                System.out.println("All approaches match! ✓");
            } else {
                System.out.println("⚠️  MISMATCH between approaches!");
            }
        }

        // Run performance benchmark
        performanceBenchmark();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("COMPLEXITY COMPARISON");
        System.out.println("=".repeat(80));
        System.out.println("Approach           | Time Complexity | Space Complexity | Notes");
        System.out.println("-".repeat(80));
        System.out.println("Sorting Approach   | O(n log n)      | O(1)             | Simple but slow");
        System.out.println("Two Pass Approach  | O(n)            | O(1)             | Good, 2 iterations");
        System.out.println("Optimal Approach   | O(n)            | O(1)             | Best, 1 iteration ⭐");
        System.out.println("=".repeat(80));

        System.out.println("\n💡 Performance Tips:");
        System.out.println("• Small arrays: Differences may be negligible due to JVM overhead");
        System.out.println("• Large arrays: O(n log n) vs O(n) difference becomes significant");
        System.out.println("• JIT compilation: Later runs may be faster than initial runs");
        System.out.println("• Memory access patterns affect real-world performance");
    }
}