# Design Search Autocomplete (Typeahead)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
As a user types into a search box, show the top 5–10 most popular queries that start with what they've typed.

## Functional requirements
1. Given a prefix, return the **top K (e.g. 5) suggestions** ranked by popularity.
2. Suggestions update as query trends change (daily, or within an hour for trending topics).
3. (Optional) Personalised and language/region-specific suggestions.

## Non-functional requirements
- Very low latency: < 100 ms end to end (it's called on every keystroke).
- Highly available; slightly stale suggestions are fine.

## Scale (assume)
- 10M daily active users, 10 searches each, ~20 characters per search → about 2 billion autocomplete requests a day.

## Deep-dive questions
1. Data structure: a **trie**. How do you get the top K for a prefix quickly? (Store the top K at each node.)
2. How is the trie built and updated? (Collect query logs → aggregate counts → rebuild offline → swap in.) Why not update it on every query?
3. How do you store and shard the trie when it doesn't fit on one machine (by prefix range)?
4. Caching: browser caching of results, a CDN, Redis.
5. Client-side optimisations: debouncing keystrokes, prefetching.
6. How do you filter out offensive or harmful suggestions?

## Concepts to revise
Trie · offline batch aggregation (MapReduce/Spark) · sharding by key range · caching · debouncing
