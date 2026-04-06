# KSS — Claude Code Context

CRITICAL: MUST read and follow `.ai/guidelines.md` at all times. It contains
code style, architecture, testing, and commit conventions for this repository.

Read `AGENTS.md` for repository overview, module map, verification workflow, and
skills.

## Quick Reference

```bash
# Build
./amper build                              # Build everything
./amper build -m core                      # Build core module only
./amper build -m lexer                     # Build lexer module only
./amper build -m parser                    # Build parser module only

# Test
./amper test                               # Run all tests
./amper test -m lexer                      # Test lexer module only
./amper test -m parser                     # Test parser module only

# Static analysis
./scripts/check.sh                         # Run ktlint + detekt
./scripts/check.sh detekt                  # Detekt only
./scripts/check.sh ktlint                  # Ktlint only
./scripts/check.sh format                  # Auto-format with ktlint

# Demo
./scripts/run-web.sh                       # Run web demo (wasmJs)

# Documentation
./scripts/dokka.sh                         # Generate API docs

# Publish
cd publishing && ./gradlew publishAllPublicationsToMavenLocalRepository
```

## Key Constraints

- **Kotlin Multiplatform** — core logic in common source sets, targets: jvm,
  macosArm64, macosX64, linuxX64, js, wasmJs.
- **Amper build system** — module configuration in `module.yaml` files, shared
  template in `kss.module-template.yaml`.
- **Detekt + ktlint** — strict static analysis. Run `./scripts/check.sh` before
  committing.
- **Conventional Commits** — `<type>(<scope>): <subject>` (see
  `.ai/guidelines.md`).

## Module CLAUDE.md Files

Each module has its own `CLAUDE.md` referencing module-specific `AGENTS.md`.
