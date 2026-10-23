# Design S3-like Object Storage

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a highly durable object storage service like Amazon S3.

## Functional requirements
1. Create buckets; `PUT`, `GET`, `DELETE` objects by key (objects from bytes to TBs).
2. **List** objects by prefix.
3. **Multipart upload** for large objects.
4. (Optional) Versioning.

## Non-functional requirements
- Durability of **eleven nines** (99.999999999%); availability of 99.99%.
- Scales to exabytes of data and billions of objects.
- Cost-efficient storage.

## Deep-dive questions
1. **Separate metadata from data:** what does the metadata service store, and what do the data nodes store?
2. **Durability:** 3× replication vs **erasure coding** (e.g. 8+4). Trade-offs in storage cost and read latency.
3. How do data nodes store many small objects efficiently (pack into large files, with an index)?
4. How is the metadata (bucket + key → location) sharded and kept consistent?
5. Listing by prefix at scale.
6. Detecting and repairing corrupted or lost data (checksums, background scrubbing).

## Concepts to revise
Metadata/data separation · replication vs erasure coding · checksums · sharding · multipart upload
