package _06_Interview_Preparation._06_1_Arrays._0_Basics._03_contries_at_war;

/**
 * CountriesAtWar - Solution for war simulation between two countries
 * <p>
 * Problem: Two countries A and B are at war. Soldiers fight 1-vs-1 battles against
 * their counterparts at the same array index. Higher power wins, equal power = both die.
 * Determine which country wins based on survivor count.
 * <p>
 * Algorithm: Battle-by-battle simulation with survivor counting
 * Time Complexity: O(n) - single pass through both arrays
 * Space Complexity: O(1) - only using counter variables
 */
public class CountriesAtWar {

    /**
     * Determines the winner of war between two countries based on individual battles.
     * <p>
     * Battle Rules:
     * - arr1[i] vs arr2[i]: soldiers fight their direct counterpart only
     * - Higher power soldier kills the enemy (survivor count +1)
     * - Equal power soldiers kill each other (no survivors)
     * - Country with more survivors wins the war
     *
     * @param arr1 Array representing power levels of country A's soldiers
     * @param arr2 Array representing power levels of country B's soldiers
     * @return "A" if country A wins, "B" if country B wins, "DRAW" if tied
     * <p>
     * Examples:
     * - countryAtWar([2,2], [5,5]) → "B" (0 vs 2 survivors)
     * - countryAtWar([9], [8]) → "A" (1 vs 0 survivors)
     * - countryAtWar([3,5], [3,5]) → "DRAW" (0 vs 0 survivors)
     */
    public static String countryAtWar(int[] arr1, int[] arr2) {
        // Input validation - handle edge cases
        if (arr1 == null || arr2 == null || arr1.length == 0) {
            // No valid armies to fight, declare draw
            return "DRAW";
        }

        // Initialize survivor counters for each country
        int aWins = 0;  // Count of battles won by country A (survivors from A)
        int bWins = 0;  // Count of battles won by country B (survivors from B)

        // Simulate each individual battle between counterpart soldiers
        for (int i = 0; i < arr1.length; i++) {
            // Battle at position i: arr1[i] (Country A) vs arr2[i] (Country B)

            if (arr1[i] > arr2[i]) {
                // Country A soldier has higher power - A wins this battle
                aWins++;        // A gets one survivor from this battle
            } else if (arr1[i] < arr2[i]) {
                // Country B soldier has higher power - B wins this battle
                bWins++;        // B gets one survivor from this battle
            }
            // If arr1[i] == arr2[i]: Equal power, both soldiers die
            // No increment for either side (both soldiers eliminate each other)
        }

        // Determine overall war winner based on survivor counts
        if (aWins > bWins) {
            return "A";     // Country A has more survivors
        } else if (aWins < bWins) {
            return "B";     // Country B has more survivors
        } else {
            return "DRAW";  // Equal survivors (including 0-0 scenario)
        }
    }

    /**
     * Helper method to display battle details for educational purposes
     * Shows step-by-step battle simulation with running survivor count
     */
    public static void simulateBattleWithDetails(int[] arr1, int[] arr2, String testName) {
        System.out.println("\n=== " + testName + " ===");
        System.out.println("Country A: " + java.util.Arrays.toString(arr1));
        System.out.println("Country B: " + java.util.Arrays.toString(arr2));
        System.out.println("\nBattle Simulation:");
        System.out.println("┌─────────┬────────────┬────────┬─────────────┬─────────────┐");
        System.out.println("│ Battle  │  A vs B    │ Winner │ A Survivors │ B Survivors │");
        System.out.println("├─────────┼────────────┼────────┼─────────────┼─────────────┤");

        int aWins = 0, bWins = 0;

        // Simulate each battle with detailed output
        for (int i = 0; i < arr1.length; i++) {
            String winner;
            if (arr1[i] > arr2[i]) {
                aWins++;
                winner = "A";
            } else if (arr1[i] < arr2[i]) {
                bWins++;
                winner = "B";
            } else {
                winner = "Tie";
            }

            System.out.printf("│   %2d    │ %3d vs %3d │   %s   │      %d      │      %d      │%n",
                    i + 1, arr1[i], arr2[i], winner, aWins, bWins);
        }

        System.out.println("└─────────┴────────────┴────────┴─────────────┴─────────────┘");

        // Show final result
        String result = countryAtWar(arr1, arr2);
        System.out.println("Final Result: " + result);
        if (!result.equals("DRAW")) {
            System.out.println("Reason: Country " + result + " has more survivors (" +
                    (result.equals("A") ? aWins : bWins) + " vs " +
                    (result.equals("A") ? bWins : aWins) + ")");
        } else {
            System.out.println("Reason: Equal survivors (" + aWins + " vs " + bWins + ")");
        }
    }

    /**
     * Main method with comprehensive test cases to validate the solution
     */
    public static void main(String[] args) {
        System.out.println("🏛️  COUNTRIES AT WAR - TEST SUITE  🏛️");
        System.out.println("=".repeat(50));

        // Test Case 1: Clear winner - B dominates
        int[] test1A = {2, 2};
        int[] test1B = {5, 5};
        simulateBattleWithDetails(test1A, test1B, "Test 1: B Dominates");

        // Test Case 2: Clear winner - A dominates
        int[] test2A = {9};
        int[] test2B = {8};
        simulateBattleWithDetails(test2A, test2B, "Test 2: A Dominates");

        // Test Case 3: Perfect draw - all battles tie
        int[] test3A = {3, 5, 7};
        int[] test3B = {3, 5, 7};
        simulateBattleWithDetails(test3A, test3B, "Test 3: Perfect Draw");

        // Test Case 4: Mixed battles resulting in draw
        int[] test4A = {10, 1, 6};
        int[] test4B = {2, 9, 8};
        simulateBattleWithDetails(test4A, test4B, "Test 4: Mixed Battles Draw");

        // Test Case 5: Edge case - single soldier
        int[] test5A = {100};
        int[] test5B = {99};
        simulateBattleWithDetails(test5A, test5B, "Test 5: Single Battle");

        // Test Case 6: Edge case - zero power soldiers
        int[] test6A = {0, 5, 0};
        int[] test6B = {0, 3, 1};
        simulateBattleWithDetails(test6A, test6B, "Test 6: Zero Power Soldiers");

        // Test Case 7: Large power differences
        int[] test7A = {100000, 1, 50000};
        int[] test7B = {99999, 2, 50001};
        simulateBattleWithDetails(test7A, test7B, "Test 7: Large Numbers");

        // Quick validation tests
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🔍 QUICK VALIDATION TESTS");
        System.out.println("=".repeat(50));

        // Simple test cases without detailed output
        System.out.println("Basic Tests:");
        System.out.println("  [2,2] vs [5,5]: " + countryAtWar(new int[]{2, 2}, new int[]{5, 5})); // Expected: B
        System.out.println("  [9] vs [8]: " + countryAtWar(new int[]{9}, new int[]{8})); // Expected: A
        System.out.println("  [3,5] vs [3,5]: " + countryAtWar(new int[]{3, 5}, new int[]{3, 5})); // Expected: DRAW

        // Edge case tests
        System.out.println("\nEdge Cases:");
        System.out.println("  Empty arrays: " + countryAtWar(new int[]{}, new int[]{})); // Expected: DRAW
        System.out.println("  Null arrays: " + countryAtWar(null, null)); // Expected: DRAW
        System.out.println("  All zeros: " + countryAtWar(new int[]{0, 0}, new int[]{0, 0})); // Expected: DRAW

        System.out.println("\n✅ All tests completed!");
        System.out.println("\nAlgorithm Performance:");
        System.out.println("  Time Complexity: O(n) - linear scan");
        System.out.println("  Space Complexity: O(1) - constant extra space");
        System.out.println("  Handles up to 10^6 soldiers efficiently");
    }
}