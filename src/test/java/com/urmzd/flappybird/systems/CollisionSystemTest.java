package com.urmzd.flappybird.systems;

import static org.junit.jupiter.api.Assertions.*;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.FloorState;
import com.urmzd.flappybird.state.PipeState;
import com.urmzd.flappybird.state.Vector;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CollisionSystem")
class CollisionSystemTest {

  private CollisionSystem collisionSystem;

  @BeforeEach
  void setUp() {
    collisionSystem = new DefaultCollisionSystem();
  }

  @Nested
  @DisplayName("checkFloorCollision")
  class CheckFloorCollision {
    @Test
    @DisplayName("should detect collision when bird hits floor")
    void detectsFloorCollision() {
      var bird = BirdState.initial().withPosition(new Vector(74, 390));
      var floor = FloorState.initial();

      var result = collisionSystem.checkFloorCollision(bird, floor);

      assertTrue(result);
    }

    @Test
    @DisplayName("should not detect collision when bird is above floor")
    void noCollisionAboveFloor() {
      var bird = BirdState.initial().withPosition(new Vector(74, 200));
      var floor = FloorState.initial();

      var result = collisionSystem.checkFloorCollision(bird, floor);

      assertFalse(result);
    }
  }

  @Nested
  @DisplayName("checkPipeCollision")
  class CheckPipeCollision {
    @Test
    @DisplayName("should detect collision when bird overlaps pipe")
    void detectsPipeCollision() {
      var bird = BirdState.initial().withPosition(new Vector(100, 150));
      var pipe = PipeState.create(100, 100, -2, false);

      var result = collisionSystem.checkPipeCollision(bird, List.of(pipe));

      assertTrue(result);
    }

    @Test
    @DisplayName("should not detect collision when bird misses pipe")
    void noCollisionWhenMissing() {
      var bird = BirdState.initial().withPosition(new Vector(50, 200));
      var pipe = PipeState.create(200, 100, -2, false);

      var result = collisionSystem.checkPipeCollision(bird, List.of(pipe));

      assertFalse(result);
    }

    @Test
    @DisplayName("should return false for empty pipe list")
    void noCollisionWithNoPipes() {
      var bird = BirdState.initial();

      var result = collisionSystem.checkPipeCollision(bird, List.of());

      assertFalse(result);
    }

    @Test
    @DisplayName("should detect collision with any pipe in list")
    void detectsCollisionWithAnyPipe() {
      var bird = BirdState.initial().withPosition(new Vector(300, 150));
      var pipe1 = PipeState.create(100, 100, -2, false);
      var pipe2 = PipeState.create(300, 100, -2, false);

      var result = collisionSystem.checkPipeCollision(bird, List.of(pipe1, pipe2));

      assertTrue(result);
    }
  }
}
