# Car Rental System

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a car rental system (like Zoomcar or Hertz) with multiple branches.

## Requirements
1. **Branches** in different cities. Each branch has **vehicles**: ID, type (HATCHBACK/SEDAN/SUV/BIKE), model, registration, price per hour/day.
2. **Search:** available vehicles at a branch for a time range, filtered by type and sorted by price.
3. **Reserve:** customer + vehicle + pickup time + return time. A vehicle can't have overlapping reservations.
4. **Reservation lifecycle:** `RESERVED → PICKED_UP → RETURNED`, or `CANCELLED`.
5. **Return:**
   - charge = rental duration × rate
   - plus a **late fee** if returned after the booked time
   - plus damage charges if any
6. **Customers** have a driving licence number; reject customers without a valid licence.
7. **Branch admin:** add/remove vehicles, mark a vehicle under maintenance.

## Bonus
- **One-way rentals:** pick up at branch A, return to branch B (vehicle moves to B).
- **Add-ons:** GPS, child seat, insurance (Decorator pattern?).
- Pricing strategies: hourly vs daily, weekend surge.
- Security deposit hold and release.

## Constraints
- In-memory. Use `LocalDateTime` for times.

## Sample flow
```
branch B1 (Bangalore): V1 SUV ₹300/h, V2 SEDAN ₹200/h, V3 SEDAN ₹180/h
search B1 SEDAN 2026-11-01 10:00–18:00 → [V3 ₹180/h, V2 ₹200/h]
reserve V3 Ravi 10:00–18:00 → R1
reserve V3 Asha 15:00–20:00 → Rejected: overlap
pickup R1; return R1 at 19:30 → 8h × 180 + late 1.5h × 270 = ₹1845
```

## Follow-up questions
1. How do you find available vehicles efficiently for a time range?
2. How would you add a new pricing model without touching the reservation code?
3. How does one-way rental change your data model?
4. Two customers reserve the last SUV at the same time. How do you handle it?
