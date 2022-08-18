public class Exercise06 {

    // 1. Create a method.
    // Name: isBetween
    // Inputs: int, int, int
    // Output: boolean
    // Description: return true if the first parameter is between the second and third parameter.
    // Otherwise, returns false.

    public static boolean isBetween(int firstParam) {
        return (firstParam > 25 && firstParam < 75);
    }
    public static void main(String[] args) {

        int firstParam = 50;
        if (isBetween(firstParam) == true)
            System.out.print(isBetween(26));
        }
        // 2. Call your method in various ways to test it here.
    }