# 4.4.2 Doubly Linked

<!-- TOC -->

* [4.4.2 Doubly Linked](#442-doubly-linked)
    * [Representation of Doubly Linked List in Data Structure](#representation-of-doubly-linked-list-in-data-structure)
    * [Key Points](#key-points)
    * [Use Cases (Real-World & Interviews)](#use-cases-real-world--interviews)
    * [Summary](#summary)

<!-- TOC -->

A doubly linked list is a more complex data structure than a singly linked list, but it offers several advantages. The
main advantage of a doubly linked list is that it allows for efficient traversal of the list in both directions. This is
because each node in the list contains a pointer to the previous node and a pointer to the next node. This allows for
quick and easy insertion and deletion of nodes from the list, as well as efficient traversal of the list in both
directions.

![image](resources/Insertion-at-the-End-in-Doubly-Linked-List.png)

A Doubly Linked List (DLL) is a linear data structure in which each node contains references to both its previous and
next nodes. This allows bidirectional traversal and more efficient insertion/deletion in the middle of the list compared
to a singly linked list.

**Doubly Linked List:**

* Data Structure: Non-contiguous
* Memory Allocation: Nodes allocated individually
* Insertion/Deletion: Efficient at both ends and middle Access: Sequential (forward and backward)

## Representation of Doubly Linked List in Data Structure

In a data structure, a doubly linked list is represented using nodes that have three fields:

1. Data
2. A pointer to the next node (next)
3. A pointer to the previous node (prev)

> Java’s built-in LinkedList is actually implemented internally as a Doubly Linked List.

![image](resources/Node-Structure-of-Doubly-Linked-List.png)

```java
class DoublyNode {
    int data;
    DoublyNode prev;
    DoublyNode next;

    DoublyNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
```

## Key Points

* Bidirectional traversal: Can go forward and backward
* More memory usage than Singly Linked List (extra prev pointer)
* Faster deletion/insertion if the node reference is known
* Good for data structures like dequeues, undo-redo systems

## Use Cases (Real-World & Interviews)

* Undo/Redo operations in editors
* Browser history (back and forward navigation)
* Music playlists (next/previous song)
* Implementing Deque (Double Ended Queue)
* LRU Cache implementations

## Summary

| Feature       | Singly Linked List | Doubly Linked List                        |
|---------------|--------------------|-------------------------------------------|
| Memory        | Less overhead      | More overhead (extra pointer)             |
| Traversal     | Forward only       | Forward & backward                        |
| Insertion/Del | O(1) at head/tail  | O(1) at head/tail if reference known      |
| Use case      | Simple linear data | Complex operations requiring backtracking |

### 1. Traversal in Doubly Linked List

1. **Traversal in Doubly Linked List**

Traversal in a Doubly Linked List involves visiting each node, processing its data, and moving to the next or previous
node using the forward (next) and backward (prev) pointers.

**Step-by-Step Approach for Traversal:**

1) Start from the head of the list.
2) Traverse forward:
    * Visit the current node and process its data (e.g., print it).
    * Move to the next node using current = current->next.
    * Repeat the process until the end of the list (current == NULL).
3) Optionally, traverse backward:
    * Start from the tail (last node).
    * Visit the current node and process its data.
    * Move to the previous node using current = current->prev.
    * Repeat the process until the beginning of the list (current == NULL).

```java
/*
 * 1. Traversal in Doubly Linked List
 * Prints all the elements in the list from head to tail.
 * Time Complexity: O(n)
 */
public void printForward() {
    Node current = head;
    System.out.print("Forward Traversal: ");
    while (current != null) {
        System.out.print(current.data + " <-> ");
        current = current.next;
    }
    System.out.println("null");
}

/**
 * Traverses the list from tail to head and prints each node's data.
 * Time Complexity: O(n)
 */
public void printBackward() {
    Node current = tail;
    System.out.print("Backward Traversal: ");
    while (current != null) {
        System.out.print(current.data + " <-> ");
        current = current.prev;
    }
    System.out.println("null");
}
```

### 2. Finding Length of Doubly Linked List

A Doubly Linked List (DLL) is a type of linked list where each node has two pointers:

1) One pointing to the next node in the sequence.
2) One pointing to the previous node in the sequence.

To find the length of a doubly linked list, we need to traverse the list while counting the nodes.

**Step-by-Step Approach for finding length:**

1) Initialize a counter: Start with a counter variable (count = 0).
2) Set a pointer to the head node: Use a pointer (current) and initialize it to the head of the linked list.
3) Traverse the list:
    * While the pointer (current) is not NULL, increment the count by 1.
    * Move to the next node (current = current.next).
4) Stop at the end of the list: When the pointer reaches NULL, stop the loop.
5) Return the count: The final value of count gives the length of the doubly linked list.

```java
/*
 * 2. Finding Length of Doubly Linked List
 * Counts the number of nodes in the list.
 * Time Complexity: O(n)
 */
public int length() {
    int count = 0;
    Node current = this.head;
    while (current != null) {
        count++;
        current = current.next;
    }
    return count;
}
```

### 3. Insertion in a Doubly Linked List

Insertion in a Doubly Linked List (DLL) involves adding a new node at a specific position while maintaining the
connections between nodes. Since each node contains a pointer to both the previous and next node, insertion requires
adjusting these pointers carefully.

**There are three primary types of insertion in a DLL:**

1. **Insertion at the Beginning**

1) Create a new node with the given data.
2) Set the next pointer of the new node to the current head.
3) If the list is not empty, update the prev pointer of the current head to point to the new node.
4) Update the head of the list to the new node.

```java
// 1. Insertion at the Beginning
// Inserts a new node at the beginning of the list.
// Time Complexity: O(1)
public void insertAtBeginning(int data) {
    Node newNode = new Node(data);
    if (head == null) {
        head = newNode;
        tail = newNode;
    } else {
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }
}
```

2. **Insertion at the End**

1) Create a new node with the given data.
2) If the list is empty, set the new node as the head.
3) Traverse the list until the last node is found.
4) Set the next pointer of the last node to the new node.
5) Set the prev pointer of the new node to the last node.

```java
// 2. Insertion at the End
// Inserts a new node at the end of the list.
// Time Complexity: O(1)
public void insertAtEnd(int data) {
    Node newNode = new Node(data);
    if (tail == null) {
        head = newNode;
        tail = newNode;
    } else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }
}
```

3. Insertion at a Specific Position

```java
```

### 4. Deletion in a Doubly Linked List

1. Deletion at the Beginning

```java
```

2. Deletion at the End

```java
```

3. Deletion at a Specific Position

```java
```

## Common Interview Questions for Doubly Linked Lists

1) **What are the key advantages of a Doubly Linked List over a Singly Linked List?**
   A Doubly Linked List allows for bidirectional traversal and efficient deletion of a given node because you have a
   direct pointer to the previous node. In a singly linked list, you'd have to traverse from the beginning to find the
   node preceding the one you want to delete.

2) **What is the main disadvantage of a Doubly Linked List?**
   The main disadvantage is increased memory overhead. Each node requires an extra pointer (prev), which uses more
   memory compared to a singly linked list.

3) **Explain the time complexity of insertion and deletion in a Doubly Linked List.**
   **Insertion at the beginning or end**: O(1). You only need to update a few pointers (head, tail, next, prev)
   regardless
   of the list's size.

   **Deletion at the beginning or end**: O(1). This is because you have direct access to the head and tail pointers.

   **Insertion or Deletion at a specific position**: O(n). To find the specific node, you must traverse the list from
   the beginning, which takes time proportional to the number of nodes (n).

4) **How would you reverse a Doubly Linked List?**
   To reverse a Doubly Linked List, you can traverse the list and swap the next and prev pointers for each node. The
   head and tail pointers would also need to be swapped at the end of the process.

5) **When is a Doubly Linked List a better choice than a Singly Linked List?**
   A Doubly Linked List is a better choice when you need to perform frequent insertions or deletions from the end of the
   list, or when you need to traverse the list backward. A common example is for implementing an "undo" feature where
   you need to easily access the previous state.