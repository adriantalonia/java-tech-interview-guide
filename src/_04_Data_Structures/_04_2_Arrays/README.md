# 4.2 Arrays

<!-- TOC -->
* [4.2 Arrays](#42-arrays)
  * [Definition](#definition)
  * [Purpose](#purpose)
  * [Key Concepts](#key-concepts)
    * [1D and 2D Arrays](#1d-and-2d-arrays)
    * [`ArrayList` Internals](#arraylist-internals)
  * [When to Use](#when-to-use)
  * [Example in Java](#example-in-java)
  * [Summary Table](#summary-table)
  * [Interview Questions](#interview-questions)
<!-- TOC -->

---

## Definition

An **Array** is a fundamental data structure that stores a fixed-size, sequential collection of elements of the same
data type. The elements are stored in contiguous memory locations, and each element can be accessed using an integer
index.

An `ArrayList` is a dynamic array in Java. It is part of the Java Collections Framework and provides a resizable array
implementation. Internally, it uses a plain Java array to store its elements, but it manages the resizing automatically
as elements are added or removed.

## Purpose

Arrays are used to store multiple items of the same type under a single variable name, making it easy to perform
operations on the data, such as sorting, searching, and iteration. Their contiguous memory layout allows for very fast,
constant-time access to any element, which is why they are so widely used.

`ArrayLists` are used when you need the benefits of an array (fast random access) but require a data structure that can
grow or shrink dynamically without manually handling the resizing.

## Key Concepts

### 1D and 2D Arrays

- **Access / get / set**: constant time `O(1)` for both arrays and `ArrayList` :contentReference[oaicite:3]{index=3}.
- **Insert at end**:
    - Static arrays: not supported dynamically; insert means create/copy → `O(n)` (allocate & copy).
    - `ArrayList`: amortized `O(1)` on add at end; occasional resizing is `O(n)` but rare overall :
      contentReference[oaicite:4]{index=4}.
- **Insert or remove at beginning or middle**: requires shifting elements → `O(n)` in both cases :
  contentReference[oaicite:5]{index=5}.
- **Search by value**: linear search → `O(n)` :contentReference[oaicite:6]{index=6}.
- **Space complexity** is `O(n)` for storing n elements. Nested lists (e.g., `List<List<Integer>>`) cost `O(n*m)` where
  m is inner sizes :contentReference[oaicite:7]{index=7}.

* **1D Array:** A linear collection of elements. Think of it as a single row or column of data.
    * **Declaration:** `int[] myArray = new int[5];`
    * **Memory:** Elements are stored back-to-back in a single block of memory.

* **2D Array:** An array of arrays, representing a grid or table of data with rows and columns.
    * **Declaration:** `int[][] my2DArray = new int[3][4];`
    * **Memory:** A 2D array is typically stored as a 1D array of references, where each reference points to another 1D
      array (the "rows").

### `ArrayList` Internals

An `ArrayList` is essentially a wrapper around a standard Java array.

* **Initialization:** When an `ArrayList` is created, it starts with a default capacity (usually 10).
* **Adding Elements:** When you add an element, it is placed in the next available spot in the internal array. This is a
  fast, $O(1)$ operation on average.
* **Resizing:** If the internal array becomes full, the `ArrayList` performs a costly operation:
    1. It creates a new, larger array (typically 1.5 times the size of the old array).
    2. It copies all elements from the old array into the new one.
    3. It discards the old array.
       This resizing operation makes adding an element a worst-case $O(n)$ operation, but because it happens
       infrequently, the **amortized** time complexity of adding an element is considered $O(1)$.

## When to Use

* **Arrays:**
    * When you know the exact number of elements in advance.
    * When performance is critical and you need constant-time access to elements.
    * For multi-dimensional data, such as a game board or image pixels.

* **`ArrayLists`:**
    * When you don't know the exact number of elements beforehand.
    * When you need to frequently add or remove elements.
    * When you want a high-level, easy-to-use list data structure provided by the Java Collections Framework.

## Example in Java

```java
public class ArrayExamples {

    public static void main(String[] args) {
        // --- 1. 1D Array Example ---
        System.out.println("--- 1D Array Example ---");
        int[] numbers = new int[5]; // Declares an array of size 5
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;

        // Accessing and iterating through the array
        System.out.println("Element at index 2: " + numbers[2]);
        System.out.println("All elements: " + Arrays.toString(numbers));
        System.out.println();

        // --- 2. 2D Array Example ---
        System.out.println("--- 2D Array Example ---");
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Accessing an element in a 2D array
        System.out.println("Element at row 1, column 2: " + matrix[1][2]); // 6
        System.out.println();

        // --- 3. ArrayList Example ---
        System.out.println("--- ArrayList Example ---");
        List<String> cities = new ArrayList<>();

        // Add elements to the ArrayList
        cities.add("New York");
        cities.add("London");
        cities.add("Tokyo");

        System.out.println("Current list: " + cities);
        System.out.println("Size of list: " + cities.size());

        // Add more elements, triggering a potential resize
        cities.add("Paris");
        cities.add("Sydney");

        // Accessing an element
        System.out.println("City at index 3: " + cities.get(3));

        // Removing an element
        cities.remove("London");
        System.out.println("List after removing London: " + cities);
    }
}
```

## Summary Table

| Operation                    | Array (static) | Java `ArrayList` (dynamic)            |
|------------------------------|----------------|---------------------------------------|
| Access by index (get/set)    | O(1)           | O(1)                                  |
| Insert at end                | — (fixed size) | Amortized O(1), worst-case O(n)       |
| Insert/remove mid/front      | O(n)           | O(n)                                  |
| Search by value (`contains`) | O(n)           | O(n)                                  |
| Create / copy array          | O(n)           | O(n)                                  |
| Space complexity             | O(n)           | O(n), plus some overhead for capacity |

## Interview Questions

**Q: What is the difference between an array and an ArrayList in Java?**
A: The primary difference is size. An array is a fixed-size data structure, meaning its size cannot change after
creation. An ArrayList is a dynamic, resizable array that can grow or shrink as needed. Additionally, arrays can hold
primitive data types and objects, while ArrayLists can only hold objects (though they can store primitive wrappers like
Integer).

**Q: What is the time complexity for accessing an element at a specific index in a standard array? Why?**
A: The time complexity is $O(1)$, or constant time. This is because arrays store elements in contiguous memory. The
memory address of any element can be calculated directly using a simple formula: base_address + (index * element_size).
This means we can jump directly to the memory location without having to traverse the data structure.

**Q: Explain the worst-case time complexity of adding an element to an ArrayList.**
A: The worst-case time complexity for adding an element to an ArrayList is $O(n)$, where n is the number of elements.
This occurs when the ArrayList's internal array is full. The add operation triggers a resize, which involves creating a
new, larger array and copying all n existing elements from the old array to the new one. This copy operation takes time
proportional to n.