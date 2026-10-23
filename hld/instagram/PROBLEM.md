# Design Instagram (Photo Sharing)

> HLD · ⏱ 45 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a photo and video sharing app like Instagram.

## Functional requirements
1. Upload photos/videos with a caption.
2. Follow users.
3. **Home feed** of posts from followed users.
4. Like and comment on posts.
5. View a user's profile with their posts in a grid.

## Non-functional requirements
- Media loads fast globally.
- Highly available; eventual consistency is fine for the feed and like counts.
- Uploaded media is never lost.

## Scale (assume)
- 500M daily active users; 100M uploads a day (average 2 MB); 10B+ feed views a day.

## Deep-dive questions
1. **Upload flow:** why upload directly to object storage with a pre-signed URL instead of through your servers? How are thumbnails/resizes generated (async workers)?
2. How do a **CDN** and multiple image sizes make loading fast?
3. Feed generation: push vs pull vs hybrid (as in the News Feed problem).
4. Data model: users, posts, follows, likes. Which databases?
5. Like counters for viral posts: how do you avoid one hot row being updated 100k times a second?
6. Storage cost: hot vs cold storage tiers for old media.

## Concepts to revise
Object storage + pre-signed URLs · CDN · async processing · fan-out · counter sharding
