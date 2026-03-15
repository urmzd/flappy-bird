# Contributing

Thanks for your interest in contributing to **flappy-bird**.

## Prerequisites

- **Java 21+** — install via [SDKMAN](https://sdkman.io/) or your OS package manager
- **[Maven](https://maven.apache.org/)**
- **[just](https://github.com/casey/just)** — command runner
- **[GH_TOKEN](https://cli.github.com/)** — GitHub CLI authentication (for releases)

## Getting Started

```sh
git clone https://github.com/urmzd/flappy-bird.git
cd flappy-bird
just install
```

## Development

| Command | What it does |
|---------|-------------|
| `just build` | Compile the project |
| `just run` | Run the game |
| `just test` | Run JUnit tests |
| `just check` | Verify build compiles |
| `just clean` | Clean build artifacts |
| `just rebuild` | Clean and build |
| `just package` | Create native app image (jlink + jpackage) |

## Commit Convention

This project uses [Conventional Commits](https://www.conventionalcommits.org/) enforced via [gitit](https://github.com/urmzd/gitit):

| Prefix | Purpose |
|--------|---------|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation |
| `refactor` | Refactoring |
| `test` | Tests |
| `chore` | Maintenance |
| `ci` | CI changes |

Format: `type(scope): description`

## Pull Requests

1. Fork the repository
2. Create a feature branch (`feat/your-feature`)
3. Make your changes and ensure `just test` passes
4. Push to your fork and open a Pull Request
5. Keep PRs focused — one logical change per PR

## Code Style

- **Google Java Format** via Spotless Maven plugin
- Java 21 records for immutable state
- Sealed interfaces for exhaustive pattern matching
- JUnit 5 for testing
