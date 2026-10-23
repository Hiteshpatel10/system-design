# Social Network Core (LinkedIn / Facebook)

> LLD · Machine coding · ⏱ 90 min · ⚪ Bonus · Level 2 (Medium)

## Problem statement
Design the core of a social network: profiles, connections, posts and a news feed.

## Requirements
1. **Users** with profiles (name, headline, location, experience/education list).
2. **Connections:**
   - send, accept and reject connection requests (two-way, LinkedIn style)
   - **follow** (one-way)
   - remove a connection
3. **Posts:** text (+ optional media URL), with author and time. **Like** and **comment** on posts (comments can have replies).
4. **News feed:** posts from connections and followed users, newest first, **paginated** (e.g. 10 per page).
5. **Privacy:** each post's visibility is PUBLIC, CONNECTIONS_ONLY or PRIVATE. The feed and profile views must respect it.
6. **Search** users by name.
7. **Notifications:** connection request received/accepted, likes and comments on your posts.

## Bonus
- **"People you may know"** (friends-of-friends ranked by mutual connections).
- **Degree of connection** (1st / 2nd / 3rd) between two users (BFS).
- Groups and messaging.
- Feed ranking by engagement instead of pure time.

## Constraints
- In-memory. The feed algorithm must be replaceable (Strategy).

## Sample flow
```
Ravi → request Asha; Asha accepts
Ben follows Ravi
Ravi posts P1 (PUBLIC), P2 (CONNECTIONS_ONLY)
feed(Asha) → [P2, P1]
feed(Ben)  → [P1]
Asha likes P1 → Ravi notified
degree(Ben, Asha) → 2nd
```

## Follow-up questions
1. How did you model the social graph (adjacency list, separate request objects)?
2. Feed generation: pull (build at read time) vs push (precompute at write time). Which did you do, and how does each scale?
3. How did you implement privacy checks without scattering `if` statements everywhere?
4. How would the friend-suggestion query scale to 1 billion users?
