package _06_Interview_Preparation._06_3_Linked_List._0_Basic._Modular_Node_in_a_Singly_Linked_List;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ModularNode {

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public int modularNode(Node head, int k) {
        Node current = head;
        int index = 1;
        int res = -1;

        while (current != null) {
            if (index % k == 0) {
                res = current.data;  // Update to the latest modular node
            }
            current = current.next;
            index++;
        }

        return res;
    }

    public static void main(String[] args) {
        ModularNode solver = new ModularNode();

        // Example 1: 1->2->3->4->5->6->7, k = 3
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        System.out.print("Linked List 1: ");
        printList(head1);
        System.out.println("k = 3, Modular Node: " + solver.modularNode(head1, 3));
        System.out.println();

        // Example 2: 19->28->37->46->55, k = 13
        Node head2 = new Node(19);
        head2.next = new Node(28);
        head2.next.next = new Node(37);
        head2.next.next.next = new Node(46);
        head2.next.next.next.next = new Node(55);
        System.out.print("Linked List 2: ");
        printList(head2);
        System.out.println("k = 13, Modular Node: " + solver.modularNode(head2, 13));
        System.out.println();

        // Additional Test 3: 10->20->30->40->50, k = 2
        Node head3 = new Node(10);
        head3.next = new Node(20);
        head3.next.next = new Node(30);
        head3.next.next.next = new Node(40);
        head3.next.next.next.next = new Node(50);
        System.out.print("Linked List 3: ");
        printList(head3);
        System.out.println("k = 2, Modular Node: " + solver.modularNode(head3, 2));
        System.out.println();

        // Additional Test 4: Single node 100, k = 1
        Node head4 = new Node(100);
        System.out.print("Linked List 4: ");
        printList(head4);
        System.out.println("k = 1, Modular Node: " + solver.modularNode(head4, 1));
        System.out.println();

        // Additional Test 5: 5->10->15, k = 4 (no divisible index)
        Node head5 = new Node(5);
        head5.next = new Node(10);
        head5.next.next = new Node(15);
        System.out.print("Linked List 5: ");
        printList(head5);
        System.out.println("k = 4, Modular Node: " + solver.modularNode(head5, 4));
        System.out.println();
    }
}
