# Design YouTube / Netflix (Video Streaming)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 3 (Hard)

## Problem statement
Design a video platform where creators upload videos and viewers stream them on any device.

## Functional requirements
1. **Upload** videos (up to several GB).
2. **Stream / watch** videos with smooth playback on different devices and network speeds.
3. Video metadata: title, description, thumbnail, view count.
4. Search videos (mention only), resume from where you left off.

## Non-functional requirements
- Playback starts quickly (< 2 s) with minimal buffering, worldwide.
- Uploads are reliable (resumable).
- Highly available for watching; eventual consistency is fine for view counts and metadata.

## Scale (assume)
- 1M uploads a day (avg 500 MB raw); 1B views a day.

## Deep-dive questions
1. **Upload:** chunked/resumable uploads directly to object storage via pre-signed URLs.
2. **Processing pipeline:** split into segments → **transcode** to several resolutions/codecs in parallel → thumbnails → publish. How do you orchestrate it (a DAG of tasks with workers and queues)?
3. **Adaptive bitrate streaming** (HLS/DASH): what's a manifest, and how does the player switch quality?
4. **CDN** strategy: which videos go to edge caches? (Popular vs long tail; Netflix-style pre-positioning.)
5. Metadata storage and view counting at scale.
6. Cost: storage tiers, and when to transcode lazily.

## Concepts to revise
Object storage · multipart upload · async pipelines · transcoding · HLS/DASH · CDN
