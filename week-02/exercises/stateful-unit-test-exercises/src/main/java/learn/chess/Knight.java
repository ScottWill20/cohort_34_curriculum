package learn.chess;

public class Knight {

    private int row = 0;
    private int column = 0;

    public Knight() {
    }

    public Knight(int row, int column) {
        this.row = row;
        this.column = column;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // Movement methods

    public boolean move(int row, int column) {
        // cannot move out of bounds
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            return false;
            // cannot move in same spot
        } else if (this.row == row && this.column == column) {
            return false;
            // good move - move in L shape
        } else if ((Math.abs(this.row - row) == 1 && Math.abs(this.column - column) == 2) ||
                (Math.abs(this.row - row) == 2 && Math.abs(this.column - column) == 1)) {

            this.row = row;
            this.column = column;
            return true;
        }
        // bad move
        return false;
    }



}
