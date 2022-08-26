import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise04 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Create a new Vehicle. Use a VIN that's easy to remember.
        Vehicle scottsCar = new Vehicle();
        scottsCar.setVin("12341235123452346");
        scottsCar.setMake("Ford");
        scottsCar.setModel("Focus");
        scottsCar.setYear(2012);
        scottsCar.setColor("Red");
        // 2. Add the Vehicle to `vehicleMap` with the `put` method.
        vehicleMap.put("12341235123452346", scottsCar);
        // 3. Confirm the Vehicle was added by retrieving it with `get` and printing it to the conso0le.
        Vehicle myVehicle = vehicleMap.get("12341235123452346");
        System.out.println(myVehicle);
    }
}
