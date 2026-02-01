package com.urmzd.flappybird.state;

public record PipeState(Vector position, Vector velocity, boolean isTop, boolean passed) {
  public static PipeState create(double x, double y, double velocityX, boolean isTop) {
    return new PipeState(new Vector(x, y), new Vector(velocityX, 0), isTop, false);
  }

  public PipeState withPosition(Vector p) {
    return new PipeState(p, velocity, isTop, passed);
  }

  public PipeState withVelocity(Vector v) {
    return new PipeState(position, v, isTop, passed);
  }

  public PipeState withPassed(boolean p) {
    return new PipeState(position, velocity, isTop, p);
  }
}
