# Maximum Element in Array

A Java solution for finding the largest element in a given array of integers.

## Problem Description

Given an integer array arr[], the task is to find the maximum element present in the array and return it.

Examples

```
Input: arr = [1, 8, 7, 56, 90]  
Output: 90  
Explanation: The largest element in the array is 90.

Input: arr = [5, 5, 5, 5]  
Output: 5  
Explanation: All elements are equal, so the maximum is 5.

Input: arr = [10]  
Output: 10  
Explanation: Only one element present, which is the maximum.

```

**Constraints**

1 ≤ arr.size() ≤ 10⁶
0 ≤ arr[i] ≤ 10⁶

## Solution Approach

The solution uses a linear scan to track the current maximum while iterating through the array.

## Algorithm Steps

Validate input array (check for null / empty)
Initialize a variable with the first element as max
Iterate through the array
Update max whenever a larger element is found
Return max at the end

## Key Insights

Linear scan is optimal where all elements must be checked
Constant space: only one variable is needed to track the maximum
Works for all valid inputs, including single-element arrays

## Time & Space Complexity

- Time Complexity: O(n) Traverses the array once
- Space Complexity: O(1) Uses a single variable for tracking

## Maximum Element Logic

```
max = arr[0]
For each element (arr[i]) from index 1 to n-1:
┌────────────────────────────────────┐
│ if arr[i] > max:                  │
│   ├── max = arr[i]               │
│ else:                            │
│   └── continue                   │
└────────────────────────────────────┘

Return max
```

## Implementation Features

### Core Functionality

✅ Single-pass scan
✅ Handles duplicates & equal values
✅ Supports large input sizes
✅ Returns maximum element
✅ Efficient time/space usage

### Edge Case Handling

✅ Null or empty array check
✅ Single element arrays
✅ All equal elements
✅ Very large arrays (up to 10⁶ elements)

## Usage

### Basic Usage

```java
MaxElementFinder finder = new MaxElementFinder();

int[] arr = {1, 8, 7, 56, 90};
int max = finder.getMaximum(arr);
System.out.println(max); // Output: 90
```

```java
// Test case 1: General case
int[] a1 = {5, 5, 5, 5};
System.out.println(finder.getMaximum(a1)); // 5

// Test case 2: Single element
int[] a2 = {10};
System.out.println(finder.getMaximum(a2)); // 10

// Test case 3: Mixed values
int[] a3 = {3, 15, 2, 9, 8};
System.out.println(finder.getMaximum(a3)); // 15

```
