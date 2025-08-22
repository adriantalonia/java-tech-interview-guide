# Modular Node in a Singly Linked List

A Java solution for finding the **modular node** in a singly linked
list.\
A **modular node** is defined as the **last node in the list whose
1-based index is divisible by `k`**.

------------------------------------------------------------------------

## Problem Description

Given the head of a singly linked list and an integer `k`, return the
data of the modular node.\
If no such node exists, return **-1**.

-   **Indexing is 1-based**.\
-   The modular node is always the **last node** whose position is
    divisible by `k`.

------------------------------------------------------------------------

## Examples

### Example 1

**Input:**

    1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7, k = 3

**Output:**

    6

**Explanation:**\
Indices divisible by 3 are: **3 and 6**.\
The last such index is **6**, and the value at the 6th node is **6**.

------------------------------------------------------------------------

### Example 2

**Input:**

    19 -> 28 -> 37 -> 46 -> 55, k = 13

**Output:**

    -1

**Explanation:**\
The linked list has 5 nodes (indices 1 to 5).\
No index is divisible by 13, so return **-1**.

------------------------------------------------------------------------

## Constraints

-   `1 ≤ number of nodes ≤ 10^5`\
-   `1 ≤ node.data, k ≤ 10^5`

------------------------------------------------------------------------

## Solution Approach

1.  **Initialize** a variable `res = -1` to store the result.

    -   Default is **-1** in case no modular node is found.

2.  **Traverse** the linked list while keeping track of the current
    index (`1-based`).

3.  **Check divisibility**:

    -   If `index % k == 0`, update `res = current.data`.\
    -   Continue traversing to ensure the **last modular node** is
        stored.

4.  **Return `res`** after traversal.

------------------------------------------------------------------------

## Java Implementation

``` java
/*
 * Finds the modular node in a singly linked list.
 */
class Solution {
    public int modularNode(Node head, int k) {
        Node current = head;
        int index = 1;
        int res = -1;

        while (current != null) {
            if (index % k == 0) {
                res = current.data;  // Update result to current modular node
            }
            current = current.next;
            index++;
        }

        return res;
    }
}
```

------------------------------------------------------------------------

✅ **Time Complexity:** `O(n)` -- Traverse each node once\
✅ **Space Complexity:** `O(1)` -- Constant extra space