# Low-Level Design (LLD)

Each folder has a `PROBLEM.md` (the statement) and, once solved, a `README.md` (your design notes) and `src/` (your code).
See [../ROADMAP.md](../ROADMAP.md) for the order to solve them in.

## Rules for every LLD problem (machine-coding style)

1. **Time limit: 90 minutes** for working code. After that, stop and note what's missing.
2. **In-memory only.** No database, no UI, no frameworks. Plain Java.
3. **A `Main` class** that demonstrates every requirement (the "driver").
4. **Get the core requirements working first,** then the bonus ones.
5. **Code quality matters as much as working code:**
   - Clear entities and separation of concerns (models / services / strategies)
   - Interfaces where behaviour may change, so a new type or rule means a new class, not an edited `if-else`
   - Meaningful names, no god classes, no long methods
   - Handle invalid input with exceptions or clear messages
6. **After coding:** write `README.md` from `../templates/PROBLEM_TEMPLATE.md` (class diagram, patterns, trade-offs), then answer the follow-up questions in writing.

## Steps for each problem
1. Read the requirements; list your assumptions at the top of `README.md`.
2. List the entities (nouns) and actions (verbs).
3. Sketch a class diagram on paper (10 min max).
4. Code the models, then the services, then `Main`.
5. Run it, fix it, refactor it.
6. Answer the follow-ups.

## Compile and run
```bash
cd lld/<problem-name>
javac -d out $(find src -name "*.java")
java -cp out <packagename>.Main
```
