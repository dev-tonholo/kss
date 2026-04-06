## Repository Overview

- **Primary language**: Kotlin
- **Build system**: Amper (JetBrains) with Gradle for publishing
- **Repository structure**: Multi-module Amper project
- **Purpose**: Kotlin Multiplatform CSS lexer and parser library

> CRITICAL:
> All Agents MUST follow the `.ai/guidelines.md` at all times for code style,
> architecture, and design principles.

## Module-Specific Context

| Module         | Description                                            | AGENTS.md                                        |
|----------------|--------------------------------------------------------|--------------------------------------------------|
| `core`         | Shared abstractions — Token, TokenKind, AST base types | [core/AGENTS.md](core/AGENTS.md)                 |
| `lexer`        | CSS tokenizer — converts CSS string to `List<Token>`   | [lexer/AGENTS.md](lexer/AGENTS.md)               |
| `parser`       | CSS parser — converts tokens to `StyleSheet` AST       | [parser/AGENTS.md](parser/AGENTS.md)             |
| `demo/shared`  | Shared Compose Multiplatform UI for the demo app       | [demo/shared/AGENTS.md](demo/shared/AGENTS.md)   |
| `demo/desktop` | Desktop entry point (JVM)                              | [demo/desktop/AGENTS.md](demo/desktop/AGENTS.md) |
| `demo/web`     | Web entry point (wasmJs)                               | [demo/web/AGENTS.md](demo/web/AGENTS.md)         |
| `publishing`   | Maven publication configuration (Gradle-based)         | —                                                |

## Module Dependency Graph

```
core
 ├── lexer (exports core)
 │    └── parser (exports core + lexer)
 │         └── demo/shared
 │              ├── demo/desktop
 │              └── demo/web
 └── publishing (Gradle wrapper for Maven Central + GitHub Packages)
```

## Build System

KSS uses **Amper**, JetBrains' modern build tool for Kotlin projects:

- **Module configuration**: Each module has a `module.yaml` file.
- **Shared template**: `kss.module-template.yaml` defines common settings
  (Kotlin version, Compose version, test dependencies).
- **Wrapper scripts**: `./amper` (Unix) and `./amper.bat` (Windows) — no local
  install required.
- **Publishing**: Uses a Gradle wrapper in `publishing/` for Maven Central and
  GitHub Packages.

## Verification Workflow

After making changes to the lexer or parser:

1. **Check**: Run `./scripts/check.sh` (ktlint + detekt).
2. **Build**: Run `./amper build`.
3. **Test**: Run `./amper test`.
4. **Demo**: Run `./scripts/run-web.sh` to visually verify CSS parsing in the
   demo app.

## Skills

| Skill                                                    | Description                                                                                                                                        |
|----------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| [Unit Test Author](.ai/skills/unit-test-author/SKILL.md) | Creates and updates Kotlin unit tests following KMP testing conventions, including Burst parameterized patterns and targeted Amper test execution. |
| [Self-Review](.ai/skills/self-review/SKILL.md)           | Performs a thorough self-review of pending changes against project conventions, checking code quality, tests, static analysis before commit/PR.    |

## Recommended Tools

- **`diff`**: Compare expected vs actual parser output.
- **`grep`**: Quickly find token consumers in the lexer or AST consumers in the
  parser.
