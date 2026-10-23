# In-memory Key-Value Store (mini Redis)

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 3 (Hard)

## Problem statement
Design an in-memory key-value store with expiry, eviction and transactions.

## Requirements
1. **Operations:**
   - `put(key, value)`
   - `get(key)`
   - `delete(key)`
   - `exists(key)`
   - `keys(prefix)`
2. **Typed values:** the value is a set of attributes, e.g. `{"name": "Ravi", "age": 30, "verified": true}`. The **type of each attribute is fixed** the first time it's seen across the store; a later put with `age: "thirty"` must fail.
3. **Search:** `search(attributeKey, attributeValue)` returns all keys whose value has that attribute.
4. **TTL:** `put(key, value, ttlSeconds)`. Expired keys behave as if they don't exist and are eventually removed from memory.
5. **Max capacity** with an eviction policy (LRU by default).
6. **Transactions:** `begin()`, `commit()`, `rollback()`, with **nested** transactions. Inside a transaction, reads see the transaction's own uncommitted writes.
7. **Thread-safe** for concurrent clients.

## Bonus
- **Snapshot to file** and restore on startup.
- An **append-only log** of writes, replayed on startup.
- Pluggable eviction policy (LRU / LFU).
- A tiny command-line interface: `SET k v`, `GET k`, `BEGIN`, …

## Constraints
- In-memory, single process.

## Sample flow
```
put("sde_bootcamp", {title: "SDE-Bootcamp", price: 30000, enrolled: false})
put("sde_kickstart", {title: "SDE-Kickstart", price: 4000})
put("bad", {price: "free"})            → Error: 'price' is a number
search("price", 4000)                   → [sde_kickstart]
put("otp", {code: 4821}, ttl=2s); sleep 3s; get("otp") → null
begin; put("a", …); begin; delete("a"); rollback; get("a") → exists; commit
```

## Follow-up questions
1. How do you expire keys: lazily on access, with a background sweeper, or both? Why does Redis use both?
2. How do nested transactions work in your design (a stack of change logs)?
3. What isolation level do your transactions give? Can another thread see uncommitted writes?
4. How would you shard this store across machines and replicate it? (See HLD: Distributed Cache / Key-Value Store.)
