# Food Ordering / Delivery (Swiggy / Zomato)

> LLD · Machine coding · ⏱ 90 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design a food-ordering system where customers order from restaurants and delivery partners deliver the food.

## Requirements
1. **Restaurants:** ID, name, location (x, y), menu items (name, price, available), rating, max orders it can process at once.
2. Restaurants can update their menu (add items, change prices, mark items unavailable).
3. **Customers** can search restaurants by name or dish, and see menus.
4. **Place an order:** customer + items (from one restaurant) + delivery address.
   - Optionally the system **auto-selects the restaurant** for a list of items, using a configurable strategy: lowest total cost, highest rating, or nearest.
   - Reject the order if the restaurant is at capacity or an item is unavailable.
5. **Order lifecycle:** `PLACED → ACCEPTED → PREPARING → READY → PICKED_UP → DELIVERED`; `CANCELLED` is allowed only before `PICKED_UP`.
6. **Assign a delivery partner:** the nearest available partner when the order is READY (or ACCEPTED).
7. When an order is delivered, the restaurant's capacity and the partner's availability are freed.
8. Customers can rate the order (restaurant and delivery partner).

## Bonus
- **Notifications** on each status change (customer, restaurant, partner).
- Pricing: delivery fee by distance, surge pricing, coupons.
- Order history per customer.
- Estimated delivery time.

## Constraints
- In-memory. Use simple Euclidean distance.

## Sample flow
```
R1 "Biryani House" (2,3) rating 4.5 capacity 2: Biryani ₹250, Kebab ₹180
R2 "Spice Hub"     (5,1) rating 4.1 capacity 5: Biryani ₹220
order C1 [Biryani x2] strategy=LOWEST_COST → R2, total ₹440, O1 PLACED
R2 accepts O1 → PREPARING → READY → partner D7 (nearest) assigned
D7 picks up → PICKED_UP; C1 cancels → Rejected: already picked up
D7 delivers → DELIVERED; R2 capacity freed; D7 available
```

## Follow-up questions
1. How did you model the order lifecycle: enum checks or the State pattern?
2. How would you plug in a new restaurant-selection strategy?
3. Two orders want the same nearest delivery partner at once. How do you avoid double assignment?
4. How would partner matching scale to 100k partners in a city? (Geohash/quadtree, covered in HLD.)
