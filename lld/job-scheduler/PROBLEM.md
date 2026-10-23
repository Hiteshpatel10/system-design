# Task / Job Scheduler

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 3 (Hard) · Asked at Uber (SDE-2)

## Problem statement
Design and implement an in-process job scheduler that runs jobs concurrently on a thread pool.

## Requirements
1. **Submit a job:** an ID, a task (`Runnable`/`Callable`) and a schedule:
   - **run now**
   - **run once at / after a delay** (e.g. in 5 seconds)
   - **recurring** at a fixed interval (e.g. every 10 seconds)
2. Run jobs on a **thread pool** with at most **N concurrent jobs** (configurable). Extra due jobs wait.
3. **Dependencies:** a job can depend on other jobs (a DAG). It runs only after all of them **succeed**. If a dependency fails, the dependent job is marked SKIPPED/FAILED.
4. **Retries:** a failed job is retried up to K times with a delay between attempts.
5. **Status:** `PENDING → SCHEDULED → RUNNING → SUCCEEDED / FAILED`; also `CANCELLED`. Query by job ID.
6. **Cancel** a job that hasn't started (and stop future runs of a recurring job).
7. **Graceful shutdown:** stop accepting jobs, let running jobs finish.

## Bonus
- **Priorities:** higher-priority jobs run first when several are due.
- **Timeouts:** a job running longer than X is interrupted and marked FAILED.
- **Cron expressions** for recurring jobs.
- Detect **cycles** in dependencies at submit time.

## Constraints
- You may use `ExecutorService` for workers, but **write the scheduling logic yourself**: don't just wrap `ScheduledExecutorService`. (Hint: a `PriorityQueue`/`DelayQueue` ordered by next run time, plus a dispatcher thread.)
- `Main` must show concurrency: e.g. 6 jobs with pool size 2, printing start/end times and thread names.

## Sample flow
```
pool size 2
submit J1 (2s task), J2 (2s task), J3 (1s task)        → J1, J2 start; J3 waits
submit J4 depends on [J1, J3]
submit J5 every 3s
t=2  J1 ✓, J2 ✓ → J3 starts
t=3  J3 ✓ → J4 starts
submit J6 (always fails, retries=2) → FAILED after 3 attempts
cancel J5 → no more runs
```

## Follow-up questions
1. How does your dispatcher thread wait for the next due job without busy-waiting?
2. How do you handle a job that's due while all workers are busy?
3. How do you trigger dependent jobs when a job finishes (callbacks vs polling)?
4. What changes if jobs must survive a process restart, or run on many machines? (See HLD: Job Scheduler.)
