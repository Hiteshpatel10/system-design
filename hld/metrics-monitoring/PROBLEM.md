# Design Metrics Monitoring and Alerting (Datadog / Prometheus)

> HLD · ⏱ 45 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design a system that collects metrics (CPU, memory, request rate, error rate…) from thousands of servers, shows dashboards, and sends alerts.

## Functional requirements
1. Collect metrics from servers/services (e.g. every 10 seconds).
2. **Query** metrics over time ranges with aggregation (avg, max, p99) and grouping by tags (host, service, region).
3. **Dashboards** with graphs.
4. **Alert rules** (e.g. "error rate > 5% for 5 minutes") → notify by email/Slack/PagerDuty.

## Non-functional requirements
- Very write-heavy; ingest millions of data points/second.
- Recent data queries are fast; older data can be lower resolution.
- Alerts fire within about a minute.
- Data retained for 1 year.

## Scale (assume)
- 100k servers × 100 metrics × every 10 s → 1M data points/second.

## Deep-dive questions
1. Push (agents send) vs pull (Prometheus scrapes): trade-offs?
2. **Time-series database:** why not a regular relational database? How do TSDBs compress data (delta-of-delta, Gorilla)?
3. Ingestion pipeline: agents → Kafka → TSDB writers. Why a buffer queue?
4. **Downsampling and retention:** raw for 7 days, 1-minute rollups for 30 days, 1-hour rollups for a year.
5. **High cardinality** (too many unique tag combinations): why does it hurt, and how do you control it?
6. Alerting engine: how do you evaluate thousands of rules efficiently, and avoid alert storms?

## Concepts to revise
Time-series databases · compression · downsampling · Kafka buffering · cardinality · alert evaluation
