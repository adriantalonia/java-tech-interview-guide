package _06_Interview_Preparation._06_1_Arrays._0_Basics._01_Search_in_a_Matrix;


import java.util.Arrays;

/**
 * A utility class for searching elements in a 2D matrix.
 * This implementation uses a brute force approach with O(m*n) time complexity.
 */
public class SearchInAMatrix {

    /**
     * Searches for a target value in a 2D matrix using linear search.
     *
     * @param mat The 2D integer matrix to search in
     * @param x   The target value to find
     * @return    true if the target value is found, false otherwise
     *
     * Time Complexity: O(m * n) where m is rows and n is columns
     * Space Complexity: O(1) - only using constant extra space
     */
    public static boolean searchMatrix(int[][] mat, int x) {
        // Input validation - check if matrix is null or empty
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return false;
        }

        // Iterate through each row of the matrix
        for (int[] row : mat) {
            // Iterate through each column in the current row
            for (int element : row) {
                // Check if current element matches the target value
                if (element == x) {
                    return true; // Target found, return immediately
                }
            }
        }

        // Target not found after checking all elements
        return false;
    }

    /**
     * Method 2: Enhanced for loops (cleaner syntax)
     */
    public static void printMatrixEnhanced(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method 3: Using Arrays.toString() for each row
     */
    public static void printMatrixArraysToString(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Method 5: Quick one-liner using Arrays.deepToString()
     * Best for debugging/quick viewing
     */
    public static void printMatrixDeepToString(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * Main method to test the searchMatrix functionality.
     */
    public static void main(String[] args) {
        // Create a test matrix with sample data
        int[][] mat = {
                {6, 23, 21},
                {4, 45, 32},
                {69, 11, 87}
        };

        // Test searching for value 32 (should return true)
        int target = 32;
        boolean result = searchMatrix(mat, target);
        printMatrixDeepToString(mat);
        System.out.println("Searching for " + target + ": " + result);

        // Additional test cases
        System.out.println("Searching for 99: " + searchMatrix(mat, 99)); // false
        System.out.println("Searching for 6: " + searchMatrix(mat, 6));   // true
    }
}
