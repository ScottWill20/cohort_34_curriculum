import java.util.Scanner;
public class Exercise09 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a minimum number: ");
        int min = Integer.parseInt(console.nextLine());
        System.out.print("Enter a maximum number: ");
        int max = Integer.parseInt(console.nextLine());
        System.out.print("Enter a number between your minimum and maximum numbers: ");
        int actual = Integer.parseInt(console.nextLine());

        if ((actual > min) && (actual < max)) {
            System.out.println("true");
        }   else {
            System.out.println("false");
        }
    }
}
