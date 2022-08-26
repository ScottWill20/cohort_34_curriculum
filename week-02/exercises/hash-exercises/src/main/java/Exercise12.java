import java.util.HashSet;
import java.util.Random;

public class Exercise12 {

    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Integer> numbers = new HashSet<>();

        // 1. Generate 10 unique random numbers between 0 and 100.
        // 2. Print the numbers to the console.
        // (Hint: You can add random numbers to the `numbers` HashSet until its size is 10.
        // That guarantees the numbers are unique.)

        // while the size of the hashset is < 10
        while(numbers.size() < 10) {
            // add a random number. Note that hashsets by default wont allow two values that are not unique
            numbers.add(random.nextInt(0, 100));
        }
        // print out all the numbers in the set
        for (int n : numbers) {
            System.out.println(n);
        }

    }
}
