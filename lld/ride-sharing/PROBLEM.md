# Ride Sharing (Uber / Ola)

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design the core of a ride-hailing app that matches riders with nearby drivers.

## Requirements
1. **Riders** and **drivers.** A driver has a vehicle (type: BIKE/AUTO/SEDAN/SUV), a current location (x, y) and a status (OFFLINE / AVAILABLE / ON_TRIP).
2. Drivers go online/offline and update their location.
3. **Request a ride:** rider + pickup + drop + vehicle type. Show a **fare estimate** before booking.
4. **Matching:** find AVAILABLE drivers of that type within radius R. Choose one with a **pluggable strategy** (nearest first by default; highest rated is an alternative). The chosen driver can **accept or decline**; on decline, offer the ride to the next driver.
5. **Trip lifecycle:** `REQUESTED → DRIVER_ASSIGNED → DRIVER_ARRIVED → IN_PROGRESS → COMPLETED`, or `CANCELLED` (by rider or driver, with a cancellation fee after assignment).
6. **Fare:** base fare + per-km + per-minute, by vehicle type; **surge multiplier** when demand > supply in an area.
7. After a trip, the rider and driver **rate each other**. Ratings affect matching.
8. **A driver can't be assigned to two trips at once,** even when two requests come in at the same moment.

## Bonus
- **Pool rides:** share with another rider going the same way.
- Scheduled rides.
- Ride history and receipts.
- Payment methods as strategies.

## Constraints
- In-memory, Euclidean distance. Simulate concurrent ride requests with threads.

## Sample flow
```
drivers: D1 SEDAN (1,1) 4.8★, D2 SEDAN (2,2) 4.9★, D3 AUTO (1,2)
R1 requests SEDAN (0,0)→(10,10): estimate ₹310 → D1 (nearest) offered → declines → D2 accepts
D2 arrives → trip starts → completes → fare ₹298; R1 rates D2 5★
R2 and R3 request SEDAN at the same time near D1 → only one gets D1
```

## Follow-up questions
1. How did you prevent double assignment of a driver?
2. Finding drivers within radius R is a linear scan. How do real systems do it? (Geohash/quadtree, covered in HLD.)
3. Where does the surge logic live, and how is it plugged in?
4. Model the trip lifecycle: State pattern or enum transitions? Why?
