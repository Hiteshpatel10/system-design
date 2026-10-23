# Splitwise (Expense Sharing)

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design an expense-sharing app like Splitwise that tracks who owes whom.

## Requirements
1. **Users** (ID, name, email). **Groups** with members.
2. **Add an expense:** paid by one user, amount, description, participants, split type.
3. **Split types:**
   - `EQUAL`: divide equally. Handle rounding so the shares add up exactly (e.g. 100 / 3 = 33.34 + 33.33 + 33.33).
   - `EXACT`: given amounts per person; they must add up to the total.
   - `PERCENT`: given percentages; they must add up to 100.
4. **Balances:**
   - show all balances for a user ("You owe Asha ₹250", "Ben owes you ₹100")
   - show all balances in a group
5. **Settle up:** a user records a payment to another user, which reduces the balance.
6. Reject invalid input with a clear message (exact amounts don't add up, percentages ≠ 100, payer not in group, …).

## Bonus
- **Simplify debts:** minimise the number of transactions within a group (A→B ₹50, B→C ₹50 becomes A→C ₹50).
- `SHARE` split (by ratio, e.g. 2:1:1).
- **Expense history** per group/user; edit or delete an expense (balances update).
- Multiple currencies.

## Constraints
- In-memory. Use `BigDecimal` or integer paise for money, **never `double`**.
- Adding a new split type must not change the `Expense` or balance code.

## Sample flow
```
users: u1 Ravi, u2 Asha, u3 Ben, u4 Neha
EXPENSE u1 1000 EQUAL u1 u2 u3 u4         → each owes Ravi 250
EXPENSE u1 1250 EXACT u2=370 u3=880
EXPENSE u4 1200 PERCENT u1=40 u2=20 u3=20 u4=20
SHOW u2    → Asha owes Ravi 620, Asha owes Neha 240
SHOW       → Asha owes Ravi 620 | Ben owes Ravi 1130 | Ravi owes Neha 480 | …
SETTLE u2 → u1 620
SHOW u2    → Asha owes Neha 240
```

## Follow-up questions
1. How do you store balances: a matrix, a map of pairs, or a net amount per user? Trade-offs?
2. Explain your debt-simplification algorithm. Is a minimum number of transactions always achievable greedily?
3. How do you handle rounding so that no paise are lost?
4. Two people add expenses to the same group at the same time. What needs locking?
