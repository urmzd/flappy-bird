package com.urmzd.flappybird.state;

import java.util.List;

public record GameState(
    GamePhase phase, BirdState bird, List<PipeState> pipes, FloorState floor, int score) {
  public static final int WIDTH = 288;
  public static final int HEIGHT = 512;

  public static GameState initial() {
    return new GameState(
        new GamePhase.Ready(), BirdState.initial(), List.of(), FloorState.initial(), 0);
  }

  public GameState withPhase(GamePhase p) {
    return new GameState(p, bird, pipes, floor, score);
  }

  public GameState withBird(BirdState b) {
    return new GameState(phase, b, pipes, floor, score);
  }

  public GameState withPipes(List<PipeState> p) {
    return new GameState(phase, bird, p, floor, score);
  }

  public GameState withFloor(FloorState f) {
    return new GameState(phase, bird, pipes, f, score);
  }

  public GameState withScore(int s) {
    return new GameState(phase, bird, pipes, floor, s);
  }
}
