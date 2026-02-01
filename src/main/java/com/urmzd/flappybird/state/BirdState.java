package com.urmzd.flappybird.state;

public record BirdState(
    Vector position,
    Vector velocity,
    Vector acceleration,
    double rotation,
    int animationFrame,
    double idleTime,
    double rotationTime,
    boolean alive,
    boolean active) {
  private static final double INITIAL_X = 74;
  private static final double INITIAL_Y = 200;

  public static BirdState initial() {
    return new BirdState(
        new Vector(INITIAL_X, INITIAL_Y), Vector.ZERO, Vector.ZERO, 0, 0, 0, 0, true, false);
  }

  public BirdState withPosition(Vector p) {
    return new BirdState(
        p, velocity, acceleration, rotation, animationFrame, idleTime, rotationTime, alive, active);
  }

  public BirdState withVelocity(Vector v) {
    return new BirdState(
        position, v, acceleration, rotation, animationFrame, idleTime, rotationTime, alive, active);
  }

  public BirdState withAcceleration(Vector a) {
    return new BirdState(
        position, velocity, a, rotation, animationFrame, idleTime, rotationTime, alive, active);
  }

  public BirdState withRotation(double r) {
    return new BirdState(
        position, velocity, acceleration, r, animationFrame, idleTime, rotationTime, alive, active);
  }

  public BirdState withAnimationFrame(int frame) {
    return new BirdState(
        position, velocity, acceleration, rotation, frame, idleTime, rotationTime, alive, active);
  }

  public BirdState withIdleTime(double time) {
    return new BirdState(
        position,
        velocity,
        acceleration,
        rotation,
        animationFrame,
        time,
        rotationTime,
        alive,
        active);
  }

  public BirdState withRotationTime(double time) {
    return new BirdState(
        position, velocity, acceleration, rotation, animationFrame, idleTime, time, alive, active);
  }

  public BirdState withAlive(boolean a) {
    return new BirdState(
        position,
        velocity,
        acceleration,
        rotation,
        animationFrame,
        idleTime,
        rotationTime,
        a,
        active);
  }

  public BirdState withActive(boolean a) {
    return new BirdState(
        position,
        velocity,
        acceleration,
        rotation,
        animationFrame,
        idleTime,
        rotationTime,
        alive,
        a);
  }
}
