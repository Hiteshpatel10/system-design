# Course Registration System

> LLD · Machine coding · ⏱ 90 min · ⚪ Bonus · Level 3 (Hard)

## Problem statement
Design a university course registration system where students register for courses each semester.

## Requirements
1. **Courses:** code, title, credits, prerequisites (other course codes). **Course offerings** per semester: course + instructor + schedule (day/time slots) + seat capacity.
2. **Students:** ID, name, program, completed courses with grades.
3. **Search** offerings by code, title, instructor or department.
4. **Register** for an offering. Reject if:
   - prerequisites aren't completed
   - the schedule clashes with another registered course
   - it would exceed the max credits per semester (e.g. 24)
   - the registration window is closed
5. **Seat limits:** when an offering is full, the student joins a **waitlist**. When someone drops, the first waitlisted student is registered automatically (if still eligible).
6. **Drop** a course before the deadline.
7. **Concurrency:** many students register at the same time when the window opens; seat counts must never exceed capacity.
8. View a student's timetable for the semester.

## Bonus
- **Priority registration** (final-year students first).
- **Instructor** view: class list, waitlist; can raise the capacity.
- **Notifications** when a waitlisted student is registered.
- Grades and GPA calculation.

## Constraints
- In-memory. Use threads in `Main` to simulate a registration rush for one popular course.

## Sample flow
```
CS301 (prereq CS201) capacity 2, Mon/Wed 10:00
Ravi (done CS201) registers CS301 → OK
Asha (no CS201) registers CS301   → Rejected: missing prerequisite CS201
Ben, Neha register CS301          → Ben OK (2/2), Neha WAITLISTED #1
Ravi drops CS301                  → Neha auto-registered, notified
Ben registers MA201 Mon 10:00     → Rejected: schedule clash with CS301
```

## Follow-up questions
1. How do you guarantee no over-registration when 500 students hit "register" in the same second?
2. Where do validation rules live, and how would you add a new rule (e.g. a department-only course)?
3. Is the waitlist promotion part of the drop transaction? What if the promoted student is no longer eligible?
4. How would this design change with a real database and 50,000 students?
