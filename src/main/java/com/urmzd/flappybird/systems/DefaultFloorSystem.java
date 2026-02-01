package com.urmzd.flappybird.systems;

import com.urmzd.flappybird.state.FloorState;
import com.urmzd.flappybird.state.Vector;

public class DefaultFloorSystem implements FloorSystem {

  private static final double FLOOR_RESET_X = -336;

  @Override
  public FloorState update(FloorState floor) {
    Vector newPosition = PhysicsSystem.applyVelocity(floor.position(), floor.velocity());

    if (newPosition.x() <= FLOOR_RESET_X) {
      newPosition = newPosition.withX(0);
    }

    return floor.withPosition(newPosition);
  }

  @Override
  public FloorState stop(FloorState floor) {
    return floor.withVelocity(Vector.ZERO);
  }
}
