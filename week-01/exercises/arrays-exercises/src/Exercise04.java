public class Exercise04 {

    public static void main(String[] args) {
        // 1. Declare an array to hold the names of the world's oceans.
        // Set its value using array literal notation.
        // 2. Loop over each element and print it.
 dev

        String[] oceans = {"Atlantic","Antarctic", "Arctic", "Indian", "Pacific"};

        for (int index = 0; index < oceans.length; index++) {
            String oceanName = oceans[index];
            System.out.printf("The ocean at index %s is %s.%n", index, oceanName);
        }

 main
    }
}
