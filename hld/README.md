# High-Level Design (HLD)

Each folder has a `PROBLEM.md` (the statement). Write your solution in that folder's `README.md`.
See [../ROADMAP.md](../ROADMAP.md) for the order to solve them in.
Study plans for the fundamentals are in [fundamentals/](fundamentals/), starting with [STAGE1_STUDY_PLAN.md](fundamentals/STAGE1_STUDY_PLAN.md).

## Rules for every HLD problem

**Time limit: 45 minutes**, on paper or a whiteboard, as if explaining to an interviewer. Write it up afterwards.

## Framework (use the same order every time)

| Step | Time | What to produce |
|---|---|---|
| 1. Requirements | ~5 min | Functional (top 3–4 features) and non-functional (scale, latency, availability vs consistency) |
| 2. Estimates | ~3 min | Daily active users, QPS (read and write), storage per year, bandwidth. Only what affects the design. |
| 3. Core entities | ~2 min | The main data objects |
| 4. API | ~5 min | Endpoints with request and response |
| 5. High-level design | ~10–15 min | Boxes and arrows: clients, load balancer, services, databases, cache, queues. Walk through each functional requirement. |
| 6. Deep dives | ~10–15 min | Use the problem's deep-dive questions: bottlenecks, scaling, failure handling, trade-offs |

## What to write in README.md afterwards
- Requirements and estimates
- API and data model (tables/collections with key fields)
- Architecture diagram (Mermaid `flowchart`)
- Answers to every deep-dive question, with **trade-offs** ("I chose X over Y because…")
- What you missed compared with a reference solution (Hello Interview, Alex Xu, etc.)

## Numbers worth memorising
- 1 day ≈ 86,400 s ≈ 10⁵ s → 1M requests/day ≈ 12 QPS
- Read from memory ~100 ns · SSD ~100 µs · disk seek ~10 ms · same-datacenter round trip ~0.5 ms · cross-continent ~150 ms
- A single well-tuned Postgres node: ~10k simple queries/s · Redis node: ~100k ops/s · Kafka partition: ~10 MB/s
