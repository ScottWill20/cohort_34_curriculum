public class Exercise04 {

    public static void main(String[] args) {
        // 1. Declare an array to hold the names of the world's oceans.
        // Set its value using array literal notation.
        // 2. Loop over each element and print it.

        String[] oceans = {"Atlantic","Antarctic", "Arctic", "Indian", "Pacific"};

        for (int i = 0; i < oceans.length; i++) {
            String oceanName = oceans[i];
            System.out.printf("The ocean at index %s is %s.%n", i, oceanName);
        }

    }
}
