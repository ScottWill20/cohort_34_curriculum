import java.util.Scanner;

public class Exercise14 {

    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
     */
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
    public static int readInt (String prompt) {
        String input = readRequiredString(prompt);
        return Integer.parseInt(input);
    }

    public static void main(String[] args) {
        String firstName = readRequiredString("What is your first name?: ");
        String lastName = readRequiredString("What is your last name?: ");
        int placesLived = readInt("How many cities or towns have you lived in?: ");
        int instrument = readInt("How many musical instruments can you play?: ");

        System.out.printf("%s %s has lived in %s places and can play %s instruments.", firstName, lastName, placesLived, instrument);
    }
}
