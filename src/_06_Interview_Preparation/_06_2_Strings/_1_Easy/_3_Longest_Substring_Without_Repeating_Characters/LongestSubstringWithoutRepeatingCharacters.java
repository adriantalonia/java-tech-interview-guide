package _06_Interview_Preparation._06_2_Strings._1_Easy._3_Longest_Substring_Without_Repeating_Characters;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains a method to find the length of the longest substring
 * without repeating characters using the sliding window technique.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Returns the length of the longest substring without repeating characters.
     *
     * @param s the input string
     * @return the length of the longest substring with all unique characters
     */
    public static int lengthOfLongestSubstring(String s) {
        // Handle edge case: null or empty input
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Set to store unique characters in the current window
        Set<Character> unique = new HashSet<>();

        int max = 0;       // Tracks the maximum length found so far
        int left = 0;      // Left pointer of the sliding window
        int right = 0;     // Right pointer of the sliding window

        // Iterate while right pointer is within bounds
        while (right < s.length()) {
            char c = s.charAt(right);

            // If the character is not in the set, it's safe to expand the window
            if (!unique.contains(c)) {
                unique.add(c);  // Add the character to the set
                max = Math.max(max, right - left + 1);  // Update max length if needed
                right++;        // Expand the window by moving right pointer
            } else {
                // Character already exists in set — need to shrink window from the left
                unique.remove(s.charAt(left));  // Remove the character at left pointer
                left++;  // Move left pointer forward to attempt to remove the duplicate
            }
        }

        return max;
    }

    /**
     * Main method for testing the function.
     */
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // Expected: 3 ("abc")
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // Expected: 1 ("b")
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // Expected: 3 ("wke")
        System.out.println(lengthOfLongestSubstring(""));         // Expected: 0 (empty string)
        System.out.println(lengthOfLongestSubstring("abcdef"));   // Expected: 6 (all unique)
        System.out.println(lengthOfLongestSubstring("aab"));      // Expected: 2 ("ab")
        System.out.println(lengthOfLongestSubstring("dvdf"));     // Expected: 3 ("vdf")
        System.out.println(lengthOfLongestSubstring("abba"));     // Expected: 2 ("ab" or "ba")
        System.out.println(lengthOfLongestSubstring("a"));        // Expected: 1 ("a")
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));  // Expected: 5 ("mzuxt")
        System.out.println(lengthOfLongestSubstring("anviaj"));   // Expected: 5 ("nviaj")
        System.out.println(lengthOfLongestSubstring("123456123")); // Expected: 6 ("123456")
    }
}

