package learn.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BicycleTest {
    // Make three bicycles available to all tests.
    Bicycle highPowerBike;
    Bicycle mediumPowerBike;
    Bicycle lowPowerBike;

    // @BeforeEach indicates that this method runs before each test.
    @BeforeEach
    void setupBeforeEachTest() {
        // The Arrangement step occurs in a dedicated setup method.
        Cyclist highPowerCyclist = new Cyclist("High Power Cyclist", 9.87);
        highPowerBike = new Bicycle(highPowerCyclist);

        Cyclist mediumPowerCyclist = new Cyclist("Medium Power Cyclist", 4.9);
        mediumPowerBike = new Bicycle(mediumPowerCyclist);

        Cyclist lowPowerCyclist = new Cyclist("Low Power Cyclist", 1.54);
        lowPowerBike = new Bicycle(lowPowerCyclist);
    }

    @Test
    void shouldAccelerate() {
        highPowerBike.accelerate();
        assertEquals(8.55, highPowerBike.getVelocity(), 0.01);

        mediumPowerBike.accelerate();
        assertEquals(6.56, mediumPowerBike.getVelocity(), 0.01);

        lowPowerBike.accelerate();
        assertEquals(5.22, lowPowerBike.getVelocity(), 0.01);
    }

    @Test
    void shouldBrake() {
        // accelerate twice (arrange)
        for (int i = 0; i < 2; i++) {
            highPowerBike.accelerate();
            mediumPowerBike.accelerate();
            lowPowerBike.accelerate();
        }

        // brake (act)
        highPowerBike.brake();
        mediumPowerBike.brake();
        lowPowerBike.brake();

        // (assert)
        assertEquals(7.096, highPowerBike.getVelocity(), 0.01);
        assertEquals(3.12, mediumPowerBike.getVelocity(), 0.01);
        assertEquals(0.432, lowPowerBike.getVelocity(), 0.01);
    }

    @Test
    void shouldNotExceedMaxVelocity() {
        // accelerate 1000 times
        for (int i = 0; i < 1000; i++) {
            highPowerBike.accelerate();
            mediumPowerBike.accelerate();
            lowPowerBike.accelerate();
        }

        assertEquals(Bicycle.MAX_VELOCITY, highPowerBike.getVelocity());
        assertEquals(Bicycle.MAX_VELOCITY, mediumPowerBike.getVelocity());
        assertEquals(Bicycle.MAX_VELOCITY, lowPowerBike.getVelocity());
    }

    @Test
    void shouldNotReachNegativeVelocity() {
        // brake 1000 times
        for (int i = 0; i < 1000; i++) {
            highPowerBike.brake();
            mediumPowerBike.brake();
            lowPowerBike.brake();
        }

        assertEquals(0.0, highPowerBike.getVelocity());
        assertEquals(0.0, mediumPowerBike.getVelocity());
        assertEquals(0.0, lowPowerBike.getVelocity());
    }

    @Test
    void shouldCreateAnonymousCyclist() {
        // independent arrangement and act
        Bicycle bike = new Bicycle(null);

        // assert
        assertNotNull(bike.getCyclist());
        assertEquals("Anonymous", bike.getCyclist().getName());
        assertEquals(Cyclist.MIN_POWER, bike.getCyclist().getPower());
    }
    
    @Test
    void getCyclist() {
    }

    @Test
    void getVelocity() {
    }

    @Test
    void accelerate() {
    }

    @Test
    void brake() {
    }
}