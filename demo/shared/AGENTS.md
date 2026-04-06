# demo/shared — Shared Demo UI

Shared Compose Multiplatform UI layer for the KSS demo application.

Read [.ai/guidelines.md](../../.ai/guidelines.md) first.

## Purpose

This module provides the shared UI for the demo app, including:

- CSS editor panel with syntax highlighting
- AST tree visualization
- AST node detail panel
- Split pane layout

This is a **demonstration** module — focus changes on user experience and
showcasing KSS capabilities, not core library logic.

## Source Structure

```
src/dev/tonholo/kss/demo/
├── App.kt                           # Main Composable entry point
├── state/
│   └── AppState.kt                  # App state management
├── theme/
│   ├── AppTheme.kt                  # Material3 theme
│   └── SyntaxColors.kt             # Syntax highlighting colors
├── model/
│   ├── AstDisplayNode.kt            # Display model for AST tree
│   ├── AstFlattener.kt              # Flattens AST for tree display
│   ├── NodeDetailExtractor.kt       # Extracts details from AST nodes
│   └── ParseErrorInfo.kt            # Parse error display model
└── ui/
    ├── CssEditorPanel.kt            # CSS input editor
    ├── AstTreePanel.kt              # AST tree view
    ├── AstTreeRow.kt                # Individual tree row
    ├── AstNodeDetailPanel.kt        # Node detail sidebar
    ├── AstFilterBar.kt              # Filter controls for AST
    ├── SyntaxHighlighter.kt         # CSS syntax highlighting
    ├── SplitPane.kt                 # Resizable split layout
    ├── PanelHeader.kt               # Panel header component
    ├── EditorSearchBar.kt           # Search within editor
    └── KeyEventHandler.kt           # Keyboard shortcuts (expect/actual)

src@jvm/dev/tonholo/kss/demo/ui/
└── KeyEventHandler.jvm.kt          # JVM keyboard implementation

src@wasmJs/dev/tonholo/kss/demo/ui/
└── KeyEventHandler.wasmJs.kt       # wasmJs keyboard implementation

test/dev/tonholo/kss/demo/
├── state/
│   └── SearchReducerTest.kt         # Search state tests
├── ui/
│   └── SplitPaneRatioTest.kt        # Split pane ratio tests
└── model/
    └── NodeDetailExtractorTest.kt   # Node detail extraction tests
```

## Key Dependencies

- `parser` module — provides the CSS parser and AST types
- Compose Multiplatform — UI framework
- Material3 — design system
- AndroidX Lifecycle ViewModel — state management
- Kotlinx Coroutines — async operations

## Platform Targets

- **jvm**: Desktop via Compose Desktop
- **wasmJs**: Web via Compose for Web

## Conventions

- Platform-specific code uses Amper's `src@<platform>/` convention.
- UI components are Composable functions following Compose best practices.
- Detekt Compose rules are active — follow modifier ordering, naming conventions.
- Tests go in `test/` and focus on logic (state, models), not UI rendering.

## Build and Test

```bash
./amper build -m demo/shared
./amper test -m demo/shared
```
