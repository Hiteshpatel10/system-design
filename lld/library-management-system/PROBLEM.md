# Library Management System

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 2 (Medium)

## Problem statement
Design a system for a library to manage books, members, loans and reservations.

## Requirements
1. **Books** (ISBN, title, authors, subject, publication year) and **book copies** (copy ID / barcode, rack location, status). One book can have many copies.
2. **Members** (ID, name, membership type) and **librarians**.
3. **Search** books by title, author, subject or ISBN; show how many copies are available.
4. **Issue a copy** to a member:
   - max 5 books at once
   - loan period 14 days
   - members with unpaid fines can't borrow
5. **Return a copy:** calculate the **fine** for late returns (e.g. ₹10/day, configurable per membership type).
6. **Reserve a book** when all copies are out. When a copy is returned, hold it for the first member in the reservation queue for 2 days.
7. **Renew** a loan once, if nobody has reserved the book.
8. Librarians can add/remove books and copies, and add/block members.

## Bonus
- **Notifications:** due-date reminders, "your reserved book is available".
- Different membership types (Student, Faculty) with different limits and fines.
- Lost/damaged book handling.
- Reports: most-borrowed books, overdue list.

## Constraints
- In-memory. Use an injectable clock so overdue fines can be demonstrated.

## Sample flow
```
add book "Clean Code" (2 copies: C1, C2)
issue C1 → Ravi (due 2026-11-06); issue C2 → Asha
reserve "Clean Code" → Ben (queue position 1)
Ravi returns C1 on 2026-11-09 → fine ₹30; C1 held for Ben until 2026-11-11
issue C1 → Neha → Rejected: reserved for Ben
issue C1 → Ben → OK
```

## Follow-up questions
1. Why separate `Book` from `BookCopy`? What would go wrong with a single class?
2. How does your fine calculation change for a new membership type without editing existing code?
3. Two librarians issue the last copy to two members at the same time. What happens?
4. How would you implement search efficiently for 1 million books?
