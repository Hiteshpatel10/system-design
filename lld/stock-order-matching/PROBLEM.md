# Stock Brokerage / Order Matching Engine

> LLD · Machine coding · ⏱ 90–120 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design an order-matching engine for a stock exchange that matches buy and sell orders.

## Requirements
1. **Orders:** ID, user, stock symbol, side (BUY/SELL), type (LIMIT/MARKET), price (limit only), quantity, timestamp.
2. One **order book** per stock: the buy side sorted by highest price first, the sell side by lowest price first; **time priority** (FIFO) at the same price.
3. **Matching:**
   - a new BUY matches the lowest SELLs priced ≤ its limit; a SELL matches the highest BUYs priced ≥ its limit
   - trades execute at the **resting order's price**
   - **partial fills** are allowed; the remaining quantity rests in the book (LIMIT) or is cancelled (MARKET)
4. Every match creates a **trade** (buy order, sell order, price, quantity, time).
5. **Cancel** an open order and **modify** it (changing price or increasing quantity loses time priority).
6. **Order status:** OPEN, PARTIALLY_FILLED, FILLED, CANCELLED.
7. Show the **top 5 price levels** per side (market depth) and the last traded price.

## Bonus
- **Brokerage layer:** user accounts with cash and holdings; reject orders that the user can't afford or sell.
- **Stop-loss orders.**
- **Order expiry:** day orders cancelled at market close.
- Process orders for different stocks in parallel.

## Constraints
- In-memory. Matching must be deterministic: process orders for one stock in arrival order.

## Sample flow
```
SELL 100 INFY @1500 (A) ; SELL 50 INFY @1495 (B) ; BUY 120 INFY @1500 (C)
→ trade C×B 50 @1495 ; trade C×A 70 @1500 ; A remains SELL 30 @1500
BUY 10 INFY MARKET (D) → trade D×A 10 @1500
depth INFY → SELL: 1500×20 | BUY: —
```

## Follow-up questions
1. Which data structures did you use for the book (`TreeMap<price, Deque<Order>>`, priority queues)? What are the time complexities?
2. Why are orders for one stock processed on a single thread in real exchanges?
3. How would you recover the order book after a crash? (Event log / event sourcing.)
4. How do you keep latency in microseconds? (See HLD: Stock Exchange.)
