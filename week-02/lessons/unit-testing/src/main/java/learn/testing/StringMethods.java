
package learn.testing;

public class StringMethods {

    // TODO: actual implementation
    public static int countDigits(String value) {

        if (value == null) { // account for null
            return 0;
        }

        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
