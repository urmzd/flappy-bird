package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.GameState;
import com.urmzd.flappybird.state.PipeState;
import com.urmzd.flappybird.state.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultPipeSystem implements PipeSystem {

  private static final double PIPE_VELOCITY_X = -2;
  private static final double SPAWN_X = 288;
  private static final double PIPE_WIDTH = 52;
  private static final double SPAWN_TRIGGER_X = GameState.WIDTH / 2.0 - PIPE_WIDTH;
  private static final double REMOVE_X = -PIPE_WIDTH;

  private final Random random;

  public DefaultPipeSystem() {
    this.random = new Random();
  }

  public DefaultPipeSystem(Random random) {
    this.random = random;
  }

  @Override
  public List<PipeState> update(List<PipeState> pipes) {
    List<PipeState> updated = new ArrayList<>();
    for (PipeState pipe : pipes) {
      Vector newPosition = PhysicsSystem.applyVelocity(pipe.position(), pipe.velocity());
      updated.add(pipe.withPosition(newPosition));
    }
    return updated;
  }

  @Override
  public List<PipeState> spawnIfNeeded(List<PipeState> pipes) {
    if (pipes.isEmpty()) {
      return spawnPair(pipes);
    }

    double lastPipeX = pipes.get(pipes.size() - 1).position().x();
    if (lastPipeX <= SPAWN_TRIGGER_X) {
      return spawnPair(pipes);
    }

    return pipes;
  }

  private List<PipeState> spawnPair(List<PipeState> pipes) {
    double heightOffset = getRandomHeight();

    PipeState bottomPipe = PipeState.create(SPAWN_X, 80 + heightOffset, PIPE_VELOCITY_X, false);

    PipeState topPipe = PipeState.create(SPAWN_X, -320 + heightOffset, PIPE_VELOCITY_X, true);

    List<PipeState> updated = new ArrayList<>(pipes);
    updated.add(bottomPipe);
    updated.add(topPipe);
    return updated;
  }

  @Override
  public List<PipeState> removeOffscreen(List<PipeState> pipes) {
    if (pipes.isEmpty()) {
      return pipes;
    }

    double firstPipeX = pipes.get(0).position().x();
    if (firstPipeX <= REMOVE_X) {
      List<PipeState> updated = new ArrayList<>(pipes);
      updated.remove(0);
      updated.remove(0);
      return updated;
    }

    return pipes;
  }

  @Override
  public List<PipeState> stopAll(List<PipeState> pipes) {
    List<PipeState> stopped = new ArrayList<>();
    for (PipeState pipe : pipes) {
      stopped.add(pipe.withVelocity(Vector.ZERO));
    }
    return stopped;
  }

  private double getRandomHeight() {
    return (random.nextDouble() * 200) + 50;
  }
}
