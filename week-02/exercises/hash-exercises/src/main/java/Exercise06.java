import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;
import java.util.Map;

public class Exercise06 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Loop over each vehicle in `vehicleMap` and print vehicles with a Dodge make.
        // 2. Loop three times with three different techniques: .values(), .entrySet(), and .keySet()
        for (Map.Entry<String, Vehicle> entry : vehicleMap.entrySet()) {
            if (entry.getValue().getMake().equalsIgnoreCase("Dodge")) {
                System.out.println(entry.getValue());
            }
        }

        for (Vehicle v : vehicleMap.values()) {
            if (v.getMake().equalsIgnoreCase("Dodge")) {
                System.out.println(v);
            }
        }

        for (String vin : vehicleMap.keySet()) {
            if (vehicleMap.get(vin).getMake().equalsIgnoreCase("Dodge")) {
                System.out.println(vehicleMap.get(vin));
            }
        }
    }
}
