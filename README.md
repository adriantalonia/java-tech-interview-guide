# Interview Study Plan: SOLID, Design Patterns, Data Structures & Algorithms in Java

<!-- TOC -->
* [Interview Study Plan: SOLID, Design Patterns, Data Structures & Algorithms in Java](#interview-study-plan-solid-design-patterns-data-structures--algorithms-in-java)
  * [Module 1: Java Fundamentals and Object-Oriented Programming (OOP)](#module-1-java-fundamentals-and-object-oriented-programming-oop)
    * [1. Review of Java Fundamentals](#1-review-of-java-fundamentals)
    * [2. Principles of Object-Oriented Programming (OOP)](#2-principles-of-object-oriented-programming-oop)
  * [Module 2: SOLID Principles](#module-2-solid-principles)
  * [Module 3: Design Patterns](#module-3-design-patterns)
    * [1. Creational Patterns](#1-creational-patterns)
    * [2. Structural Patterns](#2-structural-patterns)
    * [3. Behavioral Patterns](#3-behavioral-patterns)
  * [Module 4: Data Structures](#module-4-data-structures)
  * [Module 5: Algorithms](#module-5-algorithms)
  * [Module 6: Interview Preparation and Additional Aspects](#module-6-interview-preparation-and-additional-aspects)
<!-- TOC -->

## Module 1: Java Fundamentals and Object-Oriented Programming (OOP)

This module is crucial to ensure a solid foundation before delving into more advanced topics.

### 1. Review of Java Fundamentals

* Basic syntax, data types, operators.
* Flow control (conditionals, loops).
* Classes and objects: Instantiation, constructors, `this`.
* Encapsulation: Access modifiers (public, private, protected, default).
* Inheritance: `extends`, `super`, method overriding (`@Override`).
* Polymorphism: Method overloading, upcasting, downcasting.
* Abstract classes and interfaces: Differences and use cases.
* Exceptions: `try-catch-finally`, `throws`, checked vs. unchecked exceptions.
* Java Collections (introduction): List, Set, Map (basic concepts).
* Generics: Use and benefits.
* Streams API (introduction): Basic operations.
* Lambdas: Syntax and basic use.

### 2. Principles of Object-Oriented Programming (OOP)

* Abstraction, Encapsulation, Inheritance, Polymorphism.
* Composition vs. Inheritance: When to use each.

---

## Module 2: SOLID Principles

The SOLID principles are fundamental for writing clean, maintainable, and scalable code. They are highly valued in
technical interviews.

* **Single Responsibility Principle (SRP)**: A class should have one, and only one, reason to change.
* **Open/Closed Principle (OCP)**: Software entities should be open for extension, but closed for modification.
* **Liskov Substitution Principle (LSP)**: Objects of a superclass shall be replaceable with objects of a subclass
  without affecting the correctness of the program.
* **Interface Segregation Principle (ISP)**: No client should be forced to depend on methods it does not use.
* **Dependency Inversion Principle (DIP)**: High-level modules should not depend on low-level modules. Both should
  depend on abstractions.

---

## Module 3: Design Patterns

Design patterns are proven solutions to common problems in software design. Knowing them demonstrates experience and
good practices.

### 1. Creational Patterns

* **Singleton**: Ensures a class has only one instance and provides a global point of access to it.
* **Factory Method**: Defines an interface for creating an object, but lets subclasses alter the type of objects that
  will be created.
* **Abstract Factory**: Lets you produce families of related objects without specifying their concrete classes.
* **Builder**: Lets you construct complex objects step by step.
* **Prototype**: Lets you copy existing objects without making your code dependent on their classes.

### 2. Structural Patterns

* **Adapter**: Allows objects with incompatible interfaces to collaborate.
* **Bridge**: Decouples an abstraction from its implementation so that the two can vary independently.
* **Composite**: Lets you compose objects into tree structures and then work with these structures as if they were
  individual objects.
* **Decorator**: Lets you attach new behaviors to objects by placing these objects inside special wrapper objects.
* **Facade**: Provides a simplified interface to a library, a framework, or any other complex set of classes.
* **Proxy**: Provides a substitute or placeholder for another object to control access to it.

### 3. Behavioral Patterns

* **Observer**: Defines a subscription mechanism to notify multiple objects about any events that happen to the object
  they’re observing.
* **Strategy**: Lets you define a family of algorithms, put each of them into a separate class, and make their objects
  interchangeable.
* **Command**: Turns a request into a stand-alone object that contains all information about the request.
* **Iterator**: Provides a way to access the elements of a collection sequentially without exposing its underlying
  representation.
* **Template Method**: Defines the skeleton of an algorithm in the superclass but lets subclasses override specific
  steps of the algorithm without changing its structure.
* **State**: Lets an object alter its behavior when its internal state changes.

---

## [Module 4: Data Structures](src/_04_Data_Structures/README.md)

Mastering data structures is absolutely essential. You must understand their time and space complexities.

* **Fundamentals**: Big O Notation ($O(1)$, $O(\log n)$, $O(n)$, $O(n \log n)$, $O(n^2)$), best/worst/average case
  analysis.
* **Arrays**: 1D/2D arrays, `ArrayList` internals.
* **Strings**: Immutability, `StringBuilder`, `StringBuffer`.
* **Linked Lists**: Singly, Doubly, cycle detection.
* **Stacks**: LIFO, applications (expression evaluation, backtracking).
* **Queues**: FIFO, Deque, applications (BFS, task management).
* **Hash Tables**: Hashing, collision handling (`Chaining`, `Probing`), `HashMap`, `HashSet`.
* **Trees**: Terminology, Traversals (In-order, Pre-order, Post-order), BST, Balanced Trees (AVL, Red-Black), Heaps.
* **Graphs**: Terminology, Representations (Adjacency Matrix/List), Traversals (BFS, DFS).

---

## Module 5: Algorithms

This is the central module for coding interviews. Consistent practice is key.

* **Sorting**: Bubble, Selection, Insertion, Merge Sort, Quick Sort, Heap Sort.
* **Searching**: Linear Search, Binary Search.
* **Recursion & Backtracking**: Base cases, recursive steps, Memoization, problems like N-Queens, Sudoku.
* **Dynamic Programming (DP)**: Overlapping subproblems, optimal substructure, top-down vs. bottom-up, classic
  problems (Fibonacci, Knapsack).
* **Advanced Graph Algorithms**: Dijkstra's, Bellman-Ford, Floyd-Warshall, MST (Prim's, Kruskal's).
* **Greedy Algorithms**: Making the locally optimal choice.
* **Bit Manipulation**: Bitwise operators (`&`, `|`, `^`, `~`, `<<`, `>>`), applications.

---

## Module 6: Interview Preparation and Additional Aspects

* **Code Challenges**: Practice coding interview questions.
* **Mock Interviews**: Practice communication, thinking aloud, and handling pressure.
* **Behavioral & System Design**: Use the **STAR Method** for behavioral questions. For senior roles, review System
  Design concepts (scalability, databases, caching, load balancers).
* **Interview Day Tips**: Clarify the problem, discuss trade-offs, write clean code, test, handle edge cases, and
  prepare questions for the interviewer.