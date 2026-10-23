# Design a Web Crawler

> HLD · ⏱ 45 min · 🔴 Must-do · Level 2 (Medium)

## Problem statement
Design a crawler that downloads web pages across the internet (e.g. to build a search index).

## Functional requirements
1. Start from a list of **seed URLs**.
2. Download each page, extract its links, and add new links to the crawl.
3. Store the page content for later processing (indexing).
4. Re-crawl pages periodically to keep them fresh.

## Non-functional requirements
- **Politeness:** don't overload any website; obey `robots.txt`.
- **Scalable:** crawl billions of pages.
- **Robust:** handle bad HTML, slow servers, crawler traps (infinite URL spaces), and crashes without losing progress.
- Don't crawl the same page or the same content twice.

## Scale (assume)
- 1 billion pages per month; average page size 500 KB.

## Deep-dive questions
1. Draw the pipeline: URL frontier → fetcher → DNS resolver → parser → link extractor → URL filter/dedup → back to the frontier.
2. How does the **URL frontier** handle both priority (important pages first) and politeness (one host at a time, with delays)?
3. How do you detect duplicate URLs (normalisation + a seen-set / Bloom filter) and duplicate content (checksums/SimHash)?
4. How do you distribute the work across many crawler machines (partition by host)?
5. How do you avoid crawler traps?
6. Storage: where do the pages go (object storage), and where does the metadata go?

## Concepts to revise
Queues · Bloom filters · consistent hashing by host · DNS caching · checkpointing for fault tolerance
