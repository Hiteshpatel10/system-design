# Design Nearby Friends

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a feature that shows which of your friends are nearby (e.g. within 5 miles) and updates their locations live.

## Functional requirements
1. Users who opt in see a list of friends within a radius, with distance and last-updated time.
2. The list updates every few seconds as people move.
3. Inactive friends (no update for 10 minutes) disappear from the list.

## Non-functional requirements
- Low latency for location updates reaching friends.
- Location history is **not** needed long-term (mention privacy).
- Small staleness is OK.

## Scale (assume)
- 100M daily active users; 10% use the feature at once; each sends a location every 30 seconds; ~400 friends each.

## Deep-dive questions
1. Why is this different from the proximity service (moving users, friends-only)?
2. Architecture: WebSocket servers + a location cache (Redis with TTL) + **Redis pub/sub channel per user**. Walk through one location update.
3. How many pub/sub messages per second is that? How do you scale the pub/sub layer (sharding channels)?
4. Should you filter by distance at the sender or the receiver?
5. What happens when a user adds a friend, or goes offline?

## Concepts to revise
WebSockets · Redis pub/sub · TTL caches · fan-out cost estimation
