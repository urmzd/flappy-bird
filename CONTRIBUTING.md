# Contributing

Thanks for your interest in contributing to **Flappy Bird**.

## Prerequisites

| Tool | Required | Install |
|------|----------|---------|
| Java 21+ | Yes | [SDKMAN](https://sdkman.io/) or OS package manager |
| Maven | Yes | [maven.apache.org](https://maven.apache.org/) |
| just | Optional | [github.com/casey/just](https://github.com/casey/just) |
| GitHub CLI | Optional | [cli.github.com](https://cli.github.com/) (for releases) |

## Getting Started

```sh
git clone https://github.com/urmzd/flappy-bird.git
cd flappy-bird
just install      # or: mvn dependency:resolve
just run          # or: mvn javafx:run
```

## Commands

| Command | Description |
|---------|-------------|
| `just install` | Download dependencies |
| `just build` | Compile the project |
| `just run` | Run the game |
| `just test` | Run JUnit tests |
| `just check` | Verify build compiles |
| `just clean` | Clean build artifacts |
| `just rebuild` | Clean and build |
| `just dev` | Build and run |
| `just package` | Create native app image (jlink + jpackage) |

## Commit Convention

This project uses [Conventional Commits](https://www.conventionalcommits.org/) enforced via [gitit](https://github.com/urmzd/gitit).

**Format:** `type(scope): description`

| Prefix | Purpose |
|--------|---------|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation |
| `refactor` | Refactoring |
| `test` | Tests |
| `chore` | Maintenance |
| `ci` | CI changes |

## Pull Requests

1. Fork the repository
2. Create a feature branch (`feat/your-feature`)
3. Make your changes and ensure `just test` passes
4. Push to your fork and open a Pull Request
5. Keep PRs focused — one logical change per PR

## Code Style

- **Google Java Format** — enforced via Spotless Maven plugin
- **Immutable state** — Java 21 records for all game state
- **Sealed interfaces** — exhaustive pattern matching for phases and actions
- **Pure functions** — systems transform state without side effects
- **JUnit 5** — for all tests
