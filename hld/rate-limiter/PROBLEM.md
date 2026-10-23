# Design a Distributed Rate Limiter

> HLD · ⏱ 45 min · 🔴 Must-do · Level 1 (Easy)

## Problem statement
Design a rate limiter that protects a set of APIs running on many servers, limiting how many requests each client can make.

## Functional requirements
1. Limit requests per **client** (user ID, API key or IP) per **rule** (e.g. 100 requests/min on `/search`, 5/min on `/login`).
2. Rules are configurable without redeploying.
3. Rejected requests get **HTTP 429** with headers: limit, remaining, retry-after.
4. Different limits per plan (free vs paid).

## Non-functional requirements
- Adds very little latency (< 5 ms per request).
- **Accurate across all servers:** a client can't get 10× the limit by hitting 10 servers.
- Highly available: if the rate limiter is down, decide whether to fail open or fail closed (and justify).

## Scale (assume)
- 1M requests/second across 100 API servers; 50M distinct clients.

## Out of scope
- DDoS protection at the network layer (mention only).

## Deep-dive questions
1. Where does the limiter sit: in each service, an API gateway, or a sidecar? Trade-offs?
2. Which algorithm (token bucket, fixed window, sliding log, sliding window counter) and why? What does each store per client?
3. How do you keep counts in a shared store (Redis) **atomically**? (Race conditions; Lua scripts / `INCR` + `EXPIRE`.)
4. Redis becomes the bottleneck. How do you shard it, and what happens when a Redis node fails?
5. Can you avoid hitting Redis on every request (local counters + periodic sync)? What accuracy do you lose?
6. How are rules distributed to all servers and updated?

## Concepts to revise
Rate-limiting algorithms · Redis atomic operations · API gateway · consistent hashing · fail open vs fail closed
