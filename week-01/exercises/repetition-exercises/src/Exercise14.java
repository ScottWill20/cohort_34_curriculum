import javax.crypto.spec.ChaCha20ParameterSpec;
import java.util.Scanner;

public class Exercise14 {

    public static void main(String[] args) {
        // 1. Collect a phrase from a user via the console.
        // 2. Count the number of digits in the phrase.
        // Hint: Character.isDigit
        // 3. Print the result.
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();

        int digits = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isDigit(phrase.charAt(i)))
                digits++;
        }
        System.out.println("Total number of digits = " + digits);
    }
}
