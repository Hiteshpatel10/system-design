# E-commerce (Amazon-like)

> LLD · Machine coding · ⏱ 90–120 min · 🟡 Should-do · Level 3 (Hard)

## Problem statement
Design the core of an online shopping platform: catalogue, cart, orders, inventory and payments.

## Requirements
1. **Catalogue:** products (ID, name, description, category, price, seller). Search by name/category; filter by price range; sort by price or rating.
2. **Inventory:** stock per product.
3. **Cart:** add/remove items, update quantity, view the total. A user has one cart.
4. **Checkout:**
   1. Validate stock for every item.
   2. **Reserve the inventory.**
   3. Take payment (simulated: card/UPI/COD).
   4. Create the order.
   - If payment fails, **release** the reserved inventory.
   - Two users buying the last unit at the same time: only one succeeds.
5. **Order lifecycle:** `PLACED → CONFIRMED → SHIPPED → DELIVERED`; `CANCELLED` before shipping (restock + refund); `RETURNED` within 7 days of delivery.
6. **Order history** per user.
7. **Product reviews:** only users who bought the product can review it (1–5 stars + text).

## Bonus
- **Coupons:** flat or percentage discounts, minimum cart value, expiry, per-user usage limits.
- **Wishlist**, and notifications when a wishlisted item comes back in stock.
- Multiple sellers for the same product, with the cheapest seller chosen by default.
- Order split into shipments from different warehouses.

## Constraints
- In-memory. Money as `BigDecimal`. Demonstrate the last-unit race with threads.

## Sample flow
```
P1 "iPhone" ₹80000 stock 1; P2 "Case" ₹999 stock 50
Ravi cart: P1×1, P2×2 → total ₹81998
Asha cart: P1×1
both checkout simultaneously → Ravi order O1 PLACED; Asha → Failed: P1 out of stock
apply coupon SAVE10 (10%, max ₹500) on Ravi's next order …
O1 → SHIPPED → DELIVERED; Ravi reviews P1 5★; Asha reviews P1 → Rejected: not purchased
```

## Follow-up questions
1. Where exactly do you lock during checkout, and for how long?
2. Payment succeeded but order creation crashed. How do you recover? (Idempotency, outbox, reconciliation.)
3. How would you add a new discount rule type without changing checkout code?
4. Which parts would become separate microservices in a real system?
