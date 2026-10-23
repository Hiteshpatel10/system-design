# Parking Lot

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Status: ✅ solved

## Problem statement
Design a parking lot system that parks vehicles, issues tickets and charges a fee when vehicles leave.

## Requirements
1. The lot has multiple floors. Each floor has spots of type SMALL, MEDIUM and LARGE.
2. Supported vehicles: BIKE → SMALL, CAR → MEDIUM, TRUCK → LARGE.
3. **Park:** given a vehicle, find a free compatible spot, mark it occupied and issue a ticket (ticket ID, vehicle, spot, entry time). If there is no free spot, reject the vehicle with a clear message.
4. **Unpark:** given a ticket ID, free the spot, record the exit time and calculate the fee.
5. Fee = hourly rate (depends on vehicle type) × hours parked, rounding partial hours up.
6. **Availability:** show the number of free spots per spot type (overall and per floor).

## Bonus
- A smaller vehicle may use a bigger spot when its own type is full (configurable).
- Different spot-allocation strategies: lowest floor first, nearest to the entry gate.
- Multiple entry and exit gates.
- Different pricing strategies (flat rate, weekend rate) without changing the parking-lot code.

## Constraints
- In-memory only; the lot is configured at startup (floors and spots per type).

## Sample flow
```
init lot: 2 floors, each 2 SMALL, 2 MEDIUM, 1 LARGE
park CAR KA-01-1234      → Ticket T1, floor 1, spot 3
park TRUCK KA-02-9999    → Ticket T2, floor 1, spot 5
park TRUCK KA-03-1111    → Ticket T3, floor 2, spot 10
park TRUCK KA-04-2222    → No LARGE spot available
availability             → SMALL=4, MEDIUM=3, LARGE=0
unpark T1 (after 2h 10m) → Fee = 3 h × 20 = 60
```

## Follow-up questions
1. Two cars arrive at two gates at the same moment and both pick the same free spot. How do you prevent a double booking?
2. How would you add electric-vehicle spots with charging fees?
3. How would you support monthly passes?
4. Finding a spot scans every spot. How would you make it O(1)?
