# Next Permutation

## Problem Statement

Given an array of integers `arr[]` representing a permutation (i.e., all elements are unique and arranged in some
order), find the next lexicographically greater permutation by rearranging the elements of the array.

If such a permutation does not exist (i.e., the array is the last possible permutation), rearrange the elements to form
the lowest possible order (i.e., sorted in ascending order).

**Note:** The operation should modify the original array in-place.

## Examples

### Example 1

```
Input: arr[] = [2, 4, 1, 7, 5, 0]
Output: [2, 4, 5, 0, 1, 7]
Explanation: The next lexicographically greater arrangement is [2, 4, 5, 0, 1, 7].
```

### Example 2

```
Input: arr[] = [3, 2, 1]
Output: [1, 2, 3]
Explanation: This is the last permutation, so we return the smallest permutation.
```

### Example 3

```
Input: arr[] = [1, 3, 5, 4, 2]
Output: [1, 4, 2, 3, 5]
Explanation: The next lexicographically greater arrangement is [1, 4, 2, 3, 5].
```

### Example 4

```
Input: arr[] = [1, 2, 3]
Output: [1, 3, 2]
Explanation: Simple case - swap the last two elements.
```

## Approaches

### 1. Naive Approach - Generate All Permutations

**Algorithm:**

1. Generate all possible permutations of the array
2. Sort all permutations lexicographically
3. Find the current permutation in the sorted list
4. Return the next permutation, or first if current is last

```java
static void nextPermutationNaive(int[] arr) {
    List<List<Integer>> allPermutations = new ArrayList<>();

    // Generate all permutations
    generatePermutations(allPermutations, arr, 0);

    // Sort lexicographically
    Collections.sort(allPermutations, (a, b) -> {
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }
        return 0;
    });

    // Find current and return next
    // Implementation details...
}
```

**Time Complexity:** O(n! × n) - Generate n! permutations, each taking O(n) time  
**Space Complexity:** O(n! × n) - Store all permutations  
**Issues:** Extremely inefficient, impractical for n > 8

---

### 2. Optimal Approach - Single Pass Algorithm ⭐⭐

**Algorithm:**

1. Find the rightmost pivot where `arr[i] < arr[i+1]` (breaking point of non-increasing suffix)
2. If no pivot exists, array is the largest permutation → reverse entire array
3. Find the smallest element in suffix that's greater than pivot
4. Swap pivot with this successor
5. Reverse the suffix to get the smallest arrangement

```java
static void nextPermutation(int[] arr) {
    int n = arr.length;

    // Step 1: Find the pivot point
    int pivot = -1;
    for (int i = n - 2; i >= 0; i--) {
        if (arr[i] < arr[i + 1]) {
            pivot = i;
            break;
        }
    }

    // Step 2: If no pivot, reverse entire array
    if (pivot == -1) {
        reverse(arr, 0, n - 1);
        return;
    }

    // Step 3: Find successor (rightmost greater element)
    for (int i = n - 1; i > pivot; i--) {
        if (arr[i] > arr[pivot]) {
            swap(arr, i, pivot);
            break;
        }
    }

    // Step 4: Reverse the suffix
    reverse(arr, pivot + 1, n - 1);
}

// Helper method to reverse array
private static void reverse(int[] arr, int start, int end) {
    while (start < end) {
        swap(arr, start++, end--);
    }
}

// Helper method to swap two elements
private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

**Time Complexity:** O(n) - Three linear scans at most  
**Space Complexity:** O(1) - In-place operation

---

### 3. Built-in Function Approach (Language-Dependent)

**Algorithm:**
Use language-specific built-in functions when available

```cpp
// C++ Implementation
void nextPermutation(vector<int>& arr) {
    next_permutation(arr.begin(), arr.end());
}
```

```java
// Java doesn't have built-in next_permutation
// Must implement manually or use external libraries
```

**Time Complexity:** O(n)  
**Space Complexity:** O(1)  
**Note:** Not available in all languages, not suitable for interviews

## Key Insights

### Understanding Lexicographic Order

Lexicographic order is like dictionary order:

- `[1,2,3]` < `[1,3,2]` < `[2,1,3]` < `[2,3,1]` < `[3,1,2]` < `[3,2,1]`
- Compare element by element from left to right
- First difference determines the order

### Algorithm Visualization

**Example:** `arr[] = [2, 4, 1, 7, 5, 0]`

```
Step 1: Find pivot (rightmost arr[i] < arr[i+1])
[2, 4, 1, 7, 5, 0]
       ↑     
   pivot=2 (arr[2]=1 < arr[3]=7)
   
Step 2: Suffix analysis
[2, 4, 1, | 7, 5, 0]
         pivot  suffix (decreasing: 7>5>0)

Step 3: Find successor (rightmost element > pivot in suffix)
Suffix: [7, 5, 0], pivot value = 1
Rightmost element > 1 is 5 (at index 4)

Step 4: Swap pivot with successor
[2, 4, 5, 7, 1, 0]
       ↑     ↑
   swapped 1 and 5

Step 5: Reverse suffix to minimize
[2, 4, 5, | 7, 1, 0] → [2, 4, 5, | 0, 1, 7]

Result: [2, 4, 5, 0, 1, 7] ✓
```

### Edge Cases Analysis

#### Case 1: Last Permutation

```
Input:  [3, 2, 1] (decreasing order)
Pivot:  Not found (entire array is decreasing)
Action: Reverse entire array
Output: [1, 2, 3]
```

#### Case 2: Simple Next

```
Input:  [1, 2, 3]
Pivot:  Index 1 (arr[1]=2 < arr[2]=3)
Successor: 3 (only element > 2 in suffix)
Swap:   [1, 3, 2]
Reverse suffix: [1, 3, 2] (suffix length 1, no change)
Output: [1, 3, 2]
```

#### Case 3: Complex Pattern

```
Input:  [1, 3, 5, 4, 2]
Pivot:  Index 0 (arr[0]=1 < arr[1]=3)
Suffix: [3, 5, 4, 2] - not all decreasing, find rightmost pivot
Pivot:  Index 1 (arr[1]=3 < arr[2]=5)  
Suffix: [5, 4, 2] - decreasing
Successor: 4 (rightmost element > 3 in [5,4,2])
Swap:   [1, 4, 5, 3, 2]
Reverse suffix: [1, 4, 2, 3, 5]
Output: [1, 4, 2, 3, 5]
```

## Algorithm Properties

### Why This Algorithm Works

1. **Pivot Selection:** We find the rightmost position where we can make a "small" increase
2. **Successor Selection:** We choose the smallest possible replacement that's still greater than pivot
3. **Suffix Reversal:** After swapping, the suffix is in decreasing order; reversing gives the smallest arrangement

### Correctness Proof

- **Minimality:** The algorithm changes the rightmost possible position, ensuring the result is the immediate next
  permutation
- **Completeness:** Every permutation except the last has a valid pivot point
- **Optimality:** No smaller lexicographic permutation exists between current and result

## Implementation Details

### Helper Functions

```java
// Reverse array segment
private static void reverse(int[] arr, int start, int end) {
    while (start < end) {
        swap(arr, start++, end--);
    }
}

// Swap two elements
private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

### Optimizations

1. **Early Termination:** Stop as soon as pivot is found
2. **Reverse Search:** Find successor by scanning from right (suffix is decreasing)
3. **In-place Operations:** No extra arrays needed

## Complexity Analysis Summary

| Approach                | Time Complexity | Space Complexity | Practical | Best For                  |
|-------------------------|-----------------|------------------|-----------|---------------------------|
| Generate All            | O(n! × n)       | O(n! × n)        | No        | Understanding only        |
| **Optimal Single Pass** | **O(n)**        | **O(1)**         | **Yes**   | **Interviews** ⭐⭐         |
| Built-in Function       | O(n)            | O(1)             | Yes       | Production (if available) |

## Test Cases

```java
public class NextPermutationTests {

    public static void runTests() {
        // Basic cases
        test(new int[]{1, 2, 3}, new int[]{1, 3, 2});
        test(new int[]{3, 2, 1}, new int[]{1, 2, 3});
        test(new int[]{1, 1, 5}, new int[]{1, 5, 1});

        // Complex cases
        test(new int[]{2, 4, 1, 7, 5, 0}, new int[]{2, 4, 5, 0, 1, 7});
        test(new int[]{1, 3, 5, 4, 2}, new int[]{1, 4, 2, 3, 5});

        // Edge cases
        test(new int[]{1}, new int[]{1}); // Single element
        test(new int[]{1, 2}, new int[]{2, 1}); // Two elements
        test(new int[]{2, 1}, new int[]{1, 2}); // Two elements (last)

        // Larger sequences
        test(new int[]{1, 2, 3, 4}, new int[]{1, 2, 4, 3});
        test(new int[]{4, 3, 2, 1}, new int[]{1, 2, 3, 4});

        // With duplicates (if allowed)
        test(new int[]{1, 5, 1}, new int[]{5, 1, 1});

        // Pattern recognition
        test(new int[]{1, 4, 3, 2}, new int[]{2, 1, 3, 4});
        test(new int[]{2, 3, 1, 4}, new int[]{2, 3, 4, 1});
    }

    static void test(int[] input, int[] expected) {
        int[] arr = input.clone();
        nextPermutation(arr);

        boolean passed = Arrays.equals(arr, expected);
        System.out.println("Input: " + Arrays.toString(input) +
                " -> " + Arrays.toString(arr) +
                " | Expected: " + Arrays.toString(expected) +
                " | " + (passed ? "PASS" : "FAIL"));
    }
}
```

## Common Pitfalls

### 1. Incorrect Pivot Finding

```java
// WRONG: Find leftmost pivot
for(int i = 0;
i<n -1;i++){
        if(arr[i] <arr[i +1]){
pivot =i; // This gives wrong result
        break;
                }
                }

// CORRECT: Find rightmost pivot
                for(
int i = n - 2;
i >=0;i--){
        if(arr[i] <arr[i +1]){
pivot =i;
        break;
                }
                }
```

### 2. Wrong Successor Selection

```java
// WRONG: Find any greater element
for(int i = pivot + 1;
i<n;i++){
        if(arr[i]>arr[pivot]){

swap(arr, i, pivot); // Wrong - not rightmost
        break;
                }
                }

// CORRECT: Find rightmost greater element
                for(
int i = n - 1;
i >pivot;i--){
        if(arr[i]>arr[pivot]){

swap(arr, i, pivot);
        break;
                }
                }
```

### 3. Forgetting Suffix Reversal

```java
// WRONG: Stop after swapping
swap(arr, successorIndex, pivot);
// Missing: reverse(arr, pivot + 1, n - 1);

// CORRECT: Always reverse suffix
swap(arr, successorIndex, pivot);

reverse(arr, pivot +1, n -1);
```

## Related Problems

- [Previous Permutation](../previous-permutation/) - Find lexicographically previous permutation
- [Permutations](../permutations/) - Generate all permutations of an array
- [Permutations II](../permutations-ii/) - Handle duplicates in permutations
- [Next Greater Element](../next-greater-element/) - Similar pattern recognition
- [Palindrome Partitioning](../palindrome-partitioning/) - Lexicographic ordering applications

## Real-World Applications

- **Combinatorial Generation:** Systematic enumeration of possibilities
- **Password Cracking:** Dictionary attacks with lexicographic ordering
- **Game Development:** Turn-based game state exploration
- **Algorithm Testing:** Generating test cases systematically
- **Cryptography:** Key space exploration
- **Database Systems:** Query optimization with ordered result sets

## Interview Tips

### 1. Pattern Recognition

Start by working through small examples to identify the pattern:

```
[1,2,3] → [1,3,2] → [2,1,3] → [2,3,1] → [3,1,2] → [3,2,1] → [1,2,3]
```

### 2. Step-by-Step Explanation

1. "I need to find where to make the smallest possible increase"
2. "That's the rightmost position where arr[i] < arr[i+1]"
3. "Then I need the smallest replacement that's greater than arr[i]"
4. "Finally, I minimize the suffix by reversing it"

### 3. Edge Case Discussion

- "What if the array is already the largest permutation?"
- "The algorithm handles this by not finding a pivot and reversing the entire array"

### 4. Complexity Analysis

- "The algorithm is O(n) time and O(1) space"
- "Each step is at most O(n): finding pivot, finding successor, reversing suffix"

### 5. Code Structure

```java
public void nextPermutation(int[] nums) {
    // Step 1: Find pivot
    int pivot = findPivot(nums);

    // Step 2: Handle last permutation
    if (pivot == -1) {
        reverse(nums, 0, nums.length - 1);
        return;
    }

    // Step 3: Find and swap with successor
    int successor = findSuccessor(nums, pivot);
    swap(nums, pivot, successor);

    // Step 4: Reverse suffix
    reverse(nums, pivot + 1, nums.length - 1);
}
```

## Advanced Variations

### 1. Previous Permutation

```java
public void previousPermutation(int[] arr) {
    int n = arr.length;

    // Find rightmost pivot where arr[i] > arr[i+1]
    int pivot = -1;
    for (int i = n - 2; i >= 0; i--) {
        if (arr[i] > arr[i + 1]) {
            pivot = i;
            break;
        }
    }

    if (pivot == -1) {
        // First permutation, go to last
        reverse(arr, 0, n - 1);
        return;
    }

    // Find rightmost element smaller than pivot
    for (int i = n - 1; i > pivot; i--) {
        if (arr[i] < arr[pivot]) {
            swap(arr, i, pivot);
            break;
        }
    }

    // Reverse suffix
    reverse(arr, pivot + 1, n - 1);
}
```

### 2. K-th Permutation

```java
public String getKthPermutation(int n, int k) {
    // Generate factorial values
    int[] factorial = new int[n];
    factorial[0] = 1;
    for (int i = 1; i < n; i++) {
        factorial[i] = factorial[i - 1] * i;
    }

    // Available numbers
    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
        numbers.add(i);
    }

    k--; // Convert to 0-based indexing
    StringBuilder result = new StringBuilder();

    for (int i = n; i >= 1; i--) {
        int index = k / factorial[i - 1];
        result.append(numbers.get(index));
        numbers.remove(index);
        k %= factorial[i - 1];
    }

    return result.toString();
}
```

### 3. Permutation with Repetitions

```java
public void nextPermutationWithDuplicates(int[] arr) {
    int n = arr.length;

    // Find pivot (same as before)
    int pivot = -1;
    for (int i = n - 2; i >= 0; i--) {
        if (arr[i] < arr[i + 1]) {
            pivot = i;
            break;
        }
    }

    if (pivot == -1) {
        reverse(arr, 0, n - 1);
        return;
    }

    // Find rightmost element > arr[pivot]
    for (int i = n - 1; i > pivot; i--) {
        if (arr[i] > arr[pivot]) {
            swap(arr, i, pivot);
            break;
        }
    }

    reverse(arr, pivot + 1, n - 1);
}
```

## Mathematical Background

### Permutation Count

- n distinct elements have n! permutations
- Position of a permutation in lexicographic order can be calculated using factorial number system

### Factorial Number System

Each permutation has a unique factorial representation:

```
For n=4, permutation [2,1,4,3] has factorial representation:
- 1st position: 1 choice out of 4 (1×3!)
- 2nd position: 0 choices out of 3 (0×2!)
- 3rd position: 1 choice out of 2 (1×1!)
- 4th position: 0 choices out of 1 (0×0!)
Total: 1×6 + 0×2 + 1×1 + 0×0 = 7 (8th permutation in 0-based indexing)
```

## Performance Considerations

### Time Complexity Breakdown

1. **Find Pivot:** O(n) worst case, O(1) average case
2. **Find Successor:** O(n) worst case, O(log n) average case (suffix is decreasing)
3. **Reverse Suffix:** O(n) worst case, O(1) average case
4. **Overall:** O(n) worst case

### Space Optimization

- Algorithm runs in O(1) extra space
- All operations are in-place
- No recursion needed (unlike permutation generation)

## Tags

`Array` `Permutation` `Lexicographic Order` `Two Pointers` `Pattern Recognition` `In-Place` `Interview Classic`

## Difficulty

**Medium** 🟡

---

*Part of GFG 160 - Essential Coding Interview Problems*