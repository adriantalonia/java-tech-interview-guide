package _06_Interview_Preparation._06_0_GFG_160_problems._01_Arrays._02_Move_All_Zeroes_to_End;

import java.util.Arrays;

/**
 * MoveAllZerosToEnd
 * <p>
 * Problem: Given an array of integers arr[], move all the zeros to the end
 * of the array while maintaining the relative order of all non-zero elements.
 * <p>
 * Note: The operation should be performed in-place.
 * <p>
 * Time Complexity Solutions:
 * 1. Temporary Array Approach: O(n) time, O(n) space
 * 2. Two Traversals Approach: O(n) time, O(1) space
 * 3. One Traversal Approach: O(n) time, O(1) space - Optimal
 */
public class MoveAllZerosToEnd {

    /**
     * Approach 1: Temporary Array Approach
     * <p>
     * Algorithm:
     * 1. Create temporary array of same size
     * 2. Copy all non-zero elements to temp array first
     * 3. Fill remaining positions in temp array with zeros
     * 4. Copy all elements back to original array
     * <p>
     * Time Complexity: O(n) - three linear scans
     * Space Complexity: O(n) - temporary array of same size
     *
     * @param arr input array to modify
     */
    public static void pushZerosToEndTemporaryArray(int[] arr) {
        int len = arr.length;

        // Edge case: empty or single element array
        if (len <= 1) {
            return;
        }

        // Create temporary array
        int[] temp = new int[len];

        // Index to track position in temp array
        int j = 0;

        // First pass: Copy all non-zero elements to temp array
        for (int i = 0; i < len; i++) {
            if (arr[i] != 0) {
                temp[j++] = arr[i];
            }
        }

        // Second pass: Fill remaining positions with zeros
        // (Actually not needed since int array initializes to 0)
        while (j < len) {
            temp[j++] = 0;
        }

        // Third pass: Copy everything back to original array
        for (int i = 0; i < len; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * Approach 2: Two Traversals Approach
     * <p>
     * Algorithm:
     * 1. First traversal: Move all non-zero elements to front
     * 2. Second traversal: Fill remaining positions with zeros
     * <p>
     * Time Complexity: O(n) - two linear scans
     * Space Complexity: O(1) - in-place operation
     *
     * @param arr input array to modify
     */
    public static void pushZerosToEndTwoTraversals(int[] arr) {
        int len = arr.length;

        // Edge case: empty or single element array
        if (len <= 1) {
            return;
        }

        // Count of non-zero elements processed so far
        int count = 0;

        // First traversal: Move all non-zero elements to front
        // This overwrites some elements but maintains relative order of non-zeros
        for (int i = 0; i < len; i++) {
            if (arr[i] != 0) {
                arr[count++] = arr[i];
            }
        }

        // Second traversal: Fill remaining positions with zeros
        // Starting from 'count' index to end of array
        while (count < len) {
            arr[count++] = 0;
        }
    }

    /**
     * Approach 3: One Traversal with Swapping (Optimal Solution) ⭐
     * <p>
     * Algorithm:
     * 1. Use pointer 'count' to track position for next non-zero element
     * 2. When we find non-zero element, swap it with element at 'count'
     * 3. This naturally pushes zeros towards the end
     * 4. Increment 'count' only when we place a non-zero element
     * <p>
     * Time Complexity: O(n) - single linear scan
     * Space Complexity: O(1) - in-place operation
     * <p>
     * This is your original solution with detailed comments!
     *
     * @param arr input array to modify
     */
    public static void pushZerosToEnd(int[] arr) {
        // Pointer to track the position for next non-zero element
        int count = 0;
        int len = arr.length;

        // Edge case: empty or single element array
        if (len <= 1) {
            return;
        }

        // Single traversal through the array
        for (int i = 0; i < len; i++) {
            // Found a non-zero element
            if (arr[i] != 0) {
                // Swap current element with element at 'count' position
                // This moves non-zero to front and zero (if any) towards end
                int tmp = arr[i];
                arr[i] = arr[count];
                arr[count] = tmp;

                // Move to next position for non-zero element
                count++;
            }
            // If arr[i] == 0, we don't increment count
            // This ensures zeros stay towards the end
        }
    }

    /**
     * Optimized version of one traversal approach
     * Avoids unnecessary swaps when element is already in correct position
     *
     * @param arr input array to modify
     */
    public static void pushZerosToEndOptimized(int[] arr) {
        int count = 0;
        int len = arr.length;

        if (len <= 1) {
            return;
        }

        for (int i = 0; i < len; i++) {
            if (arr[i] != 0) {
                // Only swap if positions are different (optimization)
                if (i != count) {
                    int tmp = arr[i];
                    arr[i] = arr[count];
                    arr[count] = tmp;
                }
                count++;
            }
        }
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
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE BENCHMARK");
        System.out.println("=".repeat(80));

        // Test with different array sizes
        int[] sizes = {1000, 10000, 100000, 500000};

        for (int size : sizes) {
            System.out.printf("\n📊 Array Size: %,d elements\n", size);
            System.out.println("-".repeat(50));

            // Generate test array (worst case: alternating pattern 0,1,0,1,...)
            int[] testArray = new int[size];
            for (int i = 0; i < size; i++) {
                testArray[i] = i % 2; // Creates pattern: [0,1,0,1,0,1,...]
            }

            // Warm up JVM (important for accurate measurements)
            for (int i = 0; i < 100; i++) {
                int[] warmup = testArray.clone();
                pushZerosToEnd(warmup);
            }

            // Measure each approach multiple times and take average
            int iterations = 50;
            long totalTime1 = 0, totalTime2 = 0, totalTime3 = 0, totalTime4 = 0;

            for (int i = 0; i < iterations; i++) {
                // Temporary Array Approach
                int[] arr1 = testArray.clone();
                totalTime1 += measureExecutionTime(() ->
                        pushZerosToEndTemporaryArray(arr1));

                // Two Traversals Approach
                int[] arr2 = testArray.clone();
                totalTime2 += measureExecutionTime(() ->
                        pushZerosToEndTwoTraversals(arr2));

                // One Traversal Approach (Original)
                int[] arr3 = testArray.clone();
                totalTime3 += measureExecutionTime(() ->
                        pushZerosToEnd(arr3));

                // Optimized One Traversal
                int[] arr4 = testArray.clone();
                totalTime4 += measureExecutionTime(() ->
                        pushZerosToEndOptimized(arr4));
            }

            double avgTime1 = totalTime1 / (double) iterations;
            double avgTime2 = totalTime2 / (double) iterations;
            double avgTime3 = totalTime3 / (double) iterations;
            double avgTime4 = totalTime4 / (double) iterations;

            System.out.printf("Temporary Array:    %8.2f μs\n", avgTime1 / 1000.0);
            System.out.printf("Two Traversals:     %8.2f μs\n", avgTime2 / 1000.0);
            System.out.printf("One Traversal:      %8.2f μs ⭐\n", avgTime3 / 1000.0);
            System.out.printf("Optimized Version:  %8.2f μs\n", avgTime4 / 1000.0);

            // Calculate speedup ratios
            double speedup1 = avgTime1 / avgTime3;
            double speedup2 = avgTime2 / avgTime3;
            double speedup4 = avgTime3 / avgTime4;

            System.out.printf("vs Temporary Array: %.1fx faster\n", speedup1);
            System.out.printf("vs Two Traversals:  %.1fx faster\n", speedup2);
            System.out.printf("Optimization gain:  %.1fx\n", speedup4);
        }
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
     * Test all approaches and print results
     */
    public static void main(String[] args) {
        // Test cases with expected results
        int[][] testCases = {
                {1, 2, 0, 4, 3, 0, 5, 0},    // Expected: [1, 2, 4, 3, 5, 0, 0, 0]
                {10, 20, 30},                // Expected: [10, 20, 30]
                {0, 0, 0},                   // Expected: [0, 0, 0]
                {5},                         // Expected: [5]
                {0, 1, 0, 2, 0, 3},         // Expected: [1, 2, 3, 0, 0, 0]
                {0, 0, 1, 2, 3},            // Expected: [1, 2, 3, 0, 0]
                {1, 2, 3, 0, 0},            // Expected: [1, 2, 3, 0, 0]
                {},                          // Expected: []
                {0},                         // Expected: [0]
                {1, 0, 2, 0, 3, 0, 4}       // Expected: [1, 2, 3, 4, 0, 0, 0]
        };

        int[][] expected = {
                {1, 2, 4, 3, 5, 0, 0, 0},
                {10, 20, 30},
                {0, 0, 0},
                {5},
                {1, 2, 3, 0, 0, 0},
                {1, 2, 3, 0, 0},
                {1, 2, 3, 0, 0},
                {},
                {0},
                {1, 2, 3, 4, 0, 0, 0}
        };

        System.out.println("=".repeat(80));
        System.out.println("MOVE ALL ZEROS TO END - TEST RESULTS");
        System.out.println("=".repeat(80));

        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i].clone();
            int[] expectedResult = expected[i];

            System.out.printf("\nTest Case %d: %s\n", i + 1, Arrays.toString(testCase));
            System.out.printf("Expected: %s\n", Arrays.toString(expectedResult));

            // Test all approaches with timing
            int[] arr1 = testCase.clone();
            long startTime = System.nanoTime();
            pushZerosToEndTemporaryArray(arr1);
            long time1 = System.nanoTime() - startTime;

            int[] arr2 = testCase.clone();
            startTime = System.nanoTime();
            pushZerosToEndTwoTraversals(arr2);
            long time2 = System.nanoTime() - startTime;

            int[] arr3 = testCase.clone();
            startTime = System.nanoTime();
            pushZerosToEnd(arr3);
            long time3 = System.nanoTime() - startTime;

            int[] arr4 = testCase.clone();
            startTime = System.nanoTime();
            pushZerosToEndOptimized(arr4);
            long time4 = System.nanoTime() - startTime;

            boolean correct1 = arraysEqual(arr1, expectedResult);
            boolean correct2 = arraysEqual(arr2, expectedResult);
            boolean correct3 = arraysEqual(arr3, expectedResult);
            boolean correct4 = arraysEqual(arr4, expectedResult);

            System.out.printf("Temporary Array:    %s %s (%,d ns)\n",
                    Arrays.toString(arr1), correct1 ? "✓" : "✗", time1);
            System.out.printf("Two Traversals:     %s %s (%,d ns)\n",
                    Arrays.toString(arr2), correct2 ? "✓" : "✗", time2);
            System.out.printf("One Traversal:      %s %s (%,d ns)\n",
                    Arrays.toString(arr3), correct3 ? "✓" : "✗", time3);
            System.out.printf("Optimized Version:  %s %s (%,d ns)\n",
                    Arrays.toString(arr4), correct4 ? "✓" : "✗", time4);

            // Verify all approaches give same result
            if (correct1 && correct2 && correct3 && correct4) {
                System.out.println("All approaches correct! ✓");
            } else {
                System.out.println("⚠️  SOME APPROACHES FAILED!");
            }
        }

        // Run performance benchmark
        performanceBenchmark();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("COMPLEXITY COMPARISON");
        System.out.println("=".repeat(80));
        System.out.println("Approach           | Time Complexity | Space Complexity | Traversals | Notes");
        System.out.println("-".repeat(80));
        System.out.println("Temporary Array    | O(n)            | O(n)             | 3          | Uses extra space");
        System.out.println("Two Traversals     | O(n)            | O(1)             | 2          | In-place, overwrites");
        System.out.println("One Traversal      | O(n)            | O(1)             | 1          | Optimal solution ⭐");
        System.out.println("Optimized Version  | O(n)            | O(1)             | 1          | Avoids unnecessary swaps");
        System.out.println("=".repeat(80));

        System.out.println("\n💡 Performance Tips:");
        System.out.println("• Swapping preserves zeros better than overwriting");
        System.out.println("• Single traversal reduces cache misses");
        System.out.println("• Avoid unnecessary swaps when i == count");
        System.out.println("• In-place algorithms are memory efficient");
        System.out.println("• Two-pointer technique is very powerful for array problems");
    }
}
