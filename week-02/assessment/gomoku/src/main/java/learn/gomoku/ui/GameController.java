package learn.gomoku.ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;

import java.util.Scanner;

public class GameController {

    private final Scanner console = new Scanner(System.in);

    // run
    public void run() {
        System.out.println("Welcome to Gomoku!");
        System.out.println("*".repeat(10));

        // TODO implement support to prompt the user for what type of player to create
        //  for player one and player two
        //  we are hard coding players here but we want the user to choose the player random or human
        //  we are hard coding a name here for now, but you will want to prompt the user for a name
        Player playerOne = new HumanPlayer("Scott");
        Player playerTwo = new HumanPlayer("Evann");

        // Set up a game
        Gomoku game = new Gomoku(playerOne, playerTwo);

        // TODO print the board - Board Class??

        // TODO do while loop to keep playing while the game is not over

        do{
            Player currentPlayer = game.getCurrent();
            System.out.printf("%s, it's your turn!%n", currentPlayer.getName()); // displaying name of the current player

            System.out.print("Enter Row: ");
            int row = Integer.parseInt(console.nextLine()) - 1;

            System.out.print("Enter Column: ");
            int column = Integer.parseInt(console.nextLine()) - 1;

            Stone stone = new Stone(row, column, game.isBlacksTurn());

            Result result = game.place(stone);

            // If the result is not successful
            // then we want to print a message

            if (!result.isSuccess()){
                System.out.println("ERROR: " + result.getMessage());
            }

        } while(!game.isOver());


    }


    // loop

}
