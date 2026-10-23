# System Design Roadmap (SDE-2)

What to learn and which problems to solve, in order. Built from popular prep resources and 2025–26 interview reports (see [Sources](#sources)).

**What SDE-2 rounds look like**
- Most companies run an **LLD round** (class design / machine coding), an **HLD round**, or both. A weak design round can get you down-levelled to SDE-1.
- **Machine coding** (Flipkart, Swiggy, Uber, Meesho, PhonePe…): about 90 minutes to write working, extensible code, then about 30 minutes discussing extensions.
- **Concurrency** is usually a discussion follow-up ("what if two users book the same seat?"). Some companies (e.g. Uber) expect you to code it, so practise it in a few problems.
- **HLD** for SDE-2: clear requirements, estimates, APIs, data model, a working high-level design, and **one or two solid deep dives** with trade-offs. You don't need senior-level breadth.

**Priority key:** 🔴 Must-do · 🟡 Should-do · ⚪ Bonus  
**Status key:** ⬜ Not started · 🚧 In progress · ✅ Done

---

## Phase 0: Foundations

Learn these first. Put short notes and small Java examples in `concepts/`.

### OOP and SOLID
- ⬜ Encapsulation, abstraction, inheritance, polymorphism
- ⬜ Composition vs inheritance (prefer composition)
- ⬜ Interfaces vs abstract classes
- ⬜ SOLID principles, with a bad → good example for each
- ⬜ DRY, KISS, YAGNI
- ⬜ UML class diagrams: association, aggregation, composition, inheritance, multiplicity

### Design patterns
Learn these patterns well. They cover most LLD problems.

| Priority | Pattern | Type | Typical use in problems |
|---|---|---|---|
| 🔴 | Strategy | Behavioral | Fee calculation, matching, rate-limit algorithm, split types |
| 🔴 | Factory / Factory Method | Creational | Creating vehicles, pieces, notifications |
| 🔴 | Singleton | Creational | One `ParkingLot`, one `Logger` (thread-safe) |
| 🔴 | Observer | Behavioral | Notifications, pub-sub, auction bids |
| 🔴 | State | Behavioral | Vending machine, ATM, elevator, order status |
| 🔴 | Builder | Creational | Objects with many optional fields |
| 🟡 | Decorator | Structural | Add-ons (pizza toppings, coffee extras) |
| 🟡 | Command | Behavioral | Undo/redo, task queues, elevator requests |
| 🟡 | Chain of Responsibility | Behavioral | Logger levels, ATM cash dispensing, approvals |
| 🟡 | Adapter | Structural | Wrapping third-party payment/notification APIs |
| 🟡 | Facade | Structural | Simple entry point over many subsystems |
| 🟡 | Template Method | Behavioral | Game loop, shared steps with varying parts |
| ⚪ | Composite | Structural | File system, menus, org charts |
| ⚪ | Proxy | Structural | Caching, access control, lazy loading |
| ⚪ | Mediator, Memento, Iterator, Visitor, Prototype, Bridge, Flyweight, Abstract Factory | — | Recognise them; you rarely need them |

### Java concurrency (for LLD)
- ⬜ `Thread`, `Runnable`, `ExecutorService`, thread pools
- ⬜ `synchronized`, `volatile`, `ReentrantLock`, `ReadWriteLock`
- ⬜ `ConcurrentHashMap`, `AtomicInteger` / `AtomicLong`
- ⬜ `BlockingQueue` and the producer-consumer pattern
- ⬜ Race conditions, deadlocks, and how to avoid them
- ⬜ Optimistic locking (versioning) vs pessimistic locking

---

## Phase 1: LLD problems

Code goes in `lld/<problem-name>/`. For each problem: requirements → entities → class diagram → code → a `Main` demo → extensions and concurrency notes in the README.

### Level 1: Easy (learn to model entities)

| # | Problem | Priority | What it tests | Status |
|---|---|---|---|---|
| 1 | [Parking Lot](lld/parking-lot/PROBLEM.md) | 🔴 | Entities, Strategy, Factory, Singleton | ✅ |
| 2 | [Tic-Tac-Toe](lld/tic-tac-toe/PROBLEM.md) | 🔴 | Game modelling, win check, extensible N×N board | ⬜ |
| 3 | [Vending Machine (incl. coffee machine variant)](lld/vending-machine/PROBLEM.md) | 🔴 | **State** pattern, inventory, change | ⬜ |
| 4 | [Logging Framework](lld/logging-framework/PROBLEM.md) | 🔴 | Chain of Responsibility, Singleton, multiple sinks | ⬜ |
| 5 | [Snake & Ladder](lld/snake-and-ladder/PROBLEM.md) | 🟡 | Board, players, dice, extensible rules | ⬜ |
| 6 | [Task Management System (Jira-lite)](lld/task-management-system/PROBLEM.md) | 🟡 | CRUD modelling, status flow, assignment | ⬜ |
| 7 | [Stack Overflow](lld/stack-overflow/PROBLEM.md) | 🟡 | Users, posts, votes, reputation | ⬜ |
| 8 | [Traffic Signal Control](lld/traffic-signal-control/PROBLEM.md) | ⚪ | State, timers | ⬜ |

### Level 2: Medium (most commonly asked)

| # | Problem | Priority | What it tests | Status |
|---|---|---|---|---|
| 9 | [LRU Cache (+ LFU follow-up)](lld/lru-cache/PROBLEM.md) | 🔴 | HashMap + doubly linked list, O(1) ops, **thread-safe version** | ⬜ |
| 10 | [Rate Limiter](lld/rate-limiter/PROBLEM.md) | 🔴 | Strategy (token bucket, sliding window), per-user/API limits, thread safety | ⬜ |
| 11 | [Elevator System](lld/elevator-system/PROBLEM.md) | 🔴 | State, Strategy (scheduling), Command (requests) | ⬜ |
| 12 | [Movie / Concert Ticket Booking (BookMyShow)](lld/movie-ticket-booking/PROBLEM.md) | 🔴 | **Seat locking under concurrency**, booking flow, payment | ⬜ |
| 13 | [Splitwise / Expense Sharing](lld/splitwise/PROBLEM.md) | 🔴 | Split strategies (equal/exact/percent), balance simplification | ⬜ |
| 14 | [Pub-Sub / In-memory Message Queue](lld/pub-sub-system/PROBLEM.md) | 🔴 | Observer, topics, consumer offsets, concurrency | ⬜ |
| 15 | [Food Ordering / Delivery (Swiggy/Zomato)](lld/food-delivery/PROBLEM.md) | 🔴 | Order state machine, restaurant selection strategy | ⬜ |
| 16 | [Notification Service](lld/notification-service/PROBLEM.md) | 🟡 | Observer, Strategy per channel (email/SMS/push), retries | ⬜ |
| 17 | [ATM](lld/atm/PROBLEM.md) | 🟡 | State, Chain of Responsibility (cash dispensing) | ⬜ |
| 18 | [Library Management System](lld/library-management-system/PROBLEM.md) | 🟡 | Search, borrowing, fines, reservations | ⬜ |
| 19 | [Hotel Management / Booking](lld/hotel-management/PROBLEM.md) | 🟡 | Availability, booking overlap, cancellation | ⬜ |
| 20 | [Car Rental System](lld/car-rental-system/PROBLEM.md) | 🟡 | Inventory, date-range booking, pricing | ⬜ |
| 21 | [Digital Wallet](lld/digital-wallet/PROBLEM.md) | 🟡 | Transactions, idempotency, consistent balances | ⬜ |
| 22 | [Online Auction](lld/online-auction/PROBLEM.md) | ⚪ | Observer, bid concurrency, auction states | ⬜ |
| 23 | [LinkedIn / Facebook (social network core)](lld/social-network/PROBLEM.md) | ⚪ | Connections, feed, privacy | ⬜ |

### Level 3: Hard (concurrency and extensibility)

| # | Problem | Priority | What it tests | Status |
|---|---|---|---|---|
| 24 | [Task / Job Scheduler](lld/job-scheduler/PROBLEM.md) | 🔴 | Thread pool, delayed/recurring jobs, dependencies, retries (asked at Uber) | ⬜ |
| 25 | [In-memory Key-Value Store (with TTL)](lld/key-value-store/PROBLEM.md) | 🔴 | Expiry, eviction, transactions, `ConcurrentHashMap` | ⬜ |
| 26 | [Chess](lld/chess/PROBLEM.md) | 🟡 | Piece polymorphism, move validation, game state | ⬜ |
| 27 | [Ride Sharing (Uber/Ola)](lld/ride-sharing/PROBLEM.md) | 🟡 | Driver matching strategy, trip state, pricing | ⬜ |
| 28 | [E-commerce (Amazon-like)](lld/e-commerce/PROBLEM.md) | 🟡 | Cart, inventory, orders, payments | ⬜ |
| 29 | [Stock Brokerage / Order Matching Engine](lld/stock-order-matching/PROBLEM.md) | ⚪ | Order book, priority queues, matching | ⬜ |
| 30 | [Music Streaming (Spotify)](lld/music-streaming/PROBLEM.md) | ⚪ | Playlists, playback, recommendations hook | ⬜ |
| 31 | [CricInfo (live scoring)](lld/cricinfo/PROBLEM.md) | ⚪ | Complex domain modelling, Observer for live updates | ⬜ |
| 32 | [Course Registration](lld/course-registration/PROBLEM.md) | ⚪ | Seat limits, waitlists, concurrency | ⬜ |

---

## Phase 2: HLD fundamentals

Notes go in `concepts/` (or `hld/fundamentals/`). Every HLD problem uses these.

| # | Topic | Priority | Status |
|---|---|---|---|
| 1 | Interview framework: requirements → estimates → API → data model → high-level design → deep dives | 🔴 | ⬜ |
| 2 | Back-of-the-envelope estimation and numbers to know | 🔴 | ⬜ |
| 3 | Networking: DNS, HTTP, TCP/UDP, REST vs gRPC vs GraphQL | 🔴 | ⬜ |
| 4 | Real-time: WebSockets, SSE, long polling | 🔴 | ⬜ |
| 5 | Load balancing (L4 vs L7, algorithms) and API gateway | 🔴 | ⬜ |
| 6 | Caching: placement, eviction, write-through/back/around, cache stampede | 🔴 | ⬜ |
| 7 | CDN | 🔴 | ⬜ |
| 8 | SQL vs NoSQL, and how to pick one | 🔴 | ⬜ |
| 9 | Indexing, replication (leader-follower, leaderless), sharding/partitioning | 🔴 | ⬜ |
| 10 | Consistent hashing | 🔴 | ⬜ |
| 11 | CAP theorem, consistency models (strong vs eventual) | 🔴 | ⬜ |
| 12 | Message queues and streams: Kafka, delivery guarantees, dead-letter queues | 🔴 | ⬜ |
| 13 | Rate limiting algorithms | 🔴 | ⬜ |
| 14 | Idempotency, retries, circuit breakers | 🔴 | ⬜ |
| 15 | Contention: locks, optimistic concurrency, distributed locks | 🔴 | ⬜ |
| 16 | Blob storage (S3), pre-signed URLs, large uploads | 🟡 | ⬜ |
| 17 | Search: inverted index, Elasticsearch | 🟡 | ⬜ |
| 18 | Geospatial indexing: geohash, quadtree | 🟡 | ⬜ |
| 19 | Microservices vs monolith, service discovery | 🟡 | ⬜ |
| 20 | Distributed transactions: 2PC, Saga, outbox pattern | 🟡 | ⬜ |
| 21 | Stream processing (Flink), change data capture | ⚪ | ⬜ |
| 22 | Big-data structures: Bloom filter, HyperLogLog, Count-Min Sketch | ⚪ | ⬜ |
| 23 | Observability: metrics, logs, tracing | ⚪ | ⬜ |

**Key technologies** (know what each is for and its main trade-offs): Redis, Kafka, PostgreSQL, Cassandra, DynamoDB, Elasticsearch, S3, ZooKeeper

---

## Phase 3: HLD problems

Write-ups go in `hld/<problem-name>/README.md`, each with requirements, estimates, API, data model, a diagram (Mermaid), deep dives and trade-offs.

### Level 1: Easy

| # | Problem | Priority | Key topics | Status |
|---|---|---|---|---|
| 1 | [URL Shortener (Bitly)](hld/url-shortener/PROBLEM.md) | 🔴 | Hashing / base62, read-heavy caching, ID generation | ⬜ |
| 2 | [Rate Limiter (distributed)](hld/rate-limiter/PROBLEM.md) | 🔴 | Token bucket in Redis, gateway placement | ⬜ |
| 3 | [Unique ID Generator (Snowflake)](hld/unique-id-generator/PROBLEM.md) | 🟡 | Clock-based IDs, coordination | ⬜ |
| 4 | [Pastebin](hld/pastebin/PROBLEM.md) | ⚪ | Blob storage, expiry | ⬜ |

### Level 2: Medium (core SDE-2 set)

| # | Problem | Priority | Key topics | Status |
|---|---|---|---|---|
| 5 | [Notification System](hld/notification-system/PROBLEM.md) | 🔴 | Queues, fan-out, retries, user preferences | ⬜ |
| 6 | [News Feed (Facebook/Twitter)](hld/news-feed/PROBLEM.md) | 🔴 | Fan-out on write vs read, celebrity problem, caching | ⬜ |
| 7 | [Chat System (WhatsApp)](hld/chat-system/PROBLEM.md) | 🔴 | WebSockets, message ordering, presence, offline delivery | ⬜ |
| 8 | [Ticketmaster / BookMyShow](hld/ticket-booking/PROBLEM.md) | 🔴 | **Contention**, seat reservation with TTL, virtual waiting queue | ⬜ |
| 9 | [Distributed Cache / Key-Value Store](hld/distributed-cache/PROBLEM.md) | 🔴 | Consistent hashing, replication, quorum | ⬜ |
| 10 | [Web Crawler](hld/web-crawler/PROBLEM.md) | 🔴 | URL frontier, politeness, deduplication | ⬜ |
| 11 | [Search Autocomplete (Typeahead)](hld/search-autocomplete/PROBLEM.md) | 🔴 | Trie, top-K per prefix, caching | ⬜ |
| 12 | [Top-K / Leaderboard (YouTube Top K, gaming)](hld/top-k-leaderboard/PROBLEM.md) | 🔴 | Redis sorted sets, stream aggregation | ⬜ |
| 13 | [Instagram (photo sharing)](hld/instagram/PROBLEM.md) | 🟡 | Blob storage, CDN, feed | ⬜ |
| 14 | [Job Scheduler (distributed)](hld/job-scheduler/PROBLEM.md) | 🟡 | Scheduling, at-least-once execution, worker leases | ⬜ |
| 15 | [Yelp / Proximity Service](hld/proximity-service/PROBLEM.md) | 🟡 | Geohash/quadtree, read-heavy | ⬜ |
| 16 | [Hotel Reservation](hld/hotel-reservation/PROBLEM.md) | 🟡 | Inventory consistency, overbooking, idempotency | ⬜ |
| 17 | [Live Comments (FB Live)](hld/live-comments/PROBLEM.md) | ⚪ | Real-time push at scale, SSE, pub-sub | ⬜ |
| 18 | [Flash Sale](hld/flash-sale/PROBLEM.md) | ⚪ | Burst traffic, inventory decrement, queueing | ⬜ |

### Level 3: Hard

| # | Problem | Priority | Key topics | Status |
|---|---|---|---|---|
| 19 | [Uber / Ride Sharing](hld/ride-sharing/PROBLEM.md) | 🔴 | Location updates, geo-index, matching, consistency | ⬜ |
| 20 | [YouTube / Netflix](hld/video-streaming/PROBLEM.md) | 🔴 | Video upload pipeline, transcoding, adaptive streaming, CDN | ⬜ |
| 21 | [Google Drive / Dropbox](hld/file-sync/PROBLEM.md) | 🔴 | Chunking, sync, conflict resolution, metadata DB | ⬜ |
| 22 | [Payment System](hld/payment-system/PROBLEM.md) | 🔴 | Idempotency, ledger, reconciliation, exactly-once | ⬜ |
| 23 | [Ad Click Aggregator](hld/ad-click-aggregator/PROBLEM.md) | 🟡 | Stream processing, windowing, deduplication | ⬜ |
| 24 | [Google Docs](hld/google-docs/PROBLEM.md) | 🟡 | OT vs CRDT, real-time collaboration | ⬜ |
| 25 | [Distributed Message Queue (Kafka)](hld/message-queue/PROBLEM.md) | 🟡 | Partitions, replication, consumer groups | ⬜ |
| 26 | [Metrics Monitoring and Alerting](hld/metrics-monitoring/PROBLEM.md) | 🟡 | Time-series DB, aggregation, alerting | ⬜ |
| 27 | [ChatGPT / LLM chat app](hld/llm-chat-app/PROBLEM.md) | 🟡 | Streaming responses, GPU capacity, rate limits, conversation storage | ⬜ |
| 28 | [Digital Wallet](hld/digital-wallet/PROBLEM.md) / [Robinhood / Stock Exchange](hld/stock-exchange/PROBLEM.md) | ⚪ | Ledgers, event sourcing, low latency | ⬜ |
| 29 | [S3-like Object Storage](hld/object-storage/PROBLEM.md) | ⚪ | Data/metadata separation, erasure coding | ⬜ |
| 30 | [Google Maps](hld/google-maps/PROBLEM.md) / [Nearby Friends](hld/nearby-friends/PROBLEM.md) | ⚪ | Routing, tiles, location fan-out | ⬜ |
| 31 | [Tinder](hld/tinder/PROBLEM.md), [LeetCode](hld/online-judge/PROBLEM.md), [Online Chess](hld/online-chess/PROBLEM.md), [Strava](hld/fitness-tracker/PROBLEM.md), [Email service](hld/email-service/PROBLEM.md) | ⚪ | Extra practice once the core set is done | ⬜ |

---

## Order to follow

See **[ORDER.md](ORDER.md)**: one step-by-step list (61 steps + bonus) that alternates LLD and HLD, with checkpoints.

---

## Sources

- [awesome-low-level-design (ashishps1)](https://github.com/ashishps1/awesome-low-level-design): LLD problems by difficulty and the list of patterns
- [AlgoMaster: LLD interview types](https://algomaster.io/learn/lld/lld-interview-types) and [handling concurrency](https://algomaster.io/learn/lld/handling-concurrency)
- [Hello Interview: System Design in a Hurry](https://www.hellointerview.com/learn/system-design/in-a-hurry/introduction): problem breakdowns, core concepts, patterns
- Alex Xu, *System Design Interview* Vol 1 & 2 ([Vol 2 table of contents](https://www.goodreads.com/book/show/204607255-system-design-interview-an-insider-s-guide))
- [workat.tech: SDE-2 interview prep guide](https://workat.tech/general/article/sde-2-interview-prep-ultimate-guide-dn1am88dbutl)
- [DesignGurus: 2026 system design cheat sheet](https://designgurus.substack.com/p/the-2026-system-design-interview) · [lets-code: system design questions 2026](https://lets-code.co.in/interview/system-design-interview-questions/)
- Interview reports: [Uber SDE-2 job scheduler](https://papersadda.com/article/uber-sde2-l4-india-2025-job-scheduler-6round-offer/) · [Microsoft SDE-2 (rate limiter)](https://leetcode.com/discuss/post/7498667/microsoft-sde-2-interview-experience-noi-9ck3/) · [Startup SDE-2 (rate limiter)](https://leetcode.com/discuss/post/8187657/interview-exp-at-a-startup-sde-2-backend-cuxg/) · [Zeta SDE-2](https://leetcode.com/discuss/post/3815326/zeta-sde-2-banglore) · [Blind: concurrency in machine coding](https://www.teamblind.com/post/concurrency-inside-machine-coding-round-igesph4i) · [Flipkart interview guide](https://www.interviewquery.com/interview-guides/flipkart-software-engineer)
