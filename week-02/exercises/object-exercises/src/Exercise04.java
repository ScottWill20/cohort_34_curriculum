import java.util.Scanner;

public class Exercise04 {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        // 1. Add an empty constructor to Musician.
        // 2. Uncomment the code below and make sure it runs.

        boolean end = false;
        Musician m = new Musician();
        do {
            System.out.print("Musician name: ");
            String name = console.nextLine();
            if (name.equals("end")) {
                end = true;
            } else {
                m.setName(name);
                System.out.print("Musician rating: ");
                int rating = Integer.parseInt(console.nextLine());
                m.setRating(rating);
                System.out.printf("%s: %s%n", m.getName(), m.getRating());
            }

        } while (!end);

        // 3. Add a loop. The exercise should ask the user for musicians and print
        // them out until the user types "end".


    }
}
