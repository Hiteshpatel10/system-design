# Design Pastebin

> HLD · ⏱ 45 min · ⚪ Bonus · Level 1 (Easy)

## Problem statement
Design a service where users paste text and get a link to share it.

## Functional requirements
1. Create a paste (text up to 10 MB) and get a unique URL.
2. View a paste by its URL.
3. Optional **expiry** (10 min, 1 day, never) and **visibility** (public / unlisted / private).
4. Optional syntax highlighting (language tag).

## Non-functional requirements
- Read-heavy; reads must be fast and highly available.
- Pastes are durable until they expire.

## Scale (assume)
- 1M new pastes a day, 10M reads a day; average size 10 KB.

## Deep-dive questions
1. Where do you store the paste content (database vs object storage like S3) and the metadata? Why separate them?
2. How do you generate the paste key? (Same problem as the URL shortener.)
3. How do you delete expired pastes efficiently?
4. How do caching and a CDN help with popular pastes?
5. How would you rate-limit abuse (spam pastes)?

## Concepts to revise
Blob storage vs database · metadata/content separation · TTL cleanup · CDN
