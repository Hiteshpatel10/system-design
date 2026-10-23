# Design Google Maps

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a map service that shows maps, finds routes and estimates travel time.

## Functional requirements
1. Show the map around a location at different **zoom levels**.
2. **Directions** from A to B (driving/walking), with ETA.
3. **Live traffic** affects routes and ETAs.
4. Location updates from users' phones (to measure traffic).

## Non-functional requirements
- Map tiles load fast worldwide.
- Routing responses within ~1 second.
- Accurate ETAs.

## Scale (assume)
- 1B daily active users; huge numbers of tile requests; millions of location updates/second.

## Deep-dive questions
1. **Map tiles:** pre-rendered tiles per zoom level (or vector tiles), served from a CDN. How many tiles are there, and how are they addressed?
2. **Routing:** the road network as a graph; why plain Dijkstra/A* is too slow on a continent; **routing tiles / hierarchical graphs** (contraction hierarchies).
3. **Traffic:** collect location pings (Kafka) → compute road-segment speeds → update edge weights.
4. ETA prediction (historical + live data; ML mention).
5. Rerouting when the user goes off route.

## Concepts to revise
Tiling · CDN · graph algorithms at scale · stream processing · geospatial indexing
