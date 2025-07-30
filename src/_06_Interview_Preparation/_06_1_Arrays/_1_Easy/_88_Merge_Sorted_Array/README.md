# 88. Merge Sorted Arrays Problem

---

<!-- TOC -->
* [88. Merge Sorted Arrays Problem](#88-merge-sorted-arrays-problem)
  * [Description](#description)
  * [Explanation](#explanation)
  * [Example of Expected Result](#example-of-expected-result)
  * [Rules](#rules)
  * [Solution](#solution)
  * [Step-by-Step Explanation of How it's Solved](#step-by-step-explanation-of-how-its-solved)
  * [Time and Space Complexity](#time-and-space-complexity)
<!-- TOC -->

---

## Description

The "Merge Sorted Arrays" problem asks you to merge two given integer arrays, `nums1` and `nums2`, into a single, sorted
array. A common variation of this problem specifies that the merge should be done **in-place** into `nums1`, where
`nums1` is guaranteed to have enough space at its end to accommodate all elements from `nums2`.

## Explanation

Imagine you have two separate lists of numbers, and both lists are already neatly sorted from smallest to largest. Your
task is to combine all the numbers from both lists into one single list, ensuring that the final combined list is also
perfectly sorted.

A key aspect of this problem, in its standard form, is that you're often asked to perform this merge directly into the
first array (`nums1`). This means `nums1` will have some valid numbers at its beginning, followed by enough "empty" or
placeholder values (like zeros) at its end to fit all the numbers from `nums2`. You need to carefully place the numbers
from `nums2` into `nums1` while preserving the sorted order of all elements from both original arrays.

## Example of Expected Result

Let's illustrate with a typical scenario:

* `nums1` = `[1, 2, 3, 0, 0, 0]`
    * `m = 3` (This indicates that the first `3` elements of `nums1` are the valid, sorted numbers: `[1, 2, 3]`)
* `nums2` = `[2, 5, 6]`
    * `n = 3` (This indicates that `nums2` has `3` valid, sorted numbers: `[2, 5, 6]`)

The expected output (the modified `nums1` array) would be:

`[1, 2, 2, 3, 5, 6]`

## Rules

1. **Input Arrays:**
    * `nums1`: An integer array, which will be modified in-place. Its length is typically `m + n`, where `m` is the
      number of elements initially in `nums1` and `n` is the number of elements initially in `nums2`. The last `n`
      positions of `nums1` are placeholders (e.g., zeros) and should be overwritten.
    * `m`: The number of initialized elements in `nums1`.
    * `nums2`: An integer array.
    * `n`: The number of initialized elements in `nums2`.
2. **Sorted Input:** Both `nums1` (up to `m` elements) and `nums2` (up to `n` elements) are guaranteed to be sorted in *
   *non-decreasing order**.
3. **Output:** The merged array must be stored directly **in-place within `nums1`**, also in non-decreasing order. Your
   function should modify `nums1` and typically return `void` (or nothing, depending on the language's function
   signature).
4. **Constraints:**
    * `0 <= m, n <= 200` (typical ranges for `m` and `n`)
    * `1 <= m + n <= 200` (typical maximum length of `nums1`)
    * `nums1.length == m + n`
    * `nums2.length == n`

## Solution

```java
public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1;
    int p2 = n - 1;
    int p_merged = m + n - 1;

    while (p1 >= 0 && p2 >= 0) {
        if (nums1[p1] > nums2[p2]) {
            nums1[p_merged] = nums1[p1];
            p1--;
        } else {

            nums1[p_merged] = nums2[p2];
            p2--;
        }
        p_merged--;
    }

    while (p2 >= 0) {
        nums1[p_merged] = nums2[p2];
        p2--;
        p_merged--;
    }
}
```

## Step-by-Step Explanation of How it's Solved

The key insight is to merge from the **end of the arrays backwards**. This is because `nums1` has its available space at
the end. If we were to merge from the beginning, we would constantly have to shift elements of `nums1` to the right to
make space, which is inefficient.

Let's use `nums1 = [1, 2, 3, 0, 0, 0]`, `m = 3` and `nums2 = [2, 5, 6]`, `n = 3`.

1. **Initialize Pointers:**
    * `p1`: points to the last valid element in `nums1`: `nums1[m-1] = nums1[2] = 3`. So, `p1 = 2`.
    * `p2`: points to the last valid element in `nums2`: `nums2[n-1] = nums2[2] = 6`. So, `p2 = 2`.
    * `p_merged`: points to the last position in `nums1` (where the largest element will go):
      `nums1[m+n-1] = nums1[5] = 0`. So, `p_merged = 5`.

   Initial state visualization:

   `nums1`: `[1, 2, 3, 0, 0, 0]`
   `^ p1`          `^ p_merged`
   `nums2`: `[2, 5, 6]`
   `^ p2`

2. **Iterate and Compare (Main Loop):**
   We loop as long as both `p1` (representing valid elements in `nums1`) and `p2` (representing valid elements in
   `nums2`) are pointing to valid indices (i.e., `>= 0`).

    * **Iteration 1:** (`p_merged = 5`, `p1 = 2`, `p2 = 2`)
        * Compare `nums1[p1]` (which is `3`) and `nums2[p2]` (which is `6`).
        * `6 > 3`, so `nums2[p2]` is larger.
        * Place `6` into `nums1[p_merged]`: `nums1` becomes `[1, 2, 3, 0, 0, 6]`
        * Decrement `p2` to `1` and `p_merged` to `4`.

      State after Iteration 1:

      `nums1`: `[1, 2, 3, 0, 0, 6]`
      `^ p1`       `^ p_merged`
      `nums2`: `[2, 5, 6]`
      `^ p2`

    * **Iteration 2:** (`p_merged = 4`, `p1 = 2`, `p2 = 1`)
        * Compare `nums1[p1]` (which is `3`) and `nums2[p2]` (which is `5`).
        * `5 > 3`, so `nums2[p2]` is larger.
        * Place `5` into `nums1[p_merged]`: `nums1` becomes `[1, 2, 3, 0, 5, 6]`
        * Decrement `p2` to `0` and `p_merged` to `3`.

      State after Iteration 2:

      `nums1`: `[1, 2, 3, 0, 5, 6]`
      `^ p1`     `^ p_merged`
      `nums2`: `[2, 5, 6]`
      `^ p2`

    * **Iteration 3:** (`p_merged = 3`, `p1 = 2`, `p2 = 0`)
        * Compare `nums1[p1]` (which is `3`) and `nums2[p2]` (which is `2`).
        * `3 > 2`, so `nums1[p1]` is larger.
        * Place `3` into `nums1[p_merged]`: `nums1` becomes `[1, 2, 3, 3, 5, 6]`
        * Decrement `p1` to `1` and `p_merged` to `2`.

      State after Iteration 3:

      `nums1`: `[1, 2, 3, 3, 5, 6]`
      `^ p1`   `^ p_merged`
      `nums2`: `[2, 5, 6]`
      `^ p2`

    * **Iteration 4:** (`p_merged = 2`, `p1 = 1`, `p2 = 0`)
        * Compare `nums1[p1]` (which is `2`) and `nums2[p2]` (which is `2`).
        * `2 >= 2`, so we can pick `nums2[p2]`. (Picking `nums1[p1]` here would also be fine, but picking from `nums2`
          helps ensure stability if equal elements from `nums2` are to appear after equal elements from `nums1`).
        * Place `2` into `nums1[p_merged]`: `nums1` becomes `[1, 2, 2, 3, 5, 6]`
        * Decrement `p2` to `-1` and `p_merged` to `1`.

      State after Iteration 4:

      `nums1`: `[1, 2, 2, 3, 5, 6]`
      `^ p1` `^ p_merged`
      `nums2`: `[2, 5, 6]`
      `^ p2` (now -1, main loop terminates)

3. **Handle Remaining Elements (Post-Loop):**
   The main loop terminates when either `p1` or `p2` becomes less than 0.
    * If `p1 < 0`: This means all elements from `nums1` have been considered. Any remaining elements in `nums2` (which
      would necessarily be smaller than all elements already placed) simply need to be copied directly into the
      remaining empty slots at the beginning of `nums1`. The second `while (p2 >= 0)` loop in the code handles this.
    * If `p2 < 0`: This means all elements from `nums2` have been placed. The remaining elements in `nums1` (from `0` to
      `p1`) are already in their correct sorted positions relative to each other, and the earlier comparisons would have
      shifted the larger ones to the right. So, no further action is needed for `p1`.

   In our example, `p2` became `-1`, so the main loop finished. The second `while (p2 >= 0)` loop is skipped. The array
   `nums1` is now `[1, 2, 2, 3, 5, 6]`, which is the correct merged result.

## Time and Space Complexity

* **Time Complexity: $O(m + n)$**
    * The algorithm iterates through both arrays essentially once, from end to beginning. In the worst case, each
      element from `nums1` (up to `m`) and each element from `nums2` (up to `n`) will be visited and placed exactly
      once.
    * Therefore, the number of operations is directly proportional to the total number of elements being merged (
      `m + n`).

* **Space Complexity: $O(1)$**
    * The algorithm performs the merge **in-place** directly within `nums1`.
    * It only uses a few constant extra variables for the pointers (`p1`, `p2`, `p_merged`). It does not allocate any
      new arrays or data structures that scale with the input size.