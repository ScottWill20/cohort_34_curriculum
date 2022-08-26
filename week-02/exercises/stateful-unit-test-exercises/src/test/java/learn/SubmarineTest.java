package learn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubmarineTest {

    Submarine submarine;

    @BeforeEach
    void setUp() {
        submarine = new Submarine(100.0);
    }
    @Test
    void shouldHaveCorrectDepthAfter3Dives() {
        submarine.dive();
        submarine.dive();
        submarine.dive();
        assertEquals(9.0, submarine.getDepthInMeters(), 0.001);
    }
    @Test
    void shouldHaveCorrectPressureAfter3Dives() {
        submarine.dive();
        submarine.dive();
        submarine.dive();
        // 1.0 at sea level plus 1.0 * 0.9
        assertEquals(1.9, submarine.getPressureInAtmospheres(), 0.001);
    }
    @Test
    void shouldNotExceedMaxDepth() {
        // arrange
        moveToMaxDepth(submarine, 100);
        // act
        submarine.dive();
        // assert
        assertEquals(100,submarine.getDepthInMeters(),.001);
    }
    @Test
    void shouldNotExceedSurfaceLevel() {
        // arrange
        submarine.dive(); // depth 3
        // act
        submarine.surface();
        // assert
        assertEquals(0.0, submarine.getDepthInMeters(),.001);

    }
    @Test
    void shouldHaveCorrectDepthAfter3Surfaces() {
        // arrange
        moveToMaxDepth(submarine,100.0);
        // act
        submarine.surface();
        submarine.surface();
        submarine.surface();
        // assert
        assertEquals(85.0,submarine.getDepthInMeters(),.001);
    }
    @Test
    void shouldHaveCorrectPressureAtMaxDepth() {
        // arrange, act
        moveToMaxDepth(submarine, 100);
        // assert
        // 1.0 + .1 * 100
        assertEquals(11, submarine.getPressureInAtmospheres(), .001);
    }
    @Test
    void shouldHaveCorrectPressureAtSeaLevel() {
        submarine.getPressureInAtmospheres();
        assertEquals(1,submarine.getPressureInAtmospheres(),.001);
    }
    @Test
    void shouldHaveCorrectPressureAfter3DivesAnd1Surface() {

        submarine.dive();
        submarine.dive();
        submarine.dive();
        submarine.surface();
        assertEquals(1.4, submarine.getPressureInAtmospheres(), .001);
    }



    // 1. Create one or more tests to confirm `dive` is working properly.
    // 2. Create a test to assert the submarine can't go deeper than the max depth.
    // (Be sure to use more than one max depth.)
    // 3. Create one or more tests to confirm `surface` is working properly.
    // 4. Create a test to assert the submarine can't go above sea level.
    // (Depth can never be negative.)
    // 5. Create one or more tests to confirm `getPressureInAtmospheres` is working properly.

    // helper method
    @Test
    void moveToMaxDepth(Submarine submarine, double maxDepth){
        for (int i = 0; i <= ((maxDepth/3) + 1); i++) {
            submarine.dive();
        }
    }
}