import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class CapsuleHotel {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to Capsule-Corp.");
        System.out.println("===========================");
        System.out.print("Enter the number of capsules available: ");

        int capsules = Integer.parseInt(console.nextLine());

        System.out.println();
        System.out.printf("There are %s unoccupied capsules ready to be booked.", capsules);
        System.out.println();

        boolean exit = false;
        String[] guests = new String[capsules];

        do {
            int choice = welcome(console);

            switch (choice) {
                case 1:
                    guests = checkIn(guests, console);
                    break;
                case 2:
                    guests = checkOut(guests, console);
                    break;
                case 3:
                    viewGuests(guests, console);
                    break;
                case 4:
                    exit = exitConfirm(console);
                    break;
                default:
                    System.out.println("Sorry, that's not a valid option.");
            }

        } while (!exit);

    }

    // this closes main method
    // welcome method that contains

    public static int welcome(Scanner console) {
        System.out.println();
        System.out.println("Welcome to the Capsule Corp - What would you like to do today?");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
        System.out.println("3. View Guests");
        System.out.println("4. Exit");

        System.out.print("Your Selection: ");
        System.out.println();
        int menuOption = Integer.parseInt(console.nextLine());
        return menuOption;
    }

    public static String[] checkIn(String[] guests, Scanner console) {

        int capsuleNumber;
        String guestName;
        boolean exitNum = false;
        boolean exitName = false;

        System.out.println();
        System.out.println("Check In");
        System.out.println("========");

        do {
            System.out.println("Please enter your name: ");
            guestName = console.nextLine();

            if (guestName.equals("")) {
                System.out.println("Sorry, that is not a valid option.");
                System.out.println();

            } else {
                exitName = true;
            }
        } while (!exitName);

        do {
            System.out.printf("Please enter your preferred capsule number [1-%s]: ", guests.length);
            System.out.println();
            capsuleNumber = Integer.parseInt(console.nextLine()) - 1;

            if (capsuleNumber >= guests.length || capsuleNumber < 0) {
                System.out.println("Sorry, that is not a valid option.");
                System.out.println();

            } else if (guests[capsuleNumber] != null) {
                System.out.println("Error :(");
                System.out.printf("Capsule #%s is occupied.", capsuleNumber + 1);
                System.out.println();

            } else {
                exitNum = true;
            }
        } while (!exitNum);

        guests[capsuleNumber] = guestName;
        System.out.println("Success :)");
        System.out.printf("Capsule number %d has been booked to %s.", capsuleNumber + 1, guestName);
        System.out.println();
        return guests;
    }

    public static String[] checkOut(String[] guests, Scanner console) {

        int capsuleNumber;
        String guestName;
        boolean exitNum = false;

        System.out.println("Check In");
        System.out.println("========");

        do {
            System.out.printf("Please enter your capsule number [1-%s]: ", guests.length);
            System.out.println();
            capsuleNumber = Integer.parseInt(console.nextLine()) - 1;

            if (capsuleNumber >= guests.length) {
                System.out.println("Sorry, that is not a valid option.");
                System.out.println();

            } else if (guests[capsuleNumber] == null) {
                System.out.println("Error :(");
                System.out.printf("Capsule #%s is not occupied.", capsuleNumber + 1);
                System.out.println();

            } else {

                exitNum = true;
            }
        } while (!exitNum);

        guestName = guests[capsuleNumber];
        guests[capsuleNumber] = null;
        System.out.println("Success :)");
        System.out.printf("%s has checked out from capsule %d.", guestName, capsuleNumber + 1);
        System.out.println();
        return guests;
    }


    public static void viewGuests(String[] guests, Scanner console) {

        boolean exitNum = false;

        System.out.println("View Guests");
        System.out.println("===========");

        int capsuleNumber;

        do {
            System.out.printf("Please enter your capsule number [1-%s]: ", guests.length);
            System.out.println();
            capsuleNumber = Integer.parseInt(console.nextLine()) - 1;

            if (capsuleNumber >= guests.length) {
                System.out.println("Sorry, that is not a valid option.");
                System.out.println();
            } else {
                exitNum = true;
            }
        } while (!exitNum);


        if (capsuleNumber <= 4) {
            for (int index = 0; index <= 10; index++) {
                String guestName = guests[index];
                if (guestName == null) {
                    guestName = "[unoccupied]";
                }
                System.out.printf("%d: %s%n", index + 1, guestName);
            }
        } else if (capsuleNumber >= guests.length - 5) {
            for (int index = 0; index <= 10; index++) {
                String guestName = guests[index];
                if (guestName == null) {
                    guestName = "[unoccupied]";
                }
                System.out.printf("%d: %s%n", index + 1, guestName);
            }
        } else {
            for (int index = capsuleNumber - 5; index <= capsuleNumber + 5; index++) {
                String guestName = guests[index];
                if (guestName == null) {
                    guestName = "[unoccupied]";
                }
                System.out.printf("%d: %s%n", index + 1, guestName);
            }
        }
    }

    public static boolean exitConfirm(Scanner console) {

        System.out.println("Exit");
        System.out.println("====");
        System.out.println("Are you sure you want to exit?");
        System.out.println("All data will be lost.");
        System.out.print("exit [y/n]: ");

        String exitInput = console.nextLine().toLowerCase();

        if ((exitInput.equals("y")) || (exitInput.equals("yes"))) {
            return true;
            //close application
        } else if ((exitInput.equals("n")) || (exitInput.equals("no"))) {
            return false;
            // loop back to Main Menu
        } else {
            System.out.println("Sorry, that is not a valid option.");
            System.out.println();
            return exitConfirm(console);
        }
    }
}
