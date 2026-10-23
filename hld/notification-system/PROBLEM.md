# Design a Notification System

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design a platform that lets internal services send notifications to users by push, SMS and email.

## Functional requirements
1. Internal services call an API: send a notification (user(s), template, channel(s), priority).
2. Channels: iOS/Android push (via APNs/FCM), SMS (via providers like Twilio), email (via SES/SendGrid).
3. Respect **user preferences** and opt-outs per channel and category.
4. Support **scheduled** notifications and **bulk** sends (e.g. a campaign to 10M users).
5. Track delivery status (sent, delivered, failed, opened if available).

## Non-functional requirements
- **No lost notifications**; duplicates should be rare.
- Critical notifications (OTP) delivered within seconds even during a big campaign.
- Third-party providers are slow and sometimes fail; the system must keep working.

## Scale (assume)
- 10M push, 1M SMS and 5M emails per day; campaign peaks of 1M notifications/min.

## Deep-dive questions
1. Draw the flow: API → validation/preferences → queue(s) → channel workers → provider. Why queues?
2. How do you keep OTPs fast while a 10M-user campaign is running? (Separate queues by priority.)
3. Retries and failures: backoff, dead-letter queue, switching to a backup provider.
4. How do you avoid sending the same notification twice? (Idempotency keys, deduplication.)
5. How do you rate-limit per user (no spam) and per provider (provider limits)?
6. How do you store templates, preferences and delivery logs? Which databases?

## Concepts to revise
Message queues and priorities · retries with backoff · DLQ · idempotency · third-party integration · rate limiting
