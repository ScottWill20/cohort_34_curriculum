package learn;

/**
 * An underwater, submersible vehicle.
 * Includes two behaviors.
 * dive: go down a little deeper under water to a maximum depth
 * surface: come up a little shallower to a minimum depth of sea level
 * <p>
 * The submarine's current depth and pressure are available via getters.
 */
public class Submarine {

    private final double maxDepth;
    private double depthInMeters;
    private int atmo;

    public Submarine(double maxDepth) {
        this.maxDepth = maxDepth;
    }

    public double getDepthInMeters() {
        return depthInMeters;
    }

    public void dive() {
        depthInMeters + 3;
        // 1. Each dive should increase the depth by 3 meters.
        depthInMeters < maxDepth;
        // Depth cannot exceed maxDepth.
        return depthInMeters + 3;
    }

    public void surface() {
        depthInMeters - 5;
        // 2. Each surface should decrease the depth by 5 meters.
        depthInMeters <= 0.0;
        // Minimum depth is 0.0 (sea level).
    }

    public double getPressureInAtmospheres() {
        double sealevel = 0.0;
        int atmo = 1;
        // 3. At sea level, pressure is 1 atmosphere.

        // Pressure increases by 1 atmosphere for every 10 meters.
        return 0.0;
    }

}
