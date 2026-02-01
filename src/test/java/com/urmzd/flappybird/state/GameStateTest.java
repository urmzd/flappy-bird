package com.urmzd.flappybird.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameState")
class GameStateTest {

  @Test
  @DisplayName("initial state should have Ready phase")
  void initialStateIsReady() {
    var state = GameState.initial();

    assertInstanceOf(GamePhase.Ready.class, state.phase());
  }

  @Test
  @DisplayName("initial state should have no pipes")
  void initialStateHasNoPipes() {
    var state = GameState.initial();

    assertTrue(state.pipes().isEmpty());
  }

  @Test
  @DisplayName("initial state should have zero score")
  void initialStateHasZeroScore() {
    var state = GameState.initial();

    assertEquals(0, state.score());
  }

  @Test
  @DisplayName("initial bird should be alive but not active")
  void initialBirdState() {
    var state = GameState.initial();

    assertTrue(state.bird().alive());
    assertFalse(state.bird().active());
  }

  @Test
  @DisplayName("withScore should return new state with updated score")
  void withScoreUpdatesScore() {
    var state = GameState.initial();

    var updated = state.withScore(10);

    assertEquals(10, updated.score());
    assertEquals(0, state.score()); // Original unchanged
  }

  @Test
  @DisplayName("withPhase should return new state with updated phase")
  void withPhaseUpdatesPhase() {
    var state = GameState.initial();

    var updated = state.withPhase(new GamePhase.Playing());

    assertInstanceOf(GamePhase.Playing.class, updated.phase());
    assertInstanceOf(GamePhase.Ready.class, state.phase()); // Original unchanged
  }
}
