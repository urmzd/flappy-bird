package com.urmzd.flappybird.state;

public record FloorState(Vector position, Vector velocity) {
  private static final double FLOOR_Y = 512 - 112;
  private static final double FLOOR_VELOCITY_X = -2;

  public static FloorState initial() {
    return new FloorState(new Vector(0, FLOOR_Y), new Vector(FLOOR_VELOCITY_X, 0));
  }

  public FloorState withPosition(Vector p) {
    return new FloorState(p, velocity);
  }

  public FloorState withVelocity(Vector v) {
    return new FloorState(position, v);
  }

  public double floorY() {
    return FLOOR_Y;
  }
}
