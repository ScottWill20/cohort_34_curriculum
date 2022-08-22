import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);

        // 1. Instantiate three balloons of different colors.

        Balloon one = new Balloon("red");
        Balloon two = new Balloon("yellow");
        Balloon three = new Balloon("blue");

        do {

            System.out.println("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y")) {
                one.inflate();
                // 2. If the user confirms an inflate, inflate each balloon.
            } else if (console.nextLine().equalsIgnoreCase("y")) {
                two.inflate();
            } else if (console.nextLine().equalsIgnoreCase("y")) {
                three.inflate();
            } else {
            }

            // 3. When one or more balloons explode, stop the loop.
        } while (false);


        // 4. Print the color of the winners (balloons that exploded).
    }
}
