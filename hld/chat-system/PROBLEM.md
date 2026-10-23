# Design a Chat System (WhatsApp / Messenger)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design a real-time messaging app for 1-to-1 and group chats.

## Functional requirements
1. Send and receive **1-to-1 messages** in real time.
2. **Group chats** (up to 100–500 members).
3. **Offline delivery:** messages sent while a user is offline are delivered when they reconnect.
4. **Message status:** sent ✓, delivered ✓✓, read (blue ✓✓).
5. **Online/last-seen presence.**
6. Chat history synced across a user's devices.
7. (Optional) Media messages (images/videos).

## Non-functional requirements
- Low latency: messages arrive within ~200 ms when both users are online.
- **Messages must never be lost**, and must appear in order within a chat.
- Highly available.

## Scale (assume)
- 500M daily active users; 50 messages per user per day; peak 2M messages/second.

## Deep-dive questions
1. How do clients receive messages in real time? Compare WebSockets, long polling and SSE.
2. Users are connected to different chat servers. How does a message reach the right server? (A session/connection registry, pub-sub between servers.)
3. How do you guarantee ordering and no loss? (Per-chat sequence numbers, acknowledgements, retries.)
4. Message storage: which database and why (write-heavy, chats partitioned by chat ID; e.g. Cassandra/HBase)?
5. Group message fan-out: write once and read per member, or write per member?
6. How does presence work at scale without flooding the system (heartbeats, only notifying friends who are online)?
7. How do media messages work (upload to object storage, send a link)?

## Concepts to revise
WebSockets · stateful services and connection routing · pub-sub (Redis/Kafka) · wide-column databases · acknowledgements and ordering
