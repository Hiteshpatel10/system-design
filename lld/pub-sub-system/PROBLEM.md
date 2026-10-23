# Pub-Sub / In-memory Message Queue

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design an in-memory publish–subscribe message system (a mini Kafka) where publishers send messages to topics and subscribers consume them.

## Requirements
1. **Create/delete topics.**
2. **Publish:** a publisher sends a message (ID, payload, timestamp) to a topic.
3. **Subscribe:** a subscriber subscribes to one or more topics. Every subscriber of a topic receives **every** message published to it, **in publish order**.
4. Each subscriber consumes at **its own pace**: a slow subscriber must not block the publisher or other subscribers.
5. Track an **offset per subscriber per topic**, so each subscriber knows what it has consumed.
6. **Reset offset:** a subscriber can replay messages from an earlier offset.
7. **Unsubscribe.**
8. **Thread-safe:** multiple publishers and subscribers run concurrently.

## Bonus
- **Consumer groups:** within a group, each message goes to only **one** member (load balancing); different groups each receive all messages.
- **Retry + dead-letter queue:** if a subscriber throws, retry N times, then move the message to a DLQ.
- **Retention:** delete messages older than X, or keep only the latest N.
- **Message filtering** by attributes.

## Constraints
- In-memory. Use real threads in `Main` to show concurrent publish/consume.

## Sample flow
```
createTopic "orders"
subscribe S1(fast), S2(slow) → "orders"
publish "orders" m1, m2, m3
S1 receives m1, m2, m3 immediately
S2 receives m1 … (1s) … m2 … (1s) … m3
S1.resetOffset("orders", 0) → S1 receives m1, m2, m3 again
```

## Follow-up questions
1. Push vs pull delivery: which did you choose, and why?
2. How do you guarantee per-subscriber ordering with multiple threads?
3. What delivery guarantee do you provide: at-most-once, at-least-once or exactly-once? How would you change it?
4. How does a real Kafka scale this (partitions, replication, consumer groups)?
