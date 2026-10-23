# Design a Distributed Message Queue (Kafka)

> HLD · ⏱ 45 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design a distributed message queue / event streaming platform like Kafka.

## Functional requirements
1. Producers send messages to **topics**.
2. Consumers read messages; multiple **consumer groups** read the same topic independently.
3. Messages are retained for a configurable time (e.g. 7 days) and can be **replayed** from an offset.
4. Ordering is guaranteed within a partition.

## Non-functional requirements
- High throughput (millions of messages/second) and low latency.
- **Durable:** acknowledged messages survive broker failures.
- Configurable delivery semantics: at-most-once, at-least-once, exactly-once.
- Scales horizontally.

## Deep-dive questions
1. **Partitions:** why split a topic? How does a producer pick a partition (key hashing), and what does that mean for ordering?
2. **Storage:** append-only log segments on disk. Why is sequential disk I/O fast? Indexes for offset lookup; retention and compaction.
3. **Replication:** leader and followers, in-sync replicas (ISR), `acks=0/1/all`. What happens when a leader dies?
4. **Consumer groups:** partition assignment, rebalancing, committing offsets.
5. Push vs pull consumers.
6. Exactly-once: idempotent producers and transactions.
7. Metadata and coordination (ZooKeeper vs KRaft).

## Concepts to revise
Append-only logs · partitioning · replication and ISR · consumer groups · delivery semantics · leader election
