public class Exercise16 {

    public static void main(String[] args) {
        // BORDER BOX
        // 1. Use nested loops to print a box in the console with a different character for the border.
        // One loop should represent rows and the other should represent columns.
        // The border character should be different from the internal box character.
        // 2. Change the row and column limit to change the shape of the box.

        int row = 5;
        int column = 10;

          // Rows
        for (int r = 0; r < row; r++) {
            // Columns
            for (int c = 0; c < column; c++) {
            char star = '*';
                if (r == 0 || r == row -1 || c == 0 || c == column -1) {
                }
                System.out.print(star);
            }
            System.out.println();
        }

        // Expected Output (5X5)
        // *****
        // *###*
        // *###*
        // *###*
        // *****

        // (3X4)
        // ****
        // *##*
        // ****

        // (2X2)
        // **
        // **
    }
}
