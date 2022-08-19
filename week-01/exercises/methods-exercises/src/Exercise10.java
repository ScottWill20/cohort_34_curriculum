public class Exercise10 {
    // 1. Add a `main` method.
    // 2. Create method that accepts a String and returns that string with all of its whitespace remove.
    // 2. Call your method in various ways in the main method.
    public static void main(String[] args) {

        System.out.println(removeWhitespace("The quick brown fox jumped over the lazy dog"));
        System.out.println(removeWhitespace("Roses are red, violets are blue"));
    }

    public static String removeWhitespace(String phrase) {
        String result = "";
        for (int i=0; i< phrase.length(); i++) {
            if(!Character.isWhitespace(phrase.charAt(i))) {
                result += phrase.charAt(i);
            }
        }
        return result;
    }

}
