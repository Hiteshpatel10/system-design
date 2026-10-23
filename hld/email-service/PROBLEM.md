# Design a Distributed Email Service (Gmail)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a large-scale email service like Gmail or Outlook.

## Functional requirements
1. Send and receive email (to/from other providers using SMTP).
2. Folders/labels, read/unread, search across all emails.
3. Attachments.
4. Spam and virus filtering.

## Non-functional requirements
- **Never lose email.**
- Fast mailbox loading and search.
- Handle a huge number of users and stored emails.

## Scale (assume)
- 1B users; 100 emails received per user per day (much of it spam); many years of storage.

## Deep-dive questions
1. Sending and receiving flows (SMTP servers, outgoing queues, retries, reputation).
2. Storing emails: metadata in a wide-column database (partitioned by user), bodies/attachments in object storage. Why not a relational DB?
3. **Search:** an inverted index per user; how do you keep it updated?
4. Spam filtering pipeline.
5. Real-time inbox updates (WebSockets / push).
6. Attachment deduplication.

## Concepts to revise
SMTP basics · wide-column databases · inverted indexes · object storage · queues and retries
