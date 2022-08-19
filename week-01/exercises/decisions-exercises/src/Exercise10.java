import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // USPS
        // Below is an abbreviated version of the US Postal Service retail parcel rates:
        /*
        Lbs | Zones 1&2 | Zone 3
        ===============
        1      $7.50      $7.85
        2       8.25       8.70
        3       8.70       9.70
        4       9.20      10.55
        5      10.20      11.30
        */

        // 1. Collect the parcel lbs and zone (1, 2, or 3) from the user.
        // 2. Add `if`/`else if`/`else` logic to cover all rates.
        // Use whatever strategy you think is best. You can create compound conditions or nest if/else statements.
        // If a lbs/zone combo does not exist, print a warning message for the user.

        Scanner console = new Scanner(System.in);

        System.out.print("Enter the parcel weight: ");
        int lbs = Integer.parseInt(console.nextLine());
        System.out.print("Enter the zone number: ");
        int zone = Integer.parseInt(console.nextLine());

        if ((lbs > 5) || (lbs < 1)) {
            System.out.println("That weight is not valid. Try again.");
        } else if ((zone > 3) || (zone < 1)){
            System.out.println("That zone is not valid. Try again.");
        } else if (((zone == 1) || (zone == 2)) && (lbs == 1)) {
            System.out.println("Parcel rate: $7.50");
        } else if (((zone == 1) || (zone == 2)) && (lbs == 2)) {
            System.out.println("Parcel rate: $8.25");
        } else if (((zone == 1) || (zone == 2)) && (lbs == 3)) {
            System.out.println("Parcel rate: $8.70");
        } else if (((zone == 1) || (zone == 2)) && (lbs == 4)) {
            System.out.println("Parcel rate: $9.20");
        } else if (((zone == 1) || (zone == 2)) && (lbs == 5)) {
            System.out.println("Parcel rate: $10.20");
        } else if ((zone == 3) && (lbs == 1)) {
            System.out.println("Parcel rate: $7.85");
        } else if ((zone == 3) && (lbs == 2)) {
            System.out.println("Parcel rate: $8.70");
        } else if ((zone == 3) && (lbs == 3)) {
            System.out.println("Parcel rate: $9.70");
        } else if ((zone == 3) && (lbs == 4)) {
            System.out.println("Parcel rate: $10.55");
        } else if ((zone == 3) && (lbs == 5)) {
            System.out.println("Parcel rate: $11.30");
        } else {
            System.out.println("That zone/weight does not exist. Try again.");
        }
    }
}
