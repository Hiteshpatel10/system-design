# Elevator System

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design the control system for a building with several elevators.

## Requirements
1. A building has **N floors** and **M elevators** (configurable).
2. **External requests:** a person on a floor presses UP or DOWN.
3. **Internal requests:** a person inside an elevator presses a destination floor.
4. A **dispatcher** chooses which elevator serves each external request.
5. Each elevator has a current floor, a direction (UP/DOWN/IDLE) and a door state (OPEN/CLOSED). It serves stops in its current direction before reversing (the SCAN / "elevator" algorithm).
6. Simulate movement with time steps: each step an elevator moves one floor or opens/closes its doors. Print the state of all elevators each step.
7. Each elevator has a capacity (max people or weight). If it's full, it skips external pickups.

## Bonus
- **Pluggable dispatch strategies:** nearest idle elevator, least busy, same direction first.
- **Maintenance mode:** take an elevator out of service.
- **Emergency stop** and a fire alarm mode (all elevators go to the ground floor).
- Floors served only by some elevators (e.g. express elevators).

## Constraints
- In-memory simulation driven by a `step()` or tick loop.

## Sample flow
```
Building: 10 floors, 2 elevators (E1 at 0, E2 at 0)
External: floor 5 UP   → assigned E1
External: floor 3 DOWN → assigned E2
step 1: E1 at 1 ↑ | E2 at 1 ↑
...
step 5: E1 at 5, doors OPEN
Internal E1: floor 8
...
step 9: E1 at 8, doors OPEN | E2 idle at 3
```

## Follow-up questions
1. Which states does an elevator have? Did you use the State pattern? Why or why not?
2. How do you avoid starvation (a request waiting forever)?
3. How do you keep two elevators from being sent to the same request?
4. How would you add a new dispatch strategy at runtime?
