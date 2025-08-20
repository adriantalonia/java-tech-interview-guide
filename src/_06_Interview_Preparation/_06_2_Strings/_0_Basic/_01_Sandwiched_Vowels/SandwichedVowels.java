package _06_Interview_Preparation._06_2_Strings._0_Basic._01_Sandwiched_Vowels;

import java.util.*;

public class SandwichedVowels {

    // Helper method to check if a character is a vowel using a pre-populated Set.
    private static boolean isVowel(char c) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        return vowels.contains(c);
    }

    public static String solve(String s) {
        // Handle short strings that cannot contain a sandwiched vowel.
        if (s == null || s.length() < 3) {
            return s;
        }

        StringBuilder result = new StringBuilder();

        // The first character can never be a sandwiched vowel, so we always add it.
        result.append(s.charAt(0));

        // Iterate from the second character to the second-to-last character.
        for (int i = 1; i < s.length() - 1; i++) {
            char currentChar = s.charAt(i);
            char prevChar = s.charAt(i - 1);
            char nextChar = s.charAt(i + 1);

            // Check if the current character is a vowel AND its neighbors are consonants.
            if (isVowel(currentChar) && !isVowel(prevChar) && !isVowel(nextChar)) {
                // If the condition is met, we skip this character.
            } else {
                // Otherwise, we append the current character to the result.
                result.append(currentChar);
            }
        }

        // The last character can also never be a sandwiched vowel, so we always add it.
        result.append(s.charAt(s.length() - 1));

        return result.toString();
    }

    public static void main(String[] args) {
        // Example Test Cases
        System.out.println("Input: bab, Output: " + solve("bab") + " (Expected: bb)");
        System.out.println("Input: ceghij, Output: " + solve("ceghij") + " (Expected: cghj)");
        System.out.println("Input: beautiful, Output: " + solve("beautiful") + " (Expected: btfl)");
        System.out.println("Input: abcde, Output: " + solve("abcde") + " (Expected: abcde)");
        System.out.println("Input: aeiaou, Output: " + solve("aeiaou") + " (Expected: aeiaou)");
        System.out.println("Input: rhythm, Output: " + solve("rhythm") + " (Expected: rhythm)");
        System.out.println("Input: a, Output: " + solve("a") + " (Expected: a)");
    }
}
