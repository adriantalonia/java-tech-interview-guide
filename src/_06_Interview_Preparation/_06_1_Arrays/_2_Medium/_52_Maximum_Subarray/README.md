# Maximum Subarray Problem

<!-- TOC -->
* [Maximum Subarray Problem](#maximum-subarray-problem)
  * [Description](#description)
  * [Explanation](#explanation)
  * [Example of Expected Result](#example-of-expected-result)
  * [Rules](#rules)
  * [Solution](#solution)
    * [Step-by-Step Execution Trace](#step-by-step-execution-trace)
  * [Optimal Time Complexity: O(n)](#optimal-time-complexity-on)
  * [Optimal Space Complexity: O(1)](#optimal-space-complexity-o1)
  * [How it works (The Logic - Kadane's Algorithm)](#how-it-works-the-logic---kadanes-algorithm)
  * [What is Kadane's Algorithm?](#what-is-kadanes-algorithm)
    * [How Kadane's Algorithm Works (The Logic)](#how-kadanes-algorithm-works-the-logic)
      * [Initialization](#initialization)
      * [Iteration](#iteration)
<!-- TOC -->

## Description

The "Maximum Subarray" problem is a classic algorithmic challenge that asks you to find the contiguous subarray within a
one-dimensional array of numbers (containing at least one number) which has the largest sum.

A "contiguous subarray" means a sequence of elements that are adjacent in the original array. You are looking for the
portion of the array that, when all its numbers are added together, results in the greatest possible sum.

## Explanation

Imagine you have a list of stock prices over several days, where each number represents the profit or loss on that
particular day. You want to find the period (a contiguous block of days) during which your accumulated profit was
maximized. This is the essence of the Maximum Subarray problem. It's not about picking any numbers, but a continuous
segment.

## Example of Expected Result

Let's say you're given the following array:

* `nums` = `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`

The contiguous subarray with the largest sum is `[4, -1, 2, 1]`.

The sum of this subarray is `4 + (-1) + 2 + 1 = 6`.

Therefore, the expected output for this example is `6`.

## Rules

1. **Input:** You will be given a single array of integers (`nums`). The array will contain at least one number.
2. **Output:** Your solution should return a single integer representing the **largest sum** of any contiguous subarray
   within the input array.
3. **Contiguous:** The subarray must consist of consecutive elements from the original array. You cannot skip elements.
4. **At Least One Number:** The subarray must contain at least one number. This implies that if all numbers are
   negative, you should return the largest single negative number (e.g., for `[-2, -1]`, the max subarray sum is `-1`).

---

## Solution

```java
public static int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int currentMax = nums[0];
    int maxSoFar = nums[0];

    for (int i = 1; i < nums.length; i++) {
        currentMax = Math.max(currentMax + nums[i], nums[i]);
        maxSoFar = Math.max(maxSoFar, currentMax);
    }
    return maxSoFar;
}
```

### Step-by-Step Execution Trace

This table demonstrates the execution flow of Kadane's Algorithm with the example
`nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`. The goal is to find the maximum contiguous subarray sum.

| Step  | Index (i) | `nums[i]` | `currentMax` (before update) | `currentMax + nums[i]` | `Math.max(currentMax + nums[i], nums[i])` (new `currentMax`) | `maxSoFar` (before update)      | `Math.max(maxSoFar, currentMax)` (new `maxSoFar`) |
|:------|:----------|:----------|:-----------------------------|:-----------------------|:-------------------------------------------------------------|:--------------------------------|:--------------------------------------------------|
| Init  | -         | -         | -                            | -                      | -2 (initialized with `nums[0]`)                              | -2 (initialized with `nums[0]`) |                                                   |
| 1     | 1         | 1         | -2                           | -1                     | 1                                                            | -2                              | 1                                                 |
| 2     | 2         | -3        | 1                            | -2                     | -2                                                           | 1                               | 1                                                 |
| 3     | 3         | 4         | -2                           | 2                      | 4                                                            | 1                               | 4                                                 |
| 4     | 4         | -1        | 4                            | 3                      | 3                                                            | 4                               | 4                                                 |
| 5     | 5         | 2         | 3                            | 5                      | 5                                                            | 4                               | 5                                                 |
| 6     | 6         | 1         | 5                            | 6                      | 6                                                            | 5                               | 6                                                 |
| 7     | 7         | -5        | 6                            | 1                      | 1                                                            | 6                               | 6                                                 |
| 8     | 8         | 4         | 1                            | 5                      | 5                                                            | 6                               | 6                                                 |
| Final |           |           |                              |                        |                                                              |                                 | **Result: 6**                                     |

## Optimal Time Complexity: O(n)

- **Explanation**: The algorithm performs a single pass through the input array. For each element, it executes a
  constant number of operations (one addition and two Math.max comparisons).

- **Why it's Optimal**: To determine the maximum subarray sum, every element in the array must be examined at least once
  to ascertain its potential contribution. Since Kadane's Algorithm accomplishes this in a single, linear scan, it
  achieves the absolute minimum possible time complexity for this problem. No faster algorithm can exist, as simply
  reading the input requires O(n) time.

## Optimal Space Complexity: O(1)

- **Explanation**: The algorithm uses a fixed, small number of variables (currentMax, maxSoFar, and the loop counter i)
  regardless of the size of the input array. It does not require any additional data structures whose memory consumption
  grows with the input size.

- **Why it's Optimal**: It uses a constant amount of auxiliary space. This is the most memory-efficient solution
  possible, as you cannot solve the problem without storing at least some intermediate values.

## How it works (The Logic - Kadane's Algorithm)

Kadane's Algorithm is a dynamic programming approach that relies on two key variables to efficiently track the maximum
subarray sum:

1. _currentMax (or max_ending_here):_

    * **Purpose**: This variable stores the maximum sum of a contiguous subarray that ends at the current position (i).
    * **Logic**: When considering nums[i], there are two possibilities for the maximum subarray ending at i:
        * The subarray consists only of nums[i] itself. This means starting a new subarray from nums[i].
        * The subarray is nums[i] appended to the maximum subarray that ended at the previous position (i-1).
    * currentMax = Math.max(currentMax + nums[i], nums[i]);
        * This line captures this logic. It compares taking the current number alone (nums[i]) versus extending the
          previous best subarray (currentMax + nums[i]). It chooses whichever yields a larger sum ending at the current
          point. If currentMax + nums[i] becomes negative, it implies that adding the previous subarray sum would make
          the current sum smaller than starting fresh with nums[i], so it resets currentMax to nums[i].

2. _maxSoFar (or max_global):_

    * **Purpose**: This variable stores the overall maximum contiguous subarray sum found anywhere in the array so far,
      up to the current position i.
    * **Logic**: After currentMax is updated for the current position i (representing the best sum ending at i),
      maxSoFar is updated by comparing its current value with the new currentMax.
    * **maxSoFar** = Math.max(maxSoFar, currentMax);
        * This line ensures that maxSoFar always holds the greatest currentMax value encountered throughout the entire
          iteration. It's the global maximum.

**Initialization**:

* Both currentMax and maxSoFar are initialized to nums[0]. This correctly handles arrays with a single element and also
  acts as a baseline, especially for arrays containing all negative numbers (where the largest sum would be the least
  negative number).

**Iteration**:

* The loop starts from i = 1 because nums[0] has already been used for initialization.

By continuously updating currentMax to reflect the best possible sum ending at the current index, and simultaneously
updating maxSoFar with the best currentMax found, the algorithm efficiently finds the overall maximum contiguous
subarray sum in a single pass.

## What is Kadane's Algorithm?

Kadane's Algorithm is a **linear-time ($O(N)$)** algorithm that solves the **Maximum Subarray Problem**. It's a classic
example of dynamic programming because it builds up the solution to a larger problem by using the solutions to smaller,
overlapping subproblems.

The core idea is to iterate through the array, keeping track of two important values:

* **`current_max` (or `max_ending_here`):** The maximum sum of a contiguous subarray that **ends at the current position
  ** (i.e., includes the current element).
* **`global_max` (or `max_so_far`):** The overall maximum sum of any contiguous subarray found anywhere in the array *
  *up to the current position**.

---

### How Kadane's Algorithm Works (The Logic)

Let's walk through the logic with an array `nums`:

#### Initialization

* Initialize `global_max` and `current_max` to the first element of the array (`nums[0]`). This handles cases where the
  array has only one element or where all numbers are negative (in which case the single largest negative number is the
  answer).

#### Iteration

* Iterate through the array starting from the second element (index `i = 1`) up to the end.
* For each element `nums[i]`:

    * **Update `current_max`:** You have two choices for the maximum subarray ending at `nums[i]`:
        * Start a **new** subarray beginning at `nums[i]` (meaning `current_max` becomes `nums[i]`).
        * **Extend** the previous maximum subarray by adding `nums[i]` to it (`current_max + nums[i]`).
        * You choose the one that yields a greater sum. So, `current_max = Math.max(current_max + nums[i], nums[i])`.
        * **Key Insight:** If `current_max + nums[i]` is less than `nums[i]` (which happens if `current_max` was
          negative), it means the previous subarray was pulling down the sum. In such cases, it's better to "reset" and
          start a new subarray from `nums[i]`.

    * **Update `global_max`:** After calculating the best possible sum ending at the current position (`current_max`),
      compare it with the `global_max` found so far.
        * `global_max = Math.max(global_max, current_max)`.
        * This ensures `global_max` always keeps track of the largest subarray sum encountered anywhere in the array.

