import java.sql.SQLOutput;
import java.util.Scanner;

public class CapsuleHotel {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to Capsule-Capsule.");
        System.out.println("===========================");
        System.out.println("Enter the number of capsules available: ");
        String[] totalCapsules = new String[Integer.parseInt(console.nextLine())];
        System.out.println();
        System.out.printf("There are %s unoccupied capsules ready to be booked.",totalCapsules);

    }
}
