package _06_Interview_Preparation._06_0_GFG_160_problems._01_Arrays._04_Rotate_an_Array;

import java.util.Arrays;

/**
 * RotateArray
 * <p>
 * Problem: Given an array of integers arr[] of size n, rotate the array elements
 * to the left by d positions. Left rotation means moving elements towards the
 * beginning of the array, where elements that move past the first position wrap
 * around to the end.
 * <p>
 * Note: The operation should modify the original array in-place when possible.
 * <p>
 * Time Complexity Solutions:
 * 1. Naive Approach: O(n × d) time, O(1) space
 * 2. Temporary Array Approach: O(n) time, O(n) space
 * 3. Juggling Algorithm: O(n) time, O(1) space
 * 4. Reversal Algorithm: O(n) time, O(1) space - Optimal ⭐⭐
 */
public class RotateArray {

    /**
     * Approach 1: Naive Approach - Rotate One by One
     * <p>
     * Algorithm:
     * 1. Perform d individual left rotations
     * 2. For each rotation, store first element and shift all others left
     * 3. Place stored element at the end
     * <p>
     * Time Complexity: O(n × d) - d iterations, each taking O(n) time
     * Space Complexity: O(1) - only uses temporary variable
     * <p>
     * Warning: Becomes O(n²) when d ≈ n, avoid for large inputs!
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArrayNaive(int[] arr, int d) {
        int n = arr.length;

        // Edge cases: handle invalid inputs
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        // Optimize: reduce unnecessary rotations when d > n
        d = d % n;
        if (d == 0) return; // No rotation needed

        // Perform d individual left rotations
        for (int rotation = 0; rotation < d; rotation++) {
            // Store the first element (it will move to the end)
            int first = arr[0];

            // Shift all elements one position to the left
            for (int i = 0; i < n - 1; i++) {
                arr[i] = arr[i + 1];
            }

            // Place the first element at the end
            arr[n - 1] = first;
        }
    }

    /**
     * Approach 2: Temporary Array Approach
     * <p>
     * Algorithm:
     * 1. Create temporary array of size n
     * 2. Copy last (n - d) elements to front of temp array
     * 3. Copy first d elements to end of temp array
     * 4. Copy all elements back to original array
     * <p>
     * Time Complexity: O(n) - three linear scans
     * Space Complexity: O(n) - temporary array of same size
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArrayTemporary(int[] arr, int d) {
        int n = arr.length;

        // Edge cases: handle invalid inputs
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        // Handle case when d > n
        d = d % n;
        if (d == 0) return; // No rotation needed

        // Create temporary array to store rotated elements
        int[] temp = new int[n];

        // Copy last (n - d) elements to the front of temp array
        // These elements will be at positions [0, n-d-1] after rotation
        for (int i = 0; i < n - d; i++) {
            temp[i] = arr[d + i];
        }

        // Copy first d elements to the end of temp array
        // These elements will be at positions [n-d, n-1] after rotation
        for (int i = 0; i < d; i++) {
            temp[n - d + i] = arr[i];
        }

        // Copy all elements back from temp to original array
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * Approach 3: Juggling Algorithm
     * <p>
     * Algorithm:
     * 1. Find GCD of n and d to determine number of cycles
     * 2. For each cycle, move elements to their final positions
     * 3. Each cycle handles elements that shift among themselves
     * <p>
     * Time Complexity: O(n) - each element is moved exactly once
     * Space Complexity: O(1) - only uses temporary variables
     * <p>
     * Mathematical insight: Number of cycles = GCD(n, d)
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArrayJuggling(int[] arr, int d) {
        int n = arr.length;

        // Edge cases: handle invalid inputs
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        // Handle case when d > n
        d = d % n;
        if (d == 0) return; // No rotation needed

        // Find GCD to determine number of cycles
        int gcd = findGCD(n, d);

        // Process each cycle
        for (int cycleStart = 0; cycleStart < gcd; cycleStart++) {
            // Store the first element of current cycle
            int temp = arr[cycleStart];
            int currentPos = cycleStart;

            // Move elements within the cycle until we complete the loop
            while (true) {
                // Calculate next position in the cycle
                int nextPos = (currentPos + d) % n;

                // If we've completed the cycle, place temp element
                if (nextPos == cycleStart) {
                    arr[currentPos] = temp;
                    break;
                }

                // Move element from next position to current position
                arr[currentPos] = arr[nextPos];
                currentPos = nextPos;
            }
        }
    }

    /**
     * Approach 4: Reversal Algorithm ⭐⭐ (Most Preferred)
     * <p>
     * Algorithm:
     * 1. Reverse the first d elements
     * 2. Reverse the remaining (n - d) elements
     * 3. Reverse the entire array
     * <p>
     * Time Complexity: O(n) - three O(n) reversal operations
     * Space Complexity: O(1) - in-place operation using only temp variables
     * <p>
     * Why it works: Left rotation by d can be viewed as moving first d elements
     * to the end and last (n-d) elements to the front. The three reversals
     * achieve this transformation elegantly.
     * <p>
     * This is the optimal solution for interviews!
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArray(int[] arr, int d) {
        int n = arr.length;

        // Edge cases: handle invalid inputs
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        // Handle case when d > n (reduce unnecessary rotations)
        d = d % n;
        if (d == 0) return; // No rotation needed

        // Step 1: Reverse the first d elements
        // Example: [1,2,3,4,5,6], d=2 → [2,1,3,4,5,6]
        reverse(arr, 0, d - 1);

        // Step 2: Reverse the remaining (n - d) elements
        // Example: [2,1,3,4,5,6] → [2,1,6,5,4,3]
        reverse(arr, d, n - 1);

        // Step 3: Reverse the entire array
        // Example: [2,1,6,5,4,3] → [3,4,5,6,1,2] ✓
        reverse(arr, 0, n - 1);
    }

    /**
     * Helper method to reverse a portion of the array
     * Uses two-pointers approach for optimal performance
     *
     * @param arr   array to reverse
     * @param start starting index (inclusive)
     * @param end   ending index (inclusive)
     */
    private void reverse(int[] arr, int start, int end) {
        // Use two pointers moving toward each other
        while (start < end) {
            // Swap elements at start and end positions
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move pointers toward center
            start++;
            end--;
        }
    }

    /**
     * Helper method to find Greatest Common Divisor using Euclidean algorithm
     * Used in Juggling Algorithm to determine number of cycles
     *
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    private int findGCD(int a, int b) {
        // Base case: when b becomes 0, a is the GCD
        if (b == 0) {
            return a;
        }
        // Recursive case: GCD(a, b) = GCD(b, a % b)
        return findGCD(b, a % b);
    }

    /**
     * Alternative implementation: Right Rotation
     * Right rotation by d positions = Left rotation by (n - d) positions
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate right
     */
    public void rotateArrayRight(int[] arr, int d) {
        int n = arr.length;
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        // Convert right rotation to left rotation
        int leftRotations = n - (d % n);
        rotateArray(arr, leftRotations);
    }

    /**
     * XOR-based rotation using reversal algorithm (no temporary variables)
     * Note: Only works when elements being swapped are different
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArrayXOR(int[] arr, int d) {
        int n = arr.length;
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        d = d % n;
        if (d == 0) return;

        reverseXOR(arr, 0, d - 1);
        reverseXOR(arr, d, n - 1);
        reverseXOR(arr, 0, n - 1);
    }

    /**
     * Helper method for XOR-based reversal
     */
    private void reverseXOR(int[] arr, int start, int end) {
        while (start < end) {
            // Only use XOR if elements are different (avoid a^a = 0)
            if (arr[start] != arr[end]) {
                arr[start] ^= arr[end];
                arr[end] ^= arr[start];
                arr[start] ^= arr[end];
            }
            start++;
            end--;
        }
    }

    /**
     * Recursive implementation of reversal algorithm
     * For educational purposes - not recommended for large arrays
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArrayRecursive(int[] arr, int d) {
        int n = arr.length;
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        d = d % n;
        if (d == 0) return;

        reverseRecursive(arr, 0, d - 1);
        reverseRecursive(arr, d, n - 1);
        reverseRecursive(arr, 0, n - 1);
    }

    /**
     * Recursive helper for array reversal
     */
    private void reverseRecursive(int[] arr, int start, int end) {
        // Base case: when pointers meet or cross
        if (start >= end) {
            return;
        }

        // Swap elements at start and end
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        // Recursive call with updated bounds
        reverseRecursive(arr, start + 1, end - 1);
    }

    /**
     * Block Swap Algorithm (Advanced - for educational purposes)
     * Divides array into blocks and swaps them recursively
     *
     * @param arr input array to rotate
     * @param d   number of positions to rotate left
     */
    public void rotateArrayBlockSwap(int[] arr, int d) {
        int n = arr.length;
        if (arr == null || n <= 1 || d <= 0) {
            return;
        }

        d = d % n;
        if (d == 0) return;

        blockSwap(arr, 0, d, n - d);
    }

    /**
     * Helper method for block swap algorithm
     */
    private void blockSwap(int[] arr, int i, int a, int b) {
        // Base cases
        if (a == 0 || b == 0) return;

        if (a == b) {
            // Swap blocks of equal size
            swapBlocks(arr, i, i + a, a);
            return;
        }

        if (a < b) {
            // First block is smaller
            swapBlocks(arr, i, i + a + b - a, a);
            blockSwap(arr, i, a, b - a);
        } else {
            // Second block is smaller
            swapBlocks(arr, i, i + a, b);
            blockSwap(arr, i + b, a - b, b);
        }
    }

    /**
     * Helper method to swap two blocks of arrays
     */
    private void swapBlocks(int[] arr, int start1, int start2, int size) {
        for (int i = 0; i < size; i++) {
            int temp = arr[start1 + i];
            arr[start1 + i] = arr[start2 + i];
            arr[start2 + i] = temp;
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
     * Performance benchmark with different array sizes and rotation values
     */
    private static void performanceBenchmark() {
        RotateArray solution = new RotateArray();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE BENCHMARK - ROTATE ARRAY LEFT");
        System.out.println("=".repeat(80));

        // Test with different array sizes and rotation percentages
        int[] sizes = {1000, 10000, 100000};
        double[] rotationPercentages = {0.1, 0.25, 0.5}; // 10%, 25%, 50% of array size

        for (int size : sizes) {
            for (double rotPercentage : rotationPercentages) {
                int d = (int) (size * rotPercentage);

                System.out.printf("\n📊 Array Size: %,d elements, d = %,d (%.0f%%)\n",
                        size, d, rotPercentage * 100);
                System.out.println("-".repeat(60));

                // Generate test array
                int[] testArray = new int[size];
                for (int i = 0; i < size; i++) {
                    testArray[i] = i + 1;
                }

                // Warm up JVM
                for (int i = 0; i < 50; i++) {
                    int[] warmup = testArray.clone();
                    solution.rotateArray(warmup, d);
                }

                // Measure each approach (skip naive for large d to avoid timeout)
                int iterations = 30;
                long totalTime1 = 0, totalTime2 = 0, totalTime3 = 0, totalTime4 = 0, totalTime5 = 0;

                for (int i = 0; i < iterations; i++) {
                    // Skip naive approach for large rotations (too slow)
                    if (d <= 1000) {
                        int[] arr1 = testArray.clone();
                        totalTime1 += measureExecutionTime(() ->
                                solution.rotateArrayNaive(arr1, d));
                    }

                    // Temporary Array Approach
                    int[] arr2 = testArray.clone();
                    totalTime2 += measureExecutionTime(() ->
                            solution.rotateArrayTemporary(arr2, d));

                    // Juggling Algorithm
                    int[] arr3 = testArray.clone();
                    totalTime3 += measureExecutionTime(() ->
                            solution.rotateArrayJuggling(arr3, d));

                    // Reversal Algorithm
                    int[] arr4 = testArray.clone();
                    totalTime4 += measureExecutionTime(() ->
                            solution.rotateArray(arr4, d));

                    // Block Swap Algorithm
                    int[] arr5 = testArray.clone();
                    totalTime5 += measureExecutionTime(() ->
                            solution.rotateArrayBlockSwap(arr5, d));
                }

                double avgTime1 = totalTime1 / (double) iterations;
                double avgTime2 = totalTime2 / (double) iterations;
                double avgTime3 = totalTime3 / (double) iterations;
                double avgTime4 = totalTime4 / (double) iterations;
                double avgTime5 = totalTime5 / (double) iterations;

                if (d <= 1000) {
                    System.out.printf("Naive O(n×d):          %8.2f μs\n", avgTime1 / 1000.0);
                } else {
                    System.out.printf("Naive O(n×d):          Skipped (Too Slow)\n");
                }
                System.out.printf("Temporary Array:        %8.2f μs\n", avgTime2 / 1000.0);
                System.out.printf("Juggling Algorithm:     %8.2f μs\n", avgTime3 / 1000.0);
                System.out.printf("Reversal Algorithm:     %8.2f μs ⭐⭐\n", avgTime4 / 1000.0);
                System.out.printf("Block Swap Algorithm:   %8.2f μs\n", avgTime5 / 1000.0);

                // Show relative performance
                double fastest = Math.min(Math.min(avgTime2, avgTime3), Math.min(avgTime4, avgTime5));
                System.out.printf("Best performer: %.1fx baseline\n", fastest / avgTime4);
            }
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
     * Test all approaches with comprehensive test cases
     */
    public static void main(String[] args) {
        RotateArray solution = new RotateArray();

        // Test cases: {input array, d, expected result}
        Object[][] testCases = {
                // Basic cases
                {new int[]{1, 2, 3, 4, 5, 6}, 2, new int[]{3, 4, 5, 6, 1, 2}},
                {new int[]{1, 2, 3}, 4, new int[]{2, 3, 1}}, // d > n
                {new int[]{4, 5, 1, 2}, 1, new int[]{5, 1, 2, 4}},

                // Edge cases
                {new int[]{7}, 5, new int[]{7}}, // Single element
                {new int[]{1, 2}, 1, new int[]{2, 1}}, // Two elements
                {new int[]{}, 3, new int[]{}}, // Empty array
                {new int[]{1, 2, 3, 4}, 0, new int[]{1, 2, 3, 4}}, // d = 0
                {new int[]{1, 2, 3, 4}, 4, new int[]{1, 2, 3, 4}}, // d = n

                // Special cases
                {new int[]{5, 5, 5, 5}, 2, new int[]{5, 5, 5, 5}}, // All same
                {new int[]{1, 2, 3, 2, 1}, 2, new int[]{3, 2, 1, 1, 2}}, // Contains duplicates
                {new int[]{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{4, 5, 6, 7, 1, 2, 3}}, // Odd length
                {new int[]{10, 20, 30, 40, 50, 60}, 10, new int[]{50, 60, 10, 20, 30, 40}}, // d > n

                // Large rotations
                {new int[]{1, 2, 3, 4, 5}, 7, new int[]{3, 4, 5, 1, 2}}, // 7 % 5 = 2
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 11, new int[]{4, 5, 6, 7, 8, 1, 2, 3}} // 11 % 8 = 3
        };

        System.out.println("=".repeat(90));
        System.out.println("ROTATE ARRAY LEFT BY D POSITIONS - TEST RESULTS");
        System.out.println("=".repeat(90));

        for (int i = 0; i < testCases.length; i++) {
            int[] testArray = (int[]) testCases[i][0];
            int d = (int) testCases[i][1];
            int[] expected = (int[]) testCases[i][2];

            System.out.printf("\nTest Case %d: %s, d = %d\n",
                    i + 1, Arrays.toString(testArray), d);
            System.out.printf("Expected: %s\n", Arrays.toString(expected));

            // Test all approaches
            int[] arr1 = testArray.clone();
            int[] arr2 = testArray.clone();
            int[] arr3 = testArray.clone();
            int[] arr4 = testArray.clone();
            int[] arr5 = testArray.clone();
            int[] arr6 = testArray.clone();

            // Measure execution times
            long time1 = 0, time2 = 0, time3 = 0, time4 = 0, time5 = 0, time6 = 0;

            // Skip naive approach for large d to avoid timeout
            if (d <= 100) {
                long start = System.nanoTime();
                solution.rotateArrayNaive(arr1, d);
                time1 = System.nanoTime() - start;
            }

            long start = System.nanoTime();
            solution.rotateArrayTemporary(arr2, d);
            time2 = System.nanoTime() - start;

            start = System.nanoTime();
            solution.rotateArrayJuggling(arr3, d);
            time3 = System.nanoTime() - start;

            start = System.nanoTime();
            solution.rotateArray(arr4, d);
            time4 = System.nanoTime() - start;

            start = System.nanoTime();
            solution.rotateArrayBlockSwap(arr5, d);
            time5 = System.nanoTime() - start;

            start = System.nanoTime();
            solution.rotateArrayRecursive(arr6, d);
            time6 = System.nanoTime() - start;

            // Verify correctness
            boolean correct1 = d > 100 || arraysEqual(arr1, expected);
            boolean correct2 = arraysEqual(arr2, expected);
            boolean correct3 = arraysEqual(arr3, expected);
            boolean correct4 = arraysEqual(arr4, expected);
            boolean correct5 = arraysEqual(arr5, expected);
            boolean correct6 = arraysEqual(arr6, expected);

            // Display results
            if (d <= 100) {
                System.out.printf("Naive O(n×d):          %s %s (%,d ns)\n",
                        Arrays.toString(arr1), correct1 ? "✓" : "✗", time1);
            } else {
                System.out.printf("Naive O(n×d):          Skipped (d too large)\n");
            }
            System.out.printf("Temporary Array:        %s %s (%,d ns)\n",
                    Arrays.toString(arr2), correct2 ? "✓" : "✗", time2);
            System.out.printf("Juggling Algorithm:     %s %s (%,d ns)\n",
                    Arrays.toString(arr3), correct3 ? "✓" : "✗", time3);
            System.out.printf("Reversal Algorithm:     %s %s (%,d ns)\n",
                    Arrays.toString(arr4), correct4 ? "✓" : "✗", time4);
            System.out.printf("Block Swap Algorithm:   %s %s (%,d ns)\n",
                    Arrays.toString(arr5), correct5 ? "✓" : "✗", time5);
            System.out.printf("Recursive Reversal:     %s %s (%,d ns)\n",
                    Arrays.toString(arr6), correct6 ? "✓" : "✗", time6);

            // Overall verification
            boolean allCorrect = (d > 100 || correct1) && correct2 && correct3 &&
                    correct4 && correct5 && correct6;
            if (allCorrect) {
                System.out.println("All approaches correct! ✓");
            } else {
                System.out.println("⚠️  SOME APPROACHES FAILED!");
            }
        }

        // Run performance benchmark
        performanceBenchmark();

        // Display complexity comparison
        System.out.println("\n" + "=".repeat(90));
        System.out.println("COMPLEXITY COMPARISON - ROTATE ARRAY LEFT BY D POSITIONS");
        System.out.println("=".repeat(90));
        System.out.println("Approach              | Time Complexity | Space Complexity | Best For");
        System.out.println("-".repeat(90));
        System.out.println("Naive (One by One)    | O(n × d)        | O(1)             | Very small d values");
        System.out.println("Temporary Array       | O(n)            | O(n)             | When space is available");
        System.out.println("Juggling Algorithm    | O(n)            | O(1)             | Mathematical elegance");
        System.out.println("Reversal Algorithm    | O(n)            | O(1)             | Interviews ⭐⭐");
        System.out.println("Block Swap Algorithm  | O(n)            | O(1)             | Advanced/research");
        System.out.println("Recursive Reversal    | O(n)            | O(n)             | Educational only");
        System.out.println("=".repeat(90));

        System.out.println("\n💡 Algorithm Selection Guide:");
        System.out.println("• Reversal Algorithm: Best for interviews (simple, optimal, easy to explain)");
        System.out.println("• Juggling Algorithm: Good for understanding mathematical concepts");
        System.out.println("• Temporary Array: Use when O(n) space is acceptable and clarity is priority");
        System.out.println("• Naive Approach: Avoid except for very small d values (d < 10)");
        System.out.println("• Always handle edge cases: d=0, d>n, empty arrays, single elements");
        System.out.println("• Remember: d % n optimization is crucial for large d values");

        System.out.println("\n🎯 Interview Tips:");
        System.out.println("• Start with brute force, then optimize to reversal algorithm");
        System.out.println("• Explain why reversal works: visualize the three-step process");
        System.out.println("• Handle d > n case explicitly: d = d % n");
        System.out.println("• Walk through example step by step");
        System.out.println("• Mention time/space complexity for each approach");
    }
}
