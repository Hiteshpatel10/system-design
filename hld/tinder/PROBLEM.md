# Design Tinder (Swipe-based Matching)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a dating app where users see nearby profiles, swipe left/right, and match when both swipe right.

## Functional requirements
1. Create a profile with photos and preferences (age range, distance).
2. Get a **stack of profiles** to swipe on, matching preferences and location.
3. **Swipe** right (like) or left (pass); never show the same profile twice.
4. When two users like each other → **match** + notification to both.

## Non-functional requirements
- Swiping feels instant; the profile stack loads quickly.
- Matches are consistent: if both swipe right at the same time, exactly one match is created and both are notified.

## Scale (assume)
- 20M daily active users; 100 swipes each per day.

## Deep-dive questions
1. Generating the stack: geo-search + filters (Elasticsearch / geohash); precomputing stacks.
2. Storing swipes (2B a day) and checking "did they already like me?" quickly.
3. Avoiding repeats: a Bloom filter per user for profiles already seen?
4. The race: two right-swipes at the same moment. How do you guarantee exactly one match?
5. Photos: object storage + CDN.

## Concepts to revise
Geospatial search · write-heavy storage · Bloom filters · atomic operations · notifications
