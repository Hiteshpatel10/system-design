# Design Top-K / Leaderboard (YouTube Top-K videos, gaming leaderboard)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design a system that shows the top K items by a score, for example the most-viewed videos in the last hour/day, or a game's player leaderboard.

## Functional requirements
1. **Top K (e.g. 100)** items for time windows: last hour, last day, all time.
2. **Gaming variant:** update a player's score after each match; show the top 10, and **a player's own rank** (with the players around them).
3. Results are near real-time (at most a minute or so of delay).

## Non-functional requirements
- Low-latency reads of the top K.
- Very high write volume (every view or score event).
- Approximate counts are acceptable for views (say why); exact ranks for the game leaderboard.

## Scale (assume)
- Views: 100k events/second, 1B videos.
- Game: 25M monthly players, 5M daily active players.

## Deep-dive questions
1. Gaming leaderboard: why does a **Redis sorted set** fit? What do `ZINCRBY`, `ZREVRANGE` and `ZREVRANK` cost? How do you shard it beyond one Redis node?
2. View counts: why can't you just update a database row per view? Design the **stream-processing** pipeline (Kafka → aggregators by window → top-K merge).
3. Sliding windows vs tumbling windows: how do you compute "last hour" efficiently?
4. Exact vs approximate: when is a **Count-Min Sketch** good enough?
5. How do you merge the top K from many partitions into a global top K?

## Concepts to revise
Redis sorted sets · stream processing (Kafka, Flink) · windowing · Count-Min Sketch · heap merging
