import java.util.Scanner;

public class Exercise11 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Starting Number: ");
        int start = Integer.parseInt(console.nextLine());

        System.out.print("Ending Number: ");
        int end = Integer.parseInt(console.nextLine());

        System.out.print("Increment: ");
        int increment = Integer.parseInt(console.nextLine());

        int sum = 0;
        for (int index = start; sum < end; index++) {
            sum += increment;
            System.out.println(sum);
        }
    }
}
