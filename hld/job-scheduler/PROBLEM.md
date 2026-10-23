# Design a Distributed Job Scheduler

> HLD · ⏱ 45 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a service that runs jobs at scheduled times (one-off or recurring, like a distributed cron) on a fleet of workers.

## Functional requirements
1. Create a job: what to run (a task type + payload, or a container image), when (now, at a time, or a cron expression).
2. Run jobs on worker machines; report status (scheduled, running, succeeded, failed) and logs.
3. **Retries** with backoff for failed jobs.
4. Cancel or pause jobs.
5. (Optional) Job dependencies (run B after A).

## Non-functional requirements
- Jobs start within ~a few seconds of their scheduled time.
- **Each scheduled run executes at least once**, and ideally not twice. Say what you guarantee.
- Survives scheduler and worker crashes.

## Scale (assume)
- 10M jobs scheduled per day; peaks of 10k jobs starting per second (e.g. on the hour).

## Deep-dive questions
1. How do you find due jobs efficiently? (An index on next-run time, time-bucketed partitions, a Redis sorted set.)
2. Several scheduler instances: how do you stop two of them picking the same job? (Leasing / `SELECT … FOR UPDATE SKIP LOCKED`, partition ownership.)
3. A worker dies mid-job. How is that detected (heartbeats, lease expiry) and the job retried?
4. Exactly-once is hard. How do idempotent jobs help?
5. How do you handle the "top of the hour" spike?
6. How do you store job history and logs?

## Concepts to revise
Leases and heartbeats · at-least-once delivery · idempotency · queues · database indexing on time · leader election
