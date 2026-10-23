# Design a Distributed Cache / Key-Value Store

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design a distributed key-value store (like Redis Cluster, Memcached or DynamoDB) that spreads data across many machines.

## Functional requirements
1. `put(key, value)`, `get(key)`, `delete(key)`.
2. Optional **TTL** per key.
3. Values up to 100 KB.

## Non-functional requirements
- Low latency (single-digit milliseconds).
- **Scales horizontally:** add nodes to get more capacity.
- **Highly available:** survives node failures.
- Tunable consistency (decide strong vs eventual and justify).

## Scale (assume)
- 1 TB of data; 1M operations/second; 100 nodes.

## Deep-dive questions
1. How do you decide which node holds a key? Why does `hash(key) % N` fail when nodes change? Explain **consistent hashing** and virtual nodes.
2. **Replication:** how many copies, and where? Leader–follower vs leaderless.
3. **Quorum** reads/writes (N, R, W): what consistency do `R + W > N` settings give?
4. How do nodes detect failures (heartbeats, gossip)? What happens to writes for a node that's down (hinted handoff)?
5. **Eviction** (LRU/LFU) when memory is full; TTL expiry.
6. Cache-specific: hot keys, cache stampede/thundering herd, cache-aside vs write-through.
7. CAP: where does your design sit, and why?

## Concepts to revise
Consistent hashing · replication · quorum · gossip protocol · CAP theorem · eviction policies · hot keys
