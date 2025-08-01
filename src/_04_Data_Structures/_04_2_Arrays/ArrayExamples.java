package _04_Data_Structures._04_2_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
