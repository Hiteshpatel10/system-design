package parkinglot.models;

import parkinglot.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String registrationNumber) {
        super(registrationNumber, VehicleType.TRUCK);
    }    
}
