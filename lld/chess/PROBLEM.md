# Chess

> LLD · Machine coding · ⏱ 90–120 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design a two-player chess game.

## Requirements
1. An 8×8 board set up in the standard starting position.
2. **Pieces:** King, Queen, Rook, Bishop, Knight, Pawn. Each piece type knows how it moves.
3. Two players (WHITE, BLACK) alternate turns; WHITE moves first.
4. **Move validation:**
   - the piece belongs to the current player
   - the move follows the piece's rules
   - the path is clear (except for the knight)
   - you can't capture your own piece
   - **the move must not leave your own king in check**
5. **Capturing** an opponent's piece.
6. **Game end:**
   - **checkmate** → the other player wins
   - **stalemate** → draw
   - a player can **resign**
7. Moves are entered in algebraic coordinates (e.g. `e2 e4`). Print the board after every move. Keep a **move history**.

## Bonus
- **Special moves:** castling, en passant, pawn promotion.
- **Undo** the last move.
- **Timers** per player (chess clock).
- Draw by threefold repetition or the 50-move rule.

## Constraints
- In-memory, console only. Drive the game from a fixed list of moves in `Main` (e.g. Scholar's Mate).

## Sample flow
```
e2 e4 ; e7 e5 ; f1 c4 ; b8 c6 ; d1 h5 ; g8 f6 ; h5 f7
→ Checkmate! WHITE wins.
a1 a5 at the start → Invalid: path blocked
```

## Follow-up questions
1. How did you avoid a giant `switch` on piece type? (Polymorphism / move strategies.)
2. How do you check "does this move leave my king in check" without copying the board each time?
3. How would you add undo (Command pattern / move objects with `undo()`)?
4. How would you make it an online game (with spectators and reconnection)? (See HLD: Online Chess.)
