package _06_Interview_Preparation._06_1_Arrays._1_Easy._88_Merge_Sorted_Array;

import java.util.Arrays;

public class MergeSortedArray {
    /**
     * Merges two sorted integer arrays, nums1 and nums2, into nums1 in-place.
     * The array nums1 has a length of m + n, where the first m elements are
     * the elements to be merged, and the last n elements are 0s (or placeholders)
     * which should be overwritten.
     *
     * @param nums1 The first array, which will store the merged result.
     *              Its initial m elements are sorted, and the remaining n elements are 0s.
     * @param m     The number of valid elements in nums1.
     * @param nums2 The second array, containing n sorted elements.
     * @param n     The number of valid elements in nums2.
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize three pointers:
        // 1. p1: points to the last valid element in nums1 (index m-1).
        int p1 = m - 1;
        // 2. p2: points to the last valid element in nums2 (index n-1).
        int p2 = n - 1;
        // 3. p_merged: points to the last position in nums1 where an element should be placed (index m + n - 1).
        int p_merged = m + n - 1;

        // Iterate backwards from the end of both arrays until one of the arrays is fully processed.
        // This loop continues as long as there are elements left in both nums1 (from 0 to m-1)
        // and nums2 (from 0 to n-1) to compare.
        while (p1 >= 0 && p2 >= 0) {
            // Compare the elements at the current pointers of nums1 and nums2.
            if (nums1[p1] > nums2[p2]) {
                // If the element in nums1 is larger, it should go at the current merged position.
                nums1[p_merged] = nums1[p1];
                // Move p1 to the left (to the next smaller element in nums1).
                p1--;
            } else {
                // If the element in nums2 is larger or equal, it should go at the current merged position.
                // We pick nums2[p2] for equality because its value is effectively "newer" and ensures stability
                // if two numbers are identical.
                nums1[p_merged] = nums2[p2];
                // Move p2 to the left (to the next smaller element in nums2).
                p2--;
            }
            // In either case, move the merged pointer to the left (to the next available spot in nums1).
            p_merged--;
        }

        // After the loop, one of the arrays might still have remaining elements.
        // Since nums1 elements are already in their correct (or will be) positions if p2 finished first,
        // we only need to handle the case where nums2 still has elements left.
        // If p2 is still >= 0, it means there are remaining elements in nums2 that are smaller
        // than all remaining elements in nums1 (which would have already been placed).
        // These remaining elements from nums2 just need to be copied directly into the beginning of nums1.
        while (p2 >= 0) {
            nums1[p_merged] = nums2[p2];
            p2--;
            p_merged--;
        }
        // No need to handle p1 remaining, as its elements are already in nums1 and correctly positioned
        // relative to each other; they just needed to shift to the right, which the merge loop handled.
    }

    public static void main(String[] args) {
        // Test Case 1: Standard example
        int[] nums1_1 = {1, 2, 3, 0, 0, 0};
        int m1 = 3;
        int[] nums2_1 = {2, 5, 6};
        int n1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("nums1 (before): " + Arrays.toString(nums1_1) + ", m=" + m1);
        System.out.println("nums2: " + Arrays.toString(nums2_1) + ", n=" + n1);
        merge(nums1_1, m1, nums2_1, n1);
        System.out.println("nums1 (after): " + Arrays.toString(nums1_1)); // Expected: [1, 2, 2, 3, 5, 6]
        System.out.println("---");

        // Test Case 2: nums1 is empty initially
        int[] nums1_2 = {0, 0, 0, 0};
        int m2 = 0;
        int[] nums2_2 = {1, 2, 3, 4};
        int n2 = 4;
        System.out.println("Test Case 2 (nums1 initially empty):");
        System.out.println("nums1 (before): " + Arrays.toString(nums1_2) + ", m=" + m2);
        System.out.println("nums2: " + Arrays.toString(nums2_2) + ", n=" + n2);
        merge(nums1_2, m2, nums2_2, n2);
        System.out.println("nums1 (after): " + Arrays.toString(nums1_2)); // Expected: [1, 2, 3, 4]
        System.out.println("---");

        // Test Case 3: nums2 is empty
        int[] nums1_3 = {7, 8, 9};
        int m3 = 3;
        int[] nums2_3 = {};
        int n3 = 0;
        System.out.println("Test Case 3 (nums2 empty):");
        System.out.println("nums1 (before): " + Arrays.toString(nums1_3) + ", m=" + m3);
        System.out.println("nums2: " + Arrays.toString(nums2_3) + ", n=" + n3);
        merge(nums1_3, m3, nums2_3, n3);
        System.out.println("nums1 (after): " + Arrays.toString(nums1_3)); // Expected: [7, 8, 9] (unchanged)
        System.out.println("---");

        // Test Case 4: Elements in nums2 are smaller than all elements in nums1
        int[] nums1_4 = {4, 5, 6, 0, 0, 0};
        int m4 = 3;
        int[] nums2_4 = {1, 2, 3};
        int n4 = 3;
        System.out.println("Test Case 4 (nums2 elements smaller):");
        System.out.println("nums1 (before): " + Arrays.toString(nums1_4) + ", m=" + m4);
        System.out.println("nums2: " + Arrays.toString(nums2_4) + ", n=" + n4);
        merge(nums1_4, m4, nums2_4, n4);
        System.out.println("nums1 (after): " + Arrays.toString(nums1_4)); // Expected: [1, 2, 3, 4, 5, 6]
        System.out.println("---");
    }
}
