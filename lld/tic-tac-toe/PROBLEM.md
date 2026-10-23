# Tic-Tac-Toe

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 1 (Easy)

## Problem statement
Design a Tic-Tac-Toe game that two players can play on the console.

## Requirements
1. Board size **N×N** (default 3), set when the game is created.
2. Two players, each with a name and a symbol (X / O). Players take turns.
3. A move is (row, column). Reject invalid moves (out of bounds, cell already filled) and let the same player try again.
4. After every move, check for a winner: a full row, column or either diagonal of the same symbol.
5. If the board is full with no winner, the game is a **draw**.
6. Print the board after every move, and print the result at the end.

## Bonus
- Support **more than 2 players** with custom symbols on a bigger board.
- **Undo** the last move.
- A simple **bot player** (random valid move) that can replace a human.
- Make the win check **O(1) per move** instead of scanning the board.

## Constraints
- In-memory, console only.
- Moves can come from a hard-coded list in `Main`, so the demo runs without typing input.

## Sample flow
```
Game 3x3: Alice (X) vs Bob (O)
Alice → (0,0)
Bob   → (1,1)
Alice → (0,1)
Bob   → (0,1)   → Invalid: cell occupied, try again
Bob   → (2,2)
Alice → (0,2)   → Alice wins! (row 0)
```

## Follow-up questions
1. How do you add a new win rule (e.g. "4 in a row on a 10×10 board") without changing the `Game` class?
2. How would you make the win check O(1) per move? (Hint: counters per row, column and diagonal.)
3. How would you turn this into an online multiplayer game? Which classes change?
4. Which design patterns did you use, and why?
