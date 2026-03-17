> **Note:** This repository is archived and no longer actively maintained.

<p align="center">
  <img src="./resources/running-instance.png" alt="Flappy Bird" width="400" />
</p>

<h1 align="center">Flappy Bird</h1>

<p align="center">
  A Flappy Bird clone built with JavaFX and Java 21, featuring data-oriented design with immutable state, sealed interfaces, and dependency injection.
</p>

<p align="center">
  <a href="https://github.com/urmzd/flappy-bird/actions/workflows/ci.yml"><img src="https://github.com/urmzd/flappy-bird/actions/workflows/ci.yml/badge.svg" alt="CI" /></a>
  <a href="https://github.com/urmzd/flappy-bird/releases"><img src="https://img.shields.io/github/v/release/urmzd/flappy-bird" alt="Release" /></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-Apache--2.0-blue" alt="License" /></a>
</p>

<p align="center">
  <a href="https://github.com/urmzd/flappy-bird/releases">Download</a>
  &middot;
  <a href="https://github.com/urmzd/flappy-bird/issues">Report Bug</a>
  &middot;
  <a href="CONTRIBUTING.md">Contribute</a>
</p>

---

## Download

Get the latest release for your platform from the [Releases page](../../releases). No Java installation required — each download includes a bundled runtime.

| Platform | Download | Run |
|----------|----------|-----|
| macOS | `flappybird-macos.zip` | Double-click **FlappyBird.app** |
| Windows | `flappybird-windows.zip` | Run **FlappyBird\FlappyBird.exe** |
| Linux | `flappybird-linux.zip` | Run **FlappyBird/bin/FlappyBird** |

> **macOS users:** macOS may block the app since it is not signed. Right-click **FlappyBird.app** → **Open** → **Open** in the dialog. Alternatively: **System Settings → Privacy & Security → Open Anyway**.

## Controls

| Key | Action |
|-----|--------|
| <kbd>Space</kbd> | Jump / Start |
| Any key | Restart (after game over) |

## Development

### Prerequisites

- [Java 21+](https://openjdk.java.net/projects/jdk/21/)
- [Maven](https://maven.apache.org/)
- [just](https://github.com/casey/just) (optional command runner)

### Quick Start

```sh
git clone https://github.com/urmzd/flappy-bird.git
cd flappy-bird
just run          # or: mvn javafx:run
```

### Commands

| Command | Description |
|---------|-------------|
| `just run` | Run the game |
| `just build` | Compile the project |
| `just test` | Run tests |
| `just package` | Create native app image |
| `just dev` | Build and run |
| `just clean` | Clean build artifacts |

See [SETUP.md](./SETUP.md) for full build instructions and architecture details.

## Contributing

See [CONTRIBUTING.md](./CONTRIBUTING.md) for development workflow, commit conventions, and code style guidelines.

## Agent Skill

```bash
npx skills add urmzd/flappy-bird
```

## License

[Apache-2.0](LICENSE)
