package _06_Interview_Preparation._06_0_GFG_160_problems._01_Arrays._03_Reverse_an_Array.README;

import java.util.Arrays;
import java.util.Collections;

/**
 * ReverseAnArray
 * <p>
 * Problem: Reverse an array arr[]. Reversing an array means rearranging
 * the elements such that the first element becomes the last, the second
 * element becomes second last and so on.
 * <p>
 * Note: The operation should modify the original array in-place.
 * <p>
 * Time Complexity Solutions:
 * 1. Temporary Array Approach: O(n) time, O(n) space
 * 2. Two Pointers Approach: O(n) time, O(1) space
 * 3. Index-Based Swapping: O(n) time, O(1) space - Optimal
 * 4. Built-in Methods: O(n) time, O(1) space
 */
public class ReverseAnArray {

    /**
     * Approach 1: Temporary Array Approach
     * <p>
     * Algorithm:
     * 1. Create temporary array of same size
     * 2. Copy elements from original to temp in reverse order
     * 3. Copy elements back from temp to original array
     * <p>
     * Time Complexity: O(n) - two linear scans
     * Space Complexity: O(n) - temporary array of same size
     *
     * @param arr input array to reverse
     */
    public void reverseArrayTemporary(int[] arr) {
        int len = arr.length;

        // Edge case: arrays with 0 or 1 elements don't need reversal
        if (len <= 1) {
            return;
        }

        // Create temporary array to store reversed elements
        int[] temp = new int[len];

        // Copy elements from original array to temp in reverse order
        for (int i = 0; i < len; i++) {
            temp[i] = arr[len - i - 1];
        }

        // Copy all elements back from temp to original array
        for (int i = 0; i < len; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * Approach 2: Two Pointers Approach
     * <p>
     * Algorithm:
     * 1. Initialize left = 0, right = n - 1
     * 2. While left < right:
     * - Swap elements at left and right positions
     * - Move left forward, right backward
     * 3. Stop when pointers meet in middle
     * <p>
     * Time Complexity: O(n) - single traversal, n/2 swaps
     * Space Complexity: O(1) - in-place operation
     *
     * @param arr input array to reverse
     */
    public void reverseArrayTwoPointers(int[] arr) {
        int len = arr.length;

        // Edge case: arrays with 0 or 1 elements don't need reversal
        if (len <= 1) {
            return;
        }

        // Initialize pointers at both ends
        int left = 0;           // Start from beginning
        int right = len - 1;    // Start from end

        // Continue until pointers meet or cross
        while (left < right) {
            // Swap elements at left and right positions
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // Move pointers toward center
            left++;     // Move left pointer forward
            right--;    // Move right pointer backward
        }
        // When left >= right, we've reversed the entire array
    }

    /**
     * Approach 3: Index-Based Swapping (Optimal Solution) ⭐
     * <p>
     * Algorithm:
     * 1. Iterate only through first half of array (0 to n/2)
     * 2. For each index i, swap arr[i] with arr[n-i-1]
     * 3. This automatically handles both halves of the array
     * <p>
     * Time Complexity: O(n) - processes each element once, n/2 iterations
     * Space Complexity: O(1) - in-place operation
     * <p>
     * This is your original solution with detailed comments!
     *
     * @param arr input array to reverse
     */
    public void reverseArray(int[] arr) {
        int len = arr.length;

        // Edge case: arrays with 0 or 1 elements don't need reversal
        if (len <= 1) {
            return;
        }

        // Iterate only through first half of the array
        // This ensures each element is swapped exactly once
        for (int i = 0; i < len / 2; i++) {
            // Swap current element (i) with its mirror element (len - i - 1)
            //
            // For array [1, 4, 3, 2, 6, 5] with len = 6:
            // i=0: swap arr[0] with arr[5] → [5, 4, 3, 2, 6, 1]
            // i=1: swap arr[1] with arr[4] → [5, 6, 3, 2, 4, 1]
            // i=2: swap arr[2] with arr[3] → [5, 6, 2, 3, 4, 1]
            // i=3: i >= len/2, loop ends

            int tmp = arr[i];                // Store current element
            arr[i] = arr[len - i - 1];      // Move mirror element to current position
            arr[len - i - 1] = tmp;         // Place stored element in mirror position
        }
        // Result: Array is completely reversed
    }

    /**
     * Approach 4: Using Built-in Methods
     * <p>
     * Algorithm:
     * Uses Collections.reverse() with Arrays.asList() wrapper
     * Note: Requires Integer[] instead of int[] for Collections compatibility
     * <p>
     * Time Complexity: O(n) - internally uses similar swapping logic
     * Space Complexity: O(1) - in-place operation
     *
     * @param arr input Integer array to reverse
     */
    public void reverseArrayBuiltIn(Integer[] arr) {
        // Edge case: arrays with 0 or 1 elements don't need reversal
        if (arr.length <= 1) {
            return;
        }

        // Use Collections.reverse with Arrays.asList wrapper
        // This creates a List view of the array and reverses it in-place
        Collections.reverse(Arrays.asList(arr));
    }

    /**
     * Alternative swapping method using XOR (no temporary variable)
     * Note: Only works when arr[i] != arr[j] to avoid zeroing out
     *
     * @param arr input array to reverse
     */
    public void reverseArrayXOR(int[] arr) {
        int len = arr.length;

        if (len <= 1) {
            return;
        }

        for (int i = 0; i < len / 2; i++) {
            int j = len - i - 1;

            // Only use XOR if elements are different (avoid a^a = 0)
            if (arr[i] != arr[j]) {
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
            }
        }
    }

    /**
     * Recursive approach for educational purposes
     *
     * @param arr   input array to reverse
     * @param start starting index
     * @param end   ending index
     */
    private void reverseArrayRecursiveHelper(int[] arr, int start, int end) {
        // Debug: Add bounds checking to prevent infinite recursion
        if (arr == null || start < 0 || end >= arr.length || start >= end) {
            return;
        }

        // Swap elements at start and end positions
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        // Recursive call with updated bounds - move inward
        reverseArrayRecursiveHelper(arr, start + 1, end - 1);
    }

    /**
     * Public wrapper for recursive approach
     */
    public void reverseArrayRecursive(int[] arr) {
        // Handle edge cases
        if (arr == null || arr.length <= 1) {
            return;
        }
        reverseArrayRecursiveHelper(arr, 0, arr.length - 1);
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
        ReverseAnArray solution = new ReverseAnArray();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE BENCHMARK");
        System.out.println("=".repeat(80));

        // Test with different array sizes
        int[] sizes = {1000, 10000, 100000, 500000};

        for (int size : sizes) {
            System.out.printf("\n📊 Array Size: %,d elements\n", size);
            System.out.println("-".repeat(50));

            // Generate test array with sequential values
            int[] testArray = new int[size];
            for (int i = 0; i < size; i++) {
                testArray[i] = i + 1; // [1, 2, 3, ..., size]
            }

            // Warm up JVM (important for accurate measurements)
            for (int i = 0; i < 100; i++) {
                int[] warmup = testArray.clone();
                solution.reverseArray(warmup);
            }

            // Measure each approach multiple times and take average
            int iterations = 50;
            long totalTime1 = 0, totalTime2 = 0, totalTime3 = 0, totalTime4 = 0, totalTime5 = 0;

            for (int i = 0; i < iterations; i++) {
                // Temporary Array Approach
                int[] arr1 = testArray.clone();
                totalTime1 += measureExecutionTime(() ->
                        solution.reverseArrayTemporary(arr1));

                // Two Pointers Approach
                int[] arr2 = testArray.clone();
                totalTime2 += measureExecutionTime(() ->
                        solution.reverseArrayTwoPointers(arr2));

                // Index-Based Approach (Original)
                int[] arr3 = testArray.clone();
                totalTime3 += measureExecutionTime(() ->
                        solution.reverseArray(arr3));

                // XOR Approach
                int[] arr4 = testArray.clone();
                totalTime4 += measureExecutionTime(() ->
                        solution.reverseArrayXOR(arr4));

                // Skip recursive for large arrays to prevent stack overflow
                if (size <= 10000) {
                    int[] arr5 = testArray.clone();
                    totalTime5 += measureExecutionTime(() ->
                            solution.reverseArrayRecursive(arr5));
                }
            }

            double avgTime1 = totalTime1 / (double) iterations;
            double avgTime2 = totalTime2 / (double) iterations;
            double avgTime3 = totalTime3 / (double) iterations;
            double avgTime4 = totalTime4 / (double) iterations;
            double avgTime5 = totalTime5 / (double) iterations;

            System.out.printf("Temporary Array:    %8.2f μs\n", avgTime1 / 1000.0);
            System.out.printf("Two Pointers:       %8.2f μs\n", avgTime2 / 1000.0);
            System.out.printf("Index-Based:        %8.2f μs ⭐\n", avgTime3 / 1000.0);
            System.out.printf("XOR Swapping:       %8.2f μs\n", avgTime4 / 1000.0);

            if (size <= 10000) {
                System.out.printf("Recursive:          %8.2f μs\n", avgTime5 / 1000.0);
            } else {
                System.out.printf("Recursive:          Skipped (Stack Overflow Risk)\n");
            }

            // Calculate speedup ratios
            double speedup1 = avgTime1 / avgTime3;
            double speedup2 = avgTime2 / avgTime3;

            System.out.printf("vs Temporary Array: %.1fx faster\n", speedup1);
            System.out.printf("vs Two Pointers:    %.1fx faster\n", speedup2);
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
        ReverseAnArray solution = new ReverseAnArray();

        // Test cases with expected results
        int[][] testCases = {
                {1, 4, 3, 2, 6, 5},     // Expected: [5, 6, 2, 3, 4, 1]
                {4, 5, 1, 2},           // Expected: [2, 1, 5, 4]
                {7},                    // Expected: [7]
                {1, 2},                 // Expected: [2, 1]
                {},                     // Expected: []
                {1, 2, 3, 2, 1},       // Expected: [1, 2, 3, 2, 1] (palindrome)
                {5, 5, 5, 5},          // Expected: [5, 5, 5, 5] (all same)
                {1000, 2000, 3000},    // Expected: [3000, 2000, 1000]
                {1, 2, 3, 4, 5, 6, 7}, // Expected: [7, 6, 5, 4, 3, 2, 1] (odd length)
                {10, 20, 30, 40}       // Expected: [40, 30, 20, 10] (even length)
        };

        int[][] expected = {
                {5, 6, 2, 3, 4, 1},
                {2, 1, 5, 4},
                {7},
                {2, 1},
                {},
                {1, 2, 3, 2, 1},
                {5, 5, 5, 5},
                {3000, 2000, 1000},
                {7, 6, 5, 4, 3, 2, 1},
                {40, 30, 20, 10}
        };

        System.out.println("=".repeat(80));
        System.out.println("REVERSE AN ARRAY - TEST RESULTS");
        System.out.println("=".repeat(80));

        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i].clone();
            int[] expectedResult = expected[i];

            System.out.printf("\nTest Case %d: %s\n", i + 1, Arrays.toString(testCase));
            System.out.printf("Expected: %s\n", Arrays.toString(expectedResult));

            // Test all approaches with timing
            int[] arr1 = testCase.clone();
            long startTime = System.nanoTime();
            solution.reverseArrayTemporary(arr1);
            long time1 = System.nanoTime() - startTime;

            int[] arr2 = testCase.clone();
            startTime = System.nanoTime();
            solution.reverseArrayTwoPointers(arr2);
            long time2 = System.nanoTime() - startTime;

            int[] arr3 = testCase.clone();
            startTime = System.nanoTime();
            solution.reverseArray(arr3);
            long time3 = System.nanoTime() - startTime;

            int[] arr4 = testCase.clone();
            startTime = System.nanoTime();
            solution.reverseArrayXOR(arr4);
            long time4 = System.nanoTime() - startTime;

            int[] arr5 = testCase.clone();
            startTime = System.nanoTime();
            solution.reverseArrayRecursive(arr5);
            long time5 = System.nanoTime() - startTime;

            boolean correct1 = arraysEqual(arr1, expectedResult);
            boolean correct2 = arraysEqual(arr2, expectedResult);
            boolean correct3 = arraysEqual(arr3, expectedResult);
            boolean correct4 = arraysEqual(arr4, expectedResult);
            boolean correct5 = arraysEqual(arr5, expectedResult);

            System.out.printf("Temporary Array:    %s %s (%,d ns)\n",
                    Arrays.toString(arr1), correct1 ? "✓" : "✗", time1);
            System.out.printf("Two Pointers:       %s %s (%,d ns)\n",
                    Arrays.toString(arr2), correct2 ? "✓" : "✗", time2);
            System.out.printf("Index-Based:        %s %s (%,d ns)\n",
                    Arrays.toString(arr3), correct3 ? "✓" : "✗", time3);
            System.out.printf("XOR Swapping:       %s %s (%,d ns)\n",
                    Arrays.toString(arr4), correct4 ? "✓" : "✗", time4);
            System.out.printf("Recursive:          %s %s (%,d ns)\n",
                    Arrays.toString(arr5), correct5 ? "✓" : "✗", time5);

            // Verify all approaches give same result
            if (correct1 && correct2 && correct3 && correct4 && correct5) {
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
        System.out.println("Approach           | Time Complexity | Space Complexity | Iterations | Notes");
        System.out.println("-".repeat(80));
        System.out.println("Temporary Array    | O(n)            | O(n)             | 2n         | Uses extra space");
        System.out.println("Two Pointers       | O(n)            | O(1)             | n/2        | Intuitive, readable");
        System.out.println("Index-Based        | O(n)            | O(1)             | n/2        | Optimal performance ⭐");
        System.out.println("XOR Swapping       | O(n)            | O(1)             | n/2        | No temp variable");
        System.out.println("Recursive          | O(n)            | O(n)             | n/2        | Call stack space");
        System.out.println("Built-in Methods   | O(n)            | O(1)             | n/2        | Language-dependent");
        System.out.println("=".repeat(80));

        System.out.println("\n💡 Performance Tips:");
        System.out.println("• Index-based approach has slightly better performance due to simpler loop logic");
        System.out.println("• Two pointers is more intuitive and commonly preferred in interviews");
        System.out.println("• Avoid recursive approach for large arrays (stack overflow risk)");
        System.out.println("• XOR swapping is a clever trick but less readable");
        System.out.println("• Both optimal approaches have O(1) space complexity");
    }
}