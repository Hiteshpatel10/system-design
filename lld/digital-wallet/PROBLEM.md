# Digital Wallet (Paytm / PhonePe wallet)

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a digital wallet service where users hold money, top it up, and transfer it to other users.

## Requirements
1. **Create a wallet** for a user, with an initial balance of 0.
2. **Top up** from an external source (card/UPI/net banking, simulated).
3. **Transfer** money from one wallet to another:
   - the amount must be > 0
   - the sender must have a sufficient balance
   - the transfer must be **atomic**: both sides update or neither does
4. **Transaction history** per wallet: ID, type (CREDIT/DEBIT), amount, counterparty, time, status. Sort by time or amount; filter by type.
5. **Idempotency:** every request carries a `requestId`. Repeating the same request (e.g. a client retry) must not move money twice.
6. **Thread-safe:** concurrent transfers involving the same wallets must keep every balance correct, and **must not deadlock** (A→B and B→A at the same time).
7. **Statement:** balance + transactions between two dates.

## Bonus
- **Offers / cashback:** e.g. 10% cashback on the first top-up, or on transfers over ₹500.
- Withdraw to a bank account.
- Daily transfer limits; KYC levels with different limits.
- **Reversals / refunds.**

## Constraints
- Use `BigDecimal` or integer paise for money, never `double`.
- Demonstrate concurrent transfers with threads in `Main`, then show that the total money in the system is unchanged.

## Sample flow
```
create W1 (Ravi), W2 (Asha)
topup W1 1000 req=r1         → W1 1000
transfer W1→W2 300 req=r2    → W1 700, W2 300
transfer W1→W2 300 req=r2    → duplicate request: returns the same result, no change
transfer W2→W1 500 req=r3    → Failed: insufficient balance
history W1 → [CREDIT 1000 topup, DEBIT 300 to W2]
```

## Follow-up questions
1. How did you avoid deadlock when locking two wallets? (Lock ordering by wallet ID.)
2. Why keep a transaction ledger instead of just updating balances? Could you rebuild a balance from the ledger?
3. How does idempotency work if two retries arrive at exactly the same time?
4. In a distributed system with the wallets in different databases, how would a transfer stay consistent? (Saga, outbox pattern.)
