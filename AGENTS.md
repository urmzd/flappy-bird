# AGENTS.md

## Identity

You are an agent working on **Flappy Bird** — a Flappy Bird clone built with JavaFX and Java 21, featuring data-oriented design with immutable state, sealed interfaces, and dependency injection.

## Architecture

Data-Oriented Design with SOLID principles across four layers:

| Layer | Path | Role |
|-------|------|------|
| State | `src/main/java/.../state/` | Immutable Java 21 records (what things *are*) |
| Systems | `src/main/java/.../systems/` | Interfaces + implementations (what things *do*) |
| Rendering | `src/main/java/.../rendering/` | Drawing logic (how things *look*) |
| Input | `src/main/java/.../input/` | User input handling |
| Orchestrator | `GameOrchestrator.java` | Coordinates systems via dependency injection |

```
src/main/java/com/urmzd/flappybird/
├── state/              # Immutable records: Vector, BirdState, PipeState, FloorState, GamePhase, GameState
├── systems/            # BirdSystem, PipeSystem, FloorSystem, CollisionSystem, ScoreSystem, PhysicsSystem
├── rendering/          # Assets, Renderer
├── input/              # GameAction (sealed: Jump | Restart)
├── GameOrchestrator.java
└── GameCanvas.java     # JavaFX entry point
```

## Key Files

- `GameCanvas.java` — Application entry point (JavaFX)
- `GameOrchestrator.java` — Game loop coordinator
- `state/GameState.java` — Immutable game state record
- `state/GamePhase.java` — Sealed interface: Ready | Playing | GameOver
- `systems/` — Game logic systems (interfaces + default implementations)
- `rendering/Renderer.java` — Drawing logic
- `pom.xml` — Maven build config (JavaFX 21, JUnit 5, Spotless)
- `justfile` — Task runner

## Commands

| Task | Command |
|------|---------|
| Run game | `just run` or `mvn javafx:run` |
| Build | `just build` or `mvn compile` |
| Test | `just test` or `mvn test` |
| Clean | `just clean` or `mvn clean` |
| Package | `just package` (jlink + jpackage native image) |
| Dev | `just dev` (build + run) |

## Code Style

- Java 21, Apache-2.0 license
- Google Java Format via Spotless Maven plugin
- Immutable state via Java records
- Sealed interfaces for exhaustive pattern matching (`GamePhase`, `GameAction`)
- Dependency injection — systems injected into `GameOrchestrator`
- Pure functions — systems transform state without side effects
- JUnit 5 for testing

## Conventions

- **Do not** mutate state — always return new record instances
- **Do not** add rendering logic to systems — keep layers separate
- **Do** use sealed interfaces for any new type hierarchies
- **Do** inject dependencies via constructor parameters
- **Do** write tests for new systems
