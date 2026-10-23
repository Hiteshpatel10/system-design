# Traffic Signal Control System

> LLD · Machine coding · ⏱ 90 min · ⚪ Bonus · Level 1 (Easy)

## Problem statement
Design the controller for traffic lights at a road intersection.

## Requirements
1. An intersection has 4 directions (N, S, E, W). Each direction has a signal: RED, GREEN or YELLOW.
2. Each phase has a configurable duration (e.g. GREEN 30s, YELLOW 5s).
3. Safety rule: directions that cross (N/S vs E/W) must **never** be GREEN at the same time.
4. Signals cycle automatically: N/S GREEN → N/S YELLOW → all RED → E/W GREEN → E/W YELLOW → all RED → …
5. **Manual override:** a traffic officer can force a direction to GREEN (the others switch safely through YELLOW → RED).
6. **Emergency vehicle mode:** clear one direction (GREEN) for an ambulance, then return to the normal cycle.
7. Log every signal change with a timestamp.

## Bonus
- Different timings for peak vs off-peak hours.
- Pedestrian crossing buttons that shorten the current GREEN.
- Several intersections managed by a central controller.
- **Adaptive timing** based on vehicle counts from sensors.

## Constraints
- Simulate time (e.g. a tick method or `Thread.sleep` with short durations) so the demo finishes in seconds.

## Sample flow
```
t=0   N/S GREEN, E/W RED
t=30  N/S YELLOW
t=35  ALL RED
t=37  E/W GREEN, N/S RED
t=40  EMERGENCY from N → E/W YELLOW → ALL RED → N/S GREEN
t=60  emergency cleared → resume normal cycle
```

## Follow-up questions
1. Which pattern models the signal phases? Draw the state diagram.
2. How do you guarantee the safety rule even when an override and the timer fire at the same time?
3. How would adaptive timing plug in without changing the controller?
4. How would you test this without waiting real seconds?
