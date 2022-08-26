public class Exercise15 {

    // 1. Create a new method in the Hero class.
    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.

    public static void main(String[] args) {

        Power levitation = new Power("Levitation");
        Power flight = new Power("Flight");
        Power blastPower = new Power("Blast Power");
        Power magic = new Power("Magic");
        Power time = new Power("Control reality");
        Power strength = new Power("Super Strength");
        Power web = new Power("Web-Swinging");
        Power sticky = new Power("Climb on Anything");


        Hero iron = new Hero("Iron Man", new Power[]{levitation, flight, blastPower});
        Hero strange = new Hero("Dr. Strange", new Power[]{time, magic});
        Hero spider = new Hero("Spider-Man", new Power[]{strength, web, sticky});

        System.out.println(iron.toLine());
        System.out.println(strange.toLine());
        System.out.println(spider.toLine());

        // 2. Instantiate your three favorite super heroes with appropriate powers.
        // 3. Use the `toLine` method to print each hero's details to the console.





    }
}
