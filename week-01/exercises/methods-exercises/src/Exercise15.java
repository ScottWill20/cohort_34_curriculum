import java.util.Scanner;

public class Exercise15 {
    /* FIZZ BUZZ

    Historically, the Fizz Buzz (https://en.wikipedia.org/wiki/Fizz_buzz) problem was used in programming interviews.
    Not sure if it still is. Just in case, we'll get it out of the way in Milestone 1.

    Write a program to:
    - Prompt a user for a positive integer and store the result. (Could reuse a readInt method.)
    - Loop from the number 1 to the user's integer.
    - If the number is divisible by 3, print Fizz.
    - If the number is divisible by 5, print Buzz.
    - If the number is divisible by both 3 and 5, print Fizz Buzz.
    - If the number is not divisible by either 3 or 5, print the number.
     */

    public static void main(String[] args) {
        System.out.println("Welcome to Fizz Buzz");
        int number = readPositiveInt("Please choose a positive number");

        for (int i = 0; i < number; i++) {
            if(i % 3 == 0 && i % 5 == 0) {
                System.out.println("Fizz Buzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0){
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
    public static String readRequiredString(String prompt) {
        Scanner console = new Scanner(System.in);
        String result;
        do {
            System.out.println(prompt);
            result = console.nextLine();
            if (result.isBlank()) {
                System.out.println("Error: you must enter a value");
            }
        } while (result.isBlank());
        return result;
    }
    // Make sure it's a positive int
    public static int readPositiveInt(String prompt) {
        int result;
        do{
            String input = readRequiredString(prompt);
            result = Integer.parseInt(input);
            if (result <= 0) {
                System.out.println("Error: You must enter a positive number");
            }
        } while (result <= 0);
        return result;
    }
}
