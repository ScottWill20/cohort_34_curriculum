import javax.swing.text.rtf.RTFEditorKit;
import java.util.Scanner;

public enum Move {

    // Enums are useful when all values are known and can never change.

    ROCK, PAPER, SCISSORS;

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

//        Move p1Move = Move.ROCK;
//        Move p2Move = Move.PAPER;
//        p1Move = Move.SCISSORS;
//
//        System.out.println(p1Move); // SCISSORS
//        System.out.println(p2Move); // PAPER
//
//        if (p1Move == p2Move) {
//            System.out.println("It's a tie.");
//        } else {
//            System.out.println("There's going to be a winner and a loser.");
//        }

//        System.out.print("Player 1, choose Rock, Paper, or Scissors: ");
//        // since values are UPPER_CASE, make the input upper case
//        String input = console.nextLine().toUpperCase();
//        Move p1Move = Move.valueOf(input);
//        System.out.println("Player 1, your move is: " + p1Move);
//
//        System.out.print("Player 2, choose Rock, Paper, or Scissors: ");
//        input = console.nextLine().toUpperCase();
//        Move p2Move = Move.valueOf(input);
//        System.out.println("Player 2, your move is: " + p2Move);

//        Move p1Move = chooseMove("Player 1");
//        Move p2Move = chooseMove("Player 2");
//        System.out.printf("Player 1 chose: %s. Player 2 chose: %s.%n", p1Move, p2Move);


        // Enums and switch
//        Move p1Move = chooseMove("Player 1");
//        switch (p1Move) {
//            case ROCK:
//                System.out.println("You chose rock.");
//                System.out.println("Rock beats scissors.");
//                break;
//            case PAPER:
//                System.out.println("You chose paper.");
//                System.out.println("Paper beats rock.");
//                break;
//            case SCISSORS:
//                System.out.println("You chose scissors.");
//                System.out.println("Scissors beats paper.");
//                break;
//        }


        // Color Java Enum
        System.out.println(Color.BLUE.getTraditionalCompliment());
        System.out.println(Color.YELLOW.getSpanishName());
        System.out.println(Color.findByHex("FF0000"));
        System.out.println(Color.ORANGE.getTraditionalCompliment().getSpanishName());
        System.out.println(Color.findByHex("ffA500"));
        // Output
        // ORANGE
        // Amarillo
        // RED
        // Azul
        // ORANGE

    }

    static Move chooseMove(String playerName) {

        Scanner console = new Scanner(System.in);
        Move result = Move.ROCK;
        boolean isValid = false;

        do {
            System.out.printf("%s, choose your move [Rock/Paper/Scissors]: ", playerName);
            try {
                result = Move.valueOf(console.nextLine().toUpperCase());
                isValid = true;
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid move.");
            }
        } while (!isValid);

        return result;


    }
}
