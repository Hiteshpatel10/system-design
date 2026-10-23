# Design Ticketmaster / BookMyShow

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design an event/movie ticket booking platform where users browse events, pick seats and buy tickets.

## Functional requirements
1. **Browse/search** events by city, date, name, venue, category.
2. View an event's **seat map** with live availability.
3. **Book seats:** select seats → seats **reserved for ~10 minutes** → pay → tickets issued.
4. If payment isn't completed in time, the reservation is released.

## Non-functional requirements
- **No double booking**, ever (strong consistency for seat booking).
- Search and browsing must be fast and highly available (eventual consistency is fine there).
- Handle **huge spikes** for popular events: 10M users trying to buy 50k concert seats when sales open.

## Scale (assume)
- 100M daily active users, 1M bookings a day; peaks of 10M concurrent users for one event.

## Deep-dive questions
1. How do you reserve a seat safely? Compare database row locks (`SELECT … FOR UPDATE`), optimistic concurrency (version column), and a distributed lock with TTL (Redis).
2. How do reservations expire after 10 minutes? Who releases them?
3. How do you handle the **10M-user spike**? (A virtual waiting queue / waiting room, admission control.)
4. How do you keep the seat map up to date for viewers (polling vs SSE/WebSocket)?
5. How does search work (Elasticsearch fed by change data capture)?
6. Payment succeeded but the booking failed (or the other way round). How do you make it consistent? (Idempotency, payment webhooks, Saga.)

## Concepts to revise
Contention and locking · distributed locks · TTLs · waiting-room queues · Elasticsearch · idempotent payments
