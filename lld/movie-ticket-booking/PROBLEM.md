# Movie / Concert Ticket Booking (BookMyShow)

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design an online ticket-booking system for movies (and concerts) like BookMyShow.

## Requirements
1. **Catalogue:** cities → theatres → screens. Each screen has a seat layout (seat ID, row, type: REGULAR/PREMIUM/RECLINER).
2. **Movies and shows:** a show = movie + screen + start time. Seat prices depend on seat type (and optionally the show).
3. **Search:** list movies in a city; list the shows for a movie in a city on a date; show seat availability for a show.
4. **Booking flow:**
   1. The user selects seats → the seats are **held for 5 minutes**.
   2. The user pays → the booking is **CONFIRMED** and the seats become BOOKED.
   3. Payment failure or timeout → the hold is released and the seats become AVAILABLE again.
5. **No double booking:** if two users try to hold the same seat at the same time, exactly one succeeds. This is the core of the problem.
6. **Cancel** a confirmed booking → seats are freed; refund according to a policy.

## Bonus
- Payment methods (UPI, card, wallet) as pluggable strategies.
- **Coupons / offers.**
- Dynamic pricing (weekend, prime time, high occupancy).
- Concerts with **general admission** (a count, not specific seats).

## Constraints
- In-memory. **Simulate concurrency in `Main`**: e.g. 10 threads try to book the same seat, and exactly one succeeds.

## Sample flow
```
show S1: "Dune 3", PVR Koramangala, Screen 2, 2026-11-01 19:00
seats S1 → A1..A10 REGULAR ₹200, P1..P5 PREMIUM ₹350
Ravi holds [A1, A2]       → hold H1, expires 19:05
Asha holds [A2, A3]       → Failed: A2 not available
Ravi pays H1 (₹400)       → Booking B1 CONFIRMED
Ben holds [A5]; no payment for 5 min → hold expires, A5 AVAILABLE
Ravi cancels B1           → A1, A2 AVAILABLE, refund ₹320 (80%)
```

## Follow-up questions
1. How exactly do you lock seats? Compare `synchronized` per show, a lock per seat, and optimistic versioning (compare-and-set).
2. How do you expire holds? A scheduled cleaner, lazy expiry on read, or both?
3. In a real database, how would you prevent double booking? (Row locks / `SELECT … FOR UPDATE`, unique constraints, Redis locks with TTL.)
4. What happens if payment succeeds just after the hold expired?
