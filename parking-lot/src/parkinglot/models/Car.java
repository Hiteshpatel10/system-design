package parkinglot.models;

import parkinglot.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber, VehicleType.CAR);
    }
    
}
