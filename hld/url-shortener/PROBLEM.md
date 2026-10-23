# Design a URL Shortener (Bitly / TinyURL)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 1 (Easy)

## Problem statement
Design a service that turns long URLs into short links (e.g. `sho.rt/aZ3x9Q`) and redirects users who open a short link to the original URL.

## Functional requirements
1. Create a short URL from a long URL.
2. Optionally choose a **custom alias** (`sho.rt/my-sale`) and an **expiry date**.
3. Opening a short URL **redirects** to the original URL.
4. (Optional) Click analytics per link: total clicks, clicks per day, top countries.

## Non-functional requirements
- Redirects must be **fast** (p99 < 50 ms) and **highly available**; the system is read-heavy.
- Short codes must be **unique**: two long URLs must never get the same code.
- Codes shouldn't be easy to guess sequentially (nice to have).

## Scale (assume)
- 100M new URLs per month; 100:1 read-to-write ratio (~10B redirects a month).
- Keep links for 5 years.

## Out of scope
- User accounts and billing, spam/malware URL detection (mention only).

## Deep-dive questions
1. How do you generate short codes? Compare hashing (MD5 + truncate, handling collisions), a counter + base62, and a pre-generated key service. How long must the code be?
2. 301 vs 302 redirect: what's the difference for caching and analytics?
3. How do you make redirects fast? Where do you cache (CDN, Redis), and what's the eviction policy?
4. Which database, and why? How do you shard it?
5. How do you collect click analytics without slowing down redirects?
6. How do you handle expired links and custom-alias conflicts?

## Concepts to revise
Base62 encoding · hashing and collisions · caching · read-heavy scaling · database sharding · unique ID generation
