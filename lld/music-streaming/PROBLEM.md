# Music Streaming (Spotify)

> LLD · Machine coding · ⏱ 90 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design the core of a music streaming app: catalogue, playlists, playback and subscriptions.

## Requirements
1. **Catalogue:** artists, albums, songs (title, duration, genre, artist(s), album). Search songs, albums and artists by name.
2. **Users** have a subscription: FREE or PREMIUM.
3. **Playlists:** create, rename and delete; add, remove and reorder songs; make public or private. Users can **follow** public playlists.
4. **Player** (one per user session):
   - play a song or a playlist
   - pause, resume, next, previous, seek
   - shuffle, repeat (off / one / all)
   - a **queue** ("play next", "add to queue")
5. **Free tier rules:** an ad every 3 songs; max 6 skips per hour; shuffle only. Premium has no limits.
6. **Listening history**, and **recently played** (last 20).
7. **Like** songs (a "Liked Songs" collection).

## Bonus
- **Recommendations:** "because you listened to X" (same artist/genre), with pluggable strategies.
- **Offline downloads** for premium users.
- **Artist stats:** play counts, monthly listeners.
- Podcasts with episodes and resume position.

## Constraints
- In-memory. Simulate playback (print "Now playing …"); no real audio.

## Sample flow
```
Ravi (FREE) plays playlist "Chill" [S1..S5] shuffle on
next, next, next → after 3 songs: "Ad playing…"
skip ×7 within an hour → 7th rejected: skip limit reached
Ravi upgrades to PREMIUM → no ads, unlimited skips, repeat available
recentlyPlayed(Ravi) → [S3, S5, S1]
```

## Follow-up questions
1. How did you keep the free/premium rules out of the player code? (Strategy / Decorator / policy object.)
2. Which pattern fits play/pause/next on the player: State, Command, or both?
3. How would you implement shuffle so that every song plays once before any repeats?
4. How would streaming work at scale (CDN, chunks, adaptive bitrate)? (See HLD: Video Streaming; the ideas are similar.)
