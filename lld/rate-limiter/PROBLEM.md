# Rate Limiter

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design an in-process rate limiter library that decides whether a request should be allowed or rejected.

## Requirements
1. API: `boolean allowRequest(String clientId)` (client = a user ID, API key or IP).
2. Limits are configured as **N requests per time window** (e.g. 100 per minute).
3. Support at least two algorithms, selectable through configuration:
   - **Token bucket** (capacity + refill rate)
   - **Sliding window log** or **sliding window counter**
   (Fixed window is a nice third.)
4. Limits are tracked **separately per client**.
5. **Different limits per API/endpoint** (e.g. `/login` 5/min, `/search` 100/min). One request is checked against the rule for its endpoint.
6. **Thread-safe:** many threads call `allowRequest` at the same time for the same client.

## Bonus
- **Tiered limits** per user plan (FREE 10/min, PREMIUM 1000/min).
- Several rules at once (e.g. 10/second **and** 1000/day).
- Return `remaining` and `retryAfter`, like the `X-RateLimit-*` HTTP headers.
- Clean up idle clients so memory doesn't grow forever.

## Constraints
- In-memory, single process. Use an injectable **clock** so tests don't depend on real time.

## Sample flow
```
rule /login: token bucket, capacity 3, refill 1 token/sec
t=0.0 user1 /login → allowed (2 left)
t=0.1 user1 /login → allowed (1 left)
t=0.2 user1 /login → allowed (0 left)
t=0.3 user1 /login → rejected, retry after 0.7s
t=0.3 user2 /login → allowed (separate bucket)
t=1.2 user1 /login → allowed (refilled 1)
```

## Follow-up questions
1. Compare fixed window, sliding log, sliding counter and token bucket: accuracy, memory and burst handling.
2. How did you make the per-client state thread-safe? Is `synchronized` on the whole limiter acceptable?
3. How would this work across 10 servers behind a load balancer? (Redis + Lua script, atomicity.)
4. Where should the rate limiter sit: in the app, an API gateway or a sidecar?
