import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);

        // 1. Instantiate three balloons of different colors.

        Balloon one = new Balloon("red");
        Balloon two = new Balloon("yellow");
        Balloon three = new Balloon("blue");

        boolean stop = false;

        do {
            System.out.println("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y")) {
                one.inflate();
                two.inflate();
                three.inflate();
            }
            System.out.println(one.getPsi());
            System.out.println(two.getPsi());
            System.out.println(three.getPsi());

            if (one.isExploded() || two.isExploded() || three.isExploded()) {
                stop = true;
            }
            // 3. When one or more balloons explode, stop the loop.
        } while (!stop);

        if (one.isExploded()) {
            System.out.println(one.getColor());
        }
        if (two.isExploded()) {
            System.out.println(two.getColor());
        }
        if (three.isExploded()) {
            System.out.println(three.getColor());
        }
        // 4. Print the color of the winners (balloons that exploded).
    }
}
