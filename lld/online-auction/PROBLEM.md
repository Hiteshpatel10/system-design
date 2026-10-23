# Online Auction System (eBay-style)

> LLD · Machine coding · ⏱ 90 min · ⚪ Bonus · Level 2 (Medium)

## Problem statement
Design an online auction system where sellers list items and buyers bid on them.

## Requirements
1. **Sellers** create an auction: item (name, description, category), starting price, minimum bid increment, start time, end time.
2. **Auction states:** `SCHEDULED → ACTIVE → ENDED`, or `CANCELLED` (allowed only before the first bid).
3. **Bidding:** a buyer places a bid on an ACTIVE auction. A valid bid must:
   - be at least current highest + increment (or ≥ the starting price for the first bid)
   - not come from the seller
4. **Concurrency:** many buyers bid at the same time; the highest valid bid must win and no valid bid may be lost.
5. **Notifications:**
   - the previous highest bidder is notified when outbid
   - all bidders are notified when the auction ends
6. When the auction ends, the winner is the highest bidder. If no bids came in, the auction ends unsold.
7. Search active auctions by category or keyword; view the bid history of an auction.

## Bonus
- **Reserve price:** a hidden minimum; if the highest bid is below it, the item isn't sold.
- **Auto-bid (proxy bidding):** a buyer sets a maximum and the system bids for them in increments.
- **Anti-sniping:** a bid in the last 2 minutes extends the end time by 2 minutes.
- Buy-it-now price.

## Constraints
- In-memory. Simulate concurrent bidders with threads.

## Sample flow
```
Seller S lists "Vintage watch", start ₹1000, increment ₹100, ends 18:00
17:00 Ravi bids 1000 → highest
17:05 Asha bids 1050 → Rejected: minimum 1100
17:06 Asha bids 1200 → highest; Ravi notified "outbid"
18:00 auction ENDED → winner Asha ₹1200; all bidders notified
```

## Follow-up questions
1. How did you make bid placement thread-safe? Lock per auction, or compare-and-set on the highest bid?
2. How do you end auctions exactly on time? A scheduler, or a check on every access?
3. Which pattern did you use for notifications?
4. How does proxy bidding interact with concurrent manual bids?
