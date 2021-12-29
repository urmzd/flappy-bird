# Flappy Bird

A Flappy Bird clone developed using JavaFX18.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Build](#build)
- [Usage](#usage)
  - [Using Maven](#using-maven)
  - [Using Prebuilt Image](#using-prebuilt-image)
- [Screenshots](#screenshots)

## Prerequisites

- Optional (only required when rebuilding) - [Maven](https://maven.apache.org/)
- Optional (only required when rebuilding) - [Java 11](https://openjdk.java.net/projects/jdk/11/)

## Build

To create an executable image, simply run `mvn clean javafx:jlink` in the root directory.

## Usage

The two methods of running the project are listed below.

### Using Maven

If all [prerequisites](#prerequisites) are installed, execute `mvn clean javafx:run`.
A running instance of the game should be shown.

### Using Prebuilt Image
After downloading and extracting the `flappybird.zip` folder in the latest release,
run the executable found in `flappybird/bin/launcher`.

### Screenshots
![Running Instance](./resources/running-instance.png)

