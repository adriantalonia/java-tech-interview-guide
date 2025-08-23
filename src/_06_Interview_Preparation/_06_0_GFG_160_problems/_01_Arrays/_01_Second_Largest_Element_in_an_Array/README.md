# Second Largest Element in an Array

## Problem Statement

Given an array of positive integers `arr[]` of size `n`, find the second largest distinct element in the array.

**Note:** If the second largest element does not exist, return `-1`.

## Examples

### Example 1
```
Input: arr[] = [12, 35, 1, 10, 34, 1]
Output: 34
Explanation: The largest element is 35 and the second largest element is 34.
```

### Example 2
```
Input: arr[] = [10, 5, 10]
Output: 5
Explanation: The largest element is 10 and the second largest element is 5.
```

### Example 3
```
Input: arr[] = [10, 10, 10]
Output: -1
Explanation: All elements are the same, so there is no second largest element.
```

## Approaches

### 1. Naive Approach - Using Sorting

**Algorithm:**
1. Sort the array in non-decreasing order
2. Start from the second last element (index `n-2`)
3. Traverse backwards until we find an element different from the largest
4. Return that element, or `-1` if not found

```java
static int getSecondLargest(int[] arr) {
    int n = arr.length;
    Arrays.sort(arr);
    
    for (int i = n - 2; i >= 0; i--) {
        if (arr[i] != arr[n - 1]) {
            return arr[i];
        }
    }
    return -1;
}
```

**Time Complexity:** O(n log n)  
**Space Complexity:** O(1)

---

### 2. Better Approach - Two Pass Search

**Algorithm:**
1. First pass: Find the largest element
2. Second pass: Find the largest element that's not equal to the first maximum

```java
static int getSecondLargest(int[] arr) {
    int n = arr.length;
    int largest = -1, secondLargest = -1;

    // First pass: find largest
    for (int i = 0; i < n; i++) {
        if (arr[i] > largest)
            largest = arr[i];
    }

    // Second pass: find second largest
    for (int i = 0; i < n; i++) {
        if (arr[i] > secondLargest && arr[i] != largest) {
            secondLargest = arr[i];
        }
    }
    return secondLargest;
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### 3. Optimal Approach - One Pass Search ⭐

**Algorithm:**
1. Initialize `largest = -1` and `secondLargest = -1`
2. For each element:
    - If `arr[i] > largest`: update `secondLargest = largest` and `largest = arr[i]`
    - Else if `arr[i] < largest && arr[i] > secondLargest`: update `secondLargest = arr[i]`

```java
static int getSecondLargest(int[] arr) {
    int n = arr.length;
    int largest = -1, secondLargest = -1;

    for (int i = 0; i < n; i++) {
        if (arr[i] > largest) {
            secondLargest = largest;
            largest = arr[i];
        } else if (arr[i] < largest && arr[i] > secondLargest) {
            secondLargest = arr[i];
        }
    }
    return secondLargest;
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

## Key Insights

### Edge Cases to Consider
- Array with all identical elements → return `-1`
- Array with only one unique element → return `-1`
- Array with negative numbers (though problem states positive integers)
- Empty array or array with single element

### Why One Pass is Optimal
- Single traversal through the array
- Constant extra space
- Handles duplicates correctly
- Most efficient solution for this problem

## Complexity Analysis Summary

| Approach | Time Complexity | Space Complexity | Notes |
|----------|----------------|------------------|-------|
| Sorting | O(n log n) | O(1) | Simple but inefficient |
| Two Pass | O(n) | O(1) | Better, but two iterations |
| One Pass | O(n) | O(1) | **Optimal solution** |

## Implementation Tips

1. **Handle edge cases first:** Check for array length < 2
2. **Use appropriate initial values:** `-1` works well for positive integers
3. **Watch for duplicates:** Ensure `arr[i] != largest` in conditions
4. **Consider integer overflow:** For very large numbers, use `long` if needed

## Test Cases

```java
// Test Case 1: Normal case with distinct elements
int[] test1 = {12, 35, 1, 10, 34, 1};
// Expected: 34

// Test Case 2: Array with duplicates
int[] test2 = {10, 5, 10};
// Expected: 5

// Test Case 3: All elements same
int[] test3 = {10, 10, 10};
// Expected: -1

// Test Case 4: Two distinct elements
int[] test4 = {1, 2};
// Expected: 1

// Test Case 5: Reverse sorted
int[] test5 = {5, 4, 3, 2, 1};
// Expected: 4
```

## Related Problems

- [Largest Element in Array](../largest-element/)
- [Kth Largest Element](../kth-largest/)
- [Third Maximum Number](../third-maximum/)
- [Smallest and Second Smallest](../smallest-second-smallest/)

## Tags

`Array` `Sorting` `Two Pointers` `Linear Search` `Interview Prep`

## Difficulty

**Easy** 🟢

---

*Part of GFG 160 - Essential Coding Interview Problems*