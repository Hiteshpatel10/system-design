# Design Online Chess (chess.com / lichess)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design an online chess platform where players are matched by rating and play live games.

## Functional requirements
1. **Matchmaking:** find an opponent with a similar rating and time control.
2. **Live play:** moves are sent to the opponent in real time; the server validates moves and keeps the clocks.
3. Ratings updated after each game (Elo/Glicko).
4. (Optional) Spectators and game history.

## Non-functional requirements
- Move latency < 100 ms.
- The server is the source of truth (no cheating by modifying the client).
- Reconnection after a network drop without losing the game.

## Scale (assume)
- 10M daily active users; 1M concurrent games at peak.

## Deep-dive questions
1. Matchmaking: queues by rating band and time control; widening the band over time.
2. Game servers: WebSocket connections; why route both players of a game to the same server?
3. Clocks: server-side time vs client lag compensation.
4. Game state storage and recovery if a game server crashes.
5. Spectator fan-out for famous games.

## Concepts to revise
WebSockets · stateful routing · matchmaking queues · state recovery · fan-out
