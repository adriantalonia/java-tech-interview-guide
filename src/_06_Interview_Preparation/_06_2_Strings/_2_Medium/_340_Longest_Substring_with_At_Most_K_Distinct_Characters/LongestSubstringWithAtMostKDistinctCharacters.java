package _06_Interview_Preparation._06_2_Strings._2_Medium._340_Longest_Substring_with_At_Most_K_Distinct_Characters;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {

    /**
     * Returns the length of the longest substring that contains at most k distinct characters.
     *
     * @param s The input string.
     * @param k The maximum number of distinct characters allowed in the substring.
     * @return The length of the longest valid substring.
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        // Edge cases: if k is 0 or the string is null/empty, return 0.
        if (k == 0 || s == null || s.length() == 0) return 0;

        // HashMap to keep track of the frequency of characters in the current window
        Map<Character, Integer> map = new HashMap<>();

        int left = 0; // Start of the sliding window
        int right = 0; // End of the sliding window
        int maxLength = 0; // Result to store the maximum length of a valid window

        // Iterate through the string with the right pointer
        while (right < s.length()) {
            char c = s.charAt(right); // Current character to include in the window

            // Add the character to the map or update its count
            map.put(c, map.getOrDefault(c, 0) + 1);

            // If the number of distinct characters exceeds k, shrink the window from the left
            while (map.size() > k) {
                char leftChar = s.charAt(left); // Character at the left pointer
                map.put(leftChar, map.get(leftChar) - 1); // Decrease its count

                // If the count becomes 0, remove it from the map (no longer in window)
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                left++; // Move the left pointer to shrink the window
            }

            // Update the maximum length if the current window is larger
            maxLength = Math.max(maxLength, right - left + 1);

            // Move the right pointer to expand the window
            right++;
        }

        // Return the maximum length found
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // Output: 3 -> "ece"
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));    // Output: 2 -> "aa"
        System.out.println(lengthOfLongestSubstringKDistinct("a", 0));     // Output: 0
    }
}
