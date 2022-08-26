package learn.testing;

public class Cyclist {

    public static final double MIN_POWER = 1.0;
    public static final double MAX_POWER = 10.0;

    private final String name;
    private final double power;

    public Cyclist(String name, double power) {
        this.name = name;
        // valid power range is 1-10
        this.power = Math.min(Math.max(power, MIN_POWER), MAX_POWER);
    }

    public String getName() {
        return name;
    }

    public double getPower() {
        return power;
    }
}
