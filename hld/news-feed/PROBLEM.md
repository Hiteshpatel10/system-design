# Design a News Feed (Facebook / Twitter / Instagram home feed)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design the home feed: when a user opens the app, they see recent posts from people they follow.

## Functional requirements
1. Users create posts (text, image/video links).
2. Users follow other users.
3. The **home feed** shows posts from followed users, newest first (or ranked), with **pagination / infinite scroll**.
4. (Optional) Likes and comment counts shown on each post.

## Non-functional requirements
- Feed loads fast: p99 < 200 ms.
- Highly available; **eventual consistency** is fine (a new post may take a few seconds to appear).
- Must handle **celebrities** with 100M followers.

## Scale (assume)
- 500M daily active users; each user follows ~200 people on average; 100M posts a day.
- Each user opens the feed ~10 times a day.

## Deep-dive questions
1. **Fan-out on write (push)** vs **fan-out on read (pull)**: explain both, with their costs. What's the hybrid approach?
2. The celebrity problem: why does push break for a user with 100M followers?
3. What exactly is stored in the feed cache (post IDs only?), where (Redis lists/sorted sets), and how long is it kept?
4. How does **cursor-based pagination** work, and why not offset-based?
5. Data model and database choices for users, posts, the follow graph and feeds.
6. How are images and videos served (object storage + CDN)?

## Concepts to revise
Fan-out on write vs read · Redis · cursor pagination · CDN · denormalisation · eventual consistency
