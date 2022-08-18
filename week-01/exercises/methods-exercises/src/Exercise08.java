public class Exercise08 {
    public static void main(String[] args) {
        String fruit = getRandomFruit();
        System.out.println(fruit);
    }

    // 1. Create a method.
    // Name: getRandomFruit
    // Inputs: none
    // Output: String
    // Description: returns a random fruit name as a string.
    // See Exercise01.
    // Choose from at least 5 fruit.

    public static String getRandomFruit() {

        switch ((int) (Math.random() * 5)) {
            case 0:
                return "Apple";
            case 1:
                return "Banana";
            case 2:
                return "Pear";
            case 3:
                return "Strawberry";
            case 4:
                return "Watermelon";
        }
    return "";
    }

}
        // 2. Call your method in various ways to test it here.