public class Exercise03 {
    public static void main(String[] args) {
        String[] commonlyMisspelledWords = {"indict", "fiery", "misspell", "comparsion", "perseverance"};

 dev
        commonlyMisspelledWords[3] = "comparison";

        printArray(commonlyMisspelledWords);
    }

    public static void printArray(String[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);

        for (int i = 0; i < commonlyMisspelledWords.length; i++) {
            System.out.println(commonlyMisspelledWords[i]);
 main
        }
    }

}

 dev

// One of the commonlyMisspelledWords is misspelled.
// (IntelliJ may help here. It highlights misspelled words.)
// 1. Change it to the correct spelling. Don't alter the array literal declaration. Set the value by index.

// 2. Loop a second time and confirm all five words are spelled correctly.

        // 2. Loop a second time and confirm all five words are spelled correctly.
    }
}
main
