# parser — CSS Parser

This module implements the CSS parser, converting a token stream from the lexer
into a `StyleSheet` AST (Abstract Syntax Tree).

Read [.ai/guidelines.md](../.ai/guidelines.md) first.

## Purpose

The parser consumes tokens produced by the lexer and builds a tree of CSS syntax
nodes: stylesheets, rules, selectors, declarations, at-rules, blocks, values,
and comments.

## Source Structure

```
src/dev/tonholo/kss/parser/ast/css/
├── CssParser.kt                     # Main parser — orchestrates consumers
├── CssCombinator.kt                 # CSS selector combinators (>, +, ~, etc.)
├── CssSpecificity.kt                # Specificity calculation for selectors
├── syntax/
│   ├── CssIterator.kt               # Token iterator adapter for parsing
│   └── node/
│       ├── CssNode.kt               # Base CSS node type
│       ├── StyleSheet.kt            # Root AST node
│       ├── Rule.kt                  # Style rules and at-rules
│       ├── Selector.kt              # CSS selectors
│       ├── Declaration.kt           # Property declarations
│       ├── Block.kt                 # Block nodes ({ ... })
│       ├── Value.kt                 # Property values
│       ├── Comment.kt               # CSS comments
│       ├── Prelude.kt               # Rule preludes
│       └── AtRulePrelude.kt         # At-rule preludes (@media, @import, etc.)
└── consumer/
    ├── Consumer.kt                  # Base consumer interface
    ├── StyleSheetConsumer.kt        # Top-level stylesheet consumer
    ├── SimpleBlockConsumer.kt       # Block content consumer
    ├── SimpleSelectorConsumer.kt    # Selector consumer
    ├── DeclarationConsumer.kt       # Declaration consumer
    └── ValueConsumer.kt             # Value consumer

test/dev/tonholo/kss/parser/ast/css/
├── CssParserTest.kt                 # Full parser integration tests
├── elements/
│   └── CssSpecificityTest.kt        # Specificity calculation tests
├── syntax/
│   └── CssTreeTest.kt              # AST tree structure tests
└── consumer/
    └── StyleSheetConsumerTest.kt    # StyleSheet consumer tests
```

## Key Concepts

- **Consumer Pattern**: Similar to the lexer, parsing is broken into consumers.
  Each consumer handles a specific grammar production (stylesheet, rule, block,
  selector, declaration, value).
- **CssParser**: The main entry point. Tokenizes input via the lexer, then
  delegates to `StyleSheetConsumer` to build the AST.
- **CssNode**: Base type for all AST nodes. Each node type (Rule, Selector,
  Declaration, etc.) is a specific subtype.
- **CssSpecificity**: Calculates selector specificity per the CSS spec.
- **CssCombinator**: Represents selector combinators (descendant, child,
  sibling, etc.).

## Key Dependencies

- `core` module (exported) — AST base types, Token, Element.
- `lexer` module (exported) — CssTokenizer, CssTokenKind.

## Build and Test

```bash
./amper build -m parser
./amper test -m parser
```

## Conventions

- The parser follows the [CSS Syntax Module Level 3](https://www.w3.org/TR/css-syntax-3/)
  specification. Reference it when modifying grammar productions.
- Each consumer handles one grammar production. Keep them focused.
- Tests go in `test/` and mirror the source package structure.
- All code is in common source sets (no platform-specific code).
