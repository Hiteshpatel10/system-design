# Logging Framework

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 1 (Easy)

## Problem statement
Design a reusable logging library (like a mini Log4j / SLF4J) that applications can use to log messages.

## Requirements
1. Log levels in order: `DEBUG < INFO < WARN < ERROR < FATAL`.
2. API: `logger.debug(msg)`, `logger.info(msg)`, … and `logger.log(level, msg)`.
3. A logger has a **minimum level**. Messages below it are ignored.
4. Each message is written as: `timestamp [LEVEL] [loggerName] message`.
5. Multiple **destinations (appenders/sinks)**: console and file. One logger can write to several destinations.
6. Each destination can have its **own minimum level** (e.g. console gets everything ≥ DEBUG, the file only ≥ ERROR).
7. Configuration (levels, destinations, format) can be changed at runtime.
8. One shared logger configuration across the application.

## Bonus
- **Asynchronous logging:** the caller doesn't wait for file writes (use a queue and a background thread).
- Pluggable **formatters** (plain text, JSON).
- **Named loggers** in a hierarchy (`com.app.service` inherits config from `com.app`).
- Log file rotation by size.

## Constraints
- Must be **thread-safe**: many threads log at the same time, and lines must never interleave.
- Adding a new destination (e.g. a database or a remote server) must not require changing existing classes.

## Sample flow
```
config: logger level INFO; Console(≥DEBUG), File("app.log", ≥ERROR)
log.debug("cache miss")      → ignored (below INFO)
log.info("server started")   → console only
log.error("db down")         → console + app.log
```

## Follow-up questions
1. Which pattern lets a message pass through levels/handlers? (Chain of Responsibility vs a simple filter — which did you choose?)
2. How do you make the Singleton thread-safe? Compare eager init, synchronized, double-checked locking and the holder idiom.
3. Async logging: what happens to queued messages if the app crashes? If the queue is full?
4. How would you send logs to a central system (ELK / Datadog) without slowing the app down?
