# CricInfo (Live Cricket Scoring)

> LLD · Machine coding · ⏱ 90–120 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a live cricket scoring system like Cricinfo or Cricbuzz.

## Requirements
1. **Teams** and **players** (batter/bowler/all-rounder/keeper). **Matches** between two teams, with a format: T20 (20 overs), ODI (50), Test.
2. **Toss** and the choice to bat or bowl. Each team picks a playing XI.
3. **Ball-by-ball scoring:** for each ball, record the bowler, the batter facing, runs, and extras (wide, no-ball, bye, leg-bye). Wides and no-balls don't count as legal deliveries.
4. **Wickets:** bowled, caught, LBW, run out, stumped. A new batter comes in.
5. **Strike rotation:** odd runs swap the strike; batters swap ends at the end of each over.
6. **Live scorecard:**
   - team score/wickets, overs (e.g. 12.3), run rate
   - batting card: runs, balls, 4s, 6s, strike rate
   - bowling card: overs, maidens, runs, wickets, economy
7. **Innings end:** all out, overs completed, or target chased. The match result is calculated ("India won by 5 wickets").
8. **Live commentary feed:** a text line per ball.

## Bonus
- **Subscribers** receive live updates (Observer): each ball, each wicket, the match result.
- **Stats:** a player's career runs/wickets across matches.
- **Tournaments** with points tables and net run rate.
- Rain interruption / DLS (just the hooks).

## Constraints
- In-memory. Feed balls from a scripted list in `Main`.

## Sample flow
```
IND vs AUS, T20; IND won the toss and chose to bat
0.1 Starc to Rohit, FOUR → 4/0
0.2 Starc to Rohit, 1 run → Gill on strike
0.3 Starc to Gill, WIDE → 6/0
0.3 Starc to Gill, OUT caught → 6/1 (0.3)
scorecard → Rohit 5(2), Gill 0(1); Starc 0.3-0-6-1
```

## Follow-up questions
1. How did you model a ball and its outcomes so new extras/dismissal types are easy to add?
2. Is the scorecard stored or derived from the list of balls? Trade-offs?
3. How do you push live updates to millions of fans? (See HLD: Live Comments.)
4. How would you correct a scoring mistake made 3 balls ago?
