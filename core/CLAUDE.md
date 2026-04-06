# core — Shared Abstractions

CRITICAL: MUST read and follow `AGENTS.md` in this directory for module-specific
architecture, key files, and conventions.

This module provides shared abstractions (Token, TokenKind, AST base types) used
by the lexer and parser.

```bash
./amper build -m core
./amper test -m core
./scripts/check.sh detekt
```
