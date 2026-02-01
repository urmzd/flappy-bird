package com.urmzd.flappybird.systems;

import static org.junit.jupiter.api.Assertions.*;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.PipeState;
import com.urmzd.flappybird.state.Vector;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ScoreSystem")
class ScoreSystemTest {

  private ScoreSystem scoreSystem;

  @BeforeEach
  void setUp() {
    scoreSystem = new DefaultScoreSystem();
  }

  @Nested
  @DisplayName("checkScore")
  class CheckScore {
    @Test
    @DisplayName("should increment score when passing bottom pipe")
    void incrementsScoreOnPass() {
      var bird = BirdState.initial().withPosition(new Vector(150, 200));
      var pipe = PipeState.create(100, 300, -2, false); // Bottom pipe behind bird

      var result = scoreSystem.checkScore(bird, List.of(pipe), 0);

      assertEquals(1, result.score());
    }

    @Test
    @DisplayName("should not increment score for top pipes")
    void doesNotCountTopPipes() {
      var bird = BirdState.initial().withPosition(new Vector(150, 200));
      var pipe = PipeState.create(100, -200, -2, true); // Top pipe

      var result = scoreSystem.checkScore(bird, List.of(pipe), 0);

      assertEquals(0, result.score());
    }

    @Test
    @DisplayName("should not increment score when pipe not yet passed")
    void doesNotCountUnpassedPipes() {
      var bird = BirdState.initial().withPosition(new Vector(50, 200));
      var pipe = PipeState.create(100, 300, -2, false); // Pipe ahead of bird

      var result = scoreSystem.checkScore(bird, List.of(pipe), 0);

      assertEquals(0, result.score());
    }

    @Test
    @DisplayName("should mark pipe as passed after scoring")
    void marksPipeAsPassed() {
      var bird = BirdState.initial().withPosition(new Vector(150, 200));
      var pipe = PipeState.create(100, 300, -2, false);

      var result = scoreSystem.checkScore(bird, List.of(pipe), 0);

      assertTrue(result.pipes().get(0).passed());
    }

    @Test
    @DisplayName("should not double-count already passed pipes")
    void doesNotDoubleCont() {
      var bird = BirdState.initial().withPosition(new Vector(150, 200));
      var pipe = PipeState.create(100, 300, -2, false).withPassed(true);

      var result = scoreSystem.checkScore(bird, List.of(pipe), 5);

      assertEquals(5, result.score());
    }

    @Test
    @DisplayName("should return current score when bird is dead")
    void returnsCurrentScoreWhenDead() {
      var bird = BirdState.initial().withAlive(false).withPosition(new Vector(150, 200));
      var pipe = PipeState.create(100, 300, -2, false);

      var result = scoreSystem.checkScore(bird, List.of(pipe), 3);

      assertEquals(3, result.score());
    }

    @Test
    @DisplayName("should return current score for empty pipe list")
    void handlesEmptyPipes() {
      var bird = BirdState.initial();

      var result = scoreSystem.checkScore(bird, List.of(), 5);

      assertEquals(5, result.score());
    }
  }
}
