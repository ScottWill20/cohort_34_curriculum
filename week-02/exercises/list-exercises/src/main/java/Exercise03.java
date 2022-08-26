import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        games.add(new BoardGame("Dice Throne", 2,8,"Card Game"));
        printAll(games);
        games.add(new BoardGame("Dungeon Mayhem",2,4,"Card Game"));
        printAll(games);
        games.add(new BoardGame("Some Other Game",4,8,"Card Game"));
        printAll(games);


        // 1. Add three new games to `games` with the `add` method.
        // 2. Print `games` after each add.
    }
}
