# Design a Hotel Reservation System (Booking.com / Marriott)

> HLD · ⏱ 45 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a booking system for a large hotel chain (or a booking site) where users search hotels and reserve rooms.

## Functional requirements
1. Search hotels by city and dates; see available **room types** and prices.
2. **Reserve** a room type for a date range, and pay.
3. Cancel a reservation.
4. Hotel admins manage rooms, prices and inventory.
5. Support a configurable amount of **overbooking** (e.g. 10%).

## Non-functional requirements
- **No overselling** beyond the allowed overbooking: inventory must be strongly consistent.
- Search can be eventually consistent.
- Handle bursts (holiday sales).

## Scale (assume)
- 5,000 hotels, 1M rooms in total; ~240k reservations a day; search is ~100× the reservation traffic.

## Deep-dive questions
1. **Inventory data model:** why track availability per **room type per date** (a `room_type_inventory` table) rather than individual rooms?
2. A reservation spans several dates. How do you update inventory for all of them atomically?
3. Concurrency: pessimistic locking vs optimistic locking vs database constraints. Which would you pick at this scale?
4. **Idempotency:** a user double-clicks "Book". How do you avoid two reservations? (An idempotency key generated when the booking page loads.)
5. Payment and reservation consistency (Saga, or a pending reservation + payment webhook).
6. Scaling: shard by hotel ID; cache the search results.

## Concepts to revise
Inventory modelling · transactions and locking · idempotency keys · Saga · sharding by entity
