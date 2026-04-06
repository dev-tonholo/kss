# lexer — CSS Tokenizer

This module implements the CSS tokenizer, converting raw CSS text into a list of
`Token` objects.

Read [.ai/guidelines.md](../.ai/guidelines.md) first.

## Purpose

The lexer performs **tokenization** (not parsing). It scans CSS input
character-by-character and produces tokens classified by `CssTokenKind` (ident,
function, number, string, hash, at-keyword, etc.).

## Source Structure

```
src/dev/tonholo/kss/lexer/css/
├── CssTokenizer.kt                  # Main tokenizer — orchestrates token consumers
├── CssTokenKind.kt                  # Enum of all CSS token types
├── CssTokenIterator.kt              # Iterator over tokenized output
├── constants/
│   └── CssFunctionConstants.kt      # Well-known CSS function names
└── token/consumer/
    ├── TokenConsumer.kt             # Base consumer interface
    ├── AtKeywordTokenConsumer.kt    # @-keyword tokens
    ├── CommentTokenConsumer.kt      # CSS comments
    ├── DirectTokenConsumer.kt       # Single-character tokens ({, }, ;, etc.)
    ├── FunctionTokenConsumer.kt     # Function tokens (rgb(), calc(), etc.)
    ├── HashTokenConsumer.kt         # Hash/ID tokens (#foo)
    ├── IdentTokenConsumer.kt        # Identifier tokens
    ├── NumberTokenConsumer.kt       # Numeric tokens (with units)
    ├── StringTokenConsumer.kt       # Quoted string tokens
    ├── UrlTokenConsumer.kt          # url() tokens
    └── WhitespaceTokenConsumer.kt   # Whitespace tokens

test/dev/tonholo/kss/lexer/css/
└── CssTokenizerTest.kt             # Tokenizer tests
```

## Key Concepts

- **Token Consumer Pattern**: Each token type has a dedicated consumer class.
  The `CssTokenizer` delegates to the appropriate consumer based on the current
  character.
- **CssTokenKind**: Enum implementing `TokenKind` from core. Defines all CSS
  token types per the CSS Syntax Module Level 3 spec.
- **CssTokenIterator**: Wraps the token list for convenient sequential access by
  the parser.

## Key Dependencies

- `core` module (exported) — provides `Token`, `TokenKind`, `Tokenizer`
  interfaces.

## Build and Test

```bash
./amper build -m lexer
./amper test -m lexer
```

## Conventions

- The tokenizer follows the [CSS Syntax Module Level 3](https://www.w3.org/TR/css-syntax-3/)
  specification. Reference it when adding or modifying token consumers.
- Each token consumer handles exactly one token type. Keep consumers focused.
- Tests go in `test/` and mirror the source package structure.
- All code is in common source sets (no platform-specific code).
