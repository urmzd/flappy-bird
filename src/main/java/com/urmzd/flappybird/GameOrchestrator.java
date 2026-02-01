package com.urmzd.flappybird;

import com.urmzd.flappybird.input.GameAction;
import com.urmzd.flappybird.state.*;
import com.urmzd.flappybird.systems.*;
import java.util.List;
import java.util.Optional;

public final class GameOrchestrator {

  private final BirdSystem birdSystem;
  private final PipeSystem pipeSystem;
  private final FloorSystem floorSystem;
  private final CollisionSystem collisionSystem;
  private final ScoreSystem scoreSystem;

  public GameOrchestrator(
      BirdSystem birdSystem,
      PipeSystem pipeSystem,
      FloorSystem floorSystem,
      CollisionSystem collisionSystem,
      ScoreSystem scoreSystem) {
    this.birdSystem = birdSystem;
    this.pipeSystem = pipeSystem;
    this.floorSystem = floorSystem;
    this.collisionSystem = collisionSystem;
    this.scoreSystem = scoreSystem;
  }

  public static GameOrchestrator createDefault() {
    return new GameOrchestrator(
        new DefaultBirdSystem(),
        new DefaultPipeSystem(),
        new DefaultFloorSystem(),
        new DefaultCollisionSystem(),
        new DefaultScoreSystem());
  }

  public GameState tick(GameState state, Optional<GameAction> action) {
    GameState afterInput = handleInput(state, action);
    GameState afterPhysics = updatePhysics(afterInput);
    GameState afterCollisions = checkCollisions(afterPhysics);
    GameState afterScore = updateScore(afterCollisions);
    return afterScore;
  }

  private GameState handleInput(GameState state, Optional<GameAction> action) {
    if (action.isEmpty()) {
      return state;
    }

    return switch (action.get()) {
      case GameAction.Jump() -> handleJump(state);
      case GameAction.Restart() -> handleRestart(state);
    };
  }

  private GameState handleJump(GameState state) {
    return switch (state.phase()) {
      case GamePhase.Ready() -> {
        BirdState jumpedBird = birdSystem.jump(state.bird());
        yield state.withPhase(new GamePhase.Playing()).withBird(jumpedBird);
      }
      case GamePhase.Playing() -> {
        BirdState jumpedBird = birdSystem.jump(state.bird());
        yield state.withBird(jumpedBird);
      }
      case GamePhase.GameOver(var s) -> state;
    };
  }

  private GameState handleRestart(GameState state) {
    return switch (state.phase()) {
      case GamePhase.GameOver(var s) -> GameState.initial();
      default -> state;
    };
  }

  private GameState updatePhysics(GameState state) {
    BirdState updatedBird = birdSystem.update(state.bird(), state.phase());

    FloorState updatedFloor = state.floor();
    List<PipeState> updatedPipes = state.pipes();

    if (state.phase() instanceof GamePhase.Playing
        || (state.phase() instanceof GamePhase.GameOver && state.bird().active())) {
      updatedFloor = floorSystem.update(state.floor());
      updatedPipes = pipeSystem.update(state.pipes());
      updatedPipes = pipeSystem.spawnIfNeeded(updatedPipes);
      updatedPipes = pipeSystem.removeOffscreen(updatedPipes);
    } else if (state.phase() instanceof GamePhase.Ready) {
      updatedFloor = floorSystem.update(state.floor());
    }

    return state.withBird(updatedBird).withFloor(updatedFloor).withPipes(updatedPipes);
  }

  private GameState checkCollisions(GameState state) {
    if (!(state.phase() instanceof GamePhase.Playing)) {
      if (state.phase() instanceof GamePhase.GameOver && state.bird().active()) {
        if (collisionSystem.checkFloorCollision(state.bird(), state.floor())) {
          BirdState stoppedBird = birdSystem.stop(state.bird());
          FloorState stoppedFloor = floorSystem.stop(state.floor());
          List<PipeState> stoppedPipes = pipeSystem.stopAll(state.pipes());
          return state.withBird(stoppedBird).withFloor(stoppedFloor).withPipes(stoppedPipes);
        }
      }
      return state;
    }

    boolean hitPipe = collisionSystem.checkPipeCollision(state.bird(), state.pipes());
    boolean hitFloor = collisionSystem.checkFloorCollision(state.bird(), state.floor());

    if (hitPipe || hitFloor) {
      BirdState deadBird = birdSystem.setDead(state.bird());

      if (hitFloor) {
        deadBird = birdSystem.stop(deadBird);
        FloorState stoppedFloor = floorSystem.stop(state.floor());
        List<PipeState> stoppedPipes = pipeSystem.stopAll(state.pipes());
        return state
            .withPhase(new GamePhase.GameOver(state.score()))
            .withBird(deadBird)
            .withFloor(stoppedFloor)
            .withPipes(stoppedPipes);
      }

      return state.withPhase(new GamePhase.GameOver(state.score())).withBird(deadBird);
    }

    return state;
  }

  private GameState updateScore(GameState state) {
    if (!(state.phase() instanceof GamePhase.Playing)) {
      return state;
    }

    ScoreSystem.ScoreResult result =
        scoreSystem.checkScore(state.bird(), state.pipes(), state.score());

    return state.withScore(result.score()).withPipes(result.pipes());
  }
}
