package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.Vector;

public final class PhysicsSystem {
  private PhysicsSystem() {}

  public static Vector applyVelocity(Vector position, Vector velocity) {
    return position.add(velocity);
  }

  public static Vector applyAcceleration(Vector velocity, Vector acceleration) {
    return velocity.add(acceleration);
  }
}
