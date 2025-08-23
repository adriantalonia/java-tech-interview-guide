# Reverse an Array

## Problem Statement

Reverse an array `arr[]`. Reversing an array means rearranging the elements such that the first element becomes the last, the second element becomes the second last, and so on.

**Note:** The operation should modify the original array.

## Examples

### Example 1
```
Input: arr[] = [1, 4, 3, 2, 6, 5]
Output: [5, 6, 2, 3, 4, 1]
Explanation: The first element 1 moves to last position, the second element 4 moves to second-last and so on.
```

### Example 2
```
Input: arr[] = [4, 5, 1, 2]
Output: [2, 1, 5, 4]
Explanation: The first element 4 moves to last position, the second element 5 moves to second last and so on.
```

### Example 3
```
Input: arr[] = [7]
Output: [7]
Explanation: Single element array remains unchanged.
```

## Approaches

### 1. Naive Approach - Using Temporary Array

**Algorithm:**
1. Create a temporary array of the same size
2. Copy elements from original array to temporary array in reverse order
3. Copy all elements back from temporary array to original array

```java
static void reverseArray(int[] arr) {
    int n = arr.length;
    int[] temp = new int[n];

    // Copy elements in reverse order to temp array
    for (int i = 0; i < n; i++)
        temp[i] = arr[n - i - 1];

    // Copy back to original array
    for (int i = 0; i < n; i++)
        arr[i] = temp[i];
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n) - Extra space for temporary array

---

### 2. Two Pointers Approach ⭐

**Algorithm:**
1. Initialize `left = 0` and `right = n - 1`
2. While `left < right`:
    - Swap elements at `left` and `right` positions
    - Move `left` forward and `right` backward
3. Continue until pointers meet in the middle

```java
static void reverseArray(int[] arr) {
    int left = 0, right = arr.length - 1;

    while (left < right) {
        // Swap elements
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        left++;
        right--;
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - In-place operation

---

### 3. Index-Based Swapping Approach

**Algorithm:**
1. Iterate over the first half of the array (0 to n/2)
2. For each index `i`, swap `arr[i]` with `arr[n - i - 1]`
3. This automatically handles both halves

```java
static void reverseArray(int[] arr) {
    int n = arr.length;
    
    // Iterate over first half only
    for (int i = 0; i < n / 2; i++) {
        int temp = arr[i];
        arr[i] = arr[n - i - 1];
        arr[n - i - 1] = temp;
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - In-place operation

---

### 4. Using Built-in Methods

**Algorithm:**
Using Java's `Collections.reverse()` with Arrays.asList() wrapper:

```java
static void reverseArray(Integer[] arr) {
    Collections.reverse(Arrays.asList(arr));
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - In-place operation

## Key Insights

### Algorithm Visualization

**Two Pointers Approach:**
```
Step 0: [1, 4, 3, 2, 6, 5]
        ↑              ↑
       left          right

Step 1: [5, 4, 3, 2, 6, 1]  (swap 1 and 5)
           ↑        ↑
          left    right

Step 2: [5, 6, 3, 2, 4, 1]  (swap 4 and 6)
              ↑  ↑
            left right

Step 3: [5, 6, 2, 3, 4, 1]  (swap 3 and 2)
                left/right meet - DONE!
```

**Index-Based Swapping:**
```
Original: [1, 4, 3, 2, 6, 5]
          i=0: swap arr[0] with arr[5] → [5, 4, 3, 2, 6, 1]
          i=1: swap arr[1] with arr[4] → [5, 6, 3, 2, 4, 1]
          i=2: swap arr[2] with arr[3] → [5, 6, 2, 3, 4, 1]
          i=3: i >= n/2, stop
```

### Why Two Pointers is Preferred
- **Intuitive logic:** Move from both ends toward center
- **Clear termination:** When pointers meet or cross
- **Easy to understand:** Visual representation is straightforward
- **Less arithmetic:** No need to calculate `n - i - 1` repeatedly

### Edge Cases to Consider
- **Empty array:** No operation needed
- **Single element:** No swapping required
- **Two elements:** Single swap operation
- **Odd vs Even length:** Both approaches handle correctly

## Complexity Analysis Summary

| Approach | Time Complexity | Space Complexity | Swaps | Notes |
|----------|----------------|------------------|-------|-------|
| Temporary Array | O(n) | O(n) | 0 | Uses extra memory |
| Two Pointers | O(n) | O(1) | n/2 | **Optimal for readability** ⭐ |
| Index Swapping | O(n) | O(1) | n/2 | **Optimal for performance** |
| Built-in Methods | O(n) | O(1) | n/2 | Language-dependent |

## Implementation Tips

1. **Handle Edge Cases:** Check for arrays with length ≤ 1
2. **Avoid Overflow:** In index calculations, use `n - i - 1` carefully
3. **Loop Bounds:** For index approach, loop only to `n/2`
4. **Pointer Management:** Ensure `left < right` in two-pointer approach

## Advanced Variations

### XOR Swapping (No Temp Variable)
```java
// Swap without temporary variable using XOR
if (arr[left] != arr[right]) {  // Avoid XOR with same element
    arr[left] ^= arr[right];
    arr[right] ^= arr[left];
    arr[left] ^= arr[right];
}
```

### Recursive Approach
```java
static void reverseRecursive(int[] arr, int start, int end) {
    if (start >= end) return;
    
    // Swap
    int temp = arr[start];
    arr[start] = arr[end];
    arr[end] = temp;
    
    // Recursive call
    reverseRecursive(arr, start + 1, end - 1);
}
```

## Test Cases

```java
// Test Case 1: Regular array
int[] test1 = {1, 4, 3, 2, 6, 5};
// Expected: [5, 6, 2, 3, 4, 1]

// Test Case 2: Even length
int[] test2 = {4, 5, 1, 2};
// Expected: [2, 1, 5, 4]

// Test Case 3: Single element
int[] test3 = {7};
// Expected: [7]

// Test Case 4: Two elements
int[] test4 = {1, 2};
// Expected: [2, 1]

// Test Case 5: Empty array
int[] test5 = {};
// Expected: []

// Test Case 6: Palindrome array
int[] test6 = {1, 2, 3, 2, 1};
// Expected: [1, 2, 3, 2, 1]

// Test Case 7: All same elements
int[] test7 = {5, 5, 5, 5};
// Expected: [5, 5, 5, 5]

// Test Case 8: Large numbers
int[] test8 = {1000, 2000, 3000};
// Expected: [3000, 2000, 1000]
```

## Related Problems

- [Rotate Array](../rotate-array/)
- [Reverse String](../reverse-string/)
- [Palindrome Check](../palindrome-check/)
- [Reverse Linked List](../reverse-linked-list/)
- [Reverse Words in String](../reverse-words/)

## Real-World Applications

- **String Processing:** Reversing character arrays for palindrome checks
- **Data Processing:** Reversing data streams or logs
- **Game Development:** Reversing player move history
- **Algorithm Building Blocks:** Used in sorting and searching algorithms
- **Image Processing:** Flipping image pixel arrays

## Interview Tips

1. **Start Simple:** Begin with temporary array approach, then optimize
2. **Explain Trade-offs:** Discuss space vs time complexity
3. **Handle Edge Cases:** Always consider empty and single-element arrays
4. **Code Cleanly:** Use descriptive variable names (`left`, `right` vs `i`, `j`)
5. **Test Thoroughly:** Walk through examples step by step

## Tags

`Array` `Two Pointers` `In-Place` `Swapping` `Interview Prep` `Fundamental`

## Difficulty

**Easy** 🟢

---

*Part of GFG 160 - Essential Coding Interview Problems*