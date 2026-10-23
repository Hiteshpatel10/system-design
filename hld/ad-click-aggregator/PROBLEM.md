# Design an Ad Click Aggregator

> HLD · ⏱ 45 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design a system that collects ad click events and aggregates them so advertisers can see click counts (and are billed correctly).

## Functional requirements
1. Users click ads → the click is recorded and the user is redirected to the advertiser's site.
2. Advertisers query **click counts per ad** over time ranges (per-minute granularity), filtered by country, device, etc.
3. Results are available within about a minute.

## Non-functional requirements
- **Accuracy matters** because it's used for billing: no lost clicks, no double counting.
- Handle **10k clicks/second** peak (and spikes).
- Fast queries for advertisers (< 1 s).
- Filter out fraudulent/duplicate clicks.

## Scale (assume)
- 1B clicks a day; 10M ads.

## Deep-dive questions
1. Draw the pipeline: click service → Kafka → stream processor (Flink) with **tumbling windows** per ad per minute → OLAP store.
2. Exactly-once counting: Kafka offsets + Flink checkpoints; idempotent writes to the sink.
3. Duplicate clicks: deduplicate by a click ID (generated server-side and signed).
4. Late events: watermarks and allowed lateness.
5. **Reconciliation:** a nightly batch job (Spark) recomputes from raw events to correct the stream results (Lambda/Kappa architecture).
6. Hot ads (one ad getting most clicks): how do you avoid hot partitions?
7. Storage for queries: OLAP databases (ClickHouse, Druid, Pinot) and pre-aggregation.

## Concepts to revise
Stream processing · windowing · watermarks · exactly-once · OLAP databases · Lambda vs Kappa architecture
