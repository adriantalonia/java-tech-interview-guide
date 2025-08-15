# 4.4.2 Doubly Linked

<!-- TOC -->
* [4.4.2 Doubly Linked](#442-doubly-linked)
  * [Representation of Doubly Linked List in Data Structure](#representation-of-doubly-linked-list-in-data-structure)
  * [Key Points](#key-points)
  * [Use Cases (Real-World & Interviews)](#use-cases-real-world--interviews)
  * [Summary](#summary)
    * [1. Traversal in Doubly Linked List](#1-traversal-in-doubly-linked-list)
    * [2. Finding Length of Doubly Linked List](#2-finding-length-of-doubly-linked-list)
    * [3. Insertion in a Doubly Linked List](#3-insertion-in-a-doubly-linked-list)
    * [4. Deletion in a Doubly Linked List](#4-deletion-in-a-doubly-linked-list)
  * [Advantages of Doubly Linked List](#advantages-of-doubly-linked-list)
  * [Disadvantages of Doubly Linked List](#disadvantages-of-doubly-linked-list)
  * [Applications of Doubly Linked List](#applications-of-doubly-linked-list)
  * [Common Interview Questions for Doubly Linked Lists](#common-interview-questions-for-doubly-linked-lists)
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

1) Create a new node with the given data.
2) If inserting at the beginning, follow the steps for insertion at the start.
3) Traverse the list to find the node after which insertion is needed.
4) Set the next pointer of the new node to the next node of the current position.
5) Set the prev pointer of the new node to the current node.
6) Update the prev pointer of the next node to point to the new node (if it exists).
7) Update the next pointer of the previous node to point to the new node.

```java
// 3. Insertion at a Specific Position (0-indexed)
// Inserts a new node at a given position.
// Time Complexity: O(n)
public void insertAtPosition(int data, int position) {
    if (position < 0) {
        System.out.println("Invalid position.");
        return;
    }
    if (position == 0) {
        insertAtBeginning(data);
        return;
    }

    Node newNode = new Node(data);
    Node current = head;
    int count = 0;

    while (current != null && count < position) {
        current = current.next;
        count++;
    }

    if (current == null) { // Insertion at the end or out of bounds
        if (count == position) { // Exactly at the end
            insertAtEnd(data);
        } else {
            System.out.println("Position out of bounds.");
        }
    } else { // Insertion in the middle
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
    }
}
```

### 4. Deletion in a Doubly Linked List

Deletion in a Doubly Linked List (DLL) involves removing a node while maintaining the integrity of the list. Since each
node contains pointers to both its previous and next nodes, deletion requires careful pointer adjustments to ensure no
broken links occur.

**Types of Deletion in a Doubly Linked List**

1. **Deletion at the Beginning**

1) **Check for an empty list**. First, check if the head is null. If it is, the list is empty, so there is nothing to
   delete, and the method should exit.
2) **Handle a single-node list**. If the head and tail are the same, the list contains only one node. To delete it, set
   both head and tail to null.
3) **Update pointers for a multi-node list**. If the list has more than one node:
    - Move the head pointer to the next node: head = head.next;.
    - Set the prev pointer of the new head to null: head.prev = null;.
4) **Implicit Deletion**. The original head node is now unreferenced by the list. Java's garbage collector will
   automatically reclaim its memory, effectively "deleting" it.

```java
// 1. Deletion at the Beginning
// Deletes the first node (head) of the list.
// Time Complexity: O(1)
public void deleteAtBeginning() {
    if (head == null) {
        System.out.println("List is empty. Nothing to delete.");
        return;
    }
    if (head == tail) { // Only one node in the list
        head = null;
        tail = null;
    } else {
        head = head.next;
        head.prev = null;
    }
}
```

2. **Deletion at the End**

1) Check if the list is empty; if it is, return.
2) Traverse the list to find the last node.
3) Store the last node in a temporary variable.
4) Update the next pointer of the second-last node to NULL, making it the new tail.
5) Delete the last node to free memory.

```java
// Implementation of deletion at the end following the five-step process.
// Note: This approach is not recommended for a doubly linked list
// as it is inefficient (O(n) time complexity).
public void deleteAtEndInefficient() {
    // 1. Check if the list is empty; if it is, return.
    if (head == null) {
        System.out.println("List is empty. Nothing to delete.");
        return;
    }

    // Edge case: If there's only one node in the list
    if (head.next == null) {
        head = null;
        return;
    }

    // 2. Traverse the list to find the second-to-last node.
    // The steps say to find the last node, but to update the second-last,
    // we must traverse to the second-last node.
    Node current = head;
    while (current.next.next != null) {
        current = current.next;
    }

    // 3. Store the last node in a temporary variable (optional in Java).
    // Node lastNode = current.next;

    // 4. Update the next pointer of the second-last node to NULL, making it the new tail.
    current.next = null;

    // 5. The old last node is now unreferenced and eligible for garbage collection.
    // Explicit deletion (as mentioned in the steps) is not required in Java.
}
```

3. Deletion at a Specific Position

1) Check if the list is empty; if it is, return.
2) Traverse the list to find the node to be deleted.
3) Store the node to be deleted in a temporary variable.
4) Update the next pointer of the previous node to point to the next node.
5) Update the prev pointer of the next node to point to the previous node (if it exists).
6) Delete the target node to free memory.

```java
// 3. Deletion at a Specific Position (0-indexed)
// Deletes the node at a given position.
// Time Complexity: O(n)
public void deleteAtPosition(int position) {
    if (head == null) {
        System.out.println("List is empty.");
        return;
    }
    if (position < 0) {
        System.out.println("Invalid position.");
        return;
    }
    if (position == 0) {
        deleteAtBeginning();
        return;
    }

    Node current = head;
    int count = 0;
    while (current != null && count < position) {
        current = current.next;
        count++;
    }

    if (current == null) {
        System.out.println("Position out of bounds.");
        return;
    }

    // Deleting the node in the middle or at the end
    if (current.next != null) {
        current.next.prev = current.prev;
    }
    if (current.prev != null) {
        current.prev.next = current.next;
    }

    // If the deleted node was the tail, update the tail
    if (current == tail) {
        tail = current.prev;
    }
}
```

## Advantages of Doubly Linked List

* Efficient traversal in both directions: Doubly linked lists allow for efficient traversal of the list in both
  directions, making it suitable for applications where frequent insertions and deletions are required.
* Easy insertion and deletion of nodes: The presence of pointers to both the previous and next nodes makes it easy to
  insert or delete nodes from the list, without having to traverse the entire list.
* Can be used to implement a stack or queue: Doubly linked lists can be used to implement both stacks and queues, which
  are common data structures used in programming.

## Disadvantages of Doubly Linked List

* More complex than singly linked lists: Doubly linked lists are more complex than singly linked lists, as they require
  additional pointers for each node.
* More memory overhead: Doubly linked lists require more memory overhead than singly linked lists, as each node stores
  two pointers instead of one.

## Applications of Doubly Linked List

* Implementation of undo and redo functionality in text editors.
* Cache implementation where quick insertion and deletion of elements are required.
* Browser history management to navigate back and forth between visited pages.
* Music player applications to manage playlists and navigate through songs efficiently.
* Implementing data structures like Deque (double-ended queue) for efficient insertion and deletion at both ends.

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
