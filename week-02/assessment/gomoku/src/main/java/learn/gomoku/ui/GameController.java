package learn.gomoku.ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;

public class GameController {

    private final Scanner console = new Scanner(System.in);

    public char[][] board = {{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
                             {'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'}};


    private Gomoku game;


    // run
    public void run() { // uses setUp, play, playAgain
        System.out.println("Welcome to Gomoku!");
        System.out.println("*".repeat(10));
        boolean exit;
        do{
            setUp();
            play();
            exit = playAgain(console);
        } while (!exit);

    }

    private void setUp() { // uses getPlayerOne, getPlayerTwo

        game = new Gomoku(getPlayerOne(),getPlayerTwo());

        for (int i = 0; i < Gomoku.WIDTH; i++) {
            for (int j = 0; j < Gomoku.WIDTH; j++)
            board[i][j] = '_';
        }


        // reset the board - clear all characters
        // display messages

    }

    private Player getPlayerOne() { // uses readInt, readRequiredString
        Player playerOne = null;
        System.out.println();
        System.out.println("Player 1 is: ");
        System.out.println("1. Human Player");
        System.out.println("2. Random Player");
        System.out.print("Select [1-2]: ");
        int playerNumber = Integer.parseInt(console.nextLine());
        System.out.println();
        switch (playerNumber){
            case 1:
                System.out.println("Player 1, enter your name: ");
                playerOne = new HumanPlayer(readRequiredString(console.nextLine()));
                break;
            case 2:
                System.out.println("Generating random player...");
                System.out.println();
                playerOne = new RandomPlayer();
                break;
            default:
                System.out.println("That is not a valid input");
                break;
        }
        return playerOne;
    }
    private Player getPlayerTwo() { // uses readInt, readRequiredString
        Player playerTwo = null;
        System.out.println();
        System.out.println("Player 2 is: ");
        System.out.println("1. Human Player");
        System.out.println("2. Random Player");
        System.out.print("Select [1-2]: ");
        int playerNumber = Integer.parseInt(console.nextLine());
        System.out.println();
        switch (playerNumber){
            case 1:
                System.out.println("Player 2, enter your name: ");
                playerTwo = new HumanPlayer(readRequiredString(console.nextLine()));
                break;
            case 2:
                System.out.println("Generating random player...");
                System.out.println();
                playerTwo = new RandomPlayer();
                break;
            default:
                System.out.println("That is not a valid input");
                break;
        }
        return playerTwo;
    }

    private void play() { // uses printBoard, readInt (for row and column)
        System.out.println(game.getCurrent().getName() + " goes first!");
        do{
            printBoard();
            System.out.println();
            Stone stone = game.getCurrent().generateMove(game.getStones());

            if (stone == null) { // Human Player
                System.out.printf("%s, it's your turn!%n", game.getCurrent().getName()); // displaying name of the current player
                System.out.print("Enter Row: ");
                int row = Integer.parseInt(console.nextLine()) - 1;

                System.out.print("Enter Column: ");
                int column = Integer.parseInt(console.nextLine()) - 1;

                Stone placeStone = new Stone(row,column, game.isBlacksTurn());

                Result result = game.place(placeStone);

                board[placeStone.getRow()][placeStone.getColumn()] = game.isBlacksTurn() ? 'O' : 'X';

                // If the result is not successful
                // then we want to print a message

                if (!result.isSuccess()){
                    System.out.println("ERROR: " + result.getMessage());
                } else;

            } else { // Random Player

                System.out.printf("%s, it's your turn!%n", game.getCurrent().getName()); // displaying name of the current player

                Stone placeStone = new Stone(stone.getRow(), stone.getColumn(), game.isBlacksTurn());

                Result result = game.place(placeStone);

                board[stone.getRow()][stone.getColumn()] = game.isBlacksTurn() ? 'O' : 'X';

                if (!result.isSuccess()){
                    System.out.println("ERROR: " + result.getMessage());
                } else;
            }
        } while(!game.isOver());

        printBoard();
        if (game.getWinner() != null) {
            System.out.println();
            System.out.println(game.getWinner().getName() + " wins!");
        } else {
            System.out.println("It's a draw.");
        }
    }

    private void printBoard() {

        // top border
        System.out.println();
        System.out.print("   01 02 03 04 05 06 07 08 09 10 11 12 13 14 15");
        for (int i = 0; i < Gomoku.WIDTH; i++) {
            if (i > 8) {
                System.out.printf("%n" + (i + 1) + " ");
            } else {
                System.out.printf("%n0" + (i + 1) + " ");
            }
            for (int j = 0; j < Gomoku.WIDTH; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
        }
        //bottom border
        System.out.println();
        System.out.print("   01 02 03 04 05 06 07 08 09 10 11 12 13 14 15");
        System.out.println();

    }

    private boolean playAgain(Scanner console) { // may use readRequiredString

        System.out.println();
        System.out.println("*".repeat(10));
        System.out.println();
        System.out.print("Play again? [y/n]: ");
        System.out.println();

        String exitInput = readRequiredString(console.nextLine().toLowerCase());

        if ((exitInput.equals("n")) || (exitInput.equals("no"))) {
            return true;
            //close application
        } else if ((exitInput.equals("y")) || (exitInput.equals("yes"))) {
            return false;
            // loop back to Main Menu
        } else {
            System.out.println("Sorry, that is not a valid option.");
            System.out.println();
            return playAgain(console);
        }

    }




    // Helper Methods
    private int readInt(String message, int min, int max) {
        int value = Integer.parseInt(readRequiredString(message));
        while (value > max || value < min) {
            System.out.println("That is not a valid input. Try again.");
        }
        return value;
    }
    private String readRequiredString(String message){
        while (message == null) {
            message = console.nextLine().replaceAll("\\s", "");
        }
        return message;
    }


}
