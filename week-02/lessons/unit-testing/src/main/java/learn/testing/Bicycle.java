package learn.testing;

public class Bicycle {
    // km/h
    public static final double MAX_VELOCITY = 40.0;
    public static final double STANDARD_ACCELERATION = 5.0;
    private static final double BONUS_ACCELERATION = 4.0;

    private final Cyclist cyclist;
    private double velocity;

    public Bicycle(Cyclist cyclist) {
        if (cyclist == null) {
            cyclist = new Cyclist("Anonymous", Cyclist.MIN_POWER);
        }
        this.cyclist = cyclist;
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public double getVelocity() {
        return velocity;
    }

    public void accelerate() {
        // between 0.0 and 0.9
        double powerModifier = (cyclist.getPower() - Cyclist.MIN_POWER) / Cyclist.MAX_POWER;
        // between 0.0 and 3.6
        double powerAcceleration = BONUS_ACCELERATION * powerModifier;
        // can't go faster than MAX_VELOCITY
        velocity = Math.min(velocity + (STANDARD_ACCELERATION + powerAcceleration), MAX_VELOCITY);
    }

    public void brake() {
        // can't go slower than 0 km/h
        velocity = Math.max(0.0, velocity - 10.0);
    }
}
