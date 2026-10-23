package parkinglot.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int id;
    private int floorNumber;
    private List<ParkingSpot> parkingSpots; 

    public Floor(int id, int floorNumber) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public int getId() { return id; }
    public int getFloorNumber() { return floorNumber; }
}