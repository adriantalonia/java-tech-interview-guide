# 3. Longest Substring Without Repeating Characters

<!-- TOC -->
* [3. Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters)
  * [📝 Description](#-description)
  * [📥 Input](#-input)
  * [📤 Output](#-output)
  * [✅ Example](#-example)
  * [📌 Rules](#-rules)
  * [💡 Solution](#-solution)
    * [Java Code](#java-code)
    * [🔍 Step-by-Step Execution Trace](#-step-by-step-execution-trace)
  * [⏱️ Optimal Time Complexity](#-optimal-time-complexity)
  * [🧠 Optimal Space Complexity](#-optimal-space-complexity)
  * [⚙️ How It Works (Logic)](#-how-it-works-logic)
<!-- TOC -->

## 📝 Description

Given a string `s`, find the length of the longest substring without repeating characters.

This is a classic sliding window problem that requires keeping track of characters we've seen and their positions, while
expanding and contracting the window based on repetition.

---

## 📥 Input

- A single string `s`, consisting of ASCII characters.

---

## 📤 Output

- An integer representing the length of the longest substring without repeating characters.

---

## ✅ Example

```java
Input:"abcabcbb"
Output:3
Explanation:
The answer
is "abc",
with the
length of 3.

Input:"bbbbb"
Output:1
Explanation:
The answer
is "b",
with the
length of 1.

Input:"pwwkew"
Output:3
Explanation:
The answer
is "wke",
with the
length of 3.
```

---

## 📌 Rules

- The input string can be empty. In that case, the output should be 0.
- Characters must be **unique** within the substring.
- The substring must be **contiguous**.
- The result must be the **length**, not the actual substring.

---

## 💡 Solution

### Java Code

```java
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> unique = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;

        while (right < s.length()) {
            char currentChar = s.charAt(right);
            if (!unique.contains(currentChar)) {
                unique.add(currentChar);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                unique.remove(s.charAt(left));
                left++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // Output: 1
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // Output: 3
    }
}
```

---

### 🔍 Step-by-Step Execution Trace

For input: `"abcabcbb"`

| Step | Left | Right | Current Char | Set (Unique)    | maxLength | Action             |
|------|------|-------|--------------|-----------------|-----------|--------------------|
| 1    | 0    | 0     | 'a'          | {'a'}           | 1         | Add 'a'            |
| 2    | 0    | 1     | 'b'          | {'a', 'b'}      | 2         | Add 'b'            |
| 3    | 0    | 2     | 'c'          | {'a', 'b', 'c'} | 3         | Add 'c'            |
| 4    | 0    | 3     | 'a' (repeat) | {'a', 'b', 'c'} | 3         | Remove 'a' at left |
| 5    | 1    | 3     | 'a'          | {'b', 'c'}      | 3         | Add 'a'            |
| 6    | 1    | 4     | 'b' (repeat) | {'b', 'c', 'a'} | 3         | Remove 'b' at left |
| ...  | ...  | ...   | ...          | ...             | ...       | ...                |

Result: maxLength = 3

---

## ⏱️ Optimal Time Complexity

- **O(n)** — where *n* is the length of the string.
- Each character is visited at most twice (once by `right`, once by `left`).

---

## 🧠 Optimal Space Complexity

- **O(min(n, m))** — where *n* is the length of the string and *m* is the size of the character set.
- We're using a Set to store the characters in the current window.

---

## ⚙️ How It Works (Logic)

- We use a **sliding window** approach with two pointers `left` and `right`.
- A `Set` is used to track unique characters in the current window.
- If the character at `right` is not in the set, we add it and expand the window.
- If it is in the set, we **shrink the window from the left** until it's removed.
- We update the max length at each step based on the window size `right - left + 1`.

This approach ensures linear time traversal with optimal space usage and efficiently finds the longest substring without
repeated characters.