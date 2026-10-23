package parkinglot.system;

import parkinglot.enums.SpotType;
import parkinglot.models.Floor;
import parkinglot.models.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotFactory {

    // Creates floors with spots automatically
    public static List<Floor> createFloors(
        int numberOfFloors,
        int smallSpots,
        int mediumSpots,
        int largeSpots
    ) {
        List<Floor> floors = new ArrayList<>();
        int spotId = 1; // global spot id across all floors

        for (int i = 1; i <= numberOfFloors; i++) {
            Floor floor = new Floor(i, i);
            
            // Add small spots
            for (int s = 0; s < smallSpots; s++) {
                floor.addSpot(new ParkingSpot(spotId++, SpotType.SMALL, i));
            }
            // Add medium spots
            for (int m = 0; m < mediumSpots; m++) {
                floor.addSpot(new ParkingSpot(spotId++, SpotType.MEDIUM, i));
            }
            // Add large spots
            for (int l = 0; l < largeSpots; l++) {
                floor.addSpot(new ParkingSpot(spotId++, SpotType.LARGE, i));
            }

            floors.add(floor);
        }
        return floors;
    }
}