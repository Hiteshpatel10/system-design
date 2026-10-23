# Design Uber / Ride Sharing

> HLD · ⏱ 45 min · 🔴 Must-do · Level 3 (Hard)

## Problem statement
Design a ride-hailing platform that matches riders with nearby drivers in real time.

## Functional requirements
1. A rider enters pickup and destination and gets a **fare estimate**.
2. The rider requests a ride → the system **matches** a nearby available driver.
3. The driver accepts or declines (within ~10 s); on decline/timeout, try the next driver.
4. Both see each other's **live location** during pickup and the trip.
5. Trip completes → payment and ratings.

## Non-functional requirements
- Matching in < 1 minute (ideally seconds).
- **A driver is never assigned to two rides at once.**
- Location updates are very high-volume; some staleness (a few seconds) is fine.
- Highly available.

## Scale (assume)
- 10M daily rides; 5M active drivers sending a location every 5 seconds → ~1M location updates/second.

## Deep-dive questions
1. How do you store and query driver locations at 1M updates/second? (In-memory geo-index: Redis GEO, geohash/quadtree; why not a relational DB?)
2. Matching flow: how do you find candidates, offer the ride to one driver at a time, and handle timeouts?
3. Preventing double assignment: distributed lock on the driver with a TTL, or a status update with compare-and-set.
4. How do you stream live locations to the rider (WebSockets) at scale?
5. Surge pricing: how do you compute demand vs supply per area?
6. Handling peaks (New Year's Eve) and data-centre failures. Shard by city/region?

## Concepts to revise
Geospatial indexing · in-memory stores · distributed locks · WebSockets · sharding by geography
