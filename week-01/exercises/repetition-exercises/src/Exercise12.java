import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();
        String x = "x";
        String result = "";

        // 1. Write a loop to determine if the letter `x` occurs in a user-entered phrase.
        // 2. Print a message for both finding and not finding the `x`.

        for (int i = 0; i < phrase.length(); i++) {
            if (x.indexOf(phrase.charAt(i)) < 0) {
                result += phrase.charAt(i);
                System.out.println("No X was found");
            } else {
                System.out.println("An X was found");
            }
        }
    }
}
