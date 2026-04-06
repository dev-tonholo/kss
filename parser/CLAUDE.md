# parser — CSS Parser

CRITICAL: MUST read and follow `AGENTS.md` in this directory for module-specific
architecture, key files, and conventions.

This module parses CSS tokens into a `StyleSheet` AST following the CSS Syntax
Module Level 3 specification.

```bash
./amper build -m parser
./amper test -m parser
./scripts/check.sh detekt
```
