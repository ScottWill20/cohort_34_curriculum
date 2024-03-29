import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise07 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. How many vehicles are Pink (ignore case)?
        // Expected: 54
        int count = 0;

        for (Vehicle v : vehicleMap.values()) {
            if (v.getColor().equalsIgnoreCase("Pink")) {
                count++;
            }
        }
        System.out.println(count);
    }
}
