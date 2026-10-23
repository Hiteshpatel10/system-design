package parkinglot.system;

import parkinglot.enums.SpotStatus;
import parkinglot.enums.SpotType;
import parkinglot.enums.VehicleType;
import parkinglot.models.Floor;
import parkinglot.models.ParkingSpot;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;
import parkinglot.strategy.FeeStrategy;

import java.time.Instant;
import java.util.*;

public class ParkingLot {

    private static volatile ParkingLot instance; // volatile for thread-safe double-checked locking

    private final List<Floor> floors;
    private final FeeStrategy feeStrategy;
    private final List<Ticket> tickets = new ArrayList<>();

    // Map makes compatibility checks O(1) and easy to extend
    private static final Map<VehicleType, SpotType> VEHICLE_SPOT_MAP = Map.of(
        VehicleType.BIKE,  SpotType.SMALL,
        VehicleType.CAR,   SpotType.MEDIUM,
        VehicleType.TRUCK, SpotType.LARGE
    );

    private ParkingLot(List<Floor> floors, FeeStrategy feeStrategy) {
        this.floors = floors;
        this.feeStrategy = feeStrategy;
    }

    // Thread-safe double-checked locking
    public static ParkingLot   init(List<Floor> floors, FeeStrategy feeStrategy) {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot(floors, feeStrategy);
                }
            }
        }
        return instance;
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ParkingLot not initialized. Call init() first.");
        }
        return instance;
    }

    // Instance method, not static — no more awkward instance.field access
    public Optional<Ticket> entry(Vehicle vehicle) {
        Optional<ParkingSpot> spotOpt = findSpot(vehicle.getVehicleType());

        if (spotOpt.isEmpty()) {
            System.out.println("No available spot for vehicle type: " + vehicle.getVehicleType());
            return Optional.empty();
        }

        ParkingSpot spot = spotOpt.get();
        Ticket ticket = new Ticket(vehicle, spot);
        tickets.add(ticket);
        spot.bookSpot(ticket.getTicketId());

        System.out.println("Vehicle " + vehicle.getRegistrationNumber()
            + " parked at spot " + spot.getId()
            + " [Ticket: " + ticket.getTicketId() + "]");

        return Optional.of(ticket);
    }

    public void exit(String ticketId) {
        Ticket ticket = tickets.stream()
            .filter(t -> t.getTicketId().equals(ticketId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID: " + ticketId));

        ticket.getParkingSpot().clearSpot();
        ticket.setExitTime(Instant.now());

        double fee = feeStrategy.calculate(ticket);
        ticket.setFee(fee);

        System.out.printf("Vehicle %s exited. Spot: %s | Duration: %s | Fee: $%.2f%n",
            ticket.getVehicle().getRegistrationNumber(),
            ticket.getParkingSpot().getId(),
            formatDuration(ticket),
            fee);
    }

    public void exitByRegNumber(String regNumber) {
    Ticket ticket = tickets.stream()
        .filter(t -> t.getVehicle().getRegistrationNumber().equals(regNumber)
                  && t.getExitTime() == null)   // only active tickets
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No active ticket for: " + regNumber));

    exit(ticket.getTicketId());
}

    private Optional<ParkingSpot> findSpot(VehicleType vehicleType) {
        SpotType required = VEHICLE_SPOT_MAP.get(vehicleType);
        if (required == null) {
            throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }

        return floors.stream()
            .flatMap(floor -> floor.getParkingSpots().stream())
            .filter(spot -> spot.getSpotType() == required
                         && spot.getStatus() == SpotStatus.AVAILABLE)
            .findFirst();
    }

    private String formatDuration(Ticket ticket) {
        long minutes = java.time.Duration.between(
            ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        return minutes + " min";
    }

    // Useful for admin/display purposes
    public Map<SpotType, Long> getAvailability() {
        Map<SpotType, Long> availability = new EnumMap<>(SpotType.class);
        floors.stream()
            .flatMap(floor -> floor.getParkingSpots().stream())
            .filter(spot -> spot.getStatus() == SpotStatus.AVAILABLE)
            .forEach(spot -> availability.merge(spot.getSpotType(), 1L, Long::sum));
        return availability;
    }
}