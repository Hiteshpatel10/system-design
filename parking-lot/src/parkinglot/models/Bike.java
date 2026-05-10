package parkinglot.models;

import parkinglot.enums.VehicleType;

public class Bike extends Vehicle {
    public Bike(String registrationNumber) {
        super(registrationNumber, VehicleType.BIKE);
    }
    
}
