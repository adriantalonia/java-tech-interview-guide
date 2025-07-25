# Two Sum Problem

---

## Description

The "Two Sum" problem is a classic algorithmic challenge that involves finding two numbers in a given array of integers that add up to a specific target number. You need to return the **indices** of these two numbers.

It's guaranteed that there will be exactly one solution for each input, and you may not use the same element twice.

## Example of Expected Result

Let's say you're given the following:

* `nums` = `[2, 7, 11, 15]`
* `target` = `9`

The expected output would be `[0, 1]` because `nums[0]` (which is `2`) + `nums[1]` (which is `7`) equals `9`.

## Rules

1.  **Input:** You will be given an array of integers (`nums`) and a single integer (`target`).
2.  **Output:** Your solution should return an array or list containing two integers, which are the **0-based indices** of the two numbers in the input `nums` array that sum up to `target`.
3.  **Unique Solution:** Assume that each input will have exactly one solution.
4.  **No Duplicate Elements:** You may not use the same element twice. This means if the number `7` appears at index `1` and index `3`, and `7 + 2 = 9`, you can't use `nums[1]` and `nums[1]`. You would need to use `nums[1]` and another distinct element, or `nums[3]` and another distinct element.
5.  **Order of Indices:** The order in which you return the indices does not matter. `[0, 1]` is considered the same as `[1, 0]`.

