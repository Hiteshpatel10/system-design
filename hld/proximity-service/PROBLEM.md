# Design Yelp / Proximity Service

> HLD · ⏱ 45 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a service that finds businesses (restaurants, shops) near a user's location, like Yelp or Google "near me".

## Functional requirements
1. Search businesses within a **radius** (e.g. 500 m, 1 km, 5 km) of a latitude/longitude, optionally filtered by category.
2. View business details, photos and reviews.
3. Business owners add or update their business (changes can appear the next day).
4. Users write reviews and ratings.

## Non-functional requirements
- Search latency < 200 ms.
- Read-heavy; highly available.

## Scale (assume)
- 100M daily active users, 5 searches each; 200M businesses.

## Deep-dive questions
1. Why is `WHERE lat BETWEEN … AND lng BETWEEN …` slow? What do geospatial indexes do differently?
2. Compare **geohash**, **quadtree** and Google S2. How do you search neighbouring cells so you don't miss nearby businesses on a cell border?
3. How does the index change as businesses are added (static data → rebuild nightly vs live updates)?
4. Where does the index live (in memory on search servers, or Redis/Postgres PostGIS/Elasticsearch geo)?
5. How do you rank results (distance, rating, popularity)?
6. Scaling reads: replicas and caching popular areas.

## Concepts to revise
Geohash · quadtree · geospatial databases · read replicas · caching
