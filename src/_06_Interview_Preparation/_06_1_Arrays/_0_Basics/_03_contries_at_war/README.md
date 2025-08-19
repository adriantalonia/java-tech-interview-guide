# Countries at War

A Java solution for determining the winner in a war simulation where soldiers fight individual battles against their counterparts.

## Problem Description

Two countries **A** and **B** are at war. Each country has soldiers with different power levels. Soldiers can only fight their counterpart at the same array position (1-vs-1 battles). The country with more surviving soldiers wins the war.

**Battle Rules:**
- Higher power soldier kills the enemy soldier
- Equal power soldiers kill each other (both die)
- Soldiers fight only their direct counterpart (`arr1[i]` vs `arr2[i]`)

### Examples

```
Input: arr1[] = [2, 2], arr2[] = [5, 5]
Output: "B"
Explanation: 
├── Battle 1: 2 vs 5 → B wins (B survivor: 1)
├── Battle 2: 2 vs 5 → B wins (B survivor: 2)
└── Final: A=0 survivors, B=2 survivors → B wins

Input: arr1[] = [9], arr2[] = [8]
Output: "A" 
Explanation: 
├── Battle 1: 9 vs 8 → A wins (A survivor: 1)
└── Final: A=1 survivor, B=0 survivors → A wins

Input: arr1[] = [3, 5], arr2[] = [3, 5]
Output: "DRAW"
Explanation:
├── Battle 1: 3 vs 3 → Both die
├── Battle 2: 5 vs 5 → Both die  
└── Final: A=0 survivors, B=0 survivors → DRAW
```

### Constraints
- `1 ≤ arr1.size() ≤ 10⁶`
- `0 ≤ arr1[i], arr2[i] ≤ 10⁵`
- Both arrays have equal length (guaranteed)

## Solution Approach

The solution uses a **battle-by-battle simulation** with **survivor counting**.

### Algorithm Steps
1. **Initialize counters** for survivors of each country
2. **Simulate each battle** individually at the same array index
3. **Count survivors** based on battle outcomes
4. **Determine winner** by comparing survivor counts

### Key Insights
- **Individual battles**: Each soldier fights only their counterpart
- **Survivor counting**: Track wins, not total army strength
- **Equal power handling**: Both soldiers die (neither side gets a survivor)

## Time & Space Complexity

- **Time Complexity**: **O(n)**
    - Single pass through both arrays
    - Constant time operations per battle
- **Space Complexity**: **O(1)**
    - Only uses two counter variables
    - No additional data structures needed

## Battle Simulation Logic

```
For each battle at index i:
┌─────────────────────────────────────┐
│ if arr1[i] > arr2[i]:              │
│   ├── A wins → aWins++             │
│ elif arr1[i] < arr2[i]:            │
│   ├── B wins → bWins++             │
│ else: (arr1[i] == arr2[i])         │
│   └── Both die → no increment      │
└─────────────────────────────────────┘

Final Determination:
├── aWins > bWins → "A"
├── aWins < bWins → "B" 
└── aWins == bWins → "DRAW"
```

## Implementation Features

### Core Functionality
- ✅ **Individual battle simulation**: Processes each soldier pair
- ✅ **Survivor tracking**: Counts battles won by each side
- ✅ **Three-way result**: Returns "A", "B", or "DRAW"
- ✅ **Optimal performance**: O(n) time, O(1) space

### Edge Case Handling
- ✅ **Null array validation**
- ✅ **Empty array handling**
- ✅ **All battles result in draws**
- ✅ **Single soldier armies**
- ✅ **Large armies (up to 10⁶ soldiers)**

## Usage

### Basic Usage
```java
CountriesAtWar war = new CountriesAtWar();

int[] armyA = {2, 2};
int[] armyB = {5, 5};
String winner = war.determineWinner(armyA, armyB);
System.out.println(winner); // Output: "B"
```

### Multiple Test Cases
```java
// Test case 1: Clear winner
int[] a1 = {9}, b1 = {8};
System.out.println(war.determineWinner(a1, b1)); // "A"

// Test case 2: Draw scenario  
int[] a2 = {3, 5}, b2 = {3, 5};
System.out.println(war.determineWinner(a2, b2)); // "DRAW"

// Test case 3: Mixed battles
int[] a3 = {10, 1}, b3 = {2, 9};
System.out.println(war.determineWinner(a3, b3)); // "DRAW"
```

## Running the Code

### Prerequisites
- Java 8 or higher
- Any Java IDE or command line

### Compilation & Execution
```bash
# Compile
javac CountriesAtWar.java

# Run with built-in test cases
java CountriesAtWar
```

### Expected Output
```
Test 1 - [2,2] vs [5,5]: B
Test 2 - [9] vs [8]: A  
Test 3 - [3,5] vs [3,5]: DRAW
Test 4 - [10,1] vs [2,9]: DRAW
```

## Algorithm Analysis

### Why This Approach Works
- **Accurate simulation**: Models the actual battle scenario
- **Correct counting**: Tracks survivors, not total strength
- **Efficient processing**: Single pass through arrays
- **Clear logic**: Easy to understand and maintain

### Common Mistakes to Avoid
- ❌ **Summing array values**: Total strength ≠ survivor count
- ❌ **Ignoring equal power**: Both soldiers die in ties
- ❌ **Wrong winner logic**: Must count actual battle outcomes

## Step-by-Step Example

```java
Input: arr1 = [7, 3, 8], arr2 = [5, 3, 9]

Battle Simulation:
┌──────────┬────────┬────────┬─────────────┬─────────────┐
│ Battle # │ A vs B │ Winner │ A Survivors │ B Survivors │
├──────────┼────────┼────────┼─────────────┼─────────────┤
│    1     │ 7 vs 5 │   A    │      1      │      0      │
│    2     │ 3 vs 3 │  Tie   │      1      │      0      │
│    3     │ 8 vs 9 │   B    │      1      │      1      │
└──────────┴────────┴────────┴─────────────┴─────────────┘

Final Result: 1 == 1 → "DRAW"
```

## Edge Cases Handled

```java
// Edge Case 1: All battles are draws
[3, 5, 7] vs [3, 5, 7] → "DRAW"

// Edge Case 2: One-sided victory
[10, 10, 10] vs [1, 1, 1] → "A"

// Edge Case 3: Single battle
[5] vs [3] → "A"

// Edge Case 4: Zero power soldiers
[0, 5] vs [0, 3] → "A" (first battle: tie, second: A wins)

// Edge Case 5: Empty armies  
[] vs [] → "DRAW"
```

## Performance Characteristics

### Time Complexity Breakdown
- **Battle simulation**: O(n) - one iteration through arrays
- **Winner determination**: O(1) - simple comparison
- **Overall**: O(n) - linear in army size

### Space Complexity Analysis
- **Counter variables**: O(1) - two integers
- **No additional storage**: Arrays processed in-place
- **Overall**: O(1) - constant extra space

### Real-World Performance
```
Array Size    │ Operations    │ Time Estimate
──────────────┼───────────────┼──────────────
1,000         │ ~1,000        │ < 1ms
100,000       │ ~100,000      │ < 10ms  
1,000,000     │ ~1,000,000    │ < 100ms
```

## Alternative Approaches

| Approach | Time | Space | Description | Verdict |
|----------|------|-------|-------------|---------|
| **Battle Simulation** | O(n) | O(1) | Count individual battle winners | ✅ **Optimal** |
| **Array Sum Comparison** | O(n) | O(1) | Compare total army strength | ❌ **Incorrect logic** |
| **Store All Results** | O(n) | O(n) | Save each battle outcome | ❌ **Unnecessary space** |

## Related Problems

This solution pattern applies to:
- **Tournament simulations**: Head-to-head competitions
- **Pairwise comparisons**: Element-by-element array analysis
- **Counting problems**: Accumulating results over iterations
- **Game theory**: Simple combat simulations

---

**Problem Category**: Array, Simulation, Counting  
**Difficulty Level**: Easy-Medium  
**Optimal Solution**: O(n) time, O(1) space