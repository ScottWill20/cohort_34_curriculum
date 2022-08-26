import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise05 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Instantiate a new HashMap<String, Vehicle>.
        HashMap<String, Vehicle> newVehicleMap = new HashMap<>();
        // 2. Add two vehicles to the new map.
        newVehicleMap.put("one", new Vehicle());
        newVehicleMap.put("two", new Vehicle());
        // 3. Add items from the new map to `vehicleMap` using the `putAll` method.
        vehicleMap.putAll(newVehicleMap);
        // 4. Confirm the vehicles were added by retrieving on with its VIN and printing it to the console.
        Vehicle one = vehicleMap.get("one");
        Vehicle two = vehicleMap.get("two");
        System.out.println(one);
        System.out.println(two);
        System.out.println(vehicleMap);
    }
}
