package parkinglot.models;

import parkinglot.enums.SpotStatus;
import parkinglot.enums.SpotType;

public class ParkingSpot {
    private int id;
    private SpotType spotType;
    private SpotStatus status;
    private int floorId; 
    private String ticketId;

    public ParkingSpot(int id, SpotType spotType, int floorId) {
        this.id = id;
        this.spotType = spotType;
        this.floorId = floorId;
        this.status = SpotStatus.AVAILABLE; // use enum, not boolean
    }

    // Getters
    public int getId() { return id; }
    public SpotType getSpotType() { return spotType; }
    public SpotStatus getStatus() { return status; }
    public int getFloorId() { return floorId; }

    // Setters
    public void setStatus(SpotStatus status) { this.status = status; }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public void clearSpot() {
        this.status = SpotStatus.AVAILABLE;
        this.ticketId = null;
    }

    public void bookSpot(String ticketId) {
        this.status = SpotStatus.OCCUPIED;
        this.ticketId = ticketId;
    }
}