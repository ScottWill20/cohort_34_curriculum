import java.util.Scanner;

public class Exercise19 {
    public static void main(String[] args) {
        // INTERLEAVE
        Scanner console = new Scanner(System.in);

        System.out.print("First string: ");
        String first = console.nextLine();

        System.out.print("Second string: ");
        String second = console.nextLine();

        // 1. Write a loop to interweave two strings to form a new string.
        // To interweave, during each loop take one character from the first string and add it to the result
        // and take one character from the second string and add it to the result.
        // If there are no more characters available, don't add characters.
        // 2. Print the result.

        String result = "";
        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < first.length() || secondIndex < second.length()) {
            if(firstIndex < first.length()) {
                result += first.charAt(firstIndex);
                firstIndex++;
            }
            if (secondIndex < second.length()) {
                result += second.charAt(secondIndex);
                secondIndex++;
            }
        }
        System.out.println(result);

        // Examples
        // "abc", "123" -> "a1b2c3"
        // "cat", "dog" -> "cdaotg"
        // "wonder", "o" -> "woonder"
        // "B", "igstar" -> "Bigstar"
        // "", "huh?" -> "huh?"
        // "wha?", "" -> "wha?"
    }
}
