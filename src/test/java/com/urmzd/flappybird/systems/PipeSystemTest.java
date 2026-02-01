package com.urmzd.flappybird.systems;

import static org.junit.jupiter.api.Assertions.*;

import com.urmzd.flappybird.state.PipeState;
import com.urmzd.flappybird.state.Vector;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("PipeSystem")
class PipeSystemTest {

  private PipeSystem pipeSystem;

  @BeforeEach
  void setUp() {
    // Use seeded random for deterministic tests
    pipeSystem = new DefaultPipeSystem(new Random(42));
  }

  @Nested
  @DisplayName("spawnIfNeeded")
  class SpawnIfNeeded {
    @Test
    @DisplayName("should spawn pipes when list is empty")
    void spawnsWhenEmpty() {
      List<PipeState> pipes = List.of();

      var result = pipeSystem.spawnIfNeeded(pipes);

      assertEquals(2, result.size());
    }

    @Test
    @DisplayName("should spawn one bottom and one top pipe")
    void spawnsBottomAndTop() {
      List<PipeState> pipes = List.of();

      var result = pipeSystem.spawnIfNeeded(pipes);

      var bottomPipes = result.stream().filter(p -> !p.isTop()).count();
      var topPipes = result.stream().filter(PipeState::isTop).count();

      assertEquals(1, bottomPipes);
      assertEquals(1, topPipes);
    }

    @Test
    @DisplayName("should not spawn when pipes exist and haven't moved far enough")
    void doesNotSpawnTooSoon() {
      var pipe1 = PipeState.create(288, 100, -2, false);
      var pipe2 = PipeState.create(288, -200, -2, true);
      List<PipeState> pipes = List.of(pipe1, pipe2);

      var result = pipeSystem.spawnIfNeeded(pipes);

      assertEquals(2, result.size());
    }
  }

  @Nested
  @DisplayName("update")
  class Update {
    @Test
    @DisplayName("should move pipes left")
    void movesPipesLeft() {
      var pipe = PipeState.create(100, 200, -2, false);
      List<PipeState> pipes = List.of(pipe);

      var result = pipeSystem.update(pipes);

      assertEquals(98, result.get(0).position().x());
    }

    @Test
    @DisplayName("should preserve pipe properties during update")
    void preservesProperties() {
      var pipe = PipeState.create(100, 200, -2, true);
      List<PipeState> pipes = List.of(pipe);

      var result = pipeSystem.update(pipes);

      assertTrue(result.get(0).isTop());
      assertEquals(200, result.get(0).position().y());
    }
  }

  @Nested
  @DisplayName("removeOffscreen")
  class RemoveOffscreen {
    @Test
    @DisplayName("should remove pipes that are off screen")
    void removesOffscreenPipes() {
      var pipe1 = PipeState.create(-60, 100, -2, false);
      var pipe2 = PipeState.create(-60, -200, -2, true);
      List<PipeState> pipes = List.of(pipe1, pipe2);

      var result = pipeSystem.removeOffscreen(pipes);

      assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("should keep pipes that are on screen")
    void keepsOnscreenPipes() {
      var pipe1 = PipeState.create(100, 100, -2, false);
      var pipe2 = PipeState.create(100, -200, -2, true);
      List<PipeState> pipes = List.of(pipe1, pipe2);

      var result = pipeSystem.removeOffscreen(pipes);

      assertEquals(2, result.size());
    }
  }

  @Nested
  @DisplayName("stopAll")
  class StopAll {
    @Test
    @DisplayName("should zero velocity of all pipes")
    void zerosVelocity() {
      var pipe1 = PipeState.create(100, 100, -2, false);
      var pipe2 = PipeState.create(100, -200, -2, true);
      List<PipeState> pipes = List.of(pipe1, pipe2);

      var result = pipeSystem.stopAll(pipes);

      assertTrue(result.stream().allMatch(p -> p.velocity().equals(Vector.ZERO)));
    }
  }
}
