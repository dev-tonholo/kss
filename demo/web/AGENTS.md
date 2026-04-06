# demo/web — Web Demo App

wasmJs web entry point for the KSS demo application.

Read [.ai/guidelines.md](../../.ai/guidelines.md) first.

## Purpose

Minimal entry point that launches the shared demo UI as a Compose for Web
application. All UI logic lives in `demo/shared`.

## Source Structure

```
src/dev/tonholo/kss/demo/
└── Main.kt                          # Web application entry point
```

## Key Dependencies

- `demo/shared` — shared UI module

## Running

```bash
./scripts/run-web.sh
```

## Conventions

- Keep this module minimal — it should only contain the application entry point.
- All shared UI and logic belongs in `demo/shared`.
