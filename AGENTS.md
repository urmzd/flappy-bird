# AGENTS.md

## Identity

You are an agent working on **flappy-bird** — a Flappy Bird clone built with JavaFX and Java 21, demonstrating data-oriented design patterns with immutable state, sealed interfaces, and dependency injection.

## Architecture

Data-Oriented Design with SOLID principles across four layers:

| Layer | Path | Role |
|-------|------|------|
| State | `src/main/java/.../state/` | Immutable Java 21 records (what things *are*) |
| Systems | `src/main/java/.../systems/` | Interfaces + implementations (what things *do*) |
| Rendering | `src/main/java/.../rendering/` | Drawing logic (how things *look*) |
| Input | `src/main/java/.../input/` | User input handling |
| Orchestrator | `GameOrchestrator.java` | Coordinates systems with dependency injection |

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

- `src/main/java/com/urmzd/flappybird/GameCanvas.java` — Application entry point (JavaFX)
- `src/main/java/com/urmzd/flappybird/GameOrchestrator.java` — Game loop coordinator
- `src/main/java/com/urmzd/flappybird/state/GameState.java` — Immutable game state record
- `src/main/java/com/urmzd/flappybird/state/GamePhase.java` — Sealed interface: Ready | Playing | GameOver
- `src/main/java/com/urmzd/flappybird/systems/` — Game logic systems
- `src/main/java/com/urmzd/flappybird/rendering/Renderer.java` — Drawing logic
- `pom.xml` — Maven build config with JavaFX 21, JUnit 5, Spotless
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
