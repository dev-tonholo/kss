---
name: unit-test-author
description: Create and update Kotlin unit tests for this repository with Kotlin Multiplatform conventions. Use when requests involve adding tests, fixing failing tests, improving coverage, or validating behavior in core, lexer, and parser modules, especially for parameterized scenarios with Burst and targeted Amper test execution.
---

# Unit Test Author

## Overview

Create focused, behavior-driven tests that follow this repository's testing
conventions. Use `kotlin.test` assertions, Burst for parameterized cases, and
run the smallest relevant test command first.

## Workflow

1. Read `.ai/guidelines.md` and the target module `AGENTS.md` before writing
   tests.
2. Locate the production code and existing nearby tests.
3. Mirror the package and source-set conventions:
    - Place tests in `test/` within the module directory.
    - Mirror the source package structure.
4. Write tests that verify observable behavior, not implementation details.
5. Prefer table-driven tests for many similar cases. Use Burst when it improves
   readability.
6. Run narrow test commands first, then broader checks if requested.
7. Keep assertions explicit and deterministic.

## Test Style Rules

- Use `kotlin.test` APIs (`@Test`, `assertEquals`, `assertContentEquals`,
  `assertTrue`, `assertNull`, `assertFailsWith`).
- Use descriptive backtick test names that state behavior and condition.
- Follow Arrange/Act/Assert structure with minimal setup.
- Keep one primary behavior per test.
- Avoid flaky inputs (time, random, unordered collections) unless explicitly
  controlled.
- Reuse existing helper methods/builders when available; avoid adding
  unnecessary abstractions.
- Prefer fakes over mocks unless the behavior is platform-specific.

## Parameterized Testing

- Use Burst for repeated input/output validation.
- Prefer compact datasets that still cover edge cases.
- Include boundary and malformed inputs when behavior defines fallbacks or
  errors.

See [references/kotlin-kmp-testing.md](references/kotlin-kmp-testing.md) for
patterns and command examples.

## Execution Commands

Use the smallest useful command first:

```bash
./amper test -m lexer                  # Test lexer module
./amper test -m parser                 # Test parser module
./amper test -m core                   # Test core module
./amper test -m demo/shared            # Test demo shared module
./amper test                           # Test all modules
```

## Completion Criteria

- New/changed behavior is covered by tests.
- Tests are deterministic and readable.
- Targeted test task passes.
- If broad verification is requested, run the broader test task and report
  results.
