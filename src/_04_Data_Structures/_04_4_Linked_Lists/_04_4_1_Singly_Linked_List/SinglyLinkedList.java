package _04_Data_Structures._04_4_Linked_Lists._04_4_1_Singly_Linked_List;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class SinglyLinkedList {
    Node head; // Reference to the first node of the list

    // Constructor to initialize an empty list
    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * 1. Traversal of Singly Linked List
     * Prints all the elements in the list from head to tail.
     * Time Complexity: O(n)
     */
    public void printList() {
        Node current = this.head;
        System.out.print("Singly Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * 2. Searching in Singly Linked List
     * Searches for a specific value in the list.
     *
     * @param value The value to search for.
     * @return true if the value is found, false otherwise.
     * Time Complexity: O(n)
     */
    public boolean search(int value) {
        Node current = this.head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * 3. Length of Singly Linked List
     * Counts the number of nodes in the list.
     *
     * @return The number of nodes.
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

    /**
     * 4. Insertion in Singly Linked List
     * Inserts a new node at the end of the list.
     *
     * @param data The data for the new node.
     *             Time Complexity: O(n)
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);

        // If the list is empty, the new node becomes the head.
        if (this.head == null) {
            this.head = newNode;
            return;
        }

        // Traverse to the last node
        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        // Link the new node to the last node
        current.next = newNode;
    }

    /**
     * Inserts a new node at the beginning of the list.
     *
     * @param data The data for the new node.
     *             Time Complexity: O(1)
     */
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;
    }

    /**
     * Inserts a new node at a specific position in the list.
     *
     * @param data     The data for the new node.
     * @param position The position (0-indexed) where the new node should be inserted.
     *                 Time Complexity: O(n)
     */
    public void insertAtPosition(int data, int position) {
        // 1. Handle edge case: inserting at the beginning (position 0)
        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        // 2. Handle invalid position
        if (position < 0) {
            System.out.println("Invalid position. Position cannot be negative.");
            return;
        }

        Node newNode = new Node(data);
        Node current = this.head;
        int count = 0;

        // 3. Traverse to the node just before the desired position
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }

        // 4. Handle edge case: position is out of bounds
        if (current == null) {
            System.out.println("Position out of bounds. The list is not long enough.");
            return;
        }

        // 5. Perform the insertion
        newNode.next = current.next; // New node's next points to the next node of the current node
        current.next = newNode;      // Current node's next points to the new node
    }

    /**
     * 5. Deletion in Singly Linked List
     * Deletes the first occurrence of a node with a given value.
     *
     * @param value The value to be deleted.
     *              Time Complexity: O(n)
     */
    public void delete(int value) {
        // If the list is empty
        if (this.head == null) {
            return;
        }

        // If the head node contains the value to be deleted
        if (this.head.data == value) {
            this.head = this.head.next;
            return;
        }

        // Traverse to find the node to be deleted and its predecessor
        Node current = this.head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        // If the value was found
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    /**
     * 6. Modify a Singly Linked List
     * Updates the value of a node at a given position (0-indexed).
     *
     * @param position The position of the node to modify.
     * @param newData  The new data to set.
     *                 Time Complexity: O(n)
     */
    public void modify(int position, int newData) {
        if (position < 0 || this.head == null) {
            System.out.println("Invalid position or list is empty.");
            return;
        }

        Node current = this.head;
        int count = 0;

        while (current != null && count < position) {
            current = current.next;
            count++;
        }

        if (current != null) {
            current.data = newData;
        } else {
            System.out.println("Position out of bounds.");
        }
    }

    /**
     * 7. Reversing a Singly Linked List
     * Reverses the list iteratively.
     * Time Complexity: O(n)
     */
    public void reverse() {
        Node previous = null;
        Node current = this.head;
        Node next = null;

        while (current != null) {
            next = current.next; // Store the next node
            current.next = previous; // Reverse the current node's pointer
            previous = current; // Move previous one step forward
            current = next; // Move current one step forward
        }
        this.head = previous; // The new head is the last node of the original list
    }

    // Main method to demonstrate the operations
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("--- Insertion Tests ---");
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertAtPosition(15, 2); // Insert 15 at position 2 (0-indexed)
        list.printList(); // Expected: 5 -> 10 -> 15 -> 20 -> null
        System.out.println("Length after insertions: " + list.length());

        System.out.println("\n--- Edge Case Insertion Tests ---");
        list.insertAtPosition(0, 0); // Insert at the beginning
        list.printList(); // Expected: 0 -> 5 -> 10 -> 15 -> 20 -> null
        list.insertAtPosition(25, 5); // Insert at the end
        list.printList(); // Expected: 0 -> 5 -> 10 -> 15 -> 20 -> 25 -> null
        list.insertAtPosition(99, 10); // Invalid position
        list.printList(); // List should be unchanged

        System.out.println("\n--- Search Tests ---");
        System.out.println("Searching for 15: " + list.search(15)); // Expected: true
        System.out.println("Searching for 100: " + list.search(100)); // Expected: false

        System.out.println("\n--- Modification Test ---");
        list.modify(3, 16); // Modify the node at position 3 (data 15) to 16
        list.printList(); // Expected: 0 -> 5 -> 10 -> 16 -> 20 -> 25 -> null

        System.out.println("\n--- Deletion Tests ---");
        list.delete(0); // Delete the head
        list.printList(); // Expected: 5 -> 10 -> 16 -> 20 -> 25 -> null
        list.delete(20); // Delete a middle element
        list.printList(); // Expected: 5 -> 10 -> 16 -> 25 -> null
        list.delete(25); // Delete the tail
        list.printList(); // Expected: 5 -> 10 -> 16 -> null

        System.out.println("\n--- Reversing Test ---");
        list.reverse();
        list.printList(); // Expected: 16 -> 10 -> 5 -> null

        System.out.println("\n--- Final State ---");
        System.out.println("Final length: " + list.length()); // Expected: 3
    }
}
