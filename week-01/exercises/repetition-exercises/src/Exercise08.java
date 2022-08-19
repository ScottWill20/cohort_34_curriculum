import java.util.Scanner;

public class Exercise08 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String name;

        // 1. Rewrite the following as a `do/while` statement.
        // Run the code before you make changes to better understand current behavior.
        // The transformation from `while` to `do/while` should not change behavior.
        do {
            System.out.println("Enter a name (Required): ");
            name = console.nextLine();
        } while (name.isEmpty());
        System.out.println("Name: " + name);
    }
}
