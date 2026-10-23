# Design Live Comments (Facebook Live / YouTube Live chat)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 2 (Medium)

## Problem statement
Design live comments on live videos: viewers post comments and everyone watching sees them in real time.

## Functional requirements
1. Viewers post comments on a live video.
2. All viewers of that video see new comments in near real time.
3. New viewers see recent comments when they join, and can scroll back through older ones.

## Non-functional requirements
- Comments appear for everyone within ~1–2 seconds.
- Popular streams have **millions of concurrent viewers**.
- Eventual consistency and small ordering differences are acceptable.

## Scale (assume)
- 100M daily active users; one stream can have 5M concurrent viewers; 1M concurrent live videos at peak.

## Deep-dive questions
1. How do clients receive new comments? Compare polling, long polling, **SSE** and WebSockets for a mostly-read stream.
2. Viewers of one video are connected to thousands of servers. How does a new comment reach all of them? (Pub-sub per video, e.g. Redis; or partitioning viewers by video.)
3. For a 5M-viewer stream, nobody can read 10k comments/second. How do you sample or throttle?
4. How do you store comments for history and scroll-back (wide-column DB keyed by video + time)?
5. How do you handle connection servers going down and clients reconnecting?

## Concepts to revise
SSE vs WebSockets · pub-sub fan-out · stateful connection servers · backpressure and sampling
