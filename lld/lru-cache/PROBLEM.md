# LRU Cache

> LLD · Machine coding · ⏱ 60–90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design and implement an in-memory cache with a fixed capacity that evicts the **least recently used** entry when full.

## Requirements
1. `Cache<K, V>` is generic, with a capacity given at creation.
2. `get(key)` returns the value (or empty/null if missing) and marks the key as recently used.
3. `put(key, value)` inserts or updates. If the cache is full, evict the least recently used key first.
4. `remove(key)` and `size()`.
5. **`get` and `put` must be O(1).** You may not use `LinkedHashMap`; build the data structure yourself (HashMap + doubly linked list).
6. **Thread-safe:** many threads call `get`/`put` at the same time without corrupting the structure.

## Bonus
- **Pluggable eviction policy:** LRU, LFU, FIFO, chosen at creation (Strategy pattern).
- **TTL per entry:** expired entries are never returned.
- **Stats:** hits, misses, evictions, hit ratio.
- An **eviction listener** callback.

## Constraints
- No `LinkedHashMap`, no third-party libraries.

## Sample flow
```
cache = new Cache(capacity 2, LRU)
put(1, "A"); put(2, "B")
get(1)        → "A"     (order now: 1 most recent, 2 least recent)
put(3, "C")   → evicts 2
get(2)        → null
put(1, "AA")  → update, 1 is most recent
stats         → hits=1 misses=1 evictions=1
```

## Follow-up questions
1. Why a doubly linked list rather than a singly linked one?
2. Your lock: one lock around everything vs a `ReadWriteLock` vs lock striping. Why can't `get` simply take a read lock in LRU?
3. How would you implement LFU in O(1)?
4. How would you make this a distributed cache across several machines? (Leads to consistent hashing.)
