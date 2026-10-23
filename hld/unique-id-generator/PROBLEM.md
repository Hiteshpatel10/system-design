# Design a Unique ID Generator (Snowflake)

> HLD · ⏱ 45 min · 🟡 Should-do · Level 1 (Easy)

## Problem statement
Design a service that generates unique IDs for objects (orders, tweets, messages) in a distributed system.

## Functional requirements
1. Every ID is **globally unique**.
2. IDs are **64-bit numbers**.
3. IDs are **roughly ordered by time** (a later ID is usually bigger).
4. Generate IDs at very high throughput.

## Non-functional requirements
- At least 10,000 IDs per second **per machine**; low latency.
- Highly available; no single point of failure.
- Works across multiple data centres.

## Scale (assume)
- Up to 1,000 generator machines across 3 data centres.

## Deep-dive questions
1. Compare: database auto-increment (one DB, multiple DBs with step), UUID, a ticket server, and Twitter Snowflake. Pros and cons of each?
2. Lay out the 64 bits of a Snowflake ID (timestamp, datacentre ID, machine ID, sequence). How many years does the timestamp last? How many IDs per millisecond per machine?
3. How are machine IDs assigned (configuration, ZooKeeper)?
4. **Clock skew:** what happens if a machine's clock goes backwards (NTP adjustment)?
5. What if the sequence overflows within one millisecond?
6. Why might ordered IDs be bad for some databases (hotspots), and when are they good (index locality)?

## Concepts to revise
UUID vs Snowflake · clock synchronisation (NTP) · ZooKeeper coordination · B-tree index locality
