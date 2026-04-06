# lexer — CSS Tokenizer

CRITICAL: MUST read and follow `AGENTS.md` in this directory for module-specific
architecture, key files, and conventions.

This module tokenizes CSS input text into a stream of tokens following the CSS
Syntax Module Level 3 specification.

```bash
./amper build -m lexer
./amper test -m lexer
./scripts/check.sh detekt
```
