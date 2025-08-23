# Move All Zeros to End of Array

## Problem Statement

Given an array of integers `arr[]`, move all the zeros to the end of the array while maintaining the relative order of all non-zero elements.

**Note:** The operation should be performed in-place.

## Examples

### Example 1
```
Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
Output: [1, 2, 4, 3, 5, 0, 0, 0]
Explanation: There are three 0s that are moved to the end while maintaining the relative order of non-zero elements.
```

### Example 2
```
Input: arr[] = [10, 20, 30]
Output: [10, 20, 30]
Explanation: No change in array as there are no 0s.
```

### Example 3
```
Input: arr[] = [0, 0]
Output: [0, 0]
Explanation: No change in array as there are all 0s.
```

## Approaches

### 1. Naive Approach - Using Temporary Array

**Algorithm:**
1. Create a temporary array of the same size
2. Copy all non-zero elements to the temporary array first
3. Fill remaining positions in temporary array with zeros
4. Copy all elements back to the original array

```java
static void pushZerosToEnd(int[] arr) {
    int n = arr.length;
    int[] temp = new int[n];
    int j = 0;

    // Copy non-zero elements to temp[]
    for (int i = 0; i < n; i++) {
        if (arr[i] != 0)
            temp[j++] = arr[i];
    }

    // Fill remaining positions with zeros
    while (j < n)
        temp[j++] = 0;

    // Copy back to original array
    for (int i = 0; i < n; i++)
        arr[i] = temp[i];
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n) - Extra space for temporary array

---

### 2. Better Approach - Two Traversals

**Algorithm:**
1. **First Pass:** Move all non-zero elements to the front, maintaining order
2. **Second Pass:** Fill remaining positions with zeros

```java
static void pushZerosToEnd(int[] arr) {
    int count = 0;  // Count of non-zero elements

    // First traversal: move non-zero elements to front
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] != 0)
            arr[count++] = arr[i];
    }

    // Second traversal: fill remaining with zeros
    while (count < arr.length)
        arr[count++] = 0;
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - In-place operation

---

### 3. Optimal Approach - One Traversal with Swapping ⭐

**Algorithm:**
1. Use a pointer `count` to track position for next non-zero element
2. When we find a non-zero element, swap it with element at `count`
3. This ensures zeros naturally move towards the end

```java
static void pushZerosToEnd(int[] arr) {
    int count = 0;  // Pointer for next non-zero position

    for (int i = 0; i < arr.length; i++) {
        if (arr[i] != 0) {
            // Swap current element with element at count
            int temp = arr[i];
            arr[i] = arr[count];
            arr[count] = temp;
            
            count++;
        }
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - In-place operation

## Key Insights

### Why Swapping Works Better
- **Two Traversals:** Overwrites elements, requires separate zero-filling pass
- **One Traversal:** Swapping preserves zeros and moves them naturally to end
- **Efficiency:** Single pass reduces cache misses and iterations

### Edge Cases to Consider
- Array with no zeros → No changes needed
- Array with all zeros → No swaps occur, array remains same
- Array with single element → No operation needed
- Empty array → Handle gracefully

### Algorithm Visualization

**Two Traversals Approach:**
```
Original: [1, 2, 0, 4, 3, 0, 5, 0]
Step 1:   [1, 2, 4, 3, 5, ?, ?, ?] (move non-zeros)
Step 2:   [1, 2, 4, 3, 5, 0, 0, 0] (fill zeros)
```

**One Traversal with Swapping:**
```
Original: [1, 2, 0, 4, 3, 0, 5, 0]
i=0: [1, 2, 0, 4, 3, 0, 5, 0] count=1 (1 already in place)
i=1: [1, 2, 0, 4, 3, 0, 5, 0] count=2 (2 already in place)  
i=2: [1, 2, 0, 4, 3, 0, 5, 0] count=2 (0 found, no swap)
i=3: [1, 2, 4, 0, 3, 0, 5, 0] count=3 (swap 4 with 0)
i=4: [1, 2, 4, 3, 0, 0, 5, 0] count=4 (swap 3 with 0)
i=5: [1, 2, 4, 3, 0, 0, 5, 0] count=4 (0 found, no swap)
i=6: [1, 2, 4, 3, 5, 0, 0, 0] count=5 (swap 5 with 0)
i=7: [1, 2, 4, 3, 5, 0, 0, 0] count=5 (0 found, no swap)
```

## Complexity Analysis Summary

| Approach | Time Complexity | Space Complexity | Traversals | Notes |
|----------|----------------|------------------|------------|-------|
| Temporary Array | O(n) | O(n) | 3 | Simple but uses extra space |
| Two Traversals | O(n) | O(1) | 2 | In-place, good solution |
| One Traversal | O(n) | O(1) | 1 | **Optimal - most efficient** ⭐ |

## Implementation Tips

1. **Pointer Management:** `count` tracks where next non-zero should go
2. **Swap Optimization:** Only swap when `i != count` to avoid unnecessary operations
3. **Boundary Checks:** Handle empty arrays and single elements
4. **Preserve Order:** All approaches maintain relative order of non-zero elements

## Optimized Version

```java
static void pushZerosToEnd(int[] arr) {
    int count = 0;
    
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] != 0) {
            // Optimize: only swap if positions are different
            if (i != count) {
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;
            }
            count++;
        }
    }
}
```

## Test Cases

```java
// Test Case 1: Mixed zeros and non-zeros
int[] test1 = {1, 2, 0, 4, 3, 0, 5, 0};
// Expected: [1, 2, 4, 3, 5, 0, 0, 0]

// Test Case 2: No zeros
int[] test2 = {10, 20, 30};
// Expected: [10, 20, 30]

// Test Case 3: All zeros
int[] test3 = {0, 0, 0};
// Expected: [0, 0, 0]

// Test Case 4: Single non-zero
int[] test4 = {5};
// Expected: [5]

// Test Case 5: Alternating pattern
int[] test5 = {0, 1, 0, 2, 0, 3};
// Expected: [1, 2, 3, 0, 0, 0]

// Test Case 6: Zeros at start
int[] test6 = {0, 0, 1, 2, 3};
// Expected: [1, 2, 3, 0, 0]

// Test Case 7: Zeros at end
int[] test7 = {1, 2, 3, 0, 0};
// Expected: [1, 2, 3, 0, 0]
```

## Related Problems

- [Remove Element](../remove-element/)
- [Remove Duplicates from Sorted Array](../remove-duplicates/)
- [Partition Array](../partition-array/)
- [Dutch National Flag Problem](../dutch-flag/)

## Real-World Applications

- **Data Cleaning:** Moving invalid/null entries to end
- **Memory Management:** Compacting active memory blocks
- **Database Operations:** Segregating records based on criteria
- **File Processing:** Organizing valid vs invalid data entries

## Tags

`Array` `Two Pointers` `In-Place` `Linear Scan` `Interview Prep`

## Difficulty

**Easy** 🟢

---

*Part of GFG 160 - Essential Coding Interview Problems*