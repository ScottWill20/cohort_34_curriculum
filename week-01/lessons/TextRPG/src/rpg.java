import java.util.Scanner;

public class rpg {
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        String choice;

      do {
          System.out.println("You are in a room with no windows. There is a door.");
          System.out.print("?: ");
          choice = console.nextLine();
          if (!choice.equalsIgnoreCase("door")){
              System.out.println("I do not recognize that. Try again.");
          } else {
              System.out.println("You went into another room. It's just a room. You win!");

          }
      } while (!choice.equalsIgnoreCase("door"));

    }
}
