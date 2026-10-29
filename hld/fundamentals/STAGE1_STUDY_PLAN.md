# Stage 1 HLD Fundamentals: Study Plan

Covers [ORDER.md](../../ORDER.md) step 6: HLD fundamentals 1, 2, 3, 5, 6, 7 from the Phase 2 table in [ROADMAP.md](../../ROADMAP.md).
Comes right before the first full HLD, [URL Shortener](../url-shortener/PROBLEM.md).

**Time budget:** about 4–6 focused days.
**Rule:** write the notes in your own words. This file lists what to cover and the questions to answer; the answers are yours to write.

## Progress

| # | Topic | Time | Notes file | Status |
|---|---|---|---|---|
| 1 | Interview framework | ½ day | `01-framework.md` | ⬜ |
| 2 | Estimation | 1 day | `02-estimation.md` | ⬜ |
| 3 | Networking | 1 day | `03-networking.md` | ⬜ |
| 5 | Load balancing + API gateway | ½–1 day | `05-load-balancing.md` | ⬜ |
| 6 | Caching | 1–1½ days | `06-caching.md` | ⬜ |
| 7 | CDN | ½ day | `07-cdn.md` | ⬜ |

Put the notes files in this folder (`hld/fundamentals/`).

---

## 1. Interview framework 🔴
Steps for a 45-minute round: **requirements (5 min) → estimates (5) → API (5) → data model (5) → high-level diagram (10) → deep dives (15)**. The table in [../README.md](../README.md) has the same steps.

- Functional vs non-functional requirements. Non-functional means scale, latency, availability, and consistency.
- Ask the interviewer to confirm the scope out loud before you design anything.

**Your notes should answer:**
- [ ] Which 3–4 clarifying questions do you ask on every problem?
- [ ] How do you pick which part to deep-dive into?

## 2. Estimation 🔴
- Rule of thumb: **1 day ≈ 10⁵ seconds**, so 1M requests/day ≈ 12 QPS. Peak traffic is about 2–3× the average.
- Data sizes: 1 KB × 1B = 1 TB. Storage = writes/day × record size × retention.
- Latency numbers: memory read ~100 ns, SSD read ~100 µs, round trip inside a datacenter ~0.5 ms, cross-continent round trip ~150 ms.
- Rough capacity per box: one app server handles ~1–10K QPS, one Postgres instance ~5–10K simple QPS, one Redis ~100K+ ops/sec.

**Practice:**
- [ ] Estimate the URL Shortener's QPS, storage and bandwidth now, before you solve it.

## 3. Networking 🔴
- What happens when you type a URL: DNS resolution (and its caching layers and TTL) → TCP handshake → TLS → HTTP request.
- TCP vs UDP: reliability and ordering vs low latency. Know which systems use which (video calls, DNS, QUIC).
- HTTP/1.1 vs HTTP/2 (multiplexing) vs HTTP/3 (QUIC). Status codes. Idempotent methods.
- REST vs gRPC vs GraphQL: when each one fits. A common pattern is REST for public APIs and gRPC between services.

**Your notes should answer:**
- [ ] Why does gRPC use HTTP/2?
- [ ] When does GraphQL cause trouble (caching, N+1 queries)?

## 5. Load balancing + API gateway 🔴
- L4 balancers route on IP and port: fast but don't look at content. L7 balancers route on HTTP path, headers and cookies.
- Algorithms: round robin, weighted, least connections, IP hash, consistent hashing (you'll go deeper in fundamental #10).
- Health checks, sticky sessions (and why stateless servers are better), and how the LB itself avoids being a single point of failure (active-passive pairs, DNS).
- An API gateway handles auth, rate limiting, routing and TLS termination.

**Your notes should answer:**
- [ ] How does an API gateway differ from a load balancer, and when do you need both?
- [ ] Why are stateless app servers easier to load-balance?

## 6. Caching 🔴 (the most important topic here)
- Where caches sit: client → CDN → LB/gateway → app (in-process) → distributed cache (Redis) → DB buffer pool.
- Read patterns: **cache-aside** (the default) and read-through.
- Write patterns: **write-through, write-back, write-around**. For each, know its consistency and data-loss trade-offs.
- Eviction: LRU, LFU, TTL. This connects directly to the [LRU Cache](../../lld/lru-cache/PROBLEM.md) LLD later.
- Failure modes: **cache stampede** (fixes: locking/single-flight, early refresh, jittered TTLs), hot keys, cache penetration (fix: cache null results, Bloom filter), and invalidation.

**Your notes should answer:**
- [ ] With cache-aside, which goes first, the DB write or the cache delete, and why?
- [ ] When would you pick write-back despite the risk of losing data?
- [ ] How do you handle one very hot key?

## 7. CDN 🔴
- Edge PoPs, origin, cache hit ratio, TTL and Cache-Control headers, invalidation/purge.
- Push vs pull CDNs. Static vs dynamic content. Edge compute.

**Your notes should answer:**
- [ ] When does a CDN help an API, and when doesn't it?
- [ ] How do you serve private content from a CDN (signed URLs)?

---

## Resources
- **ByteByteGo / Alex Xu, Vol. 1, chapters 1–3**: scaling basics, estimation, framework.
- **Hello Interview's "Core Concepts" and "Delivery Framework"** pages: closest to what interviewers expect today.
- **DDIA, chapter 1**: optional at this stage. You'll need it properly in Stage 2.

## Done check
- [ ] Do the URL Shortener's estimates in under 5 minutes.
- [ ] Explain out loud why you'd put a cache in front of the DB and a CDN in front of the redirects.
- [ ] Mark fundamentals 1, 2, 3, 5, 6, 7 as done in [ROADMAP.md](../../ROADMAP.md), then move on to step 7.
