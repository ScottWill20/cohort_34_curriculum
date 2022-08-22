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
}
