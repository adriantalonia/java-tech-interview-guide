# 88. Merge Sorted Arrays Problem

---

<!-- TOC -->
* [Merge Sorted Arrays Problem](#merge-sorted-arrays-problem)
  * [Description](#description)
  * [Explanation](#explanation)
  * [Example of Expected Result](#example-of-expected-result)
  * [Rules](#rules)
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

---

This README describes the "Merge Sorted Arrays" problem without revealing specific solution code or algorithms, setting
the stage for problem-solving.