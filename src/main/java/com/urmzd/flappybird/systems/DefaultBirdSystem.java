package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.BirdState;
import com.urmzd.flappybird.state.GamePhase;
import com.urmzd.flappybird.state.Vector;

public class DefaultBirdSystem implements BirdSystem {

  private static final double MAX_ACCELERATION = 0.08;
  private static final double ACCELERATION_INCREMENT = 0.004;
  private static final double JUMP_VELOCITY = -2.7;
  private static final double JUMP_ROTATION = -30;
  private static final double ROTATION_DELAY = 18;
  private static final double MAX_FALL_SPEED = 3.5;
  private static final double ROTATION_SCALE = 25;
  private static final double ROTATION_EASING = 0.12;
  private static final double DEAD_VELOCITY = 6;
  private static final int TYPE_DELAY = 10;
  private static final int ANIMATION_FRAMES = 3;

  @Override
  public BirdState update(BirdState bird, GamePhase phase) {
    return switch (phase) {
      case GamePhase.Ready() -> updateIdle(bird);
      case GamePhase.Playing() -> updatePlaying(bird);
      case GamePhase.GameOver(var s) -> updateDead(bird);
    };
  }

  private BirdState updateIdle(BirdState bird) {
    double newIdleTime = bird.idleTime() + 1;
    double oscillation = Math.sin(Math.PI * 0.03 * newIdleTime);
    Vector newVelocity = new Vector(0, oscillation);
    Vector newPosition = PhysicsSystem.applyVelocity(bird.position(), newVelocity);

    return bird.withIdleTime(newIdleTime)
        .withVelocity(newVelocity)
        .withPosition(newPosition)
        .withAnimationFrame(nextAnimationFrame(bird));
  }

  private BirdState updatePlaying(BirdState bird) {
    if (!bird.alive()) {
      return updateDead(bird);
    }

    double newRotationTime = bird.rotationTime() + 1;
    double yAccel = bird.acceleration().y();

    Vector newAcceleration = bird.acceleration();
    if (yAccel <= MAX_ACCELERATION) {
      newAcceleration = new Vector(0, yAccel + ACCELERATION_INCREMENT);
    }

    Vector newVelocity = PhysicsSystem.applyAcceleration(bird.velocity(), newAcceleration);
    if (newVelocity.y() > MAX_FALL_SPEED) {
      newVelocity = new Vector(0, MAX_FALL_SPEED);
    }

    double newRotation = bird.rotation();
    if (newRotationTime >= ROTATION_DELAY) {
      double targetRotation =
          Math.min(90, Math.max(JUMP_ROTATION, newVelocity.y() * ROTATION_SCALE));
      newRotation = bird.rotation() + (targetRotation - bird.rotation()) * ROTATION_EASING;
    }
    Vector newPosition = PhysicsSystem.applyVelocity(bird.position(), newVelocity);

    return bird.withAcceleration(newAcceleration)
        .withVelocity(newVelocity)
        .withPosition(newPosition)
        .withRotation(newRotation)
        .withRotationTime(newRotationTime)
        .withAnimationFrame(nextAnimationFrame(bird));
  }

  private BirdState updateDead(BirdState bird) {
    if (!bird.active()) {
      return bird;
    }

    Vector newVelocity = new Vector(0, DEAD_VELOCITY);
    Vector newPosition = PhysicsSystem.applyVelocity(bird.position(), newVelocity);

    return bird.withVelocity(newVelocity).withPosition(newPosition).withRotation(90);
  }

  @Override
  public BirdState jump(BirdState bird) {
    if (!bird.alive()) {
      return bird;
    }

    return bird.withVelocity(new Vector(0, JUMP_VELOCITY))
        .withAcceleration(Vector.ZERO)
        .withRotation(JUMP_ROTATION)
        .withActive(true)
        .withRotationTime(0);
  }

  @Override
  public BirdState setDead(BirdState bird) {
    return bird.withAlive(false).withRotation(90).withVelocity(new Vector(0, DEAD_VELOCITY));
  }

  @Override
  public BirdState stop(BirdState bird) {
    return bird.withVelocity(Vector.ZERO).withAcceleration(Vector.ZERO).withActive(false);
  }

  private int nextAnimationFrame(BirdState bird) {
    if (!bird.alive()) {
      return bird.animationFrame();
    }
    return ((int) (bird.idleTime()) / TYPE_DELAY) % ANIMATION_FRAMES;
  }
}
