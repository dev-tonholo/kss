# core — Shared Abstractions

This module defines the foundational types shared across the lexer and parser
modules.

Read [.ai/guidelines.md](../.ai/guidelines.md) first.

## Purpose

The core module provides:

- **Token types**: `Token`, `TokenKind`, `TokenIterator` — the contract between
  the lexer and parser.
- **AST base types**: `Element`, `AstParser`, `AstParserIterator` — the
  foundation for building syntax trees.
- **Extensions**: Shared `String` and `Char` extension functions used across
  modules.

## Source Structure

```
src/dev/tonholo/kss/
├── lexer/
│   ├── Token.kt                     # Token data class with kind, value, location
│   ├── Tokenizer.kt                 # Tokenizer interface (implemented by lexer)
│   ├── TokenKind.kt                 # TokenKind interface (implemented by CssTokenKind)
│   └── TokenIterator.kt            # Iterator abstraction over token streams
├── parser/ast/
│   ├── Element.kt                   # Base AST element interface
│   ├── AstParser.kt                 # AST parser interface
│   ├── css/syntax/
│   │   └── AstParserException.kt    # Parser exception types
│   └── iterator/
│       └── AstParserIterator.kt     # Iterator for AST parsing
└── extensions/
    ├── String.extension.kt          # String utility extensions
    └── Char.extension.kt            # Char utility extensions
```

## Key Concepts

- **Token**: Immutable data class holding a `TokenKind`, raw string value, and
  source location (`CssLocation`).
- **TokenKind**: Interface that token kind enums implement. `CssTokenKind` in the
  lexer module is the CSS-specific implementation.
- **Element**: Base interface for all AST nodes produced by the parser.
- **AstParser**: Interface defining `parse()` contract for AST parsers.

## Conventions

- This module has **no dependencies** beyond the Kotlin stdlib.
- Types here define contracts — implementations live in `lexer` and `parser`.
- Keep this module minimal. Only add types that are genuinely shared.
- All code is in common source sets (no platform-specific code).

## Build and Test

```bash
./amper build -m core
./amper test -m core
```
