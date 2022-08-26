import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PangramTest {

    @Test
    void isPangram() {
        boolean expected = true;
        boolean actual = Pangram.isPangram("The quick brown fox jumps over the lazy dog");
        //should return true for a pangram
        assertEquals(expected,actual);

        //should return true for a pangram with punctuation
        actual = Pangram.isPangram("How vexingly quick daft zebras jump!");
        assertEquals(expected,actual);

        //should return false for a non-pangram
        expected = false;
        actual = Pangram.isPangram("hello world");
        assertEquals(expected,actual);

    }
}