package parkinglot;

import parkinglot.system.ParkingLot;
import parkinglot.system.ParkingLotFactory;
import parkinglot.strategy.FeeStrategy;
import parkinglot.strategy.VehicleBasedFeeStrategy;
import parkinglot.models.Floor;
import parkinglot.models.Vehicle;
import parkinglot.models.Ticket;
import parkinglot.enums.VehicleType;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Floor> floors = ParkingLotFactory.createFloors(3, 5, 5, 2);
        FeeStrategy feeStrategy = new VehicleBasedFeeStrategy();
        ParkingLot lot = ParkingLot.init(floors, feeStrategy);  // hold onto this reference

        System.out.println("═══════════════════════════════════════");
        System.out.println("   Parking Lot opened for the day!");
        System.out.println("═══════════════════════════════════════\n");

        Map<String, Ticket> issuedTickets = new LinkedHashMap<>();

        // ── Morning rush ──────────────────────────────────────────────────────
        System.out.println("─── Morning rush (8:00 AM) ───");
        parkVehicle(lot, new Vehicle("MH01AB1001", VehicleType.CAR),   issuedTickets);
        parkVehicle(lot, new Vehicle("MH01AB1002", VehicleType.CAR),   issuedTickets);
        parkVehicle(lot, new Vehicle("MH01CD2001", VehicleType.BIKE),  issuedTickets);
        parkVehicle(lot, new Vehicle("MH01EF3001", VehicleType.TRUCK), issuedTickets);
        System.out.println("  Availability → " + lot.getAvailability());

        // ── Lunch exits ───────────────────────────────────────────────────────
        System.out.println("\n─── Lunch exits (12:00 PM) ───");
        exitVehicle(lot, "MH01AB1001", issuedTickets);
        exitVehicle(lot, "MH01CD2001", issuedTickets);
        System.out.println("  Availability → " + lot.getAvailability());

        // ── Afternoon + overflow test ─────────────────────────────────────────
        System.out.println("\n─── Afternoon (1:00 PM) ───");
        parkVehicle(lot, new Vehicle("KA01KL6001", VehicleType.CAR),   issuedTickets);
        parkVehicle(lot, new Vehicle("KA01MN7001", VehicleType.BIKE),  issuedTickets);

        System.out.println("\n─── Truck overflow test ───");
        parkVehicle(lot, new Vehicle("TN01EF9999", VehicleType.TRUCK), issuedTickets);
        parkVehicle(lot, new Vehicle("TN01EF0000", VehicleType.TRUCK), issuedTickets);
        parkVehicle(lot, new Vehicle("TN01EF1111", VehicleType.TRUCK), issuedTickets); // should be turned away
        System.out.println("  Availability → " + lot.getAvailability());

        // ── Evening — everyone out ────────────────────────────────────────────
        System.out.println("\n─── Closing time (6:00 PM) ───");
        for (String regNum : new ArrayList<>(issuedTickets.keySet())) {
            exitVehicle(lot, regNum, issuedTickets);
        }

        System.out.println("\n  Final availability → " + lot.getAvailability());
        System.out.println("Lot is empty. Good night!");
    }

    private static void parkVehicle(ParkingLot lot, Vehicle vehicle,
                                    Map<String, Ticket> issuedTickets) {
        lot.entry(vehicle).ifPresent(ticket ->          // call on `lot`, not ParkingLot.entry()
            issuedTickets.put(vehicle.getRegistrationNumber(), ticket)
        );
    }

    private static void exitVehicle(ParkingLot lot, String regNum,
                                    Map<String, Ticket> issuedTickets) {
        Ticket ticket = issuedTickets.remove(regNum);
        if (ticket == null) {
            System.out.println("[WARN] No active ticket for: " + regNum);
            return;
        }
        try {
            lot.exit(ticket.getTicketId());             // call on `lot`, not ParkingLot.exit()
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}