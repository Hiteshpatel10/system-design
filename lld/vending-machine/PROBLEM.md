# Vending Machine

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 1 (Easy)

## Problem statement
Design a vending machine that sells products, accepts coins and notes, and returns change.

## Requirements
1. The machine has **slots**. Each slot has a code (e.g. `A1`), a product (name, price) and a quantity.
2. The user can:
   - insert coins/notes (accepted denominations: 1, 2, 5, 10, 20, 50, 100)
   - select a product by slot code
   - cancel and get a refund of everything inserted
3. On selection:
   - if the slot is empty → show "Sold out" and keep the inserted money
   - if not enough money → show the amount still needed
   - otherwise → dispense the product and return the **change** using the fewest coins/notes possible
4. If exact change can't be given from the coins the machine holds, refuse the sale and refund.
5. **Admin operations:** restock a slot, add coins for change, collect the cash.
6. The machine must behave correctly depending on its current **state**: e.g. you can't select a product before inserting money, and you can't insert money while dispensing.

## Bonus
- Card payment as a second payment method.
- A coffee machine variant: a drink = a recipe of ingredients (milk, water, coffee beans); refuse the drink if any ingredient is short.
- A low-stock alert for the admin.

## Constraints
- In-memory. Model the machine as a **state machine**, not with `if (state == ...)` checks everywhere.

## Sample flow
```
Slots: A1 = Coke ₹25 (qty 2), A2 = Chips ₹20 (qty 0)
insert 10, insert 10      → balance 20
select A1                 → Insufficient funds: insert ₹5 more
insert 10                 → balance 30
select A1                 → Dispensed Coke, change ₹5 (1×5)
select A2 (balance 0)     → Insert money first
insert 20, select A2      → Sold out. Choose another product or cancel.
cancel                    → Refunded ₹20
```

## Follow-up questions
1. Draw your state diagram. Which states and transitions did you define?
2. Two users press buttons at the same time on a networked machine. What could go wrong?
3. Is your change-making algorithm (greedy) always optimal? When does it fail?
4. How would you add a "discount on Fridays" rule without changing the machine class?
