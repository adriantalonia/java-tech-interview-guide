# Two Sum Problem

---

## Description

The "Two Sum" problem is a classic algorithmic challenge that involves finding two numbers in a given array of integers
that add up to a specific target number. You need to return the **indices** of these two numbers.

It's guaranteed that there will be exactly one solution for each input, and you may not use the same element twice.

## Example of Expected Result

Let's say you're given the following:

* `nums` = `[2, 7, 11, 15]`
* `target` = `9`

The expected output would be `[0, 1]` because `nums[0]` (which is `2`) + `nums[1]` (which is `7`) equals `9`.

## Rules

1. **Input:** You will be given an array of integers (`nums`) and a single integer (`target`).
2. **Output:** Your solution should return an array or list containing two integers, which are the **0-based indices**
   of the two numbers in the input `nums` array that sum up to `target`.
3. **Unique Solution:** Assume that each input will have exactly one solution.
4. **No Duplicate Elements:** You may not use the same element twice. This means if the number `7` appears at index `1`
   and index `3`, and `7 + 2 = 9`, you can't use `nums[1]` and `nums[1]`. You would need to use `nums[1]` and another
   distinct element, or `nums[3]` and another distinct element.
5. **Order of Indices:** The order in which you return the indices does not matter. `[0, 1]` is considered the same as
   `[1, 0]`.

## Solutions:

* **Brute-force approach:**

```java
public static int[] twoNumberSumForSolution(int[] array, int targetSum) {
    int x = 0;
    int y = 0;
    boolean change = false;

    for (int i = 0; i < array.length; i++) {
        for (int j = i + 1; j < array.length; j++) {
            if (array[i] + array[j] == targetSum) {
                x = array[i]; // value or can change to return index
                y = array[j]; // value or can change to return index
                change = true;
                break;
            }
        }
        if (change) break;
    }
    return change ? new int[]{x, y} : new int[]{};
}
```

* **HashSet for efficient lookups:**

```java
    public static int[] twoNumberSumHashSetSolution(int[] array, int targetSum) {
    Set<Integer> nums = new HashSet<>();
    for (int num : array) {
        int complement = targetSum - num;

        if (nums.contains(complement)) {
            return new int[]{complement, num}; // return values array
        } else {
            nums.add(num);
        }
    }
    return new int[0];
}
```

* **HashMap efficient solution when indices are required:**

```java
public static int[] twoNumberSumArrayIndexReturn(int[] array, int targetSum) {
    Map<Integer, Integer> numsMap = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
        int currentNum = array[i];
        int complement = targetSum - currentNum;
        if (numsMap.containsKey(complement)) {
            return new int[]{numsMap.get(complement), i}; // return index array
        } else {
            numsMap.put(currentNum, i);
        }
    }
    return new int[0];
}
```

## Optimal Time Complexity: O(n)

- **Explanation**: The solution iterates through the input array once. For each number, it performs a contains check and
  an add operation on the **HashSet** or **HashMap**.
- **Why it's O(n)**: On average, **HashSet** or **HashMap** operations (add, contains, remove) have a time complexity of
  O(1) (constant
  time). This means the time taken for these operations does not significantly increase with the size of the set. Since
  we iterate through the n elements of the array once, the overall time complexity is dominated by this single pass,
  making it O(n).
- **Comparison to other approaches:**
    - **Brute-Force (Nested Loops)**: A naive approach would involve two nested loops, checking every possible pair.
      This would result in an O(n^2) time complexity, which becomes very slow for large input arrays.
    - **Sorting + Two Pointers**: Another approach involves sorting the array first (O(n log n)) and then using two
      pointers (O(n)). The overall complexity would be O(n log n), which is better than O(n^2) but not as good as O(n).

## Optimal Space Complexity: O(n)

- **Explanation**: In the worst-case scenario (no pair found until the very end, or no pair exists), the HashSet could
  potentially store all n elements from the input array.
- **Why it's O(n)**: The space used by the HashSet grows proportionally to the number of unique elements inserted into
  it, which can be up to n (the size of the input array).
- **Trade-off**: This O(n) space complexity is the trade-off for achieving O(n) time complexity. To avoid O(n^2) time,
  we need to store information about previously seen numbers, and the HashSet is an efficient way to do this.

## How it works (The Logic):

For each num in the array:

1) **Calculate the complement**: It calculates the complement needed to reach the targetSum, which is targetSum - num.
2) **Check if complement exists**: It checks if this complement already exists in the nums HashSet.
    - **If true**: This means we have found the two numbers (complement and num) that add up to targetSum. We
      immediately return them.
    - **If false**: The complement has not been seen yet. So, we add the current num to the HashSet so that it can be
      considered as a potential complement for future numbers.
3) **No Solution**: If the loop finishes without finding a pair, it means no such pair exists, and an empty array new
   int[0] is returned.

## What is a HashSet? (HashMap)

In Java (and similar data structures exist in other languages like Python's set, JavaScript's Set, C++'s std::
unordered_set), a HashSet is a class that implements the Set interface. Here are its core characteristics:

1. **Unordered Collection**: Elements stored in a HashSet do not maintain any specific order. When you iterate through a
   HashSet, the order of elements is not guaranteed and can even change.
2. **No Duplicate Elements**: This is the most defining feature. A HashSet can only contain unique elements. If you try
   to add an element that already exists in the set, the operation will simply be ignored, and the set will remain
   unchanged.
3. **Based on Hashing**: Internally, HashSet uses a hash table (or hash map) for storage. Each element you add is "
   hashed" – a special function generates an integer code (the hash code) that helps determine where the element should
   be stored in memory. This hashing mechanism is what enables its incredible performance.

## Why Use a HashSet? (HasMap)

The primary reason to use a HashSet is for very fast lookups (checking if an element exists), insertions, and deletions.

Here's a more detailed breakdown of its advantages and common use cases:

1. **Fast Lookups (Average O(1) Time Complexity):**

    - **This is the biggest advantage**. If you need to frequently check whether an element is present in a large
      collection, a HashSet is usually your best bet.
    - **How it works**: When you call set.contains(element), the HashSet computes the hash code of element and directly
      goes to the memory location suggested by that hash code. This is a very quick operation, typically taking constant
      time, regardless of how many elements are in the set.

    - Contrast:
        - **Array/ArrayList**: To check if an element exists, you might have to iterate through the entire list (O(n)
          time complexity).
        - **Sorted Array/ArrayList**: You could use binary search (O(log n) time complexity), which is good, but still
          slower than O(1).

2. **Efficient Storage of Unique Elements:**

- If your primary goal is to maintain a collection of distinct items and you don't care about their order, HashSet is
  perfect. It automatically handles uniqueness.

3. **Removing Duplicates:**

- A common trick to quickly remove duplicates from a list or array is to add all its elements to a HashSet and then
  convert the HashSet back to a list/array. Since HashSet only stores unique elements, the duplicates will automatically
  be filtered out.

4. **Implementing Algorithms (Like Two Sum):**

- As seen in the Two Sum problem, HashSet provides a crucial optimization. Instead of searching through the rest of the
  array for a complement (which would be slow), we can instantly check if the complement has been seen before by
  querying the HashSet.

5. **Example Use Case (Beyond Two Sum):**

Imagine you have a large list of customer IDs and you want to quickly check if a particular ID has signed up for a
service.

* **Bad Approach (ArrayList):**

```java
List<String> customerIds = new ArrayList<>();
// ... add millions of IDs
boolean exists = customerIds.contains("some_id_123"); // Could be very slow
```

* **Good Approach (HashSet):**

```java
Set<String> uniqueCustomerIds = new HashSet<>();
// ... add millions of IDs
boolean exists = uniqueCustomerIds.contains("some_id_123"); // Very fast!
```