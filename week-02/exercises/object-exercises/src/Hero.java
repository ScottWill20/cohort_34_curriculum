public class Hero {
    private String name;
    private Power[] powers;

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;

    }

    public String getName() {
        return name;
    }
    public Power[] getPowers() {
        return powers;
    }
    public String toLine() {
        //
        String[] powerNames = new String[powers.length];

        for (int i = 0; i < powers.length; i++) {
            powerNames[i] = powers[i].getName();
        }
        return String.format("%s (%s)", getName(), String.join(", ", powerNames));
    }
}
