# Hotel Management / Booking

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a hotel booking system that manages rooms, reservations, check-in/check-out and billing.

## Requirements
1. **Hotel** with rooms: room number, type (SINGLE/DOUBLE/SUITE), price per night, status (AVAILABLE / OCCUPIED / MAINTENANCE).
2. **Search availability:** for a date range and room type, list the rooms free for **every night** in that range.
3. **Book:** guest + room + check-in date + check-out date. Bookings for the same room must **never overlap**.
4. **Booking lifecycle:** `CONFIRMED → CHECKED_IN → CHECKED_OUT`, or `CANCELLED`.
5. **Cancel:** refund policy by notice period (e.g. full refund ≥ 48h before check-in, 50% within 48h, none after check-in).
6. **Check-in / check-out:** check-out produces a **bill**: room charges + extra services (room service, laundry, minibar) + taxes.
7. **Housekeeping:** after check-out, the room goes to CLEANING, then back to AVAILABLE.

## Bonus
- **Seasonal / weekend pricing** strategies.
- Multiple hotels in a chain; search across a city.
- **Overbooking** up to a configured percentage.
- Loyalty points.

## Constraints
- In-memory. Use `LocalDate` for dates; an overlap check is the key correctness test.

## Sample flow
```
rooms: 101 SINGLE ₹2000, 102 DOUBLE ₹3500, 201 SUITE ₹8000
search DOUBLE 2026-11-10..2026-11-12 → [102]
book 102 Ravi 11-10..11-12 → B1 CONFIRMED
book 102 Asha 11-11..11-13 → Rejected: overlaps B1
book 102 Asha 11-12..11-14 → B2 CONFIRMED (check-out day = next check-in day is fine)
check-in B1; add room service ₹600; check-out → bill 2 nights ₹7000 + ₹600 + 12% tax = ₹8512
```

## Follow-up questions
1. How did you store bookings so that availability checks are fast?
2. Two guests book the last suite at the same moment. How do you stop a double booking?
3. How would you plug in different pricing rules?
4. What changes in your design for a hotel chain with 5,000 hotels? (See HLD: Hotel Reservation.)
