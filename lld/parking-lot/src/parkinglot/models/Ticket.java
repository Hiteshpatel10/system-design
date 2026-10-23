package parkinglot.models;

import java.time.Instant;
import java.util.UUID;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private Instant entryTime;
    private Instant exitTime; 
    private double fee;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString(); 
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = Instant.now(); 
        this.exitTime = null;
    }

    // Getters
    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }
    public Instant getEntryTime() { return entryTime; }
    public Instant getExitTime() { return exitTime; }
    public double getFee() { return fee; }

    public void setExitTime(Instant exitTime) { 
        this.exitTime = exitTime; 
    }
    public void setFee(double fee) { this.fee = fee; }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle.getVehicleType() +
                ", spot=" + parkingSpot.getId() +
                ", entryTime=" + entryTime +
                '}';
    }
}