# Design ChatGPT / an LLM Chat Application

> HLD · ⏱ 45 min · 🟡 Should-do · Level 3 (Hard) · Newer interview topic (2025–26)

## Problem statement
Design a chat application where users talk to a large language model (LLM), like ChatGPT or Claude.

## Functional requirements
1. Users create conversations and send messages; the model's reply **streams back token by token**.
2. Conversation history is saved; users can reopen, rename and delete conversations.
3. The model sees the conversation history (within its **context window** limit).
4. Usage limits per plan (free vs paid: messages per hour, model access).
5. (Optional) File uploads and retrieval over documents (RAG).

## Non-functional requirements
- Time to first token < 1–2 seconds; smooth streaming.
- **GPU capacity is expensive and limited**: use it efficiently and degrade gracefully under load.
- Highly available; conversations never lost.

## Scale (assume)
- 50M daily active users; 20 messages each per day; average reply 500 tokens.

## Deep-dive questions
1. **Streaming:** SSE vs WebSockets for token streaming. What happens if the client disconnects mid-reply?
2. **Inference serving:** a request queue in front of a GPU fleet; batching requests per GPU; routing by model. How do you handle a spike that exceeds GPU capacity (queueing, rate limits, fallback to a smaller model)?
3. **Context management:** history longer than the context window → truncation or summarisation. Where is the history stored, and how is it fetched quickly?
4. **Rate limiting and quotas** by tokens, not just requests.
5. Caching: prompt caching (reusing work for a shared prefix), and when caching responses is (not) safe.
6. Safety and abuse: content moderation before and after the model; prompt-injection risks with RAG.
7. Data model for users, conversations and messages; which databases?

## Concepts to revise
SSE · queues and backpressure · GPU batching · token-based rate limiting · caching · RAG basics (embeddings, vector DB)
