# Notification Service

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a notification library that other services use to send messages to users over different channels.

## Requirements
1. **Channels:** EMAIL, SMS, PUSH (simulate sending by printing).
2. API: `send(userId, templateId, params, channels?)`.
3. **Templates:** e.g. `ORDER_SHIPPED` = "Hi {name}, your order {orderId} has shipped." Fill in the placeholders from `params`.
4. **User preferences:** each user chooses which channels they want, per notification category (e.g. marketing: email only; transactional: all). Respect them unless the notification is marked critical.
5. **Priority:** HIGH (OTP, alerts) is sent before LOW (marketing). Use a queue processed by worker threads.
6. **Retries:** if a channel fails, retry with backoff (e.g. 3 attempts), then mark the notification FAILED.
7. **Status tracking:** QUEUED → SENT / FAILED per channel; queryable by notification ID.

## Bonus
- **Rate limit** per user (e.g. at most 5 marketing messages a day).
- **Quiet hours:** delay non-critical notifications at night.
- **Fallback channel:** if PUSH fails, send SMS.
- **Scheduled** notifications ("send at 9 am tomorrow").

## Constraints
- In-memory. Adding a new channel (e.g. WhatsApp) must not change existing classes.
- Simulate failures randomly (or with a flag) to show the retries.

## Sample flow
```
prefs u1: transactional=[EMAIL, PUSH], marketing=[EMAIL]
send(u1, OTP, {code:4821}, HIGH)                 → PUSH sent, EMAIL sent
send(u1, SALE, {pct:50}, LOW)                    → EMAIL only
send(u1, ORDER_SHIPPED, {...}) PUSH fails twice → retry 1, retry 2, sent on attempt 3
status N3 → PUSH: SENT (3 attempts), EMAIL: SENT
```

## Follow-up questions
1. Which patterns did you use for channels and for choosing channels (Strategy, Factory, Observer)?
2. How do you avoid sending the same notification twice when retrying? (Idempotency key.)
3. What happens to queued notifications if the service restarts?
4. How would this become a distributed system handling millions of notifications per day? (See HLD: Notification System.)
