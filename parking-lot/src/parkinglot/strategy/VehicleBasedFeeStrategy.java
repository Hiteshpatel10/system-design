package parkinglot.strategy;

import parkinglot.enums.VehicleType;
import parkinglot.models.Ticket;
import java.time.Duration;
import java.time.Instant;

public class VehicleBasedFeeStrategy extends FeeStrategy {

    private double getRate(VehicleType type) {
        switch (type) {
            case BIKE:  return 2.0;
            case CAR:   return 5.0;
            case TRUCK: return 10.0;
            default:    return 5.0;
        }
    }

    @Override
    public double calculate(Ticket ticket) {
        Instant entry = ticket.getEntryTime();
        long hours = Duration.between(entry, Instant.now()).toHours() + 1;

        double rate = getRate(ticket.getVehicle().getVehicleType());
        return hours * rate;
    }
}