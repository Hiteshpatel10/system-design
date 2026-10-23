# Design Strava (Fitness Activity Tracker)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design an app where athletes record runs/rides with GPS, share them, and compare with friends.

## Functional requirements
1. **Record an activity:** GPS points while running/cycling (also offline), then upload.
2. View activity stats: distance, pace, route on a map.
3. Follow friends; a **feed** of friends' activities.
4. (Optional) Leaderboards for popular route **segments**.

## Non-functional requirements
- Recording works offline; uploads are reliable.
- Activity data is never lost.
- Reads (feeds) are fast.

## Scale (assume)
- 10M daily active users; 1 activity a day each; a GPS point every second.

## Deep-dive questions
1. Client-side recording: buffer locally, upload when done (or periodically). Why not stream every point live?
2. Storing GPS tracks: raw files in object storage vs a time-series/DB; summaries in the database.
3. Computing stats asynchronously after upload.
4. Segment leaderboards: matching a track against segments (geospatial), and ranking efforts.
5. Feed: fan-out (as in News Feed).

## Concepts to revise
Offline-first clients · object storage · async processing · geospatial matching · feeds
