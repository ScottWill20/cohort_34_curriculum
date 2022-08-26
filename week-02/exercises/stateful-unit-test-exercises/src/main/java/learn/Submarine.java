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


    public Submarine(double maxDepth) {
        this.maxDepth = maxDepth;
    }

    public double getDepthInMeters() {
        return depthInMeters;
    }

    public void dive() {
        double result = depthInMeters + 3;

        if (result > maxDepth) {
            depthInMeters =maxDepth;
        } else {
            depthInMeters = result;
        }
    }
    public void surface() {
        double result = depthInMeters -5;
        if (result < 0) {
            depthInMeters = 0;
        } else {
            depthInMeters = result;
        }
        // Minimum depth is 0.0 (sea level).
        // 2. Each surface should decrease the depth by 5 meters.

    }

    public double getPressureInAtmospheres() {
        double result = 1.0;
        result = result + (depthInMeters / 10);
        return result;

        // 3. At sea level, pressure is 1 atmosphere.
        // Pressure increases by 1 atmosphere for every 10 meters
    }
}
