public class Hero {

    private final String name;
    private final String symbol;
    private int x;
    private int y;

    // Create a hero with a name and an initial position.
    public Hero(String name,String symbol, int x, int y) {
        this.name = name;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // movement
    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveUp() {
        y--;
    }

    public void moveDown() {
        y++;
    }
}
