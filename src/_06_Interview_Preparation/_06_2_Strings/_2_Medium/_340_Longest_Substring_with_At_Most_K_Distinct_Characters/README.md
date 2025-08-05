# Longest Substring with At Most K Distinct Characters

<!-- TOC -->

* [Longest Substring with At Most K Distinct Characters](#longest-substring-with-at-most-k-distinct-characters)
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

Given a string `s` and an integer `k`, find the length of the longest substring that contains at most `k` distinct
characters.

This is a common variation of the sliding window problem where we maintain a window containing at most `k` different
characters and slide it across the input string to find the maximum length.

---

## 📥 Input

- A string `s` of length `n`, consisting of ASCII characters.
- An integer `k` where `k >= 0`.

---

## 📤 Output

- An integer representing the length of the longest substring with at most `k` distinct characters.

---

## ✅ Example

```java
Input:s ="eceba",k =2
Output:3
Explanation:
The longest
substring is "ece"
with length 3.

Input:s ="aa",k =1
Output:2
Explanation:
The longest
substring is "aa"
with length 2.

Input:s ="a",k =0
Output:0
Explanation:
No characters
allowed when
k is 0.
```

---

## 📌 Rules

- The substring must be contiguous.
- Characters can repeat but there should be at most `k` distinct characters.
- If `k == 0`, the result is always 0.

---

## 💡 Solution

### Java Code

```java
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0 || s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, maxLength = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // Output: 3
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));    // Output: 2
        System.out.println(lengthOfLongestSubstringKDistinct("a", 0));     // Output: 0
    }
}
```

---

### 🔍 Step-by-Step Execution Trace

For input: `"eceba"`, `k = 2`

| Step | `right` | char | `map`           | `left` | Action                               | `max` |
|------|---------|------|-----------------|--------|--------------------------------------|-------|
| 1    | 0       | e    | {e=1}           | 0      | ≤ k, update max                      | 1     |
| 2    | 1       | c    | {e=1, c=1}      | 0      | ≤ k, update max                      | 2     |
| 3    | 2       | e    | {e=2, c=1}      | 0      | still 2 distinct, update max         | 3     |
| 4    | 3       | b    | {e=2, c=1, b=1} | 0      | > k: shrink from left                | 3     |
|      |         |      | {e=1, c=1, b=1} | 1      | e--                                  |       |
|      |         |      | {e=1, c=0, b=1} | 2      | c-- (remove c), now map = {e=1, b=1} |       |
| 5    |         |      |                 | 2      | ≤ k, update max                      | 3     |
| 6    | 4       | a    | {e=1, b=1, a=1} | 2      | > k, shrink again                    |       |
|      |         |      | {e=0, b=1, a=1} | 3      | e-- (remove e), map = {b=1, a=1}     |       |
| 7    |         |      |                 | 3      | ≤ k, update max                      | 3     |

Final maxLength = 3

---

## ⏱️ Optimal Time Complexity

- **O(n)** — Each character is visited at most twice (once by right, once by left).

---

## 🧠 Optimal Space Complexity

- **O(k)** — At most `k` keys are stored in the map at any time.

---

## ⚙️ How It Works (Logic)

- The algorithm uses a **sliding window** approach to explore substrings.
- A **HashMap** tracks character frequencies within the window.
- The window (`left` to `right`) grows by adding new characters until the number of distinct characters exceeds `k`.
- If the constraint is violated, the window is **shrunk from the left** until it's valid again.
- During each valid window, we update the `maxLength`.

This efficient strategy ensures linear time traversal and solves the problem optimally.

---