# ATM

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design the software for an ATM that lets bank customers withdraw cash, deposit money and check their balance.

## Requirements
1. **Session flow:** insert card → enter PIN → choose a transaction → eject card.
2. **PIN check:** 3 wrong attempts → the card is blocked and kept by the machine.
3. **Transactions:**
   - balance enquiry
   - cash withdrawal
   - cash deposit
   - change PIN
4. **Withdrawal:**
   - check the account balance and a daily withdrawal limit (e.g. ₹25,000)
   - dispense using the available notes (₹2000, ₹500, ₹200, ₹100) with the **fewest notes**
   - if the exact amount can't be made from the notes in the machine, reject before debiting the account
5. The ATM tracks its **cash inventory** per denomination.
6. The machine's behaviour depends on its **state**: IDLE, CARD_INSERTED, AUTHENTICATED, TRANSACTION_IN_PROGRESS, OUT_OF_SERVICE. Invalid actions in a state are rejected (e.g. withdrawing before entering the PIN).
7. Print a receipt for each transaction.

## Bonus
- **Admin:** refill cash, take the ATM out of service.
- **Fund transfer** between accounts.
- Multiple banks: the ATM talks to the bank through an interface (another bank's card → network fee).
- Mini-statement (last 5 transactions).

## Constraints
- In-memory. Simulate the bank with an in-memory `BankService`.

## Sample flow
```
ATM cash: 2000×2, 500×5, 100×10
insert card 1234-XXXX; enter PIN 0000 → wrong (2 left); enter PIN 1111 → OK
withdraw 3700 → 2000×1, 500×3, 100×2 dispensed; balance 46300
withdraw 7000 → Rejected: ATM can't dispense this amount
eject card → IDLE
```

## Follow-up questions
1. Draw your state diagram. Where did the State pattern help, and where was it overkill?
2. Note dispensing as a **Chain of Responsibility** (2000 handler → 500 handler → …): what are its pros and cons compared with a loop?
3. The account is debited, then the dispenser jams. How do you keep the system consistent?
4. Two cards on the same account withdraw at the same time from two ATMs. What prevents overdraw?
