# Design a Stock Exchange / Trading Platform (NSE / Robinhood)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design an electronic stock exchange that accepts buy/sell orders and matches them, plus market data for traders. (Robinhood variant: a broker app that sends orders to exchanges and shows live prices.)

## Functional requirements
1. Place, cancel and modify **limit** and **market** orders.
2. **Match** orders by price-time priority; publish trades.
3. Publish **market data**: order book depth, last price, candlestick charts.
4. Risk checks before accepting orders (enough cash/shares, order limits).

## Non-functional requirements
- **Very low latency** (microseconds to low milliseconds for matching).
- **Deterministic, fair** ordering; strong consistency.
- Never lose an accepted order; fast recovery after failure.

## Scale (assume)
- 100 symbols with heavy trading; 1B orders a day; peaks at market open.

## Deep-dive questions
1. Why is the matching engine for one symbol usually **single-threaded** and in memory?
2. Order flow: gateway → risk checks → **sequencer** (assigns order numbers) → matching engine → market data / reporting.
3. **Event sourcing** and the sequencer: how do you recover the order book after a crash? How does a hot standby take over?
4. How do you distribute market data to thousands of subscribers quickly (multicast, ring buffers)?
5. Robinhood variant: live price fan-out to millions of app users (WebSockets, throttling updates).

## Concepts to revise
In-memory processing · event sourcing · sequencers · hot standby / replication · low-latency messaging · fan-out
