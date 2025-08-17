package _06_Interview_Preparation._06_1_Arrays._0_Basics._Search_in_a_Matrix;

import java.util.Arrays;

/**
 * Comprehensive comparison of different matrix search algorithms
 * based on matrix properties and their time complexities.
 */
public class MatrixSearchAlgorithms {

    // 1. BRUTE FORCE - Your current approach
    // Time: O(m × n), Space: O(1)
    // Use when: Matrix has no sorted properties
    public static boolean bruteForceSearch(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        for (int[] row : matrix) {
            for (int element : row) {
                if (element == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // 2. BINARY SEARCH ON EACH ROW - For row-wise sorted matrices
    // Time: O(m × log n), Space: O(1)
    // Use when: Each row is sorted individually
    public static boolean binarySearchRows(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // Search each row using binary search
        for (int[] row : matrix) {
            if (binarySearch(row, target)) {
                return true;
            }
        }
        return false;
    }

    // Helper method for binary search in a single array
    private static boolean binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return true;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // 3. STAIRCASE SEARCH - For fully sorted matrices
    // Time: O(m + n), Space: O(1) - BEST for sorted matrices!
    // Use when: Rows are sorted AND columns are sorted
    public static boolean staircaseSearch(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = 0;                    // Start from top row
        int col = matrix[0].length - 1; // Start from rightmost column

        while (row < matrix.length && col >= 0) {
            int current = matrix[row][col];

            if (current == target) {
                return true;           // Found target
            } else if (current > target) {
                col--;                 // Move left (smaller values)
            } else {
                row++;                 // Move down (larger values)
            }
        }
        return false;
    }

    // 4. BINARY SEARCH ON FLATTENED MATRIX - For fully sorted matrices
    // Time: O(log(m × n)), Space: O(1) - FASTEST for sorted matrices!
    // Use when: Matrix is sorted as if it were a 1D array
    public static boolean binarySearchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols]; // Convert 1D index to 2D

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // Test all algorithms with different matrix types
    public static void main(String[] args) {
        System.out.println("=== ALGORITHM PERFORMANCE COMPARISON ===\n");

        // Test Case 1: Unsorted Matrix
        int[][] unsortedMatrix = {
                {15, 2, 89},
                {7, 44, 3},
                {91, 12, 67}
        };
        testAlgorithm("Unsorted Matrix", unsortedMatrix, 44);

        // Test Case 2: Row-wise Sorted Matrix
        int[][] rowSortedMatrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16}
        };
        testAlgorithm("Row-wise Sorted Matrix", rowSortedMatrix, 5);

        // Test Case 3: Fully Sorted Matrix (both rows and columns)
        int[][] fullySortedMatrix = {
                {1,  4,  7,  11, 15},
                {2,  5,  8,  12, 19},
                {3,  6,  9,  16, 22},
                {10, 13, 14, 17, 24}
        };
        testAlgorithm("Fully Sorted Matrix", fullySortedMatrix, 9);

        printComplexityComparison();
    }

    private static void testAlgorithm(String matrixType, int[][] matrix, int target) {
        System.out.println("Matrix Type: " + matrixType);
        System.out.println("Target: " + target);
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nResults:");
        System.out.println("  Brute Force:      " + bruteForceSearch(matrix, target));
        System.out.println("  Binary on Rows:   " + binarySearchRows(matrix, target));
        System.out.println("  Staircase Search: " + staircaseSearch(matrix, target));
        System.out.println("  Binary on Matrix: " + binarySearchMatrix(matrix, target));
        System.out.println("  Built-in Arrays:  " + Arrays.deepToString(matrix).contains(String.valueOf(target)));
        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    private static void printComplexityComparison() {
        System.out.println("TIME COMPLEXITY COMPARISON:");
        System.out.println("┌─────────────────────────┬─────────────────┬──────────────────────┐");
        System.out.println("│ Algorithm               │ Time Complexity │ Best Use Case        │");
        System.out.println("├─────────────────────────┼─────────────────┼──────────────────────┤");
        System.out.println("│ Brute Force            │ O(m × n)        │ Unsorted matrix      │");
        System.out.println("│ Binary Search Rows     │ O(m × log n)    │ Row-wise sorted      │");
        System.out.println("│ Staircase Search       │ O(m + n)        │ Fully sorted         │");
        System.out.println("│ Binary Search Matrix   │ O(log(m × n))   │ Fully sorted (best!) │");
        System.out.println("└─────────────────────────┴─────────────────┴──────────────────────┘");

        System.out.println("\n📊 PERFORMANCE RANKING (for 1000×1000 matrix):");
        System.out.println("1. 🥇 Binary Search Matrix: ~20 operations");
        System.out.println("2. 🥈 Staircase Search:     ~2,000 operations");
        System.out.println("3. 🥉 Binary Search Rows:   ~10,000 operations");
        System.out.println("4. 💀 Brute Force:          ~1,000,000 operations");
    }
}
