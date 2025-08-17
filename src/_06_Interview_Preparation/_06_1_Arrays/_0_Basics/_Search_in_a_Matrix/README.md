# Search in a Matrix

<!-- TOC -->
* [Search in a Matrix](#search-in-a-matrix)
  * [Problem Description](#problem-description)
    * [Example](#example)
  * [Solution Approach](#solution-approach)
  * [Time & Space Complexity](#time--space-complexity)
  * [Features](#features)
    * [Core Functionality](#core-functionality)
    * [Utility Methods](#utility-methods)
  * [Usage](#usage)
    * [Basic Search](#basic-search)
    * [Display Matrix](#display-matrix)
  * [Running the Code](#running-the-code)
    * [Prerequisites](#prerequisites)
    * [Compilation & Execution](#compilation--execution)
    * [Expected Output](#expected-output)
  * [Algorithm Analysis](#algorithm-analysis)
    * [When to Use This Approach](#when-to-use-this-approach)
    * [Alternative Approaches](#alternative-approaches)
  * [Code Structure](#code-structure)
  * [Edge Cases Handled](#edge-cases-handled)
<!-- TOC -->

A Java implementation for searching elements in a 2D matrix using linear search algorithm.

## Problem Description

Given a 2D matrix and a target value, determine whether the target exists in the matrix. This implementation provides a
brute force approach that works for any unsorted matrix.

### Example

```
Matrix:
[ 6  23  21]
[ 4  45  32]
[69  11  87]

Target: 32
Result: true (found at position [1][2])

Target: 99
Result: false (not found in matrix)
```

## Solution Approach

The algorithm uses a **linear search** approach:

1. Iterate through each row of the matrix
2. For each row, iterate through each element
3. Compare each element with the target value
4. Return `true` immediately when target is found
5. Return `false` if target is not found after checking all elements

## Time & Space Complexity

- **Time Complexity**: O(m × n)
    - Where `m` is the number of rows and `n` is the number of columns
    - In worst case, we need to check every element
- **Space Complexity**: O(1)
    - Uses constant extra space regardless of input size

## Features

### Core Functionality

- ✅ Search for any integer value in a 2D matrix
- ✅ Input validation for null/empty matrices
- ✅ Early termination when target is found
- ✅ Handles rectangular matrices of any size

### Utility Methods

- 🖨️ **Enhanced Print**: Clean row-by-row output
- 🖨️ **Arrays.toString() Print**: Formatted array notation
- 🖨️ **Deep ToString Print**: One-liner matrix display for debugging

## Usage

### Basic Search

```java
int[][] matrix = {
        {6, 23, 21},
        {4, 45, 32},
        {69, 11, 87}
};

boolean found = SearchInAMatrix.searchMatrix(matrix, 32);
System.out.

println(found); // Output: true
```

### Display Matrix

```java
// Method 1: Enhanced formatting
SearchInAMatrix.printMatrixEnhanced(matrix);

// Method 2: Array notation
SearchInAMatrix.

printMatrixArraysToString(matrix);

// Method 3: Quick debug view
SearchInAMatrix.

printMatrixDeepToString(matrix);
```

## Running the Code

### Prerequisites

- Java 8 or higher
- Any Java IDE or command line

### Compilation & Execution

```bash
# Compile
javac SearchInAMatrix.java

# Run
java SearchInAMatrix
```

### Expected Output

```
[[6, 23, 21], [4, 45, 32], [69, 11, 87]]
Searching for 32: true
Searching for 99: false
Searching for 6: true
```

## Algorithm Analysis

### When to Use This Approach

- ✅ **Unsorted matrices**: Works with any arrangement of elements
- ✅ **Small to medium matrices**: Efficient for reasonable matrix sizes
- ✅ **Simple implementation**: Easy to understand and maintain
- ✅ **General purpose**: No assumptions about data ordering

### Alternative Approaches

For specific matrix properties, more efficient algorithms exist:

| Matrix Type            | Algorithm                           | Time Complexity |
|------------------------|-------------------------------------|-----------------|
| **Row-wise sorted**    | Binary search each row              | O(m log n)      |
| **Column-wise sorted** | Binary search each column           | O(n log m)      |
| **Fully sorted**       | Staircase search                    | O(m + n)        |
| **Unsorted**           | Linear search (this implementation) | O(m × n)        |

## Code Structure

```
SearchInAMatrix.java
├── searchMatrix()           # Main search algorithm
├── printMatrixEnhanced()    # Pretty print method
├── printMatrixArraysToString() # Array notation print
├── printMatrixDeepToString() # Debug print method
└── main()                   # Test cases and examples
```

## Edge Cases Handled

- ✅ Null matrix input
- ✅ Empty matrix (0 rows)
- ✅ Matrix with empty rows (0 columns)
- ✅ Single element matrix
- ✅ Target not present in matrix
- ✅ Target present multiple times (returns true on first occurrence)

