# Rotate Array Left by d Positions

## Problem Statement

Given an array of integers `arr[]` of size `n`, rotate the array elements to the left by `d` positions. Left rotation means moving elements towards the beginning of the array, where elements that move past the first position wrap around to the end.

**Note:** The operation should modify the original array in-place when possible.

## Examples

### Example 1
```
Input: arr[] = [1, 2, 3, 4, 5, 6], d = 2
Output: [3, 4, 5, 6, 1, 2]
Explanation: 
- After 1st left rotation: [2, 3, 4, 5, 6, 1]
- After 2nd left rotation: [3, 4, 5, 6, 1, 2]
```

### Example 2
```
Input: arr[] = [1, 2, 3], d = 4
Output: [2, 3, 1]
Explanation: Since d > n, effective rotation = d % n = 4 % 3 = 1
- After 1st left rotation: [2, 3, 1]
```

### Example 3
```
Input: arr[] = [7], d = 5
Output: [7]
Explanation: Single element array remains unchanged regardless of d.
```

## Approaches

### 1. Naive Approach - Rotate One by One

**Algorithm:**
1. Perform d individual left rotations
2. For each rotation, store the first element and shift all others left
3. Place the stored element at the end

```java
static void rotateArr(int[] arr, int d) {
    int n = arr.length;
    
    // Repeat the rotation d times
    for (int i = 0; i < d; i++) {
        // Left rotate the array by one position
        int first = arr[0];
        for (int j = 0; j < n - 1; j++) {
            arr[j] = arr[j + 1];
        }
        arr[n - 1] = first;
    }
}
```

**Time Complexity:** O(n × d)  
**Space Complexity:** O(1)

---

### 2. Better Approach - Using Temporary Array

**Algorithm:**
1. Create a temporary array of size n
2. Copy last (n - d) elements to the front of temp array
3. Copy first d elements to the end of temp array
4. Copy all elements back to original array

```java
static void rotateArr(int[] arr, int d) {
    int n = arr.length;
    
    // Handle case when d > n
    d %= n;
    
    // Create temporary array
    int[] temp = new int[n];
    
    // Copy last n - d elements to the front of temp
    for (int i = 0; i < n - d; i++)
        temp[i] = arr[d + i];
    
    // Copy the first d elements to the back of temp
    for (int i = 0; i < d; i++)
        temp[n - d + i] = arr[i];
    
    // Copy elements back to original array
    for (int i = 0; i < n; i++)
        arr[i] = temp[i];
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

---

### 3. Optimal Approach 1 - Juggling Algorithm ⭐

**Algorithm:**
1. Find GCD of n and d to determine number of cycles
2. For each cycle, move elements to their final positions
3. Each cycle handles a group of elements that shift among themselves

```java
static void rotateArr(int[] arr, int d) {
    int n = arr.length;
    d %= n;  // Handle d > n
    
    int gcd = findGCD(n, d);
    
    // Process each cycle
    for (int i = 0; i < gcd; i++) {
        int temp = arr[i];
        int j = i;
        
        // Move elements in current cycle
        while (true) {
            int k = (j + d) % n;
            if (k == i) break;
            
            arr[j] = arr[k];
            j = k;
        }
        arr[j] = temp;
    }
}

static int findGCD(int a, int b) {
    return b == 0 ? a : findGCD(b, a % b);
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)

---

### 4. Optimal Approach 2 - Reversal Algorithm ⭐⭐

**Algorithm:**
1. Reverse the first d elements
2. Reverse the remaining (n - d) elements
3. Reverse the entire array

```java
static void rotateArr(int[] arr, int d) {
    int n = arr.length;
    d %= n;  // Handle d > n
    
    // Reverse the first d elements
    reverse(arr, 0, d - 1);
    
    // Reverse the remaining n-d elements
    reverse(arr, d, n - 1);
    
    // Reverse the entire array
    reverse(arr, 0, n - 1);
}

static void reverse(int[] arr, int start, int end) {
    while (start < end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1) - **Most Preferred** ⭐⭐

## Key Insights

### Reversal Algorithm Visualization

**Example:** arr[] = [1, 2, 3, 4, 5, 6], d = 2

```
Original:     [1, 2, 3, 4, 5, 6]

Step 1: Reverse first d elements [1, 2]
Result:       [2, 1, 3, 4, 5, 6]

Step 2: Reverse last (n-d) elements [3, 4, 5, 6]
Result:       [2, 1, 6, 5, 4, 3]

Step 3: Reverse entire array
Result:       [3, 4, 5, 6, 1, 2]  ✓
```

### Juggling Algorithm Cycles

**Example:** arr[] = [1, 2, 3, 4, 5, 6], d = 2, GCD(6,2) = 2

```
Cycle 0: indices 0 → 2 → 4 → 0
- Move: arr[0] → temp, arr[2] → arr[0], arr[4] → arr[2], temp → arr[4]
- Elements: 1 → temp, 3 → pos[0], 5 → pos[2], 1 → pos[4]

Cycle 1: indices 1 → 3 → 5 → 1  
- Move: arr[1] → temp, arr[3] → arr[1], arr[5] → arr[3], temp → arr[5]
- Elements: 2 → temp, 4 → pos[1], 6 → pos[3], 2 → pos[5]

Final: [3, 4, 5, 6, 1, 2]
```

### Important Edge Cases

1. **d = 0:** No rotation needed
2. **d >= n:** Use d % n for actual rotations
3. **n = 1:** Single element, no change
4. **Empty array:** No operation needed
5. **d = n:** Array returns to original state

## Complexity Analysis Summary

| Approach | Time Complexity | Space Complexity | Rotations | Best For |
|----------|----------------|------------------|-----------|----------|
| Naive | O(n × d) | O(1) | d | Small d values |
| Temporary Array | O(n) | O(n) | 1 | When extra space is available |
| Juggling Algorithm | O(n) | O(1) | 1 | Mathematical approach |
| **Reversal Algorithm** | **O(n)** | **O(1)** | **1** | **Most interviews** ⭐⭐ |

## Algorithm Comparison

### When d is small (d << n):
- **Naive approach** might be acceptable for very small d
- **Reversal algorithm** still preferred for consistency

### When d is large (d ≈ n):
- **Never use naive approach** (becomes O(n²))
- **Reversal algorithm** performs consistently well

### Memory constraints:
- **Juggling/Reversal algorithms** for O(1) space
- **Temporary array** when space is not a concern

## Implementation Tips

### 1. Handle d > n Case
```java
d = d % n;  // Always do this first!
if (d == 0) return;  // No rotation needed
```

### 2. Edge Case Handling
```java
if (arr == null || arr.length <= 1) return;
```

### 3. Optimized Reversal Function
```java
static void reverse(int[] arr, int start, int end) {
    while (start < end) {
        // XOR swap (optional, but avoid if elements can be same)
        arr[start] ^= arr[end];
        arr[end] ^= arr[start];
        arr[start] ^= arr[end];
        start++;
        end--;
    }
}
```

## Advanced Variations

### 1. Right Rotation
```java
// To rotate right by d, rotate left by (n - d)
static void rotateRight(int[] arr, int d) {
    rotateLeft(arr, arr.length - (d % arr.length));
}
```

### 2. Recursive Reversal
```java
static void reverseRecursive(int[] arr, int start, int end) {
    if (start >= end) return;
    
    // Swap
    int temp = arr[start];
    arr[start] = arr[end];
    arr[end] = temp;
    
    reverseRecursive(arr, start + 1, end - 1);
}
```

### 3. Block Swap Algorithm
```java
static void rotateBlockSwap(int[] arr, int d) {
    int n = arr.length;
    d %= n;
    
    blockSwap(arr, 0, d, n - d);
}

static void blockSwap(int[] arr, int i, int a, int b) {
    if (a == 0 || b == 0) return;
    
    if (a == b) {
        swap(arr, i, i + a, a);
        return;
    }
    
    if (a < b) {
        swap(arr, i, i + a + b - a, a);
        blockSwap(arr, i, a, b - a);
    } else {
        swap(arr, i, i + a, b);
        blockSwap(arr, i + b, a - b, b);
    }
}
```

## Test Cases

```java
public class RotateArrayTests {
    
    public static void runTests() {
        // Test Case 1: Basic rotation
        test(new int[]{1, 2, 3, 4, 5, 6}, 2, new int[]{3, 4, 5, 6, 1, 2});
        
        // Test Case 2: d > n
        test(new int[]{1, 2, 3}, 4, new int[]{2, 3, 1});
        
        // Test Case 3: d = n (full rotation)
        test(new int[]{1, 2, 3, 4}, 4, new int[]{1, 2, 3, 4});
        
        // Test Case 4: d = 0 (no rotation)
        test(new int[]{1, 2, 3, 4}, 0, new int[]{1, 2, 3, 4});
        
        // Test Case 5: Single element
        test(new int[]{5}, 3, new int[]{5});
        
        // Test Case 6: Two elements
        test(new int[]{1, 2}, 1, new int[]{2, 1});
        
        // Test Case 7: All same elements
        test(new int[]{7, 7, 7, 7}, 2, new int[]{7, 7, 7, 7});
        
        // Test Case 8: Large d
        test(new int[]{1, 2, 3, 4, 5}, 12, new int[]{3, 4, 5, 1, 2}); // 12%5 = 2
        
        // Test Case 9: Negative numbers
        test(new int[]{-1, -2, 3, 4}, 1, new int[]{-2, 3, 4, -1});
        
        // Test Case 10: Empty array
        test(new int[]{}, 5, new int[]{});
    }
    
    static void test(int[] input, int d, int[] expected) {
        int[] arr = input.clone();
        rotateArr(arr, d);
        
        boolean passed = Arrays.equals(arr, expected);
        System.out.println("Input: " + Arrays.toString(input) + 
                          ", d=" + d + " -> " + Arrays.toString(arr) + 
                          " | " + (passed ? "PASS" : "FAIL"));
    }
}
```

## Related Problems

- [Reverse Array](../reverse-array/) - Building block for reversal algorithm
- [Rotate Array Right](../rotate-array-right/) - Mirror problem
- [Cyclic Rotation Check](../cyclic-rotation-check/) - Verify if one array is rotation of another
- [Search in Rotated Array](../search-rotated-array/) - Binary search variation
- [Find Rotation Point](../find-rotation-point/) - Find where rotation occurred

## Real-World Applications

- **Circular Buffers:** Implementing ring buffers in system programming
- **Game Development:** Rotating player inventories or UI elements
- **Image Processing:** Rotating pixel arrays for image transformations
- **Data Streaming:** Shifting windows in time-series data analysis
- **Cryptography:** Key rotation in encryption algorithms
- **Operating Systems:** Process scheduling with round-robin algorithms

## Interview Tips

1. **Always ask about constraints:** What's the range of n and d?
2. **Clarify rotation direction:** Left vs Right rotation
3. **Handle edge cases first:** d=0, n=1, d>n, empty array
4. **Start with brute force:** Show the naive approach, then optimize
5. **Explain trade-offs:** Time vs Space complexity
6. **Walk through examples:** Use small arrays to demonstrate
7. **Code cleanly:** Use helper functions like reverse()
8. **Test thoroughly:** Include edge cases in your testing

### Interview Flow
```
1. Understand the problem (2 mins)
   - Clarify left vs right rotation
   - Ask about in-place requirement
   
2. Discuss approaches (3 mins)  
   - Mention naive O(n×d) approach
   - Explain temp array O(n) space solution
   - Introduce reversal algorithm as optimal
   
3. Code the solution (8 mins)
   - Implement reversal algorithm
   - Handle d > n case
   - Add input validation
   
4. Test and verify (2 mins)
   - Walk through example
   - Check edge cases
   - Verify time/space complexity
```

## Mathematical Insights

### Why Reversal Algorithm Works

The key insight is that left rotation by d positions can be viewed as:
1. Moving first d elements to the end
2. Moving last (n-d) elements to the front

**Proof by transformation:**
- Original: [A₁A₂...Aₐ][B₁B₂...Bₙ₋ₐ]
- After Step 1: [AₐAₐ₋₁...A₁][B₁B₂...Bₙ₋ₐ]
- After Step 2: [AₐAₐ₋₁...A₁][Bₙ₋ₐBₙ₋ₐ₋₁...B₁]
- After Step 3: [B₁B₂...Bₙ₋ₐ][A₁A₂...Aₐ] ✓

### GCD and Cycles in Juggling Algorithm

The number of cycles in juggling algorithm equals GCD(n, d) because:
- Each element moves d positions in each step
- An element returns to its original position after LCM(n,d)/d = n/GCD(n,d) steps
- Total elements in each cycle = n/GCD(n,d)
- Number of cycles = n ÷ (n/GCD(n,d)) = GCD(n,d)

## Performance Benchmarks

| Array Size | Naive O(n×d) | Temp Array O(n) | Reversal O(n) |
|------------|--------------|------------------|----------------|
| n=1,000, d=100 | ~100ms | ~1ms | ~1ms |
| n=10,000, d=1,000 | ~10s | ~10ms | ~10ms |
| n=100,000, d=50,000 | ~50min | ~100ms | ~100ms |

*Benchmarks show why O(n×d) approach fails for large inputs*

## Tags

`Array` `Rotation` `Two Pointers` `Reversal Algorithm` `Juggling Algorithm` `In-Place` `Optimization` `Interview Prep`

## Difficulty

**Medium** 🟡

---

*Part of GFG 160 - Essential Coding Interview Problems*