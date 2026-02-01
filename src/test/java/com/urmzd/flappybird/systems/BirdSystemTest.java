package com.urmzd.flappybird.systems;

import static org.junit.jupiter.api.Assertions.*;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.GamePhase;
import com.urmzd.flappybird.state.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BirdSystem")
class BirdSystemTest {

  private BirdSystem birdSystem;

  @BeforeEach
  void setUp() {
    birdSystem = new DefaultBirdSystem();
  }

  @Nested
  @DisplayName("jump")
  class Jump {
    @Test
    @DisplayName("should set negative velocity when alive")
    void setsNegativeVelocity() {
      var bird = BirdState.initial();

      var result = birdSystem.jump(bird);

      assertTrue(result.velocity().y() < 0);
    }

    @Test
    @DisplayName("should activate bird")
    void activatesBird() {
      var bird = BirdState.initial();
      assertFalse(bird.active());

      var result = birdSystem.jump(bird);

      assertTrue(result.active());
    }

    @Test
    @DisplayName("should set upward rotation")
    void setsUpwardRotation() {
      var bird = BirdState.initial();

      var result = birdSystem.jump(bird);

      assertTrue(result.rotation() < 0);
    }

    @Test
    @DisplayName("should not jump when dead")
    void doesNotJumpWhenDead() {
      var bird = BirdState.initial().withAlive(false);

      var result = birdSystem.jump(bird);

      assertEquals(bird.velocity(), result.velocity());
    }
  }

  @Nested
  @DisplayName("setDead")
  class SetDead {
    @Test
    @DisplayName("should mark bird as not alive")
    void marksDead() {
      var bird = BirdState.initial();

      var result = birdSystem.setDead(bird);

      assertFalse(result.alive());
    }

    @Test
    @DisplayName("should set rotation to 90 degrees")
    void setsDownwardRotation() {
      var bird = BirdState.initial();

      var result = birdSystem.setDead(bird);

      assertEquals(90, result.rotation());
    }

    @Test
    @DisplayName("should set falling velocity")
    void setsFallingVelocity() {
      var bird = BirdState.initial();

      var result = birdSystem.setDead(bird);

      assertTrue(result.velocity().y() > 0);
    }
  }

  @Nested
  @DisplayName("stop")
  class Stop {
    @Test
    @DisplayName("should zero velocity")
    void zerosVelocity() {
      var bird = BirdState.initial().withVelocity(new Vector(1, 5));

      var result = birdSystem.stop(bird);

      assertEquals(Vector.ZERO, result.velocity());
    }

    @Test
    @DisplayName("should zero acceleration")
    void zerosAcceleration() {
      var bird = BirdState.initial().withAcceleration(new Vector(0, 0.1));

      var result = birdSystem.stop(bird);

      assertEquals(Vector.ZERO, result.acceleration());
    }

    @Test
    @DisplayName("should deactivate bird")
    void deactivatesBird() {
      var bird = BirdState.initial().withActive(true);

      var result = birdSystem.stop(bird);

      assertFalse(result.active());
    }
  }

  @Nested
  @DisplayName("update")
  class Update {
    @Test
    @DisplayName("should oscillate in Ready phase")
    void oscillatesInReady() {
      var bird = BirdState.initial();

      var result = birdSystem.update(bird, new GamePhase.Ready());

      assertNotEquals(bird.position(), result.position());
      assertEquals(1, result.idleTime());
    }

    @Test
    @DisplayName("should apply gravity in Playing phase")
    void appliesGravityInPlaying() {
      var bird = BirdState.initial().withActive(true);

      var result = birdSystem.update(bird, new GamePhase.Playing());

      assertTrue(result.acceleration().y() > 0);
    }
  }
}
