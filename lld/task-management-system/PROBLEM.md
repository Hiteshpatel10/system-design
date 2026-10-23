# Task Management System (Jira-lite)

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 1 (Easy)

## Problem statement
Design a simple task tracker where users create tasks, assign them and move them through a workflow.

## Requirements
1. **Users** (ID, name). **Tasks**: ID, title, description, priority (LOW/MEDIUM/HIGH), due date, assignee, reporter, status, created/updated time.
2. Create, update and delete a task. Assign or reassign a task to a user.
3. **Status workflow:** `TODO → IN_PROGRESS → IN_REVIEW → DONE`; also `IN_REVIEW → IN_PROGRESS` (rework). Reject any other transition.
4. **Search/filter** tasks by assignee, status, priority, and due before a date. Sort by priority or due date.
5. **Comments** on a task (author, text, time).
6. **History:** every change to a task is recorded (who changed what, when).

## Bonus
- **Subtasks:** a task can't be DONE while any subtask is open.
- **Sprints:** group tasks into a sprint with start/end dates; show the sprint's progress.
- **Notifications** to the assignee when a task is assigned or changes status.
- Configurable workflows per task type (Bug vs Story have different statuses).

## Constraints
- In-memory. The workflow rules must be easy to change without editing the `Task` class.

## Sample flow
```
create task T1 "Fix login bug" HIGH due 2026-11-01 by Ravi
assign T1 → Asha
move T1 TODO → DONE            → Invalid transition
move T1 TODO → IN_PROGRESS     → OK
comment T1 by Asha "Root cause found"
filter assignee=Asha status=IN_PROGRESS → [T1]
history T1 → created, assigned to Asha, status TODO→IN_PROGRESS, comment added
```

## Follow-up questions
1. How did you model the workflow — enum with allowed transitions, State pattern, or a config table? Trade-offs?
2. Two users update the same task at the same time. How do you avoid lost updates?
3. Filtering scans all tasks. How would you index them for fast search?
4. How would notifications work without the `Task` class knowing about email/SMS?
