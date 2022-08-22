public class Hero {
    private final String name;
    private final char symbol = '@';

    // final modifier, when applied to a field, requires that the field receives a value during object
    // construction, but prevents assignment after construction

    // a final field is read-only once it's value is set
    // If a field can't change, always use the final modifier

    private int x;
    private int y;

    // create a hero with a name and initial position:
    public Hero(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // getters
    public String getName() {
        return name;
    }

    public char getSymbol() {
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
