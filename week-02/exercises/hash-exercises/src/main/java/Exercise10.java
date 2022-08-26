import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise10 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Replace the vehicle with VIN 2G4WD582061270646 with a new Orange 1994 Chrysler School Bus.
        Vehicle orangeBus = new Vehicle();
        orangeBus.setVin("2G4WD582061270646");
        orangeBus.setColor("Orange");
        orangeBus.setYear(1994);
        orangeBus.setModel("School Bus");
        orangeBus.setMake("Chrysler");

        // 2. Retrieve the new vehicle from `vehicleMap` and print it to confirm it was updated.

        Vehicle oldVehicle = vehicleMap.get("2G4WD582061270646");
        System.out.println(oldVehicle);

        vehicleMap.replace("2G4WD582061270646", orangeBus);
        System.out.println(vehicleMap.get("2G4WD582061270646"));

    }
}
