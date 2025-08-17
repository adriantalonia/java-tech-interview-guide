# Triplet Family

<!-- TOC -->
* [Triplet Family](#triplet-family)
  * [Problem Description](#problem-description)
    * [Examples](#examples)
    * [Constraints](#constraints)
  * [Solution Approach](#solution-approach)
    * [Algorithm Steps](#algorithm-steps)
    * [Key Insights](#key-insights)
  * [Time & Space Complexity](#time--space-complexity)
  * [Algorithm Visualization](#algorithm-visualization)
  * [Implementation Features](#implementation-features)
    * [Core Algorithm](#core-algorithm)
    * [Edge Case Handling](#edge-case-handling)
    * [Code Quality](#code-quality)
  * [Usage](#usage)
    * [Basic Usage](#basic-usage)
    * [With Unsorted Input](#with-unsorted-input)
  * [Running the Code](#running-the-code)
    * [Prerequisites](#prerequisites)
    * [Compilation & Execution](#compilation--execution)
    * [Expected Output](#expected-output)
  * [Algorithm Comparison](#algorithm-comparison)
    * [Performance Analysis (1000 elements)](#performance-analysis-1000-elements)
  * [Step-by-Step Example](#step-by-step-example)
  * [Edge Cases Tested](#edge-cases-tested)
  * [Alternative Approaches](#alternative-approaches)
    * [When to Use This Solution](#when-to-use-this-solution)
    * [When to Consider Alternatives](#when-to-consider-alternatives)
  * [Related Problems](#related-problems)
<!-- TOC -->

A Java solution for finding triplets where the sum of two elements equals the third element, using an optimized
two-pointers approach.

## Problem Description

Given an array of integers, determine if there exist three numbers such that the sum of two elements equals the third
element.

**Mathematical representation**: Find triplet `(a, b, c)` where `a + b = c`

### Examples

```
Input: arr[] = [1, 2, 3, 4, 5]
Output: true
Explanation: The triplet (1, 2, 3) satisfies the condition: 1 + 2 = 3

Input: arr[] = [3, 4, 5] 
Output: false
Explanation: No triplets satisfy the condition.
```

### Constraints

- `1 ≤ arr.size() ≤ 10³`
- `0 ≤ arr[i] ≤ 10⁵`

## Solution Approach

This solution uses a **sort-first, then two-pointers** strategy for optimal performance.

### Algorithm Steps

1. **Sort the array** (required by problem statement)
2. **Fix the largest element** as the target sum `c`
3. **Use two pointers** to find if any two smaller elements sum to `c`
4. **Return immediately** when a valid triplet is found
5. **Continue searching** with next largest element if no pair found

### Key Insights

- **Why sort first?** Enables efficient two-pointers technique
- **Why start with largest element?** In equation `a + b = c`, `c` is typically the largest value
- **Two-pointers magic**: On sorted array, we can systematically search all pairs in O(n) time

## Time & Space Complexity

- **Time Complexity**: **O(n²)**
    - Sorting: O(n log n)
    - Main algorithm: O(n²) - O(n) outer loop × O(n) two-pointers
    - Overall: O(n²) since n² dominates
- **Space Complexity**: **O(1)**
    - Only uses constant extra space for pointers and variables

## Algorithm Visualization

```
Sorted Array: [1, 2, 3, 4, 5]

Iteration 1: target = 5
├── left = 0, right = 3
├── arr[0] + arr[3] = 1 + 4 = 5 ✓
└── FOUND! Return true

If not found, continue:
Iteration 2: target = 4  
├── left = 0, right = 2
├── Check all pairs: (1,3), (2,3)...
└── Continue searching...
```

## Implementation Features

### Core Algorithm

- ✅ **Optimal approach**: O(n²) instead of brute force O(n³)
- ✅ **Two-pointers technique**: Efficient pair searching
- ✅ **Early termination**: Returns immediately when found
- ✅ **Sorted array utilization**: Leverages ordering for faster search

### Edge Case Handling

- ✅ **Null input validation**
- ✅ **Arrays with less than 3 elements**
- ✅ **Empty arrays**
- ✅ **Arrays with duplicate numbers**
- ✅ **No valid triplets exist**

### Code Quality

- 📝 **Comprehensive documentation**
- 🧪 **Built-in test cases**
- 🎯 **Clean, readable implementation**
- ⚡ **Performance optimized**

## Usage

### Basic Usage

```java
TripletFamily solver = new TripletFamily();

int[] arr = {1, 2, 3, 4, 5};
boolean result = solver.findTriplet(arr);
System.out.

println(result); // Output: true
```

### With Unsorted Input

```java
// Works with unsorted arrays (sorts automatically)
int[] unsorted = {5, 1, 3, 2, 4};
boolean result = solver.findTriplet(unsorted);
System.out.

println(result); // Output: true (1 + 2 = 3)
```

## Running the Code

### Prerequisites

- Java 8 or higher
- Any Java IDE or command line

### Compilation & Execution

```bash
# Compile
javac TripletFamily.java

# Run with built-in test cases
java TripletFamily
```

### Expected Output

```
Test 1 - [1,2,3,4,5]: true
Test 2 - [3,4,5]: false  
Test 3 - [5,3,1,4,2]: true
Test 4 - [1,2,3]: true
```

## Algorithm Comparison

| Approach         | Time Complexity | Space Complexity | Description                                |
|------------------|-----------------|------------------|--------------------------------------------|
| **Brute Force**  | O(n³)           | O(1)             | Check all triplet combinations             |
| **Hash Set**     | O(n²)           | O(n)             | Use hash for fast lookups                  |
| **Two Pointers** | **O(n²)**       | **O(1)**         | **Optimal solution (this implementation)** |

### Performance Analysis (1000 elements)

- **Brute Force**: ~1,000,000,000 operations
- **This Solution**: ~1,000,000 operations
- **Improvement**: **1000x faster!** 🚀

## Step-by-Step Example

```java
Input:[2,1,4,3,5]

Step 1:
Sort array
Result:[1,2,3,4,5]

Step 2:
Try target = 5 (largest)
        ├──left=0,right=3:1+4=5 ✓
        └──

Found triplet(1,4,5)!

Return:true
```

## Edge Cases Tested

```java
// Edge Case 1: Minimum valid size
[1,2,3] → true(1+2=3)

// Edge Case 2: No valid triplet  
        [1,2,4] → false(no a+b=
c exists)

// Edge Case 3: All zeros
        [0,0,0] → true(0+0=0)

// Edge Case 4: Large numbers
        [100000,50000,50000] → true(50000+50000=100000)

// Edge Case 5: Insufficient elements
        [1,2] → false(
less than 3 elements)
```

## Alternative Approaches

### When to Use This Solution

- ✅ **General purpose**: Works for any integer array
- ✅ **Memory constrained**: O(1) space complexity
- ✅ **Medium to large arrays**: Efficient O(n²) performance
- ✅ **Interview/coding challenges**: Clean, optimal implementation

### When to Consider Alternatives

- **Extremely large arrays**: Consider parallel processing
- **Real-time constraints**: Hash-based approach might have better constants
- **Memory abundant**: Trade space for potentially better average-case performance

## Related Problems

This solution technique applies to similar problems:

- **Three Sum**: Find triplets that sum to zero
- **Three Sum Closest**: Find triplet closest to target sum
- **Four Sum**: Extend to four numbers
- **Two Sum variations**: Foundation for understanding two-pointers
