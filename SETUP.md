# Setup Guide

## Prerequisites

- [Java 21](https://openjdk.java.net/projects/jdk/21/) or higher
- [Maven](https://maven.apache.org/)
- [just](https://github.com/casey/just) (optional)

## Quick Start

```bash
# Using just
just run

# Using Maven
mvn javafx:run
```

## Commands

| Command | Description |
|---------|-------------|
| `just install` | Download dependencies |
| `just build` | Compile the project |
| `just run` | Run the game |
| `just test` | Run tests |
| `just clean` | Clean build artifacts |
| `just rebuild` | Clean and build |
| `just package` | Create native app image (jlink + jpackage) |
| `just image` | Alias for `just package` |
| `just dev` | Build and run |

## Creating a Release

Releases are automated via GitHub Actions:

```bash
git tag v1.0.0
git push --tags
```

This builds native images for Linux, macOS, and Windows and publishes them to GitHub Releases.

## Project Structure

```
src/main/java/com/urmzd/flappybird/
├── state/              # Immutable game state (records)
│   ├── Vector.java
│   ├── BirdState.java
│   ├── PipeState.java
│   ├── FloorState.java
│   ├── GamePhase.java  # Sealed: Ready | Playing | GameOver
│   └── GameState.java
│
├── systems/            # Game logic (interfaces + implementations)
│   ├── BirdSystem.java / DefaultBirdSystem.java
│   ├── PipeSystem.java / DefaultPipeSystem.java
│   ├── FloorSystem.java / DefaultFloorSystem.java
│   ├── CollisionSystem.java / DefaultCollisionSystem.java
│   ├── ScoreSystem.java / DefaultScoreSystem.java
│   └── PhysicsSystem.java
│
├── rendering/
│   ├── Assets.java
│   └── Renderer.java
│
├── input/
│   └── GameAction.java # Sealed: Jump | Restart
│
├── GameOrchestrator.java
└── GameCanvas.java
```

## Architecture

Data-Oriented Design with SOLID principles:

| Layer | Responsibility |
|-------|---------------|
| **State** | Immutable records (what things *are*) |
| **Systems** | Interfaces + implementations (what things *do*) |
| **Rendering** | Drawing logic (how things *look*) |
| **Orchestrator** | Coordinates systems with dependency injection |

### Key Features

- **Immutable State** - Java 21 records for all game state
- **Dependency Injection** - Systems injected into GameOrchestrator
- **Sealed Interfaces** - Exhaustive pattern matching for phases/actions
- **Pure Functions** - Systems transform state without side effects
