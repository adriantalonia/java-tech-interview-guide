package _04_Data_Structures._04_4_Linked_Lists._04_4_2_Doubly_Linked_List;

// Node definition for a Doubly Linked List
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DoublyLinkedList {
    Node head;
    Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // A utility method to print the list (forward traversal)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // A utility method to print the list backwards
    public void printBackward() {
        Node current = tail;
        System.out.print("Backward Traversal: ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

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

    /*
     * 3. Insertion in a Doubly Linked List
     */

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

    /*
     * 4. Deletion in a Doubly Linked List
     */

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

    // 2. Deletion at the End
    // Deletes the last node (tail) of the list.
    // Time Complexity: O(1)
    public void deleteAtEnd() {
        if (tail == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }
        if (head == tail) { // Only one node in the list
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

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

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("--- Insertion Scenarios ---");
        System.out.println("Initial List:");
        list.printList();

        // 1. Insert at the beginning
        System.out.println("\nInserting 10 at beginning.");
        list.insertAtBeginning(10); // 10 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        System.out.println("\nInserting 5 at beginning.");
        list.insertAtBeginning(5); // 5 <-> 10 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        // 2. Insert at the end
        System.out.println("\nInserting 20 at end.");
        list.insertAtEnd(20); // 5 <-> 10 <-> 20 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        System.out.println("\nInserting 25 at end.");
        list.insertAtEnd(25); // 5 <-> 10 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        // 3. Insert at a specific position
        System.out.println("\nInserting 15 at position 2 (0-indexed).");
        list.insertAtPosition(15, 2); // 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        System.out.println("\nInserting 3 at position 0.");
        list.insertAtPosition(3, 0); // 3 <-> 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        System.out.println("\nInserting 30 at position 6 (end).");
        list.insertAtPosition(30, 6); // 3 <-> 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> 30 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        System.out.println("\nInserting at invalid position -5.");
        list.insertAtPosition(99, -5); // Invalid position.
        System.out.println("\nInserting at out-of-bounds position 10.");
        list.insertAtPosition(99, 10); // Position out of bounds.
        System.out.println("Current list:"); // 3 <-> 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> 30 <-> null
        list.printList();
        System.out.println("List length: " + list.length());

        System.out.println("\n--- Deletion Scenarios ---");
        System.out.println("Current List:"); // 3 <-> 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> 30 <-> null
        list.printList();

        // 1. Delete from the beginning
        System.out.println("\nDeleting from beginning.");
        list.deleteAtBeginning(); // 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> 30 <-> null
        list.printList();
        System.out.println("List length: " + list.length());
        list.printBackward(); // 30 <-> 25 <-> 20 <-> 15 <-> 10 <-> 5 <-> null

        // 2. Delete from the end
        System.out.println("\nDeleting from end.");
        list.deleteAtEnd(); // 5 <-> 10 <-> 15 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());
        list.printBackward(); // 25 <-> 20 <-> 15 <-> 10 <-> 5 <-> null

        // 3. Delete at a specific position
        System.out.println("\nDeleting node at position 2.");
        list.deleteAtPosition(2); // 5 <-> 10 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());
        list.printBackward();

        System.out.println("\nDeleting node at position 0.");
        list.deleteAtPosition(0); // 10 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());
        list.printBackward();

        System.out.println("\nDeleting node at out-of-bounds position 5.");
        list.deleteAtPosition(5);
        System.out.println("Current list:"); // 10 <-> 20 <-> 25 <-> null
        list.printList();
        System.out.println("List length: " + list.length());
    }
}
