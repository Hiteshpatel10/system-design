# Stack Overflow (Q&A Platform)

> LLD · Machine coding · ⏱ 90 min · 🟡 Should-do · Level 1 (Easy)

## Problem statement
Design the core of a Q&A site like Stack Overflow.

## Requirements
1. Users can **post questions** (title, body, tags) and **answers** to questions.
2. Users can **comment** on questions and answers.
3. Users can **upvote/downvote** questions and answers. One vote per user per post; a user can change or remove their vote. Users can't vote on their own posts.
4. The question's author can **accept** one answer.
5. **Reputation:**
   - question upvote +5, answer upvote +10, accepted answer +15
   - downvote −2 for the author
   - undoing a vote reverts its effect
6. **Search** questions by keyword (in title/body) and by tag.
7. Show a question with its answers sorted: accepted answer first, then by score.

## Bonus
- **Badges** (e.g. "Nice Question" at score ≥ 10).
- Privileges by reputation: e.g. downvoting needs ≥ 125 reputation.
- **Bounties** on questions.
- Close/reopen questions as duplicates.

## Constraints
- In-memory. Reputation rules should be changeable without editing the voting code.

## Sample flow
```
Ravi asks Q1 "How does HashMap work?" [java, collections]
Asha answers A1 on Q1; Ben answers A2 on Q1
Ben upvotes Q1            → Ravi rep +5
Ravi upvotes A1           → Asha rep +10
Asha upvotes A1           → Rejected: can't vote on own post
Ravi accepts A1           → Asha rep +15
search tag=java           → [Q1]
view Q1                   → A1 (accepted, score 1), A2 (score 0)
```

## Follow-up questions
1. Questions and answers share votes and comments. How did you model this: inheritance, interface or composition?
2. How do you make the vote + reputation update atomic if two users vote at once?
3. How would you add a new reputation event without touching existing classes?
4. Keyword search is a linear scan. What would you use in production?
