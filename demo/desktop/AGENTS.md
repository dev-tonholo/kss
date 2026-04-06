# demo/desktop — Desktop Demo App

JVM desktop entry point for the KSS demo application.

Read [.ai/guidelines.md](../../.ai/guidelines.md) first.

## Purpose

Minimal entry point that launches the shared demo UI as a Compose Desktop
window. All UI logic lives in `demo/shared`.

## Source Structure

```
src/dev/tonholo/kss/demo/
└── Main.kt                          # Desktop application entry point
```

## Key Dependencies

- `demo/shared` — shared UI module
- Compose Desktop — windowing and rendering

## Conventions

- Keep this module minimal — it should only contain the application entry point.
- All shared UI and logic belongs in `demo/shared`.
