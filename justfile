# Flappy Bird - justfile
# Run `just --list` to see available commands

# Default recipe - show help
default:
    @just --list

# Install dependencies (downloads Maven dependencies)
install:
    mvn dependency:resolve

# Build the project (compile without running)
build:
    mvn compile

# Run the game
run:
    mvn javafx:run

# Run tests
test:
    mvn test

# Clean build artifacts
clean:
    mvn clean

# Full rebuild (clean + build)
rebuild: clean build

# Package as JAR
package:
    mvn package -DskipTests

# Create executable image with jlink
image:
    mvn clean javafx:jlink

# Run with hot reload (recompile and run)
dev: build run

# Check for dependency updates
outdated:
    mvn versions:display-dependency-updates

# Format check (if formatter is configured)
check:
    mvn compile -q && echo "Build OK"
