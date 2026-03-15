# Skill: flappy-bird

## Description

Work on **flappy-bird** — a Flappy Bird clone built with JavaFX and Java 21, demonstrating data-oriented design with immutable state, sealed interfaces, and dependency injection.

## When to Use

- Building or modifying game logic (state, systems, rendering)
- Working with JavaFX game loops and animation
- Adding new game features (power-ups, difficulty scaling, new systems)
- Debugging collision detection, physics, or rendering issues
- Creating native app images with jlink/jpackage

## Project Context

- **Language**: Java 21
- **Framework**: JavaFX
- **Build**: Maven (`pom.xml`)
- **Test**: JUnit 5
- **Format**: Google Java Format via Spotless

### Architecture

Data-Oriented Design with four layers:

| Layer | Path | Role |
|-------|------|------|
| State | `src/main/java/.../state/` | Immutable Java 21 records |
| Systems | `src/main/java/.../systems/` | Game logic interfaces + implementations |
| Rendering | `src/main/java/.../rendering/` | Drawing logic |
| Orchestrator | `GameOrchestrator.java` | Coordinates systems with DI |

### Key Patterns

- **Immutable State**: All game state uses Java 21 records
- **Sealed Interfaces**: `GamePhase` (Ready | Playing | GameOver), `GameAction` (Jump | Restart)
- **Pure Functions**: Systems transform state without side effects
- **Dependency Injection**: Systems injected into GameOrchestrator

### Commands

| Task | Command |
|------|---------|
| Run | `just run` or `mvn javafx:run` |
| Build | `just build` or `mvn compile` |
| Test | `just test` or `mvn test` |
| Package | `just package` (native image) |

## Instructions

1. Read `AGENTS.md` and `SETUP.md` for full architecture details
2. Keep state immutable — use records, never mutate
3. Add new game logic as a System interface + Default implementation
4. Use sealed interfaces for new enums/sum types
5. Run `just test` before committing
6. Follow Google Java Format style
