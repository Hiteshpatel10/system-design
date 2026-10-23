# Design Google Docs (Real-time Collaborative Editing)

> HLD · ⏱ 45 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design a document editor where several people edit the same document at the same time and see each other's changes live.

## Functional requirements
1. Create, open and edit documents (text).
2. **Concurrent editing:** many users edit the same document; everyone converges to the same content.
3. See other editors' **cursors/presence**.
4. **Version history.**
5. Share documents with view/comment/edit permissions.

## Non-functional requirements
- Edits appear to others within ~100–200 ms.
- **Consistency:** all users eventually see the same document, without losing anyone's edits.
- Durable: no edits lost on crash.
- Up to ~100 concurrent editors per document; millions of documents.

## Deep-dive questions
1. Why does "last write wins" fail for text? Show an example where two inserts conflict.
2. **Operational Transformation (OT)** vs **CRDTs**: how does each resolve concurrent edits? Which would you choose and why?
3. Architecture: clients ↔ WebSocket ↔ document service. Why route all editors of one document to the **same server**? How do you do that (consistent hashing by document ID)?
4. Storage: operation log + periodic snapshots. How do you load a document quickly?
5. Offline editing and reconnection.
6. How do cursor positions stay correct while text changes?

## Concepts to revise
OT · CRDTs · WebSockets · stateful routing · event sourcing (op log + snapshots)
