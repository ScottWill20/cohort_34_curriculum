import java.util.Scanner;

public class Exercise17 {

    public static void main(String[] args) {
        // USER-DEFINED BOX
        // 1. Collect the following from a user: rows, columns, box character, border character.
        // 2. Use nested loops to print a user-defined box in the console.
        // (See Exercise16.)
        Scanner console = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int row = Integer.parseInt(console.nextLine());

        System.out.print("Enter the number of columns: ");
        int column = Integer.parseInt(console.nextLine());

        System.out.print("Enter the box character: ");
        char box = (console.nextLine().charAt(0));

        System.out.print("Enter the border character: ");
        char border = console.nextLine().charAt(0);

        for (int r = 0; r < row; r++){
            for (int col = 0; col< column; col++){
                char c = box;
                if (r == 0 || r == row -1 || col == 0 ||col == column -1){
                    c= border;
                }
                System.out.print(c);
            }
            System.out.println();
        }

    }
}
