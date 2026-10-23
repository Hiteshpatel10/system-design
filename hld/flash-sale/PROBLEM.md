# Design a Flash Sale System

> HLD · ⏱ 45 min · ⚪ Bonus · Level 2 (Medium)

## Problem statement
Design an e-commerce flash sale: a limited number of items (e.g. 10,000 phones at a huge discount) go on sale at an exact time, and millions of users try to buy at once.

## Functional requirements
1. Admins create a sale: product, quantity, price, start time, per-user limit (e.g. 1).
2. Users see a countdown, then try to buy when the sale starts.
3. Successful buyers have a few minutes to pay; unpaid items go back to the pool.
4. Show "sold out" as soon as stock runs out.

## Non-functional requirements
- **Never oversell.** Each user buys at most the per-user limit.
- The rest of the site must stay up during the spike.
- Fair-ish ordering; resistant to bots.

## Scale (assume)
- 10,000 units; 5M users; ~1M requests in the first second.

## Deep-dive questions
1. Why can't the main order database take 1M requests/second for one product row?
2. Fast stock decrement: Redis atomic `DECR` / Lua script, or pre-split tokens. How do you keep Redis and the database consistent?
3. **Shave the peak:** CDN for static pages, a request queue/waiting room, rejecting early once sold out.
4. How do you enforce the per-user limit and stop bots (captcha, rate limiting, tokens issued before the sale)?
5. Reservation expiry and returning unpaid stock.
6. Isolate the sale from the rest of the site (separate services/clusters).

## Concepts to revise
Redis atomic operations · queues for load levelling · rate limiting · isolation (bulkheads) · eventual reconciliation
