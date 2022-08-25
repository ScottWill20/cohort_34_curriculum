

public class Pangram {

    public static boolean isPangram(String string) {
        // if string is null, return false
        if (string.equals(null)) {
            return false;
        }
        // lowercase the string
        string = string.toLowerCase();

        //create ABC string with all letters
        String alphabet = "abcdefghijklmnopqrstuvqwxyz";

        // loop through ABC string
        for (int i = 0; i< alphabet.length(); i++) {
            // grab current letter
            char letter = alphabet.charAt(i);
            // check to see if current letter is in the input string
            if (string.indexOf(letter) == -1) {
                // if not, return false
                return false;
            }
        }
        return true;
    }

}
