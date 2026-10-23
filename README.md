# system-design

My notes and Java solutions while learning system design.

📍 **[ORDER.md](ORDER.md)**: start here. The step-by-step order to follow.  
📋 **[ROADMAP.md](ROADMAP.md)**: every topic and problem with priorities, what each one tests, and sources.

## Structure

```
lld/          Low-level design: Java code (classes, design patterns)
hld/          High-level design: architecture notes and diagrams
concepts/     Reusable notes: design patterns, SOLID, etc.
templates/    PROBLEM_TEMPLATE.md, copy it when starting a new problem
```

Every problem folder has a `PROBLEM.md`, the problem statement, so you can work offline.
Rules for each type: [lld/README.md](lld/README.md) (90-minute machine coding) · [hld/README.md](hld/README.md) (45-minute design framework).

```
lld/<problem-name>/
  PROBLEM.md                 the statement (given)
  README.md                  your design notes (copy templates/PROBLEM_TEMPLATE.md)
  src/<problemname>/...      your code, in its own top-level package
hld/<problem-name>/
  PROBLEM.md                 the statement (given)
  README.md                  your design write-up
```

## Solving a problem

1. Pick the next step from [ORDER.md](ORDER.md) and read its `PROBLEM.md`
2. LLD: write code in `lld/<problem-name>/src/<problemname>/` (folder names can use hyphens, package names can't)
3. Write `README.md` in the same folder
4. Mark it ✅ in the roadmap and add a row to the table below

## Running a problem

```bash
cd lld/<problem-name>
javac -d out $(find src -name "*.java")
java -cp out <problemname>.Main
```

## Problems

### Low-Level Design

| # | Problem | Patterns | Status |
|---|---------|----------|--------|
| 1 | [Parking Lot](lld/parking-lot) | Singleton, Strategy, Factory | ✅ |

### High-Level Design

| # | Problem | Key Topics | Status |
|---|---------|------------|--------|
|   |         |            |        |
