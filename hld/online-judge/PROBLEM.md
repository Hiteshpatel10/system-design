# Design LeetCode (Online Judge)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design an online coding platform where users solve problems, submit code and get results, including during live contests.

## Functional requirements
1. Browse problems; view a problem statement.
2. **Submit code** in several languages → it's run against hidden test cases → result (Accepted, Wrong Answer, TLE, Runtime Error).
3. **Contests** with a live **leaderboard**.

## Non-functional requirements
- **Security:** user code must not harm the system or see other users' data.
- Results within a few seconds normally.
- Handle contest spikes (100k users submitting in the same minutes).

## Deep-dive questions
1. **Sandboxing:** containers (Docker) vs microVMs (Firecracker) vs gVisor; limits on CPU, memory, time, network.
2. Submission flow: API → queue → worker pool → result store → client polls or gets pushed.
3. Scaling workers for contest spikes (autoscaling, pre-warming).
4. Live leaderboard (Redis sorted sets; see Top-K / Leaderboard).
5. Storing test cases and making them available to workers.

## Concepts to revise
Sandboxing · job queues · autoscaling · Redis sorted sets · polling vs push
