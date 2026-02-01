package com.urmzd.flappybird.state;

public record Vector(double x, double y) {
  public static final Vector ZERO = new Vector(0, 0);

  public Vector add(Vector other) {
    return new Vector(x + other.x, y + other.y);
  }

  public Vector withX(double newX) {
    return new Vector(newX, y);
  }

  public Vector withY(double newY) {
    return new Vector(x, newY);
  }

  public Vector scale(double factor) {
    return new Vector(x * factor, y * factor);
  }
}
