public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.println(ocean.getName());
        // 2. Uncomment the line below and ensure that it compiles and runs.
         System.out.println(ocean.getRating());

        // 3. Instantiate two musicians and assign them to new variables.
        Musician lamar = new Musician("Kendrick Lamar", 10);
        Musician weeknd = new Musician("The Weeknd", 10);

        // 4. Print each musicians' name and rating on a single line.

        String format = "name: %s, rating: %s%n";

        System.out.printf(format, ocean.getName(), ocean.getRating());
        System.out.printf(format, lamar.getName(), lamar.getRating());
        System.out.printf(format, weeknd.getName(), weeknd.getRating());

    }
}
