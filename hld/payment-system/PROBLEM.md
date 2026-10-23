# Design a Payment System (Stripe / Amazon checkout payments)

> HLD · ⏱ 45 min · 🔴 Must-do · Level 3 (Hard)

## Problem statement
Design the payment backend for an e-commerce site: take money from buyers using external payment providers and pay out to sellers.

## Functional requirements
1. **Pay-in:** when a user places an order, charge their card/UPI through a payment service provider (PSP like Stripe, Razorpay, Adyen).
2. **Pay-out:** pay sellers their share periodically.
3. Refunds.
4. Payment status visible to the user and the order service.

## Non-functional requirements
- **Correctness above everything:** never charge twice, never lose a payment.
- Every money movement is **auditable**.
- PSPs are slow and unreliable; the system must handle timeouts and retries safely.
- Throughput is modest (~1M transactions/day); reliability matters more than speed.

## Scale (assume)
- 1M transactions a day; ~10 transactions/second, with peaks of 100 TPS.

## Deep-dive questions
1. Draw the flow: order → payment service → PSP (hosted payment page) → webhook back → update status → notify the order service.
2. **Idempotency:** how do idempotency keys prevent double charges when the client or a service retries?
3. **Ledger:** what is double-entry bookkeeping, and why is it append-only?
4. PSP call timed out: did the charge go through? How do you find out (status query, webhooks, retries with the same key)?
5. **Reconciliation:** comparing your records with the PSP's daily settlement file. What do you do about mismatches?
6. Distributed consistency between the order, payment, wallet and ledger services (Saga, outbox pattern, exactly-once processing).

## Concepts to revise
Idempotency · double-entry ledger · webhooks · retries · reconciliation · Saga / outbox pattern
