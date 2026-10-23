# Snake & Ladder

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 1 (Easy)

## Problem statement
Design the Snake & Ladder board game for multiple players.

## Requirements
1. Board of 100 cells (configurable size). Players start at 0 (off the board).
2. **Snakes** (head → tail, moves down) and **ladders** (start → end, moves up) are given as input at startup.
3. 2+ players take turns rolling a six-sided die and move forward by that number.
4. If a move would go beyond the last cell, the player stays where they are.
5. Landing on a snake head or a ladder start moves the player to the tail / end.
6. The first player to land **exactly** on the last cell wins. The game can continue to find 2nd, 3rd… places, or stop at the first winner (configurable).
7. Print every move: `Alice rolled 4 and moved from 12 to 16`, plus snake/ladder events.

## Bonus
- **Multiple dice** (sum of N dice).
- Rolling a 6 gives another turn; three 6s in a row cancels the turn.
- Special cells: e.g. a "crocodile" sends you back 5 cells, a "mine" skips your next turn.
- A **deterministic dice** option for tests (fixed sequence of rolls).

## Constraints
- Validate input: no snake and ladder on the same cell, no snake head on the last cell, no cycles.
- In-memory, console only.

## Sample flow
```
Board 100; snakes: 99→7, 54→34; ladders: 4→14, 40→79
Alice rolled 4: 0 → 4, climbed ladder to 14
Bob rolled 6: 0 → 6
...
Alice rolled 3: 96 → 99, bitten by snake to 7
...
Bob wins!
```

## Follow-up questions
1. How did you make the dice replaceable (random vs fixed)? Why does that help testing?
2. How would you add a new special cell type without editing the movement logic?
3. How would you check the board configuration for cycles (snake → ladder → snake …)?
4. Which class owns the turn order, and why?
