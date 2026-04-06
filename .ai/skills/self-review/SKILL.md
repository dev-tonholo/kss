---
name: self-review
description: Perform a thorough self-review of pending changes before committing or opening a PR. Checks code quality, adherence to project conventions, test coverage, and static analysis. Use after completing a task and before requesting commit or PR creation.
---

# Self-Review

## Overview

Systematically review all pending changes against this repository's standards
before they are committed. Catch issues early — style violations, missing tests,
Detekt/ktlint failures — so that PRs pass CI on the first try.

## Workflow

1. **Read context** — Load `.ai/guidelines.md` and the `AGENTS.md` for every
   module touched by the changes.
2. **Enumerate changes** — Run `git diff` (staged + unstaged) and `git status`
   to build a complete picture of what changed.
3. **Review each file** against the checklist below.
4. **Run verification commands** to confirm nothing is broken.
5. **Report findings** with actionable items, grouped by severity.

## Review Checklist

### Code Quality

- [ ] No `java.io` or `java.nio` imports in common source sets.
- [ ] No new global state or singletons that could cause issues on native targets.
- [ ] Max line length is 120 characters.
- [ ] No `TODO`/`FIXME`/`STOPSHIP` without a linked GitHub issue.
- [ ] No hardcoded secrets, credentials, or local paths.

### Architecture

- [ ] Core logic stays in common source sets; platform code in `src@<platform>/`.
- [ ] Module configuration uses Amper `module.yaml`, not duplicated across
  modules.
- [ ] Dependencies added to `libs.versions.toml`, not inlined.
- [ ] New dependencies support all targets: `jvm`, `macosArm64`, `macosX64`,
  `linuxX64`, `js`, `wasmJs`.

### Style & Conventions

- [ ] Follows `kotlin.code.style=official`.
- [ ] Make sure you followed SOLID principles.
- [ ] Make sure you followed DRY principle.
- [ ] Make sure you followed KISS principle.
- [ ] Make sure you followed YAGNI principle.
- [ ] Changes are minimal and focused — no unrelated refactors, no added
  docstrings/comments on unchanged code.
- [ ] Public APIs you introduced must have KDocs.
- [ ] No unnecessary abstractions or over-engineering.
- [ ] Commit message follows Conventional Commits: `<type>(<scope>): <subject>`.
- [ ] Branch name follows `<type>/<short-topic>` convention.

### Testing

- [ ] New or changed behavior has corresponding tests.
- [ ] Tests are in `test/` directories and mirror the source package structure.
- [ ] Tests use `kotlin.test` APIs with descriptive backtick names.
- [ ] Parameterized tests use Burst where appropriate.
- [ ] No flaky patterns (time-dependent, random, unordered collection assertions).

### Static Analysis

- [ ] `./scripts/check.sh` passes (both ktlint and Detekt).
- [ ] Cyclomatic complexity, method length, and parameter count are within
  thresholds.
- [ ] Compose rules pass for any Composable functions modified.

## Verification Commands

Run these in order, stopping at the first failure to fix before proceeding:

```bash
# 1. Static analysis (ktlint + detekt)
./scripts/check.sh

# 2. Build
./amper build

# 3. Tests
./amper test
```

## Severity Levels

- **Blocker** — Will fail CI or break functionality. Must fix before commit.
  Examples: Detekt failure, test failure, `java.io` in common code.
- **Warning** — Won't fail CI but degrades quality. Should fix before PR.
  Examples: Missing test for new branch, overly complex method.
- **Nit** — Minor style or readability preference. Fix if convenient.
  Examples: Slightly long line, verbose variable name.

## Report Format

```
## Self-Review Report

### Summary
<one-line summary of changes reviewed>

### Blockers
- [ ] <description and file:line>

### Warnings
- [ ] <description and file:line>

### Nits
- [ ] <description and file:line>

### Verification Results
- Static analysis: PASS/FAIL
- Build: PASS/FAIL
- Tests: PASS/FAIL
```

## Completion Criteria

- All checklist items verified against the diff.
- All verification commands executed and passing.
- Report delivered with zero blockers remaining.
