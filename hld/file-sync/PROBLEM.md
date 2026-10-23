# Design Google Drive / Dropbox (File Storage and Sync)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 3 (Hard)

## Problem statement
Design a cloud file storage service that syncs files across a user's devices and lets them share files.

## Functional requirements
1. Upload and download files (up to 50 GB).
2. **Automatic sync:** a change on one device appears on the user's other devices.
3. Share files/folders with other users (view/edit).
4. File **version history**.

## Non-functional requirements
- **Never lose data**; high durability.
- Large files upload efficiently and **resume** after network failures.
- Sync changes quickly (within seconds to a minute).
- Strong consistency for file metadata (no "ghost" files).

## Scale (assume)
- 50M daily active users; 500M files uploaded a day.

## Deep-dive questions
1. **Chunking:** split files into chunks (e.g. 4 MB) with hashes. How does that help resumable uploads, deduplication and syncing only the changed part (delta sync)?
2. **Metadata vs blocks:** what goes into the metadata database (files, versions, chunk lists, sharing) vs object storage (chunks)?
3. **Sync notifications:** how does a device learn about changes? (Long polling / WebSocket + a change log with a cursor per device.)
4. **Conflicts:** two devices edit the same file offline. How do you detect and resolve them?
5. How do you give strong consistency for metadata while scaling (relational DB, sharded by user)?
6. Security: encryption at rest and in transit; pre-signed URLs for downloads.

## Concepts to revise
Chunking and content hashing · deduplication · object storage · change logs and cursors · conflict resolution
