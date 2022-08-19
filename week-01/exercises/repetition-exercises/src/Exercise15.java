public class Exercise15 {

    public static void main(String[] args) {
        // BOX
        // 1. Use nested loops to print a box in the console.
        // One loop should represent rows and the other should represent columns.
        // 2. Change the row and column limit to change the shape of the box.

        // Rows
        int row = 5;
        int column = 5;

        for (int r = 0; r < row; r++){
            for (int c = 0; c < column; c++){
                System.out.print("#");
            }
            System.out.println();
        }

        // Expected Output (5X5)
        // #####
        // #####
        // #####
        // #####
        // #####

        // (3X4)
        // ####
        // ####
        // ####
    }
}
