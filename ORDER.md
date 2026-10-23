# Study Order (SDE-2)

The order to solve everything in. Problem details and priorities are in [ROADMAP.md](ROADMAP.md); fundamentals numbers (e.g. "HLD fundamentals 8–11") refer to the Phase 2 table there.

One list, top to bottom. 🟦 = LLD (code) · 🟩 = HLD (design) · 📘 = study a topic first.
Related LLD and HLD problems sit next to each other: solve the in-memory version in code, then design the same thing at scale.
Each stage ends with a ⏱ checkpoint: one timed mock without notes.

## Stage 0: Foundations
1. 📘 OOP, SOLID, UML class diagrams (Phase 0)
2. 📘 Patterns: Strategy, Factory, Singleton, Observer, State, Builder (learn the 🟡 ones when a problem needs them)
3. 🟦 Revisit [Parking Lot](lld/parking-lot/PROBLEM.md): answer its follow-ups and refactor with what you learned

## Stage 1: Modelling basics + HLD basics
4. 🟦 [Tic-Tac-Toe](lld/tic-tac-toe/PROBLEM.md): clean entities, extensibility
5. 🟦 [Vending Machine](lld/vending-machine/PROBLEM.md): State pattern
6. 📘 HLD fundamentals 1, 2, 3, 5, 6, 7 (framework, estimation, networking, load balancing, caching, CDN)
7. 🟩 [URL Shortener](hld/url-shortener/PROBLEM.md): your first full HLD
8. 📘 Java concurrency basics (Phase 0)
9. 🟦 [Logging Framework](lld/logging-framework/PROBLEM.md): Chain of Responsibility, thread-safe Singleton
10. 🟦 [Snake & Ladder](lld/snake-and-ladder/PROBLEM.md) 🟡
11. 🟦 [Task Management System](lld/task-management-system/PROBLEM.md) 🟡: workflows
12. 🟦 [Stack Overflow](lld/stack-overflow/PROBLEM.md) 🟡: entity relationships

⏱ **Checkpoint 1:** an unseen easy LLD in 90 min + URL Shortener again in 45 min, from memory

## Stage 2: Data, caching and concurrency
13. 📘 HLD fundamentals 8–11 (SQL vs NoSQL, indexing/replication/sharding, consistent hashing, CAP)
14. 🟦 [LRU Cache](lld/lru-cache/PROBLEM.md): data structure + thread safety
15. 🟩 [Distributed Cache / Key-Value Store](hld/distributed-cache/PROBLEM.md): the same cache, across many machines
16. 🟦 [Rate Limiter](lld/rate-limiter/PROBLEM.md): Strategy + concurrency
17. 📘 HLD fundamental 13 (rate limiting algorithms)
18. 🟩 [Rate Limiter (distributed)](hld/rate-limiter/PROBLEM.md)
19. 🟩 [Unique ID Generator](hld/unique-id-generator/PROBLEM.md) 🟡
20. 🟦 [Elevator System](lld/elevator-system/PROBLEM.md): State + Strategy + Command
21. 🟦 [Splitwise](lld/splitwise/PROBLEM.md): Strategy, money handling

⏱ **Checkpoint 2:** LRU Cache or Rate Limiter in 60 min + Distributed Cache in 45 min

## Stage 3: Messaging and real-time
22. 📘 HLD fundamentals 4, 12, 14 (WebSockets/SSE, message queues, idempotency and retries)
23. 🟦 [Notification Service](lld/notification-service/PROBLEM.md) 🟡: Observer, retries
24. 🟩 [Notification System](hld/notification-system/PROBLEM.md)
25. 🟦 [Pub-Sub System](lld/pub-sub-system/PROBLEM.md): producer–consumer with threads
26. 🟩 [News Feed](hld/news-feed/PROBLEM.md): fan-out
27. 🟩 [Chat System](hld/chat-system/PROBLEM.md): WebSockets, ordering

⏱ **Checkpoint 3:** Chat System in 45 min, explained out loud

## Stage 4: Contention (booking problems)
28. 📘 HLD fundamental 15 (locks, optimistic concurrency, distributed locks)
29. 🟦 [Movie Ticket Booking](lld/movie-ticket-booking/PROBLEM.md): seat locking
30. 🟩 [Ticketmaster / BookMyShow](hld/ticket-booking/PROBLEM.md)
31. 🟦 [Hotel Management](lld/hotel-management/PROBLEM.md) 🟡: date-range overlaps
32. 🟩 [Hotel Reservation](hld/hotel-reservation/PROBLEM.md) 🟡
33. 🟦 [Food Delivery](lld/food-delivery/PROBLEM.md): order state machine (Flipkart favourite)
34. 🟦 [Car Rental System](lld/car-rental-system/PROBLEM.md) 🟡
35. 🟦 [Library Management System](lld/library-management-system/PROBLEM.md) 🟡
36. 🟦 [ATM](lld/atm/PROBLEM.md) 🟡

⏱ **Checkpoint 4:** Movie Ticket Booking in 90 min with a working concurrency demo

## Stage 5: Search, ranking and crawling
37. 📘 HLD fundamental 17 (search, inverted index)
38. 🟩 [Search Autocomplete](hld/search-autocomplete/PROBLEM.md)
39. 🟩 [Top-K / Leaderboard](hld/top-k-leaderboard/PROBLEM.md)
40. 🟩 [Web Crawler](hld/web-crawler/PROBLEM.md)

## Stage 6: Hard concurrency + money
41. 🟦 [Job Scheduler](lld/job-scheduler/PROBLEM.md): thread pool, delay queue, dependencies (asked at Uber)
42. 🟩 [Job Scheduler (distributed)](hld/job-scheduler/PROBLEM.md) 🟡
43. 🟦 [Key-Value Store](lld/key-value-store/PROBLEM.md): TTL, transactions
44. 🟦 [Digital Wallet](lld/digital-wallet/PROBLEM.md) 🟡: atomic transfers, deadlock avoidance
45. 📘 HLD fundamental 20 (2PC, Saga, outbox pattern)
46. 🟩 [Payment System](hld/payment-system/PROBLEM.md)

⏱ **Checkpoint 5:** Job Scheduler in 90 min + Payment System in 45 min

## Stage 7: Media, files and location
47. 📘 HLD fundamentals 16, 18 (blob storage, geospatial indexing)
48. 🟩 [Instagram](hld/instagram/PROBLEM.md) 🟡
49. 🟩 [YouTube / Netflix](hld/video-streaming/PROBLEM.md)
50. 🟩 [Google Drive / Dropbox](hld/file-sync/PROBLEM.md)
51. 🟩 [Yelp / Proximity Service](hld/proximity-service/PROBLEM.md) 🟡
52. 🟦 [Ride Sharing](lld/ride-sharing/PROBLEM.md) 🟡
53. 🟩 [Uber](hld/ride-sharing/PROBLEM.md)

## Stage 8: Remaining should-dos
54. 🟦 [Chess](lld/chess/PROBLEM.md) 🟡
55. 🟦 [E-commerce](lld/e-commerce/PROBLEM.md) 🟡
56. 📘 HLD fundamental 19 (microservices) + 21 (stream processing, ⚪ but needed next)
57. 🟩 [Ad Click Aggregator](hld/ad-click-aggregator/PROBLEM.md) 🟡
58. 🟩 [Distributed Message Queue](hld/message-queue/PROBLEM.md) 🟡
59. 🟩 [Metrics Monitoring](hld/metrics-monitoring/PROBLEM.md) 🟡
60. 🟩 [Google Docs](hld/google-docs/PROBLEM.md) 🟡
61. 🟩 [ChatGPT / LLM Chat App](hld/llm-chat-app/PROBLEM.md) 🟡

⏱ **Checkpoint 6:** full mock loop: one LLD (90 min) + one HLD (45 min) on problems you haven't seen, e.g. Online Auction + Flash Sale

## Stage 9: Bonus (⚪) and company focus
Pick by target company, or go in table order: [Traffic Signal](lld/traffic-signal-control/PROBLEM.md), [Online Auction](lld/online-auction/PROBLEM.md), [Social Network](lld/social-network/PROBLEM.md), [Order Matching](lld/stock-order-matching/PROBLEM.md), [Music Streaming](lld/music-streaming/PROBLEM.md), [CricInfo](lld/cricinfo/PROBLEM.md), [Course Registration](lld/course-registration/PROBLEM.md) · [Pastebin](hld/pastebin/PROBLEM.md), [Live Comments](hld/live-comments/PROBLEM.md), [Flash Sale](hld/flash-sale/PROBLEM.md), [Digital Wallet](hld/digital-wallet/PROBLEM.md), [Stock Exchange](hld/stock-exchange/PROBLEM.md), [S3](hld/object-storage/PROBLEM.md), [Google Maps](hld/google-maps/PROBLEM.md), [Nearby Friends](hld/nearby-friends/PROBLEM.md), [Tinder](hld/tinder/PROBLEM.md), [LeetCode](hld/online-judge/PROBLEM.md), [Online Chess](hld/online-chess/PROBLEM.md), [Strava](hld/fitness-tracker/PROBLEM.md), [Email](hld/email-service/PROBLEM.md)

**Company hints:** Flipkart / Swiggy / Meesho → more machine coding (Food Delivery, BookMyShow, Splitwise style) · Uber → Job Scheduler, Ride Sharing, concurrency · Amazon / Microsoft → OOD + HLD balance (Rate Limiter, Elevator, Notification System)

## How to do each item
- **🟦 LLD:** first attempt in **90 minutes** with working code (machine-coding conditions); then refactor, write `README.md` and answer the follow-ups.
- **🟩 HLD:** first attempt in **45 minutes** on paper; then compare with a reference solution and write down what you missed.
- **📘 Study:** short notes in `concepts/`, in your own words.
- Revisit any problem you struggled with a week later, from scratch.
