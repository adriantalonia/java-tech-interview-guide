# 21. Merge Two Sorted Lists

## Problem Description
Merge two sorted linked lists and return the head of the merged list. The merged list should be made by splicing together nodes from the two input lists in sorted order.

**Examples**:
- Input: `list1 = [1,2,4]`, `list2 = [1,3,4]` → Output: `[1,1,2,3,4,4]`
- Input: `list1 = []`, `list2 = [0]` → Output: `[0]`

**Constraints**:
- Number of nodes: `[0, 50]`
- Node values: `[-100, 100]`
- Both lists are sorted in **non-decreasing order**.

## Solution Approaches

### 1. Iterative Approach (Optimal)
**Key Insight**:  
Use a dummy node to simplify edge cases and a pointer (`curr`) to incrementally build the merged list by comparing nodes from both lists.

**Steps**:
1. Create a dummy node and set `curr = dummy`
2. While both lists are non-empty:
    - If `list1.val ≤ list2.val`:
        - Link `curr.next` to `list1`
        - Advance `list1` to its next node
    - Else:
        - Link `curr.next` to `list2`
        - Advance `list2` to its next node
    - Move `curr` to `curr.next`
3. Attach remaining nodes from the non-empty list to `curr.next`
4. Return `dummy.next` (skips placeholder)

**Complexity**:
- ⏱️ Time: **O(n + m)** (single pass)
- 💾 Space: **O(1)** (in-place reordering)

### 2. Recursive Approach
**Key Insight**:  
Leverage recursion to compare nodes and link the smaller node to the result of merging the remaining lists.

**Steps**:
1. Base case: If either list is `null`, return the other list
2. Compare node values:
    - If `list1.val ≤ list2.val`:
        - Set `list1.next = mergeTwoLists(list1.next, list2)`
        - Return `list1`
    - Else:
        - Set `list2.next = mergeTwoLists(list1, list2.next)`
        - Return `list2`

**Complexity**:
- ⏱️ Time: **O(n + m)**
- 💾 Space: **O(n + m)** (recursion stack)  