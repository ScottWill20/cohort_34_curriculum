import learn.BoardGame;
import learn.GameRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Exercise04 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        ArrayList<BoardGame> threeGames = new ArrayList<>();
        threeGames.add(new BoardGame());
        threeGames.add(new BoardGame());
        threeGames.add(new BoardGame());
        printAll(threeGames);

        games.addAll(threeGames);
        printAll(games);



        // 1. Instantiate a new ArrayList<BoardGame>.
        // 2. Add three BoardGames to the new list.
        // 3. Print the new list.
        // 4. Add items in the new list to `games` with the `addAll` method.
        // 5. Print `games`.
    }
}
