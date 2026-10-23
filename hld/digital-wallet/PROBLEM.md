# Design a Digital Wallet (Paytm / PayPal balance)

> HLD · ⏱ 45 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design the backend of a digital wallet: users hold a balance and transfer money to each other instantly.

## Functional requirements
1. Top up the wallet from a bank/card; withdraw to a bank.
2. **Transfer** money between wallets instantly.
3. View the balance and transaction history.

## Non-functional requirements
- **Correctness:** money is never created or lost; balances are never negative.
- Every transfer is atomic, and auditable.
- ~1M transfers/second at peak (festival sales); highly available.

## Deep-dive questions
1. Single database: a transfer is one transaction. Why doesn't this scale to 1M TPS?
2. Wallets are sharded across databases. How is a cross-shard transfer made atomic? Compare **2PC**, **TC/C** (try-confirm/cancel) and **Saga**.
3. **Event sourcing:** store commands/events instead of balances; rebuild state by replaying. How does this give auditability and recovery?
4. Idempotency for retried transfer requests.
5. Reconciliation with banks.
6. Hot wallets (e.g. a merchant receiving thousands of payments a second).

## Concepts to revise
Distributed transactions (2PC, TC/C, Saga) · event sourcing · idempotency · sharding · ledgers
